package pe.com.claro.cae.bean;

public class EncryptBean {
	private String encrypy_pass;
	private byte[] tdes_key;
	
	public EncryptBean(){
	}
	
	public EncryptBean (String encrypy_pass, byte[] tdes_key){
		this.encrypy_pass = encrypy_pass;
		this.tdes_key = tdes_key;
	}
	
	public String getEncrypy_pass() {
		return encrypy_pass;
	}
	
	public void setEncrypy_pass(String encrypy_pass) {
		this.encrypy_pass = encrypy_pass;
	}
	
	public byte[] getTdes_key() {
		return tdes_key;
	}
	
	public void setTdes_key(byte[] tdes_key) {
		this.tdes_key = tdes_key;
	}
		

}
