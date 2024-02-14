package com.abler.controller;

import com.abler.domain.OAuth.GoogleOAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:4000")
@Lazy
@RestController
public class OAuthController {
    private final GoogleOAuth googleOAuth;

    @Autowired
    public OAuthController(GoogleOAuth googleOAuth) {
        this.googleOAuth = googleOAuth;
    }

    @GetMapping("google")
    public void getGoogleAuthUrl(HttpServletResponse response) throws Exception { // 수정된 부분: HttpServletRequest -> HttpServletResponse
        response.sendRedirect(googleOAuth.getOauthRedirectUrl());
    }
}
