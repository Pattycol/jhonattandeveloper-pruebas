var MM_latestPluginRevision = new Object();
MM_latestPluginRevision["6.0"] = new Object();
MM_latestPluginRevision["5.0"] = new Object();
MM_latestPluginRevision["4.0"] = new Object();
MM_latestPluginRevision["3.0"] = new Object();
MM_latestPluginRevision["2.0"] = new Object();

MM_latestPluginRevision["6.0"]["Windows"] = 65;
MM_latestPluginRevision["6.0"]["Macintosh"] = 65;
MM_latestPluginRevision["6.0"]["Unix"] = 69;

MM_latestPluginRevision["5.0"]["Windows"] = 42;
MM_latestPluginRevision["5.0"]["Macintosh"] = 41;
MM_latestPluginRevision["5.0"]["Unix"] = 51;

MM_latestPluginRevision["4.0"]["Windows"] = 28;
MM_latestPluginRevision["4.0"]["Macintosh"] = 27;
MM_latestPluginRevision["4.0"]["Unix"] = 12;

MM_latestPluginRevision["3.0"]["Windows"] = 10;
MM_latestPluginRevision["3.0"]["Macintosh"] = 10;

MM_latestPluginRevision["2.0"]["Windows"] = 11;
MM_latestPluginRevision["2.0"]["Macintosh"] = 11;

var MM_FlashControlInstalled;
var MM_FlashControlVersion;	

function MM_FlashInfo()
{
    if (navigator.plugins && navigator.plugins.length > 0)
    {
	this.implementation = "Plug-in";
	this.autoInstallable = false;

	if (navigator.plugins["Shockwave Flash"])
	{
	    this.installed = true;

	    var words =
		navigator.plugins["Shockwave Flash"].description.split(" ");

	    for (var i = 0; i < words.length; ++i)
	    {
		if (isNaN(parseInt(words[i])))
		continue;

		this.version = words[i];
		

		this.revision = parseInt(words[i + 1].substring(1));
	    }
	}
	else
	{
	    this.installed = false;
	}
    }
    else if (MM_FlashControlInstalled != null)
    {
	this.implementation = "ActiveX control";
	this.installed = MM_FlashControlInstalled;
	this.version = MM_FlashControlVersion;
	this.autoInstallable = true;
    }
    else if (MM_FlashDetectedSelf())
    {
	this.installed = true;
	this.implementation = "Plug-in";
	this.autoInstallable = false;
    }

    this.canPlay = MM_FlashCanPlay;
}

var MM_FlashPluginsPage = "http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash";

function MM_FlashDispatch(gifSRC, swfSRC, contentVersion, requireLatestRevision, install, disableAutoInstall)
{
    if (disableAutoInstall == null)
    {
	alert("ERROR: FALTAM ARGUMENTOS.");
	return;
    }

    var player = new MM_FlashInfo();

    if (player.installed == null)
    {
		//usar detect flash para autoinstall	
		//alert("GIF");
		document.write(gifSRC);
    }
    else if (player.installed)
    {
	if (player.canPlay(contentVersion, requireLatestRevision))
	{
	    //alert("FLASH");
	    document.write(swfSRC);
	}
	else
	{
	if (disableAutoInstall)
	{
		//alert("GIF");
		//alert("UPGRADE MACROMEDIA");
		document.write(gifSRC);
	}else
	{
	    //alert("FLASH");
	    document.write(swfSRC);
	}
	}
    }
    else if (install)
    {
		//alert("GIF");
		document.write(gifSRC);
	}
}

function MM_FlashRememberIfDetectedSelf(count, units)
{
    if (document.location.search.indexOf("?") != -1)
    {
	if (!count) count = 60;
	if (!units) units = "days";

	var msecs = new Object();

	msecs.minute = msecs.minutes = 60000;
	msecs.hour = msecs.hours = 60 * msecs.minute;
	msecs.day = msecs.days = 24 * msecs.hour;

	var expires = new Date();

	expires.setTime(expires.getTime() + count * msecs[units]);

	document.cookie =
	    'MM_FlashDetectedSelf=true ; expires=' + expires.toGMTString();
    }
}

function MM_FlashDemur(count, units)
{
    if (!count) count = 60;
    if (!units) units = "days";

    var msecs = new Object();

    msecs.minute = msecs.minutes = 60000;
    msecs.hour = msecs.hours = 60 * msecs.minute;
    msecs.day = msecs.days = 24 * msecs.hour;

    var expires = new Date();

    expires.setTime(expires.getTime() + count * msecs[units]);

    document.cookie =
	'MM_FlashUserDemurred=true ; expires=' + expires.toGMTString();


    if (!MM_FlashUserDemurred())
    {
	alert("seu browser precisa aceitar cookies");

	return false;
    }
    else
	return true;
}

function MM_FlashUserDemurred()
{
    return (document.cookie.indexOf("MM_FlashUserDemurred") != -1);
}

function MM_FlashLatestPluginRevision(playerVersion)
{
    var latestRevision;
    var platform;

    if (navigator.appVersion.indexOf("Win") != -1)
	platform = "Windows";
    else if (navigator.appVersion.indexOf("Macintosh") != -1)
	platform = "Macintosh";
    else if (navigator.appVersion.indexOf("X11") != -1)
	platform = "Unix";

    latestRevision = MM_latestPluginRevision[playerVersion][platform];

    return latestRevision;
}

function MM_FlashCanPlay(contentVersion, requireLatestRevision)
{
    var canPlay;

    if (this.version)
    {
	canPlay = (parseInt(contentVersion) <= this.version);

	if (requireLatestRevision)
	{
	    if (this.revision &&
		this.revision < MM_FlashLatestPluginRevision(this.version))
	    {
		canPlay = false;
	    }
	}
    }
    else
    {
	canPlay = MM_FlashDetectedSelf();
    }

    return canPlay;
}

function MM_FlashDetectedSelf()
{
    return (document.cookie.indexOf("MM_FlashDetectedSelf") != -1);
}