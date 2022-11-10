using Domain.DTOs;
using Domain.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Mappings
{
    public static class UserMapping
    {
        public static UserReadDto UserReadDtoFromUser(User entity)
        {
            return new UserReadDto()
            {
                Address = entity.Address,
                Email = entity.Email,
                Id = entity.Id,
                Name = entity.Name,
                PhoneNumber = entity.PhoneNumber,
                RegistrationDateTime = entity.registeredOn,
                Role = entity.Role,
                UserName = entity.UserName,
                LastSeenDateTime = DateTime.Now,
                Rating = 5,
            };
        }

        public static User UserFromUserCreationDto(UserCreationDto dto)
        {
            return new User()
            {
                UserName = dto.UserName,
                Address = dto.Address,
                Email = dto.Email,
                Name = dto.Name,
                Password = dto.Password,
                PhoneNumber = dto.PhoneNumber,
                rating = 5,
                lastSeen = DateTime.Now,
                registeredOn = DateTime.Now,
                Role = "User",
            };
        }
    }
}
