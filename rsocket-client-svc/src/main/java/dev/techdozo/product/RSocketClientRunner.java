package dev.techdozo.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import dev.techdozo.product.api.ProductServiceClient;
import dev.techdozo.product.api.Resources;
import dev.techdozo.product.api.Resources.GetProductRequest;
import dev.techdozo.product.api.Resources.GetProductResponse;
import io.rsocket.RSocket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Configuration
public class RSocketClientRunner implements ApplicationRunner {

//	@Autowired
	private ExecutorService executorService;
	//@Autowired
//	private ProductServiceClient productServiceClient;
	@Autowired
	private RSocketRequester rsocketRequester;
//	@Value("${call_iteration}")
//	private int iteration;

	private static Logger log = LoggerFactory.getLogger(RSocketClientRunner.class);

	@SneakyThrows
	@Override
	public void run(ApplicationArguments args) {
		long startTime = System.currentTimeMillis();
		log.info("Calling Server..");
		executorService = Executors.newFixedThreadPool(2);
		List<Future<Resources.GetProductResponse>> futures = new ArrayList<>();

		for (int i = 0; i < 1000000; i++) {
			ProductServiceRunnable runnable = new ProductServiceRunnable();
			runnable.setI(i);
			futures.add(executorService.submit(runnable));
		}

		for (Future<Resources.GetProductResponse> future : futures) {
			GetProductResponse productResponse;
			try {
				productResponse = future.get();
				log.info("Received product, description {}", productResponse.getDescription());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		log.info("Finished calling server, total time {} ", System.currentTimeMillis() - startTime);
	}

	class ProductServiceRunnable implements Callable<GetProductResponse> {
		private int i;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		@Override
		public GetProductResponse call() throws Exception {
			RSocket rsocket = rsocketRequester.rsocketClient().source().block();
			ProductServiceClient productServiceClient = new ProductServiceClient(rsocket);
			GetProductRequest request = GetProductRequest.newBuilder().setProductId(String.valueOf(i))
					.setProductId("Product" + i).build();
			return productServiceClient.getProduct(request, null).block();
		}

	}
}
