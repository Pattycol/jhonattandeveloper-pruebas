package pe.com.claro.caef.web.beans;

public class ListaMenu {
	
	private String levelCoord;
	private String menuId;
	private String menuName;
	private String url;
	private String listChildMenus;
	
	public String getLevelCoord() {
		return levelCoord;
	}
	public void setLevelCoord(String levelCoord) {
		this.levelCoord = levelCoord;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getListChildMenus() {
		return listChildMenus;
	}
	public void setListChildMenus(String listChildMenus) {
		this.listChildMenus = listChildMenus;
	}
	
}
