package com.thi.bt.knn.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.thi.bt.knn.User;
import com.thi.bt.knn.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * TODO: write you class description here
 *
 * @author: sangnk
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired CrmUserDetailsService userDetailService;
    @Autowired private TokenHelper tokenHelper;
    @Autowired private UserRepository userRepository;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        boolean isWebIgroring = false;
        String contextPath = request.getContextPath();
        for (String check : Constants.WEB_IGNORING) {
            isWebIgroring = new AntPathMatcher().match(contextPath + check, request.getRequestURI());
            if (isWebIgroring) {
                break;
            }
        }
        return isWebIgroring;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("url " + request.getRequestURL());
        String accessToken = getToken(request);

        // json web token
        if (accessToken != null) {
            String username = tokenHelper.getUsernameFromToken(accessToken);
            if (username != null) {
                Optional<User> userOpt = userRepository.findByUsername(username);
                if (userOpt.isPresent()) {
                    User user = (User) this.userDetailService.loadUserByUsername(username);
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(user, true);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
    public static String getToken(HttpServletRequest request) {
        String accessToken = request.getHeader(Constants.HEADER_FIELD.AUTHORIZATION);
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            return accessToken.substring(7);
        }
        return null;
    }
}
