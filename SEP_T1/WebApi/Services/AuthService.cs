using System.ComponentModel.DataAnnotations;
using Shared.Models;

namespace WebApi.Services;

public class AuthService : IAuthService
{

    private readonly IList<User> users = new List<User>
    {/*
        new User
        {
            Email = "trmo@via.dk",
            Name = "Troels Mortensen",
            Password = "onetwo3FOUR",
            Username = "trmo",
        },
        new User
        {
           
            Email = "jakob@gmail.com",
          
            Name = "Jakob Rasmussen",
            Password = "password",
    
            Username = "jknr",
         
        }
        */
    };
    

    public Task<User> ValidateUser(string username, string password)
    {
        User? existingUser = users.FirstOrDefault(u => u.Username.Equals(username, StringComparison.OrdinalIgnoreCase));
        if (existingUser == null)
        {
            throw new Exception("User not found");
        }

        if (!existingUser.Password.Equals(password))
        {
            throw new Exception("Password mismatch");
        }

        return Task.FromResult(existingUser);
    }

    public Task RegisterUser(User user)
    {

        if (string.IsNullOrEmpty(user.Username))
        {
            throw new ValidationException("Username cannot be null");
        }

        if (string.IsNullOrEmpty(user.Password))
        {
            throw new ValidationException("Password cannot be null");
        }
        
        if (string.IsNullOrEmpty(user.Name))
        {
            throw new ValidationException("Name cannot be null");
        }
        if (string.IsNullOrEmpty(user.Email))
        {
            throw new ValidationException("Email cannot be null");
        }
        if (string.IsNullOrEmpty(user.PhoneNumber))
        {
            throw new ValidationException("PhoneNumber cannot be null");
        }
        if (string.IsNullOrEmpty(user.Address))
        {
            throw new ValidationException("Address cannot be null");
        }
        // Do more user info validation here
        
        // save to persistence instead of list
        
        users.Add(user);
        
        return Task.CompletedTask;
    }
}