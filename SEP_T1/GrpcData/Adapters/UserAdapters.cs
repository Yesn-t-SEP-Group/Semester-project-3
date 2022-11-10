using Domain.DTOs;

namespace GrpcData.Adapters;

public class UserAdapters
{
    public static UserCreationGrpcDto ConvertToGrpcCreationDto(UserCreationDto input)
    {

        return new UserCreationGrpcDto
        {
            Username = input.UserName,
            Password = input.Password,
            Role = input.Role,
            Email = input.Email,
            FullName = input.Name,
            Address = input.Address,
            PhoneNumber = input.PhoneNumber
        };
    }

    public static UserReadDto ConvertFromGrpcReadDto(UserReadGrpcDTO input)
    {
        var epoch = DateTime.UnixEpoch;
        return new UserReadDto
        {
            Id = input.Id,
            UserName = input.Username,
            Role = input.Role,
            Email = input.Email,
            PhoneNumber = input.PhoneNumber,
            Address = input.Address,
            Rating = input.Rating,
            RegistrationDateTime = epoch.AddSeconds(input.RegistrationDate),
            LastSeenDateTime = epoch.AddSeconds(input.LastSeenDate)
        };
    }
}