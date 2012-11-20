/**
 * FancyTabs - tabbed navigation
 * @version		1.0.0
 * @MooTools version 1.2
 * @author Constantin Boiangiu <constantin.b [at] gmail.com>
 * @copyright Constantin Boiangiu
 */

var FancyTabs = new Class({
	Implements: [Options],
	
	options: {
		tabs: null, // tab elements
		contentContainer: null, // overall content container. Used to resize from tab to tab navigation
		contentElement:null,
		tabFx: {
			classSelected: null, // css class to morph to when tab is clicked ( in stylesheet, use it as .class; do not use descendants )
			classDefault: null // same as the above, used for default state		
		},
		fxStyle: 1, // can be 1 - only fade, 2 - slide fade
		slideEnter: 'left', // left or right
		auto: null, // number of milliseconds for auto sliding content. If null, no auto slide will occur
		stopOnClick: true // if auto is on and the user clicks a tab, is this is set to true, auto sliding will stop
	},
	
	initialize: function(options){
		
		this.setOptions(options);	
		/* by default, the starting tab is the first one. To start with another tab, add it class selected ( ie: <a class="tab selected" ) */
		this.currentTab = 0;		
		this.tabs = $(document.body).getElements(this.options.tabs);
		this.contents = $(this.options.contentContainer).getElements(this.options.contentElement);
		this.hide = {
			'opacity':0,
			'display':'none'
		};
		this.show = {
			'display':'block'
		}
		
		this.content = $(this.options.contentContainer);
		this.content.set('morph', {duration:500, transition:'back:out', wait:false});
		/* tabs manipulation */
		this.tabs.each(function(el, i){
								
			el.set('morph',{duration: 400, transition:'sine:out', wait:false});	
			
			if(el.hasClass('selected')){
				this.currentTab = i;
				el.morph(this.options.tabFx.classSelected);
			}
			el.addEvents({
				'click': function(event){ 
					new Event(event).stop();
					if( $defined(this.rotate) && this.options.auto ){
						$clear(this.rotate);						
					}
					this.changeTab(i);
				}.bind(this)
			});
		}.bind(this))
		/* sets auto rotating */
		this.setPeriodical();
		/* content manipulation */
		this.contents.each(function(el, i){
			
			el.set('morph', {duration:600, transition:'sine:out', wait:false });
			var elSize = el.getSize();
			el.store('size', elSize).setStyles({'width':elSize.x-(2*el.getStyle('padding').toInt()), 'height':elSize.y});
			if(i!==this.currentTab){
				el.setStyles({
					'opacity':0,
					'display':'none'
				})
			}
			else {
				/* if first tab is ajax, load it */
				if( this.tabs[i].get('rel') == 'ajax' ){
					this.changeTab(i, true);
				}
				this.content.morph({'height':[0,elSize.y]});
			}
			/* if auto set on, stops rotating the content until mouseleave event happens */
			if( $defined(this.rotate) && this.options.auto ){				
				el.addEvents({
					'mouseenter': function(){
						$clear(this.rotate);
					}.bind(this),
					'mouseleave': function(){
						this.setPeriodical();
					}.bind(this)
				});
			}				
			
		}.bind(this))
		/* sets styles to display content according to this.options.fxStyle and this.options.slideEnter */
		switch( this.options.fxStyle ){
			case 1:
				this.showFx = {'opacity' : [.3,1]};
			break;			
			case 2:
				var s = this.contents[0].retrieve('size');				
				switch(this.options.slideEnter){					
					case 'left':
						this.showFx = {'margin-left': [-s.x/2, 0], 'opacity':[.3,1]};
					break;					
					case 'right':
						this.showFx = {'margin-left': [s.x/2, 0], 'opacity':[.3,1]};
					break;					
				}
			break;			
			default:
				this.showFx = {'opacity' : 1};
			break;
		}	
		
	},
	
	/* function that sets content auto rotating */
	setPeriodical: function(){
		if(!this.options.auto) return;
		this.rotate = this.autoSlide.periodical(this.options.auto, this);
	},
	/* triggered at every this.options.auto milliseconds */
	autoSlide: function(){
		var key = this.currentTab+1;
		if( key >= this.tabs.length ) key = 0;
		this.changeTab(key);
	},
	/* changes the current tab to the next one if tab navigation clicked or auto set on */
	changeTab: function(key, ignore){
		// don't load the same tab again if loaded
		if(!ignore && key == this.currentTab) return;
		
		// if tab was changed before loading completely, get its size
		if( this.tabs[key].get('rel')=='ajax' && !this.contents[key].retrieve('completeLoad') &&  this.tabs[key].retrieve('loaded') ){
			this.contents[key].setStyles(this.show);
			var elSize = this.contents[key].getScrollSize();
			this.contents[key].store('size', elSize);			
		}
		
		var from = this.contents[this.currentTab].retrieve('size');
		var to = this.contents[key].retrieve('size');		
		this.contents[this.currentTab].morph(this.hide);		
		this.contents[key].setStyles(this.show).morph(this.showFx);
		this.content.morph({'height':[from.y, to.y]});		
		this.tabs[this.currentTab].morph(this.options.tabFx.classDefault);
		this.tabs[key].morph(this.options.tabFx.classSelected);
		
		// if tab is ajax and content not loaded yet
		if( this.tabs[key].get('rel')=='ajax' && !this.tabs[key].retrieve('loaded') && !this.tabs[key].retrieve('loading') ){
			
			this.tabs[key].store('loading', true);
			this.contents[key].addClass('loadingAjax');
			
			var tabContent = new Request.HTML({
				url:this.tabs[key].get('href'),
				onSuccess: function(tree, elems, html){
					
					this.tabs[key].store('loaded', true);
					this.tabs[key].set('loading', false);					
					var tabContent = this.contents[key];					
					var currentSize = tabContent.retrieve('size');					
					tabContent.set({'html':html}).setStyles({'width':'auto', 'height':'auto'}).removeClass('loadingAjax');
					
					if( this.currentTab == key ){
						var elSize = tabContent.getSize();
						tabContent.store('size', elSize)
								  .setStyles({'width':elSize.x-(2*tabContent.getStyle('padding').toInt()), 'height':elSize.y});					
					
						this.content.morph({'height':[currentSize.y, elSize.y]});
						tabContent.store('completeLoad', true);
					}
					
					if( !this.options.stopOnClick && !$defined(this.rotate) && this.options.auto  )
						this.setPeriodical();
						
				}.bind(this),
				onFailure: function(){
					
					var message = 'Sorry, could not load content. Please contact the website administrator regarding this error.';
					var tabContent = this.contents[key];
					var currentSize = tabContent.retrieve('size');	
					tabContent.set({'html':message}).setStyles({'width':'auto', 'height':'auto'}).removeClass('loadingAjax');
					if( this.currentTab == key ){
						var elSize = tabContent.getSize();
						tabContent.store('size', elSize)
								  .setStyles({'width':elSize.x-(2*tabContent.getStyle('padding').toInt()), 'height':elSize.y});					
					
						this.content.morph({'height':[currentSize.y, elSize.y]});
						tabContent.store('completeLoad', true);
					}
					
				}.bind(this)
			}).get();			
		}
		// if not ajax tab, check if auto rotation set and stat it
		else if( !this.options.stopOnClick && !$defined(this.rotate) && this.options.auto  )
			this.setPeriodical();
		
		this.currentTab = key;
	}
})