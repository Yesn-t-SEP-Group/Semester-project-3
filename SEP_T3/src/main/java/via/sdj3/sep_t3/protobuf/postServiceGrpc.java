package via.sdj3.sep_t3.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: PostGrpcModel.proto")
public final class postServiceGrpc {

  private postServiceGrpc() {}

  public static final String SERVICE_NAME = "postService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty,
      via.sdj3.sep_t3.protobuf.AllPosts> getGetAllPostsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllPosts",
      requestType = via.sdj3.sep_t3.protobuf.Empty.class,
      responseType = via.sdj3.sep_t3.protobuf.AllPosts.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty,
      via.sdj3.sep_t3.protobuf.AllPosts> getGetAllPostsMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.Empty, via.sdj3.sep_t3.protobuf.AllPosts> getGetAllPostsMethod;
    if ((getGetAllPostsMethod = postServiceGrpc.getGetAllPostsMethod) == null) {
      synchronized (postServiceGrpc.class) {
        if ((getGetAllPostsMethod = postServiceGrpc.getGetAllPostsMethod) == null) {
          postServiceGrpc.getGetAllPostsMethod = getGetAllPostsMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.Empty, via.sdj3.sep_t3.protobuf.AllPosts>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllPosts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.AllPosts.getDefaultInstance()))
              .setSchemaDescriptor(new postServiceMethodDescriptorSupplier("getAllPosts"))
              .build();
        }
      }
    }
    return getGetAllPostsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostCreationGrpcDto,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getCreatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createPost",
      requestType = via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.class,
      responseType = via.sdj3.sep_t3.protobuf.PostReadGrpcDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostCreationGrpcDto,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getCreatePostMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostCreationGrpcDto, via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getCreatePostMethod;
    if ((getCreatePostMethod = postServiceGrpc.getCreatePostMethod) == null) {
      synchronized (postServiceGrpc.class) {
        if ((getCreatePostMethod = postServiceGrpc.getCreatePostMethod) == null) {
          postServiceGrpc.getCreatePostMethod = getCreatePostMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.PostCreationGrpcDto, via.sdj3.sep_t3.protobuf.PostReadGrpcDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createPost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.PostReadGrpcDto.getDefaultInstance()))
              .setSchemaDescriptor(new postServiceMethodDescriptorSupplier("createPost"))
              .build();
        }
      }
    }
    return getCreatePostMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.GenericMessage,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getGetPostByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPostById",
      requestType = via.sdj3.sep_t3.protobuf.GenericMessage.class,
      responseType = via.sdj3.sep_t3.protobuf.PostReadGrpcDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.GenericMessage,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getGetPostByIdMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.GenericMessage, via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getGetPostByIdMethod;
    if ((getGetPostByIdMethod = postServiceGrpc.getGetPostByIdMethod) == null) {
      synchronized (postServiceGrpc.class) {
        if ((getGetPostByIdMethod = postServiceGrpc.getGetPostByIdMethod) == null) {
          postServiceGrpc.getGetPostByIdMethod = getGetPostByIdMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.GenericMessage, via.sdj3.sep_t3.protobuf.PostReadGrpcDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPostById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.GenericMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.PostReadGrpcDto.getDefaultInstance()))
              .setSchemaDescriptor(new postServiceMethodDescriptorSupplier("getPostById"))
              .build();
        }
      }
    }
    return getGetPostByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getUpdatePostMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updatePost",
      requestType = via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto.class,
      responseType = via.sdj3.sep_t3.protobuf.PostReadGrpcDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto,
      via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getUpdatePostMethod() {
    io.grpc.MethodDescriptor<via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto, via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getUpdatePostMethod;
    if ((getUpdatePostMethod = postServiceGrpc.getUpdatePostMethod) == null) {
      synchronized (postServiceGrpc.class) {
        if ((getUpdatePostMethod = postServiceGrpc.getUpdatePostMethod) == null) {
          postServiceGrpc.getUpdatePostMethod = getUpdatePostMethod =
              io.grpc.MethodDescriptor.<via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto, via.sdj3.sep_t3.protobuf.PostReadGrpcDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updatePost"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.sdj3.sep_t3.protobuf.PostReadGrpcDto.getDefaultInstance()))
              .setSchemaDescriptor(new postServiceMethodDescriptorSupplier("updatePost"))
              .build();
        }
      }
    }
    return getUpdatePostMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static postServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<postServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<postServiceStub>() {
        @java.lang.Override
        public postServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new postServiceStub(channel, callOptions);
        }
      };
    return postServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static postServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<postServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<postServiceBlockingStub>() {
        @java.lang.Override
        public postServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new postServiceBlockingStub(channel, callOptions);
        }
      };
    return postServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static postServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<postServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<postServiceFutureStub>() {
        @java.lang.Override
        public postServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new postServiceFutureStub(channel, callOptions);
        }
      };
    return postServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class postServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAllPosts(via.sdj3.sep_t3.protobuf.Empty request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.AllPosts> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllPostsMethod(), responseObserver);
    }

    /**
     */
    public void createPost(via.sdj3.sep_t3.protobuf.PostCreationGrpcDto request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePostMethod(), responseObserver);
    }

    /**
     */
    public void getPostById(via.sdj3.sep_t3.protobuf.GenericMessage request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPostByIdMethod(), responseObserver);
    }

    /**
     */
    public void updatePost(via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePostMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAllPostsMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.Empty,
                via.sdj3.sep_t3.protobuf.AllPosts>(
                  this, METHODID_GET_ALL_POSTS)))
          .addMethod(
            getCreatePostMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.PostCreationGrpcDto,
                via.sdj3.sep_t3.protobuf.PostReadGrpcDto>(
                  this, METHODID_CREATE_POST)))
          .addMethod(
            getGetPostByIdMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.GenericMessage,
                via.sdj3.sep_t3.protobuf.PostReadGrpcDto>(
                  this, METHODID_GET_POST_BY_ID)))
          .addMethod(
            getUpdatePostMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto,
                via.sdj3.sep_t3.protobuf.PostReadGrpcDto>(
                  this, METHODID_UPDATE_POST)))
          .build();
    }
  }

  /**
   */
  public static final class postServiceStub extends io.grpc.stub.AbstractAsyncStub<postServiceStub> {
    private postServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected postServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new postServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAllPosts(via.sdj3.sep_t3.protobuf.Empty request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.AllPosts> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllPostsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPost(via.sdj3.sep_t3.protobuf.PostCreationGrpcDto request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPostById(via.sdj3.sep_t3.protobuf.GenericMessage request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPostByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePost(via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto request,
        io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class postServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<postServiceBlockingStub> {
    private postServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected postServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new postServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.AllPosts getAllPosts(via.sdj3.sep_t3.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllPostsMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.PostReadGrpcDto createPost(via.sdj3.sep_t3.protobuf.PostCreationGrpcDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePostMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.PostReadGrpcDto getPostById(via.sdj3.sep_t3.protobuf.GenericMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPostByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.sdj3.sep_t3.protobuf.PostReadGrpcDto updatePost(via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePostMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class postServiceFutureStub extends io.grpc.stub.AbstractFutureStub<postServiceFutureStub> {
    private postServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected postServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new postServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.AllPosts> getAllPosts(
        via.sdj3.sep_t3.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllPostsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> createPost(
        via.sdj3.sep_t3.protobuf.PostCreationGrpcDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePostMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> getPostById(
        via.sdj3.sep_t3.protobuf.GenericMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPostByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.sdj3.sep_t3.protobuf.PostReadGrpcDto> updatePost(
        via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePostMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_POSTS = 0;
  private static final int METHODID_CREATE_POST = 1;
  private static final int METHODID_GET_POST_BY_ID = 2;
  private static final int METHODID_UPDATE_POST = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final postServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(postServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_POSTS:
          serviceImpl.getAllPosts((via.sdj3.sep_t3.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.AllPosts>) responseObserver);
          break;
        case METHODID_CREATE_POST:
          serviceImpl.createPost((via.sdj3.sep_t3.protobuf.PostCreationGrpcDto) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto>) responseObserver);
          break;
        case METHODID_GET_POST_BY_ID:
          serviceImpl.getPostById((via.sdj3.sep_t3.protobuf.GenericMessage) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto>) responseObserver);
          break;
        case METHODID_UPDATE_POST:
          serviceImpl.updatePost((via.sdj3.sep_t3.protobuf.PostUpdateGrpcDto) request,
              (io.grpc.stub.StreamObserver<via.sdj3.sep_t3.protobuf.PostReadGrpcDto>) responseObserver);
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

  private static abstract class postServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    postServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.sdj3.sep_t3.protobuf.PostGrpcModel.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("postService");
    }
  }

  private static final class postServiceFileDescriptorSupplier
      extends postServiceBaseDescriptorSupplier {
    postServiceFileDescriptorSupplier() {}
  }

  private static final class postServiceMethodDescriptorSupplier
      extends postServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    postServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (postServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new postServiceFileDescriptorSupplier())
              .addMethod(getGetAllPostsMethod())
              .addMethod(getCreatePostMethod())
              .addMethod(getGetPostByIdMethod())
              .addMethod(getUpdatePostMethod())
              .build();
        }
      }
    }
    return result;
  }
}
