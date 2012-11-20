package pe.com.claro.caef.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CaefWebLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // maybe do some other things ...
        super.handle(request, response, authentication);
    }
}
