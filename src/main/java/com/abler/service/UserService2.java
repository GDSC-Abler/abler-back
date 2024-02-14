package com.abler.service;

import com.abler.domain.user.User;
import com.abler.dto.request.JoinRequest;
import com.abler.dto.request.LoginRequest;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface UserService2 {



    /*
     * userId 중복체크
     * 중복 시 return true
     * */
    boolean checkUserIdDuplicate(String userId);

    /*
     * 이메일 중복체크
     * 중복 시 return true
     * */
    boolean checkEmailDuplicate(String email);

    User join(JoinRequest req);

    User login(LoginRequest req);

    User getLoginUserByUserUuid(UUID userUuid);

    User getLoginUserByUserId(String userId);

    boolean resetPassword(String userId, String newPassword);

    String findUserId(String email);

    User findByUserUuid(UUID userUuid);
}