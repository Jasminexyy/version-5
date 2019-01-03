package cm.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

class LoginSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        if (roles.contains("ADMIN")) {
            response.sendRedirect(basePath + "cm/admin");
            return;
        }
        if (roles.contains("TEACHER")) {
            response.sendRedirect(basePath + "cm/teacher");
            return;
        }
        if (roles.contains("STUDENT")) {
            response.sendRedirect(basePath + "cm/student");
            return;
        }
        response.sendRedirect(basePath + "cm/login");
    }
}
