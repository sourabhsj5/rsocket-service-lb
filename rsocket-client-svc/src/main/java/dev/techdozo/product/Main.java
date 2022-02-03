package dev.techdozo.product;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress[] ipaddresses = InetAddress.getAllByName("rsocket-server-service");
		for (InetAddress ipaddress:ipaddresses) {
			System.out.println(ipaddress.getHostAddress());
		}
	}
}
