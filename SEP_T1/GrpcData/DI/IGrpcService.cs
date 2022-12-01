using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GrpcData.DI
{
    public interface IGrpcService
    {
        public sepService.sepServiceClient CreateUserServiceClient();
        public postService.postServiceClient CreatePostServiceClient();
        
        public reportService.reportServiceClient CreateReportServiceClient();
        public ratingService.ratingServiceClient CreateRatingServiceClient();

    }
}
