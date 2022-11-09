using Google.Protobuf.WellKnownTypes;

namespace GrpcDemo.Adapters;

public class UserAdapter
{
    public User ConvertToGrpc(Domain.Models.User modelUser)
    {
        User ret = new User
        {
            Address = modelUser.Address,
            Email = modelUser.Email,
            Id = modelUser.Id,
            Username = modelUser.UserName,
            UserPass = modelUser.Password,
            FullName = modelUser.Name,
            PhoneNumber = modelUser.PhoneNumber,
            RegisteredOn = (int)modelUser.registeredOn.ToTimestamp().Seconds,
            LastSeen = (int)modelUser.lastSeen.ToTimestamp().Seconds
        };
        return ret;
    }

    public Domain.Models.User ConvertFromGrpc(User grpcUser)
    {
        var epoch=DateTime.UnixEpoch;
        var ret = new Domain.Models.User()
        {
            Address = grpcUser.Address,
            Email = grpcUser.Email,
            Id = grpcUser.Id,
            registeredOn = epoch.AddSeconds(grpcUser.RegisteredOn),
            lastSeen = epoch.AddSeconds(grpcUser.LastSeen),
            Name = grpcUser.FullName,
            Password = grpcUser.UserPass,
            PhoneNumber = grpcUser.PhoneNumber
        };
        return ret;
    }
}