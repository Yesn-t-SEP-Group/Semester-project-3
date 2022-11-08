package via.sdj3.sep_t3.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: SEP3.proto")
public final class sepServiceGrpc {

  private sepServiceGrpc() {}

  public static final String SERVICE_NAME = "sepService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty,
      via.sdj3.sep_t3.protobuf.UsersGrpc> getGetAllUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllUsers",
      requestType = via.sdj3.sep_t3.protobuf.Empty.class,
      responseType = via.sdj3.sep_t3.protobuf.UsersGrpc.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty,
      via.sdj3.sep_t3.protobuf.UsersGrpc> getGetAllUsersMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty, via.sdj3.sep_t3.protobuf.UsersGrpc> getGetAllUsersMethod;
    if ((getGetAllUsersMethod = sepServiceGrpc.getGetAllUsersMethod) == null) {
      synchronized (sepServiceGrpc.class) {
        if ((getGetAllUsersMethod = sepServiceGrpc.getGetAllUsersMethod) == null) {
          sepServiceGrpc.getGetAllUsersMethod = getGetAllUsersMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.Empty, via.sdj3.sep_t3.protobuf.UsersGrpc>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.UsersGrpc.getDefaultInstance()))
              .setSchemaDescriptor(new sepServiceMethodDescriptorSupplier("getAllUsers"))
              .build();
        }
      }
    }
    return getGetAllUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.UserDTO,
      via.sdj3.sep_t3.protobuf.GenericReply> getCreateUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createUser",
      requestType = via.sdj3.sep_t3.protobuf.UserDTO.class,
      responseType = via.sdj3.sep_t3.protobuf.GenericReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.UserDTO,
      via.sdj3.sep_t3.protobuf.GenericReply> getCreateUserMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.UserDTO, via.sdj3.sep_t3.protobuf.GenericReply> getCreateUserMethod;
    if ((getCreateUserMethod = sepServiceGrpc.getCreateUserMethod) == null) {
      synchronized (sepServiceGrpc.class) {
        if ((getCreateUserMethod = sepServiceGrpc.getCreateUserMethod) == null) {
          sepServiceGrpc.getCreateUserMethod = getCreateUserMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.UserDTO, via.sdj3.sep_t3.protobuf.GenericReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.UserDTO.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.GenericReply.getDefaultInstance()))
              .setSchemaDescriptor(new sepServiceMethodDescriptorSupplier("createUser"))
              .build();
        }
      }
    }
    return getCreateUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static sepServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<sepServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<sepServiceStub>() {
        @java.lang.Override
        public sepServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new sepServiceStub(channel, callOptions);
        }
      };
    return sepServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static sepServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<sepServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<sepServiceBlockingStub>() {
        @java.lang.Override
        public sepServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new sepServiceBlockingStub(channel, callOptions);
        }
      };
    return sepServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static sepServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<sepServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<sepServiceFutureStub>() {
        @java.lang.Override
        public sepServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new sepServiceFutureStub(channel, callOptions);
        }
      };
    return sepServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class sepServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAllUsers(via.sdj3.sep_t3.protobuf.Empty request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.UsersGrpc> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllUsersMethod(), responseObserver);
    }

    /**
     */
    public void createUser(via.sdj3.sep_t3.protobuf.UserDTO request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.GenericReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateUserMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAllUsersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.Empty,
                via.sdj3.sep_t3.protobuf.UsersGrpc>(
                  this, METHODID_GET_ALL_USERS)))
          .addMethod(
            getCreateUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.UserDTO,
                via.sdj3.sep_t3.protobuf.GenericReply>(
                  this, METHODID_CREATE_USER)))
          .build();
    }
  }

  /**
   */
  public static final class sepServiceStub extends io.grpc.stub.AbstractAsyncStub<sepServiceStub> {
    private sepServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected sepServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new sepServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAllUsers(via.sdj3.sep_t3.protobuf.Empty request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.UsersGrpc> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createUser(via.sdj3.sep_t3.protobuf.UserDTO request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.GenericReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class sepServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<sepServiceBlockingStub> {
    private sepServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected sepServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new sepServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.UsersGrpc getAllUsers(via.sdj3.sep_t3.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllUsersMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.GenericReply createUser(via.sdj3.sep_t3.protobuf.UserDTO request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateUserMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class sepServiceFutureStub extends io.grpc.stub.AbstractFutureStub<sepServiceFutureStub> {
    private sepServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected sepServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new sepServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.UsersGrpc> getAllUsers(
        via.sdj3.sep_t3.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllUsersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.GenericReply> createUser(
        via.sdj3.sep_t3.protobuf.UserDTO request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_USERS = 0;
  private static final int METHODID_CREATE_USER = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final sepServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(sepServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_USERS:
          serviceImpl.getAllUsers((via.sdj3.sep_t3.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.UsersGrpc>) responseObserver);
          break;
        case METHODID_CREATE_USER:
          serviceImpl.createUser((via.sdj3.sep_t3.protobuf.UserDTO) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.GenericReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class sepServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    sepServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.sdj3.sep_t3.protobuf.SEP3.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("sepService");
    }
  }

  private static final class sepServiceFileDescriptorSupplier
      extends sepServiceBaseDescriptorSupplier {
    sepServiceFileDescriptorSupplier() {}
  }

  private static final class sepServiceMethodDescriptorSupplier
      extends sepServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    sepServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (sepServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new sepServiceFileDescriptorSupplier())
              .addMethod(getGetAllUsersMethod())
              .addMethod(getCreateUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
