package com.btg.claro.localizacion.endpoints.respuestas;

public class LoginRespuesta {
	
	private String error;
	
	private String token;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
