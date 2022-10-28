namespace Shared.Models;

public class User
{
    public string Username { get; set; }
    public string Password { get; set; }
    public string Name { get; set; }
    public string Email { get; set; }
    public string PhoneNumber { get; set; }

    public string Address { get; set; }

    public Boolean isAdmin { get; set; }

    public User(string username, string password, bool isAdmin)
    {
        Username = username;
        Password = password;
        this.isAdmin = isAdmin;
    }

    public User(string username, string password, string name, string email, string phoneNumber, string address, bool isAdmin)
    {
        Username = username;
        Password = password;
        Name = name;
        Email = email;
        PhoneNumber = phoneNumber;
        Address = address;
        this.isAdmin = isAdmin;
    }

    public User()
    {
    }
}