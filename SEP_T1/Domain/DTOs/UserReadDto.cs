using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.DTOs
{
    public class UserReadDto
    {
        public int Id { get; set; }

        public string UserName { get; set; }
        public string Password { get; set; }
        

        public string Role { get; set; }

        public string Name { get; set; }

        public string Email { get; set; }

        public string PhoneNumber { get; set; }

        public string Address { get; set; }

        public int Rating { get; set; }

        public DateTime RegistrationDateTime { get; set; }

        public DateTime LastSeenDateTime { get; set; }
    }
}
