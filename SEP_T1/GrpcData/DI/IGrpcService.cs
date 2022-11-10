using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GrpcData.DI
{
    public interface IGrpcService
    {
        public sepService.sepServiceClient CreateServiceClient();
    }
}
