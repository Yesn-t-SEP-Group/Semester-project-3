using System.ComponentModel.DataAnnotations;

namespace Domain.DTOs;

public class UserCreationDto
{
    [Required(ErrorMessage = "Username is required")]
    [StringLength(16, ErrorMessage = "Must be between 3 and 16 characters", MinimumLength = 3)]
    public string UserName { get; set; } = String.Empty;
    
    [Required(ErrorMessage = "Password is required")]
    [StringLength(255, ErrorMessage = "Must be between 5 and 255 characters", MinimumLength = 5)]
    [DataType(DataType.Password)]
    public string Password { get; set; } = String.Empty;

    [Required(ErrorMessage = "Confirm Password is required")]
    [StringLength(255, ErrorMessage = "Must be between 5 and 255 characters", MinimumLength = 5)]
    [DataType(DataType.Password)]
    [Compare("Password")]
    public string ConfirmPassword { get; set; } = String.Empty;
    
    [Required(ErrorMessage = "Must select a role")]
    public string Role { get; set; } = String.Empty;
    
    [Required(ErrorMessage = "Enter full name")]
    public string Name { get; set; } = String.Empty;
    [Required]
    [EmailAddress(ErrorMessage = "Enter valid email address")] 
    public string Email { get; set; } = String.Empty;
    
    [Required(ErrorMessage = "You must provide a phone number")]
    [Display(Name = "Mobile Phone")]
    [DataType(DataType.PhoneNumber)]
    [RegularExpression(@"^(\+|00)45\d{8}", ErrorMessage = "The phone number must be from Denmark")]
    public string PhoneNumber { get; set; } = String.Empty;
    
    [Required(ErrorMessage = "You must provide an address")]
    [StringLength(200, MinimumLength = 5)]
    public string Address { get; set; } = String.Empty;
}