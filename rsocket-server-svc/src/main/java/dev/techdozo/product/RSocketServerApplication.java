package dev.techdozo.product;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import dev.techdozo.product.api.ProductService;
import dev.techdozo.product.api.ProductServiceServer;
import io.rsocket.core.RSocketServer;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootApplication
@Configuration
public class RSocketServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(RSocketServerApplication.class);
			
	@Value("${server_host}")
	private String serverHost;

	public ProductService productService() {
		return new DefaultService();
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			SpringApplication.run(RSocketServerApplication.class, args);
			logger.error("Starting rsocket server on host " + inetAddress.getHostAddress());
			System.out.println("Starting rsocket server on host " + inetAddress.getHostAddress());
			RSocketServer.create().payloadDecoder(PayloadDecoder.ZERO_COPY)
					.acceptor((connectionSetupPayload,
							rSocket) -> Mono.just(new ProductServiceServer(new DefaultService(), Optional.empty(),
									Optional.empty(), Optional.empty())))
					.bind(TcpServerTransport.create(inetAddress.getHostAddress(), 6565)).block();
			Thread.currentThread().join();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
