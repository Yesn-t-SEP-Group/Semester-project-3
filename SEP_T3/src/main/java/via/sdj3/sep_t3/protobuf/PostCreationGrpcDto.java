// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PostGrpcModel.proto

package via.sdj3.sep_t3.protobuf;

/**
 * Protobuf type {@code PostCreationGrpcDto}
 */
public final class PostCreationGrpcDto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PostCreationGrpcDto)
    PostCreationGrpcDtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PostCreationGrpcDto.newBuilder() to construct.
  private PostCreationGrpcDto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PostCreationGrpcDto() {
    description_ = "";
    location_ = "";
    categories_ = "";
    picture_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new PostCreationGrpcDto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PostCreationGrpcDto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            description_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            location_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            categories_ = s;
            break;
          }
          case 32: {

            sellerId_ = input.readInt32();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            picture_ = s;
            break;
          }
          case 48: {

            price_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return via.sdj3.sep_t3.protobuf.PostGrpcModel.internal_static_PostCreationGrpcDto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return via.sdj3.sep_t3.protobuf.PostGrpcModel.internal_static_PostCreationGrpcDto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.class, via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.Builder.class);
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 1;
  private volatile java.lang.Object description_;
  /**
   * <code>string description = 1;</code>
   * @return The description.
   */
  @java.lang.Override
  public java.lang.String getDescription() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      description_ = s;
      return s;
    }
  }
  /**
   * <code>string description = 1;</code>
   * @return The bytes for description.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDescriptionBytes() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      description_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LOCATION_FIELD_NUMBER = 2;
  private volatile java.lang.Object location_;
  /**
   * <code>string location = 2;</code>
   * @return The location.
   */
  @java.lang.Override
  public java.lang.String getLocation() {
    java.lang.Object ref = location_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      location_ = s;
      return s;
    }
  }
  /**
   * <code>string location = 2;</code>
   * @return The bytes for location.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getLocationBytes() {
    java.lang.Object ref = location_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      location_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CATEGORIES_FIELD_NUMBER = 3;
  private volatile java.lang.Object categories_;
  /**
   * <code>string categories = 3;</code>
   * @return The categories.
   */
  @java.lang.Override
  public java.lang.String getCategories() {
    java.lang.Object ref = categories_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      categories_ = s;
      return s;
    }
  }
  /**
   * <code>string categories = 3;</code>
   * @return The bytes for categories.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCategoriesBytes() {
    java.lang.Object ref = categories_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      categories_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SELLERID_FIELD_NUMBER = 4;
  private int sellerId_;
  /**
   * <code>int32 sellerId = 4;</code>
   * @return The sellerId.
   */
  @java.lang.Override
  public int getSellerId() {
    return sellerId_;
  }

  public static final int PICTURE_FIELD_NUMBER = 5;
  private volatile java.lang.Object picture_;
  /**
   * <code>string picture = 5;</code>
   * @return The picture.
   */
  @java.lang.Override
  public java.lang.String getPicture() {
    java.lang.Object ref = picture_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      picture_ = s;
      return s;
    }
  }
  /**
   * <code>string picture = 5;</code>
   * @return The bytes for picture.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPictureBytes() {
    java.lang.Object ref = picture_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      picture_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRICE_FIELD_NUMBER = 6;
  private int price_;
  /**
   * <code>int32 price = 6;</code>
   * @return The price.
   */
  @java.lang.Override
  public int getPrice() {
    return price_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, description_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(location_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, location_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(categories_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, categories_);
    }
    if (sellerId_ != 0) {
      output.writeInt32(4, sellerId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(picture_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, picture_);
    }
    if (price_ != 0) {
      output.writeInt32(6, price_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(description_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, description_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(location_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, location_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(categories_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, categories_);
    }
    if (sellerId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, sellerId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(picture_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, picture_);
    }
    if (price_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, price_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof via.sdj3.sep_t3.protobuf.PostCreationGrpcDto)) {
      return super.equals(obj);
    }
    via.sdj3.sep_t3.protobuf.PostCreationGrpcDto other = (via.sdj3.sep_t3.protobuf.PostCreationGrpcDto) obj;

    if (!getDescription()
        .equals(other.getDescription())) return false;
    if (!getLocation()
        .equals(other.getLocation())) return false;
    if (!getCategories()
        .equals(other.getCategories())) return false;
    if (getSellerId()
        != other.getSellerId()) return false;
    if (!getPicture()
        .equals(other.getPicture())) return false;
    if (getPrice()
        != other.getPrice()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getDescription().hashCode();
    hash = (37 * hash) + LOCATION_FIELD_NUMBER;
    hash = (53 * hash) + getLocation().hashCode();
    hash = (37 * hash) + CATEGORIES_FIELD_NUMBER;
    hash = (53 * hash) + getCategories().hashCode();
    hash = (37 * hash) + SELLERID_FIELD_NUMBER;
    hash = (53 * hash) + getSellerId();
    hash = (37 * hash) + PICTURE_FIELD_NUMBER;
    hash = (53 * hash) + getPicture().hashCode();
    hash = (37 * hash) + PRICE_FIELD_NUMBER;
    hash = (53 * hash) + getPrice();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(via.sdj3.sep_t3.protobuf.PostCreationGrpcDto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code PostCreationGrpcDto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PostCreationGrpcDto)
      via.sdj3.sep_t3.protobuf.PostCreationGrpcDtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return via.sdj3.sep_t3.protobuf.PostGrpcModel.internal_static_PostCreationGrpcDto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return via.sdj3.sep_t3.protobuf.PostGrpcModel.internal_static_PostCreationGrpcDto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.class, via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.Builder.class);
    }

    // Construct using via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      description_ = "";

      location_ = "";

      categories_ = "";

      sellerId_ = 0;

      picture_ = "";

      price_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return via.sdj3.sep_t3.protobuf.PostGrpcModel.internal_static_PostCreationGrpcDto_descriptor;
    }

    @java.lang.Override
    public via.sdj3.sep_t3.protobuf.PostCreationGrpcDto getDefaultInstanceForType() {
      return via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.getDefaultInstance();
    }

    @java.lang.Override
    public via.sdj3.sep_t3.protobuf.PostCreationGrpcDto build() {
      via.sdj3.sep_t3.protobuf.PostCreationGrpcDto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public via.sdj3.sep_t3.protobuf.PostCreationGrpcDto buildPartial() {
      via.sdj3.sep_t3.protobuf.PostCreationGrpcDto result = new via.sdj3.sep_t3.protobuf.PostCreationGrpcDto(this);
      result.description_ = description_;
      result.location_ = location_;
      result.categories_ = categories_;
      result.sellerId_ = sellerId_;
      result.picture_ = picture_;
      result.price_ = price_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof via.sdj3.sep_t3.protobuf.PostCreationGrpcDto) {
        return mergeFrom((via.sdj3.sep_t3.protobuf.PostCreationGrpcDto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(via.sdj3.sep_t3.protobuf.PostCreationGrpcDto other) {
      if (other == via.sdj3.sep_t3.protobuf.PostCreationGrpcDto.getDefaultInstance()) return this;
      if (!other.getDescription().isEmpty()) {
        description_ = other.description_;
        onChanged();
      }
      if (!other.getLocation().isEmpty()) {
        location_ = other.location_;
        onChanged();
      }
      if (!other.getCategories().isEmpty()) {
        categories_ = other.categories_;
        onChanged();
      }
      if (other.getSellerId() != 0) {
        setSellerId(other.getSellerId());
      }
      if (!other.getPicture().isEmpty()) {
        picture_ = other.picture_;
        onChanged();
      }
      if (other.getPrice() != 0) {
        setPrice(other.getPrice());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      via.sdj3.sep_t3.protobuf.PostCreationGrpcDto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (via.sdj3.sep_t3.protobuf.PostCreationGrpcDto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object description_ = "";
    /**
     * <code>string description = 1;</code>
     * @return The description.
     */
    public java.lang.String getDescription() {
      java.lang.Object ref = description_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        description_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string description = 1;</code>
     * @return The bytes for description.
     */
    public com.google.protobuf.ByteString
        getDescriptionBytes() {
      java.lang.Object ref = description_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        description_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string description = 1;</code>
     * @param value The description to set.
     * @return This builder for chaining.
     */
    public Builder setDescription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      description_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string description = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDescription() {
      
      description_ = getDefaultInstance().getDescription();
      onChanged();
      return this;
    }
    /**
     * <code>string description = 1;</code>
     * @param value The bytes for description to set.
     * @return This builder for chaining.
     */
    public Builder setDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      description_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object location_ = "";
    /**
     * <code>string location = 2;</code>
     * @return The location.
     */
    public java.lang.String getLocation() {
      java.lang.Object ref = location_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        location_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string location = 2;</code>
     * @return The bytes for location.
     */
    public com.google.protobuf.ByteString
        getLocationBytes() {
      java.lang.Object ref = location_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        location_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string location = 2;</code>
     * @param value The location to set.
     * @return This builder for chaining.
     */
    public Builder setLocation(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      location_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string location = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLocation() {
      
      location_ = getDefaultInstance().getLocation();
      onChanged();
      return this;
    }
    /**
     * <code>string location = 2;</code>
     * @param value The bytes for location to set.
     * @return This builder for chaining.
     */
    public Builder setLocationBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      location_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object categories_ = "";
    /**
     * <code>string categories = 3;</code>
     * @return The categories.
     */
    public java.lang.String getCategories() {
      java.lang.Object ref = categories_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        categories_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string categories = 3;</code>
     * @return The bytes for categories.
     */
    public com.google.protobuf.ByteString
        getCategoriesBytes() {
      java.lang.Object ref = categories_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        categories_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string categories = 3;</code>
     * @param value The categories to set.
     * @return This builder for chaining.
     */
    public Builder setCategories(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      categories_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string categories = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearCategories() {
      
      categories_ = getDefaultInstance().getCategories();
      onChanged();
      return this;
    }
    /**
     * <code>string categories = 3;</code>
     * @param value The bytes for categories to set.
     * @return This builder for chaining.
     */
    public Builder setCategoriesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      categories_ = value;
      onChanged();
      return this;
    }

    private int sellerId_ ;
    /**
     * <code>int32 sellerId = 4;</code>
     * @return The sellerId.
     */
    @java.lang.Override
    public int getSellerId() {
      return sellerId_;
    }
    /**
     * <code>int32 sellerId = 4;</code>
     * @param value The sellerId to set.
     * @return This builder for chaining.
     */
    public Builder setSellerId(int value) {
      
      sellerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 sellerId = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSellerId() {
      
      sellerId_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object picture_ = "";
    /**
     * <code>string picture = 5;</code>
     * @return The picture.
     */
    public java.lang.String getPicture() {
      java.lang.Object ref = picture_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        picture_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string picture = 5;</code>
     * @return The bytes for picture.
     */
    public com.google.protobuf.ByteString
        getPictureBytes() {
      java.lang.Object ref = picture_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        picture_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string picture = 5;</code>
     * @param value The picture to set.
     * @return This builder for chaining.
     */
    public Builder setPicture(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      picture_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string picture = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPicture() {
      
      picture_ = getDefaultInstance().getPicture();
      onChanged();
      return this;
    }
    /**
     * <code>string picture = 5;</code>
     * @param value The bytes for picture to set.
     * @return This builder for chaining.
     */
    public Builder setPictureBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      picture_ = value;
      onChanged();
      return this;
    }

    private int price_ ;
    /**
     * <code>int32 price = 6;</code>
     * @return The price.
     */
    @java.lang.Override
    public int getPrice() {
      return price_;
    }
    /**
     * <code>int32 price = 6;</code>
     * @param value The price to set.
     * @return This builder for chaining.
     */
    public Builder setPrice(int value) {
      
      price_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 price = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearPrice() {
      
      price_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:PostCreationGrpcDto)
  }

  // @@protoc_insertion_point(class_scope:PostCreationGrpcDto)
  private static final via.sdj3.sep_t3.protobuf.PostCreationGrpcDto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new via.sdj3.sep_t3.protobuf.PostCreationGrpcDto();
  }

  public static via.sdj3.sep_t3.protobuf.PostCreationGrpcDto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PostCreationGrpcDto>
      PARSER = new com.google.protobuf.AbstractParser<PostCreationGrpcDto>() {
    @java.lang.Override
    public PostCreationGrpcDto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PostCreationGrpcDto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PostCreationGrpcDto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PostCreationGrpcDto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public via.sdj3.sep_t3.protobuf.PostCreationGrpcDto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

