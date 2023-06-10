package com.thi.bt.knn.service;


import com.thi.bt.knn.LoginRequest;
import com.thi.bt.knn.RegisterRequest;
import com.thi.bt.knn.User;
import com.thi.bt.knn.config.TokenHelper;
import com.thi.bt.knn.exception.BadRequestException;
import com.thi.bt.knn.repository.UserRepository;
import com.thi.bt.knn.response.ResponseData;
import com.thi.bt.knn.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor = Exception.class)
public class LoginService  {
    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    public ResponseData<User> loginJWT(LoginRequest loginRequest) throws BadRequestException {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            String session = servletRequestAttributes.getSessionId();
            User user = (User) authentication.getPrincipal();
            String jwt = tokenHelper.generateToken(loginRequest.getUsername(), user, session);
            user.setJwt(jwt);
            return new ResponseData<>(user, Result.SUCCESS);

        } catch (BadCredentialsException e) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (AccessDeniedException ex1) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (LockedException ex) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (DisabledException exx) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (AuthenticationException atx) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException ex) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }

    public ResponseData<User> register(RegisterRequest registerRequest) throws BadRequestException {
        //kiểm tra username đã tồn tại chưa
        Optional<User> users = userRepository.findByUsername(registerRequest.getUsername());
        if (users.isPresent()) {
            return new ResponseData<>(Result.BAD_REQUEST, "Tên đăng nhập đã tồn tại");
        }
        //kiểm tra password và confirm password có giống nhau không
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return new ResponseData<>(Result.BAD_REQUEST,"Xác nhận mật khẩu không đúng");
        }
        //tạo user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullname(registerRequest.getFullname());
        return new ResponseData<>(userRepository.save(user), Result.SUCCESS );

    }
}
