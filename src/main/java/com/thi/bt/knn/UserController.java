package com.thi.bt.knn;

import com.thi.bt.knn.exception.BadRequestException;
import com.thi.bt.knn.response.ResponseData;
import com.thi.bt.knn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;
    @PostMapping(value = "/public/login")
    public ResponseEntity<?> loginJWT(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        System.out.println(request.getHeader("Accept-Language"));

        return new ResponseEntity<>(loginService.loginJWT(loginRequest), HttpStatus.OK);
    }
    @PostMapping(value = "/public/register")
    public ResponseEntity<ResponseData<User>> register(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) throws BadRequestException {
//        System.out.println(request.getHeader("Accept-Language"));

//        return new ResponseEntity<>(loginService.register(registerRequest), HttpStatus.OK);
        ResponseData<User> responseData = loginService.register(registerRequest);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping(value = "/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        //get userlogin from spring security
        User userLongin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(userLongin, HttpStatus.OK);
    }

}
