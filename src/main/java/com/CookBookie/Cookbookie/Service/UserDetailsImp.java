package com.CookBookie.Cookbookie.Service;

import com.CookBookie.Cookbookie.Model.User;
import com.CookBookie.Cookbookie.Repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found")
        );
    }

}
