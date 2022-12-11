package via.sdj3.sep_t3.service;

import com.google.rpc.Code;
import com.google.rpc.Status;

/**
 * Class that contains methods for often used grpc calls
 */
public class GrpcImplementationHelper
{
    /**
     * Use this to create a better error on gRPC
     * @param message the message shown on the client
     * @return StatusEnum to be passed into onError
     */
    public static Status generateCustomError(String message, Code code)
    {
        return Status.newBuilder()
                .setCode(code.getNumber())
                .setMessage(message)
                .build();
    }
}
