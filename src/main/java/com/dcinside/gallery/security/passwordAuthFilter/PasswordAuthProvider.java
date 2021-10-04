package com.dcinside.gallery.security.passwordAuthFilter;

import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class PasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Post post = postRepository.findById(Long.parseLong(username)).get();

        if(!post.matches(password)) {
            throw new BadCredentialsException("password 가 일치하지 않습니다");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, null);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
