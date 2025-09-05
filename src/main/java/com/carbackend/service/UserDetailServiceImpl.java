package com.carbackend.service;

import com.carbackend.domain.AppUser;
import com.carbackend.domain.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //username(id) 유저 db에 존재 확인, 유저정보를 UserDetails 타입으로 반환
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        UserDetails userDetails = null;
        if (user.isPresent()) {
            AppUser appUser = user.get();
            userDetails = User.withUsername(username)
                    .password(appUser.getPassword())
                    .roles(appUser.getRole())
                    .build();
        }
        else {
            throw new UsernameNotFoundException("Username not found");
        }
        return userDetails;
    }
}
