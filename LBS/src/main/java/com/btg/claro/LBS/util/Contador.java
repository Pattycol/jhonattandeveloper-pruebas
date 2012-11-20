package com.btg.claro.LBS.util;

public class Contador extends Thread{
	
	private int tiempoTranscurrido;
	
	public void run(){
		setTiempoTranscurrido(0);
		while(true){
			try{
				Thread.sleep(1000);
				setTiempoTranscurrido(getTiempoTranscurrido()+1);
			}
			catch(InterruptedException e){}
		}
	}

	public synchronized int getTiempoTranscurrido(){
		return tiempoTranscurrido;
	}

	public synchronized void setTiempoTranscurrido(int tiempoTranscurrido){
		this.tiempoTranscurrido=tiempoTranscurrido;
	}	

}
