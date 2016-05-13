package org.courseregistration.auth;

import org.courseregistration.dao.UserDAO;
import org.courseregistration.model.Role;
import org.courseregistration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class RestAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDAO userDAO;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            RestToken restToken = (RestToken) authentication;

            String key = decodeUsingBase64(restToken.getKey());
            String credentials = decodeUsingBase64(restToken.getCredentials());

            User user = userDAO.findByUsername(key);

            if(user !=null){
                if(decodeUsingBase64(user.getHashedPassword()).equals(credentials)){
                    Set<Role> roles = user.getRoles();
                    List<String> userRoles = newArrayList();
                    for(Role role:roles){
                        userRoles.add(role.getName());
                    }
                    return getAuthenticatedUser(key, credentials,userRoles);
                }else{
                    throw new BadCredentialsException("Enter a username and password");
                }
            }
               throw new UsernameNotFoundException("User with given Id not found");

        }

        private Authentication getAuthenticatedUser(String key, String credentials,List<String> roles) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            for(String role : roles){
                authorities.add(new SimpleGrantedAuthority(role));
            }

            return new RestToken(key, credentials, authorities);
        }

        @Override
    /*
        Determines if this class can support the token provided by the filter.
     */
        public boolean supports(Class<?> authentication) {
            return RestToken.class.equals(authentication);
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

