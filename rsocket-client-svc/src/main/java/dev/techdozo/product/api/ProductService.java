//package dev.techdozo.product.api;
//
//import lombok.extern.slf4j.Slf4j;
//import java.util.List;
//import java.util.Random;
//
//@Slf4j
//public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {
//
//  private final List<String> products = List.of("Apple", "Samsung", "Nokia", "Pixel", "LG");
//  private final Random random = new Random();
//
//  @Override
//  public void getProduct(
//      Resources.GetProductRequest request,
//      StreamObserver<Resources.GetProductResponse> responseObserver) {
//
//    var productId = request.getProductId();
//
//    log.info("RPC received, id {}", productId);
//
//    Resources.GetProductResponse productApiResponse =
//        Resources.GetProductResponse.newBuilder()
//            .setName(products.get(random.nextInt(products.size())))
//            .setDescription("Product Description " + productId)
//            .setPrice(random.nextDouble() * 100)
//            .build();
//    responseObserver.onNext(productApiResponse);
//    responseObserver.onCompleted();
//
//    log.info("Returned id {}", productId);
//  }
//}
