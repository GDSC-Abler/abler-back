package com.abler.service.test;

import com.abler.domain.test.Test;
import com.abler.dto.test.TestDto;
import com.abler.dto.test.TestRequestDto;
import org.springframework.context.annotation.Lazy;

import java.util.UUID;

@Lazy
public interface TestService {

    // 회원가입
    Test join(TestRequestDto req);

    // 로그인
    Test login(TestDto req);

//    Test getLoginUserByUserUuid(UUID userUuid);

//    Test getLoginUserByUserId(String userId);

    boolean resetPassword(String userId, String newPassword);


    Test findByUserUuid(UUID userUuid);
}
