package com.example.mycli.web;

import com.example.mycli.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Log
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("do filter...");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null) {
            if (jwtProvider.validateToken(token)) {
                String userLogin = jwtProvider.getLoginFromToken(token);
                CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails,
                        null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest httpServletRequest) {
//        Cookie[] cookies = httpServletRequest.getCookies();
//        String token = null;
//        String username = null;
//        if (cookies == null)  return null;
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")) {
//                token = cookie.getValue();
//                username = jwtProvider.getLoginFromToken(token);
//            }
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null || token != null) {
//                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//                if (Boolean.TRUE.equals(jwtProvider.validateToken(token))) {
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                                    userDetails, null, userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                            httpServletRequest));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        }
//        return null;
        String token = httpServletRequest.getHeader("token");
        String username = null;
        if (token != null) {
            username = jwtProvider.getLoginFromToken(token);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null || token != null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if (Boolean.TRUE.equals(jwtProvider.validateToken(token))) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
        }
        return token;
    }
}
