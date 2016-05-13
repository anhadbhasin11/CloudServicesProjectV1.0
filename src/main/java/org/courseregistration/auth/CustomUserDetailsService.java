package org.courseregistration.auth;

import org.courseregistration.dao.UserDAO;
import org.courseregistration.model.Role;
import org.courseregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;


public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.findByUsername(username);

        if(user !=null){
            String userNameDB = user.getUsername();
            String hashedPassword = user.getHashedPassword();
            String password = decodeUsingBase64(hashedPassword);

            Set<Role> roles = user.getRoles();

            return new org.springframework.security.core.userdetails.User(userNameDB,password,getAuthorities(roles));
        }

        throw new BadCredentialsException("User does not exist");
    }


    List<GrantedAuthority> getAuthorities(Collection<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        authorities.addAll(roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        return  authorities;
    }
    public String decodeUsingBase64(String toDecode) {
        try {
            byte[] decoded = Base64.decode(toDecode.getBytes("UTF-8"));
            return new String(decoded);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

}
