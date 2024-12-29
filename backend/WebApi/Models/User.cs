using System.ComponentModel.DataAnnotations;

namespace WebApi.Models;

public class User
{
    [Key]
    public Guid Id { get; set; }

    [Required]
    public string Name { get; set; }

    [Required]
    [EmailAddress]
    public string Email { get; set; }
    
    public double Height { get; set; } 
    public double Weight { get; set; } 
    public double Bmi { get; set; }
    public string Password { get; set; }
}