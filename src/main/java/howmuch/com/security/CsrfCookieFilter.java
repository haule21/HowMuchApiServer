package howmuch.com.security;

import java.io.IOException;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException   {
        // 요청 속성에서 CSRF 토큰을 가져옴
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null && csrfToken.getHeaderName() != null) {
            // CSRF 토큰을 응답 헤더에 설정
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        // 다음 필터로 요청과 응답을 전달
        filterChain.doFilter(request, response);
    }
}
