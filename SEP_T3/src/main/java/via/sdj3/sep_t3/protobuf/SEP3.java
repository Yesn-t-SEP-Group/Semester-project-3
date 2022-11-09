// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SEP3.proto

package via.sdj3.sep_t3.protobuf;

public final class SEP3 {
  private SEP3() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_User_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_User_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserDTO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserDTO_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Empty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_loginCredentials_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_loginCredentials_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GenericMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_GenericMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UsersGrpc_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UsersGrpc_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nSEP3.proto\"\245\001\n\004User\022\n\n\002id\030\001 \001(\005\022\020\n\010use" +
      "rname\030\002 \001(\t\022\020\n\010userPass\030\003 \001(\t\022\020\n\010fullNam" +
      "e\030\004 \001(\t\022\r\n\005email\030\005 \001(\t\022\023\n\013phoneNumber\030\006 " +
      "\001(\t\022\017\n\007address\030\007 \001(\t\022\024\n\014registeredOn\030\010 \001" +
      "(\005\022\020\n\010lastSeen\030\t \001(\005\"t\n\007UserDTO\022\020\n\010usern" +
      "ame\030\001 \001(\t\022\020\n\010userPass\030\002 \001(\t\022\r\n\005email\030\003 \001" +
      "(\t\022\020\n\010fullName\030\004 \001(\t\022\017\n\007address\030\005 \001(\t\022\023\n" +
      "\013phoneNumber\030\006 \001(\t\"\007\n\005Empty\"6\n\020loginCred" +
      "entials\022\020\n\010userName\030\001 \001(\t\022\020\n\010password\030\002 " +
      "\001(\t\"!\n\016GenericMessage\022\017\n\007message\030\001 \001(\t\"!" +
      "\n\tUsersGrpc\022\024\n\005users\030\001 \003(\0132\005.User2\252\001\n\nse" +
      "pService\022!\n\013getAllUsers\022\006.Empty\032\n.UsersG" +
      "rpc\022\035\n\ncreateUser\022\010.UserDTO\032\005.User\022%\n\013ge" +
      "tUserById\022\017.GenericMessage\032\005.User\0223\n\rval" +
      "idateLogin\022\021.loginCredentials\032\017.GenericM" +
      "essageB\034\n\030via.sdj3.sep_t3.protobufP\001b\006pr" +
      "oto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_User_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_User_descriptor,
        new java.lang.String[] { "Id", "Username", "UserPass", "FullName", "Email", "PhoneNumber", "Address", "RegisteredOn", "LastSeen", });
    internal_static_UserDTO_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_UserDTO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserDTO_descriptor,
        new java.lang.String[] { "Username", "UserPass", "Email", "FullName", "Address", "PhoneNumber", });
    internal_static_Empty_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Empty_descriptor,
        new java.lang.String[] { });
    internal_static_loginCredentials_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_loginCredentials_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_loginCredentials_descriptor,
        new java.lang.String[] { "UserName", "Password", });
    internal_static_GenericMessage_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_GenericMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_GenericMessage_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_UsersGrpc_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_UsersGrpc_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UsersGrpc_descriptor,
        new java.lang.String[] { "Users", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
