package pe.com.claro.caef.web.util;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class CaeMessagesUtil {

	private ResourceBundleMessageSource boundle;
	private Locale locale;
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void setBoundle(ResourceBundleMessageSource boundle) {
		this.boundle = boundle;
	}
	
	public String getMessage(String key, String[] args ) {
		return this.boundle.getMessage(key, args, this.locale);
	}
	
	public String getMessage(String key) {
		return this.boundle.getMessage(key, new String[]{}, this.locale);
	}
	
}
