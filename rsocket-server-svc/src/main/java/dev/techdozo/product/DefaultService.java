package dev.techdozo.product;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dev.techdozo.product.api.ProductService;
import dev.techdozo.product.api.Resources;
import dev.techdozo.product.api.Resources.GetProductRequest;
import dev.techdozo.product.api.Resources.GetProductResponse;
import io.netty.buffer.ByteBuf;
import reactor.core.publisher.Mono;

@Component
public class DefaultService implements ProductService {
	private final List<String> products = Arrays.asList("Apple", "Samsung", "Nokia", "Pixel", "LG");
	private final Random random = new Random();
	private static Logger log = LoggerFactory.getLogger(DefaultService.class);

	@Override
	public Mono<GetProductResponse> getProduct(GetProductRequest message, ByteBuf metadata) {
		String productId = message.getProductId();
		log.info("RPC received, id {}", productId);
		Resources.GetProductResponse productApiResponse = Resources.GetProductResponse.newBuilder()
				.setName(products.get(random.nextInt(products.size())))
				.setDescription("Product Description " + productId).setPrice(random.nextDouble() * 100).build();
		log.info("Returned id {}", productId);
		return Mono.just(productApiResponse);
	}

}