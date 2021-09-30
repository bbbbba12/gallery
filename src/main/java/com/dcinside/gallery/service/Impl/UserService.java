package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.repository.AccountRepository;
import com.dcinside.gallery.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> optionalAccount = accountRepository.findByUsername(username);

        if(optionalAccount.isEmpty()) throw new UsernameNotFoundException("유저를 찾을 수 없읍니다");

        Account account = optionalAccount.get();

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole()));
        return new PrincipalDetails(account, authorities);
    }

    public void signUp(AccountDto accountDto) {
        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .build();
        accountRepository.save(account);
    }
}
