package via.dk.sep_t2.RestAPI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import via.dk.sep_t2.Service.TokenService;

@RestController
public class AuthController
{
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private TokenService tokenService;

    public AuthController(TokenService tokenService)
    {
        this.tokenService = tokenService;
    }
    @PostMapping("/token")
    public String token(Authentication authentication)
    {
        LOG.debug("Token requested for user '{}'",authentication.getName());
        String token=tokenService.generateToken(authentication);
        LOG.debug("Token granted {}",token);
        return token;
    }

    @PostMapping("/login")
    public String result(@RequestParam String username,@RequestParam String password)
    {
        LOG.debug("Registration requested for user '{}'",username);
        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
        try
        {
            inMemoryUserDetailsManager.createUser(User.withUsername(username).password(password).roles("USER").build());
        }
        catch (Exception e)
        {
           return "Error";
        }
        return "success";
    }


}
