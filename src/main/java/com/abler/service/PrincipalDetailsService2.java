package com.abler.service;

import com.abler.security.PrincipalDetails;
import com.abler.domain.user.User;
import com.abler.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/*
* UserDetailsService
* 유저 정보 가져오는 인터페이스
* */

@Transactional
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService2 implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new PrincipalDetails(user);
    }



}
