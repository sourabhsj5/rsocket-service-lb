package dev.techdozo.product.api;

@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: service.proto")
@io.rsocket.rpc.annotations.internal.Generated(
    type = io.rsocket.rpc.annotations.internal.ResourceType.CLIENT,
    idlClass = ProductService.class)
public final class ProductServiceClient implements ProductService {
  private final io.rsocket.RSocket rSocket;
  private final io.rsocket.ipc.MetadataEncoder metadataEncoder;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<dev.techdozo.product.api.Resources.GetProductResponse>, ? extends org.reactivestreams.Publisher<dev.techdozo.product.api.Resources.GetProductResponse>> getProduct;
  private final java.util.function.Function<java.util.Map<String, String>, java.util.function.Function<? super org.reactivestreams.Publisher<dev.techdozo.product.api.Resources.GetProductResponse>, ? extends org.reactivestreams.Publisher<dev.techdozo.product.api.Resources.GetProductResponse>>> getProductTrace;

  public ProductServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
    this.metadataEncoder = new io.rsocket.ipc.encoders.DefaultMetadataEncoder(io.netty.buffer.ByteBufAllocator.DEFAULT);
    this.getProduct = java.util.function.Function.identity();
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace();
  }


  public ProductServiceClient(io.rsocket.RSocket rSocket, io.rsocket.ipc.MetadataEncoder metadataEncoder) {
    this.rSocket = rSocket;
    this.metadataEncoder = metadataEncoder;
    this.getProduct = java.util.function.Function.identity();
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace();
  }

  public ProductServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.metadataEncoder = new io.rsocket.ipc.encoders.DefaultMetadataEncoder(io.netty.buffer.ByteBufAllocator.DEFAULT);
    this.getProduct = io.rsocket.ipc.metrics.Metrics.timed(registry, "rsocket.client", "service", ProductService.SERVICE, "method", ProductService.METHOD_GET_PRODUCT);
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace();
  }

  public ProductServiceClient(io.rsocket.RSocket rSocket, io.rsocket.ipc.MetadataEncoder metadataEncoder, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.metadataEncoder = metadataEncoder;
    this.getProduct = io.rsocket.ipc.metrics.Metrics.timed(registry, "rsocket.client", "service", ProductService.SERVICE, "method", ProductService.METHOD_GET_PRODUCT);
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace();
  }


  public ProductServiceClient(io.rsocket.RSocket rSocket, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.metadataEncoder = new io.rsocket.ipc.encoders.DefaultMetadataEncoder(io.netty.buffer.ByteBufAllocator.DEFAULT);
    this.getProduct = java.util.function.Function.identity();
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace(tracer, ProductService.METHOD_GET_PRODUCT, io.rsocket.ipc.tracing.Tag.of("rsocket.service", ProductService.SERVICE), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.version", ""));
  }


  public ProductServiceClient(io.rsocket.RSocket rSocket, io.rsocket.ipc.MetadataEncoder metadataEncoder, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.metadataEncoder = metadataEncoder;
    this.getProduct = java.util.function.Function.identity();
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace(tracer, ProductService.METHOD_GET_PRODUCT, io.rsocket.ipc.tracing.Tag.of("rsocket.service", ProductService.SERVICE), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.version", ""));
  }


  public ProductServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.metadataEncoder = new io.rsocket.ipc.encoders.DefaultMetadataEncoder(io.netty.buffer.ByteBufAllocator.DEFAULT);
    this.getProduct = io.rsocket.ipc.metrics.Metrics.timed(registry, "rsocket.client", "service", ProductService.SERVICE, "method", ProductService.METHOD_GET_PRODUCT);
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace(tracer, ProductService.METHOD_GET_PRODUCT, io.rsocket.ipc.tracing.Tag.of("rsocket.service", ProductService.SERVICE), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.version", ""));
  }


  public ProductServiceClient(io.rsocket.RSocket rSocket, io.rsocket.ipc.MetadataEncoder metadataEncoder, io.micrometer.core.instrument.MeterRegistry registry, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.metadataEncoder = metadataEncoder;
    this.getProduct = io.rsocket.ipc.metrics.Metrics.timed(registry, "rsocket.client", "service", ProductService.SERVICE, "method", ProductService.METHOD_GET_PRODUCT);
    this.getProductTrace = io.rsocket.ipc.tracing.Tracing.trace(tracer, ProductService.METHOD_GET_PRODUCT, io.rsocket.ipc.tracing.Tag.of("rsocket.service", ProductService.SERVICE), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.version", ""));
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = dev.techdozo.product.api.Resources.GetProductResponse.class)
  public reactor.core.publisher.Mono<dev.techdozo.product.api.Resources.GetProductResponse> getProduct(dev.techdozo.product.api.Resources.GetProductRequest message) {
    return getProduct(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = dev.techdozo.product.api.Resources.GetProductResponse.class)
  public reactor.core.publisher.Mono<dev.techdozo.product.api.Resources.GetProductResponse> getProduct(dev.techdozo.product.api.Resources.GetProductRequest message, io.netty.buffer.ByteBuf metadata) {
  java.util.Map<String, String> map = new java.util.HashMap<>();
    return reactor.core.publisher.Mono.defer(new java.util.function.Supplier<reactor.core.publisher.Mono<io.rsocket.Payload>>() {
      @java.lang.Override
      public reactor.core.publisher.Mono<io.rsocket.Payload> get() {
        final io.netty.buffer.ByteBuf data = serialize(message);
//        final io.netty.buffer.ByteBuf metadataBuf = metadataEncoder.encode(metadata, new io.rsocket.ipc.tracing.SimpleSpanContext(map), ProductService.SERVICE, ProductService.METHOD_GET_PRODUCT);
//        metadata.release();
			return rSocket.requestResponse(io.rsocket.util.ByteBufPayload.create(data/* , metadataBuf */));
      }
    }).map(deserializer(dev.techdozo.product.api.Resources.GetProductResponse.parser())).transform(getProduct).transform(getProductTrace.apply(map));
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    int length = message.getSerializedSize();
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
      byteBuf.writerIndex(length);
      return byteBuf;
    } catch (Throwable t) {
      byteBuf.release();
      throw new RuntimeException(t);
    }
  }

  private static <T> java.util.function.Function<io.rsocket.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new java.util.function.Function<io.rsocket.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.Payload payload) {
        try {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return parser.parseFrom(is);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } finally {
          payload.release();
        }
      }
    };
  }
}
