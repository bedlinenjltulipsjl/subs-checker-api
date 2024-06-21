package dev.guarmo.jwttokenserver.security;

import dev.guarmo.jwttokenserver.model.user.UserCredentials;
import dev.guarmo.jwttokenserver.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserCredentials userCredentials = repository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("Cant find user by login/username: " + login)
        );
        return userCredentials;
    }
}
