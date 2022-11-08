package via.dk.sep_t2.Service;

public class AuthenticationRequest
{

    private String login;
    private String password;

    public AuthenticationRequest(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }
}
