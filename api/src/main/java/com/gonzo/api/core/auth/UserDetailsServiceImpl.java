package com.gonzo.api.core.auth;

import com.gonzo.api.core.exception.CmsException;
import com.gonzo.api.core.exception.ErrorCode;
import com.gonzo.api.domain.Account;
import com.gonzo.api.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Create by park031517@gmail.com on 2020-09-23, 수
 * Blog : https://zzz-oficial.tistory.com
 * Github : https://github.com/Gon-Zo
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository repository;

    public UserDetailsServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account loginUser = repository.findByEmail(email)
                .orElseThrow(() -> new CmsException(ErrorCode.NOT_FOUND_USER));

        return UserDetailsImpl.builder()
                .email(loginUser.getEmail())
                .password(loginUser.getPassword())
                .roles(loginUser.getRoles())
                .build();

    }

}
