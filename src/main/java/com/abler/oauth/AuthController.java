package com.abler.oauth;

import com.abler.oauth.dto.*;
import com.abler.oauth.jwt.JwtTokenProvider;
import com.abler.oauth.model.PrincipalDetails;
import com.abler.util.BaseResponse;
import com.abler.user.UserService3;
import com.abler.user.model.User;
import com.abler.util.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService3 userService3;
    private final AuthService authService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService3 userService3, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService3 = userService3;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public BaseResponse<String> join(@RequestBody PostSignupReq postUserReq) throws BaseException {

        String encodedPassword = passwordEncoder.encode(postUserReq.getPassword());
        User user = new User(postUserReq.getUsername(), postUserReq.getNickname(),
                postUserReq.getEmail(), encodedPassword, "ROLE_USER", "none", "none");
        try {
            userService3.createUser(user);
            return new BaseResponse("회원가입에 성공하였습니다.");
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
    }

    @PostMapping("/signin")
    public BaseResponse<PostSigninRes> loginAuto(@RequestBody PostSigninReq postLoginReq) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(postLoginReq.getEmail(), postLoginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("유저 인증 성공. 일반 로그인을 진행합니다.");

        PrincipalDetails userEntity = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(userEntity);

        Long user_id = userEntity.getUser().getId();
        String accessToken = jwtTokenProvider.createAccessToken(user_id);

        return new BaseResponse<>(new PostSigninRes(accessToken, ""));
    }

    @PostMapping("/signin/auto")
    public BaseResponse<PostSigninAutoRes> login(@RequestBody PostSigninAutoReq postLoginReq) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(postLoginReq.getEmail(), postLoginReq.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("유저 인증 . 자동 로그인을 진행합니다.");

        PrincipalDetails userEntity = (PrincipalDetails) authentication.getPrincipal();
        System.out.println(userEntity);

        Long user_id = userEntity.getUser().getId();
        String accessToken = jwtTokenProvider.createAccessToken(user_id);
        String refreshToken = jwtTokenProvider.createRefreshToken(user_id);

        authService.registerRefreshToken(user_id, refreshToken);
        return new BaseResponse<>(new PostSigninAutoRes(accessToken, refreshToken));
    }

    @GetMapping("/oauth2/success")
    public BaseResponse<PostSigninAutoRes> loginSuccess(@RequestParam("accessToken") String accessToken, @RequestParam("refreshToken") String refreshToken) {
        PostSigninAutoRes postLoginRes = new PostSigninAutoRes(accessToken, refreshToken);
        return new BaseResponse<>(postLoginRes);
    }

}
