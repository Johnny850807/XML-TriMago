import java.util.Date;

public class AirQuality {
	private int siteId;
	private String siteName;
	private String county;
	private int itemId;
	private String itemName;
	private String itemEngName;
	private String itemUnit;
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemEngName() {
		return itemEngName;
	}
	public void setItemEngName(String itemEngName) {
		this.itemEngName = itemEngName;
	}
	public String getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}
	public Date getMonitorDate() {
		return monitorDate;
	}
	public void setMonitorDate(Date monitorDate) {
		this.monitorDate = monitorDate;
	}
	public int getConcentration() {
		return concentration;
	}
	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}
	private Date monitorDate;
	private int concentration;
}
