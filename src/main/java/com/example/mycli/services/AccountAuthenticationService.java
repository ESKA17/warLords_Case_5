package com.example.mycli.services;

import javax.servlet.http.HttpServletResponse;

public interface AccountAuthenticationService {

    void authenticateAccount(String email, String password, HttpServletResponse httpServletResponse);

}
