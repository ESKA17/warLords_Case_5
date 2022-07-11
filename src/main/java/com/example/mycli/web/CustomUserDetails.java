package com.example.mycli.web;

import com.example.mycli.entity.UserEntity;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private UserEntity userEntity;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserEntity userEntity) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        if (userEntity != null) {
            customUserDetails.login = userEntity.getAuthdata().getEmail();
            customUserDetails.password = userEntity.getAuthdata().getPassword();
            customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(
                    userEntity.getAuthdata().getRoleEntity().getName()));
        }
        return customUserDetails;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getActive();
    }
}

