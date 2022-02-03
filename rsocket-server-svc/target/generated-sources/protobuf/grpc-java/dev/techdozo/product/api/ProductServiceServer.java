package dev.techdozo.product.api;

@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: service.proto")
@io.rsocket.rpc.annotations.internal.Generated(
    type = io.rsocket.rpc.annotations.internal.ResourceType.SERVICE,
    idlClass = ProductService.class)
@javax.inject.Named(
    value ="ProductServiceServer")
public final class ProductServiceServer extends io.rsocket.rpc.AbstractRSocketService {
  private final ProductService service;
  private final io.rsocket.ipc.MetadataDecoder metadataDecoder;
  private final io.opentracing.Tracer tracer;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.Payload>, ? extends org.reactivestreams.Publisher<io.rsocket.Payload>> getProduct;
  private final java.util.function.Function<io.opentracing.SpanContext, java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.Payload>, ? extends org.reactivestreams.Publisher<io.rsocket.Payload>>> getProductTrace;
  @javax.inject.Inject
  public ProductServiceServer(ProductService service, java.util.Optional<io.rsocket.ipc.MetadataDecoder> metadataDecoder, java.util.Optional<io.micrometer.core.instrument.MeterRegistry> registry, java.util.Optional<io.opentracing.Tracer> tracer) {
    this.service = service;
    if (!registry.isPresent()) {
      this.getProduct = java.util.function.Function.identity();
    } else {
      this.getProduct = io.rsocket.ipc.metrics.Metrics.timed(registry.get(), "rsocket.server", "service", ProductService.SERVICE, "method", ProductService.METHOD_GET_PRODUCT);
    }

    if (!tracer.isPresent()) {
      this.tracer = null;
      this.getProductTrace = (ignored) -> java.util.function.Function.identity();
    } else {
      this.tracer = tracer.get();
      this.getProductTrace = io.rsocket.ipc.tracing.Tracing.traceAsChild(this.tracer, ProductService.METHOD_GET_PRODUCT, io.rsocket.ipc.tracing.Tag.of("rsocket.service", ProductService.SERVICE), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.role", "server"), io.rsocket.ipc.tracing.Tag.of("rsocket.rpc.version", ""));
    }

    if (metadataDecoder.isPresent()) {
      this.metadataDecoder = metadataDecoder.get();
    } else {
      this.metadataDecoder = new io.rsocket.ipc.decoders.CompositeMetadataDecoder(this.tracer);
    }
  }

  @java.lang.Override
  public String getService() {
    return ProductService.SERVICE;
  }

  @java.lang.Override
  public Class<?> getServiceClass() {
    return service.getClass();
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<java.lang.Void> fireAndForget(io.rsocket.Payload payload) {
    return reactor.core.publisher.Mono.error(new UnsupportedOperationException("Fire And Forget is not implemented."));
  }

  reactor.core.publisher.Mono<Void> doDecodeAndHandleFireAndForget(
    io.rsocket.Payload payload,
    io.rsocket.ipc.MetadataDecoder.Metadata decoded
  ) throws java.lang.Exception {
    switch(decoded.route()) {
      default: {
        return reactor.core.publisher.Mono.error(new UnsupportedOperationException());
      }
    }
  }

  @java.lang.Override
  public reactor.core.publisher.Mono<io.rsocket.Payload> requestResponse(io.rsocket.Payload payload) {
    try {
      io.rsocket.ipc.MetadataDecoder.Metadata decoded = metadataDecoder.decode(payload.sliceMetadata());

      reactor.core.publisher.Mono<io.rsocket.Payload> response = this.doDecodeAndHandleRequestResponse(payload, decoded);

      payload.release();

      return response;
    } catch (Throwable t) {
      payload.release();
      return reactor.core.publisher.Mono.error(t);
    }
  }

  reactor.core.publisher.Mono<io.rsocket.Payload> doDecodeAndHandleRequestResponse(
    io.rsocket.Payload payload,
    io.rsocket.ipc.MetadataDecoder.Metadata decoded
  ) throws java.lang.Exception {
//    switch(decoded.route()) {
//      case ProductService.ROUTE_GET_PRODUCT: {
        return this.dogetProductRequestResponse(payload, decoded);
//      }
//      default: {
//        return reactor.core.publisher.Mono.error(new UnsupportedOperationException());
//      }
//    }
  }

  private reactor.core.publisher.Mono<io.rsocket.Payload> dogetProductRequestResponse(io.rsocket.Payload payload, io.rsocket.ipc.MetadataDecoder.Metadata decoded) throws java.lang.Exception {
    com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
    return service.getProduct(dev.techdozo.product.api.Resources.GetProductRequest.parseFrom(is), decoded.metadata()).map(serializer).transform(getProduct).transform(getProductTrace.apply(decoded.spanContext()));
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestStream(io.rsocket.Payload payload) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request Stream is not implemented."));
  }

  reactor.core.publisher.Flux<io.rsocket.Payload> doDecodeAndHandleRequestStream(
    io.rsocket.Payload payload,
    io.rsocket.ipc.MetadataDecoder.Metadata decoded
  ) throws java.lang.Exception {
    switch(decoded.route()) {
      default: {
        return reactor.core.publisher.Flux.error(new UnsupportedOperationException());
      }
    }
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestChannel(io.rsocket.Payload payload, reactor.core.publisher.Flux<io.rsocket.Payload> payloads) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request Channel is not implemented."));
  }

  @java.lang.Override
  public reactor.core.publisher.Flux<io.rsocket.Payload> requestChannel(org.reactivestreams.Publisher<io.rsocket.Payload> payloads) {
    return reactor.core.publisher.Flux.error(new UnsupportedOperationException("Request-Channel not implemented."));
  }

  @java.lang.Override
  public void selfRegister(io.rsocket.ipc.MutableRouter router) {
    router.withRequestResponseRoute(ProductService.ROUTE_GET_PRODUCT, this::dogetProductRequestResponse);
  }

  private static final java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload> serializer =
    new java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload>() {
      @java.lang.Override
      public io.rsocket.Payload apply(com.google.protobuf.MessageLite message) {
        int length = message.getSerializedSize();
        io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
        try {
          message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
          byteBuf.writerIndex(length);
          return io.rsocket.util.ByteBufPayload.create(byteBuf);
        } catch (Throwable t) {
          byteBuf.release();
          throw new RuntimeException(t);
        }
      }
    };

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
