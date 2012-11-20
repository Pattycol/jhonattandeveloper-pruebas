package com.btg.claro.LBS.mdw;

public interface ClienteMDW{
	
	RespuestaMDW reserve(String application,String msisdn,String productId);
	
	RespuestaMDW acceptReserve(String application,String msisdn,String productId,String platform,String transactionId);
	
	RespuestaMDW cancelReserve(String application,String msisdn,String productId,String platform,String transactionId);

}
