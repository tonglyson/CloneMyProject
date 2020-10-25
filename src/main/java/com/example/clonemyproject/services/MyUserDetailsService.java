package com.example.clonemyproject.services;

import com.example.clonemyproject.entities.User;
import com.example.clonemyproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;
@Service
@Primary
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(s).orElseThrow(()->new UsernameNotFoundException("khong tim thay"));

        Set<GrantedAuthority> grantedAuthorities = u.getRoles()
                .stream().map(x-> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(),grantedAuthorities);
    }
}
