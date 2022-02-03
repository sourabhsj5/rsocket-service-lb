package dev.techdozo.product;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.rsocket.RSocketRequester;

import io.rsocket.RSocket;
import io.rsocket.loadbalance.LoadbalanceTarget;
import io.rsocket.loadbalance.RoundRobinLoadbalanceStrategy;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class AppConfig {

  @Value("${server_host}")
  private String serverHost;
  
  @Value("${server_port}")
  private int serverPort;

  @Value("${num_thread}")
  private int numThread;
  private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public Flux<List<LoadbalanceTarget>> targets(){
      return Mono.fromSupplier(()-> getServers())
              .repeatWhen(longFlux -> longFlux.delayElements(Duration.ofSeconds(2)))
              .map(this::toLoadBalanceTarget);
  }

  private List<RSocketServerInstance> getServers() {
	  List<RSocketServerInstance> servers = new ArrayList<>();
	  InetAddress[] ipaddresses;
		try {
			ipaddresses = InetAddress.getAllByName("rsocket-server-service");
			for (InetAddress ipaddress:ipaddresses) {
				System.out.println("ipaddress: " + ipaddress.getHostAddress());
				logger.error("ipaddress: " + ipaddress.getHostAddress());
				RSocketServerInstance rsi = new RSocketServerInstance();
				rsi.setHost(ipaddress.getHostAddress());
				rsi.setPort(serverPort);
				servers.add(rsi);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.error("error occured while fetching ipaddresses for rsocket-server-service", e);
		}	
	return servers;
}

private List<LoadbalanceTarget> toLoadBalanceTarget(List<RSocketServerInstance> rSocketServers){
      return rSocketServers.stream()
              .map(server -> LoadbalanceTarget.from(server.getHost() + server.getPort(), TcpClientTransport.create(server.getHost(), server.getPort())))
              .collect(Collectors.toList());
  }
@Bean
public RSocketRequester rSocketClient(RSocketRequester.Builder builder, Flux<List<LoadbalanceTarget>> targetFlux){
	return builder.transports(targetFlux, new RoundRobinLoadbalanceStrategy());
}
@Autowired
RSocketRequester rSocketRequester;
/*
 * @Bean
 * 
 * @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) public RSocket
 * rsocket() { logger.error("serverHost: " + serverHost);
 * System.out.println("serverHost: " + serverHost); logger.error("serverPort: "
 * + serverPort); System.out.println("serverPort: " + serverPort); RSocket rs =
 * rSocketRequester.rsocketClient().source().block();
 * logger.error("rsocket instance " + rs);
 * System.out.println("rsocket instance " + rs); return rs; // return
 * RSocketConnector.create().payloadDecoder(PayloadDecoder.ZERO_COPY) //
 * .keepAlive(Duration.ofSeconds(1), Duration.ofSeconds(5)) //
 * .connect(TcpClientTransport.create(serverHost, serverPort)).cache().block();
 * 
 * // return ManagedChannelBuilder.forTarget(serverHost) //
 * .defaultLoadBalancingPolicy("round_robin") // .usePlaintext() // .build(); }
 */

  @Bean
  public ExecutorService executorService() {
    return Executors.newFixedThreadPool(numThread);
  }

//  @Bean
//  public ProductServiceClient productServiceClient() {
//    return new ProductServiceClient(rsocket);
//  }
}
