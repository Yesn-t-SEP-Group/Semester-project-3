package via.dk.sep_t2.Service;

import ch.qos.logback.core.net.server.Client;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService
{

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;


    @Override
    public UserDetails loadUserByUsername(final String username) {
        //do password authenticcation here
        //throw new UsernameNotFoundException("User " + username + " not found");

        //todo only call this if found with correct hashed password
        return new User(username, "password", Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
    }

}
