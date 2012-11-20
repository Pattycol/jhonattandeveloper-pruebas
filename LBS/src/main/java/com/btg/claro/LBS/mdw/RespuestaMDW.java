package com.btg.claro.LBS.mdw;

public class RespuestaMDW{
	
	private int codigo;
	
	private String transactionId;
	
	private String platform;

	public int getCodigo(){
		return codigo;
	}

	public void setCodigo(int codigo){
		this.codigo=codigo;
	}

	public String getTransactionId(){
		return transactionId;
	}

	public void setTransactionId(String transactionId){
		this.transactionId=transactionId;
	}

	public String getPlatform(){
		return platform;
	}

	public void setPlatform(String platform){
		this.platform=platform;
	}

}
