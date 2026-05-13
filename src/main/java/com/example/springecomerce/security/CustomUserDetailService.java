package com.example.springecomerce.security;

import com.example.springecomerce.entity.Role;
import com.example.springecomerce.entity.User;
import com.example.springecomerce.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // ត្រូវប្តូរពី findByUsername មក findByEmail វិញ
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("រកមិនឃើញ User ជាមួយ Email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // ប្រើ email ជា username ក្នុង security context
                user.getPassword(),
                new ArrayList<>() // ឬ user.getAuthorities() បើអ្នកមាន Role
        );
    }
}







