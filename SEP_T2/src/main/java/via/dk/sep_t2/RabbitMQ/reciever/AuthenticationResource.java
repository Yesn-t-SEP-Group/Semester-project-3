package via.dk.sep_t2.RabbitMQ.reciever;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import via.dk.sep_t2.Service.AuthenticationRequest;
import via.dk.sep_t2.Service.AuthenticationResponse;
import via.dk.sep_t2.Service.JwtTokenService;
import via.dk.sep_t2.Service.JwtUserDetailsService;

@RestController
public class AuthenticationResource
{

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    JwtTokenService jwtTokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestParam String userName, @RequestParam String password)
    {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(userName, password);

        /*
          try
        {
            System.out.println(userName+"<:>"+password);
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            //        authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        } catch (final BadCredentialsException ex)
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
         */



        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        return authenticationResponse;
    }

}

