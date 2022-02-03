package dev.techdozo.product.api;

/**
 */
@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: service.proto")
public interface ProductService {
  String SERVICE = "product.ProductService";
  String METHOD_GET_PRODUCT = "getProduct";
  String ROUTE_GET_PRODUCT = SERVICE + "." + METHOD_GET_PRODUCT;

  /**
   */
  reactor.core.publisher.Mono<dev.techdozo.product.api.Resources.GetProductResponse> getProduct(dev.techdozo.product.api.Resources.GetProductRequest message, io.netty.buffer.ByteBuf metadata);
}
