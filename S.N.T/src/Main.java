/**
 * 
 * @author Sam

 *
 * Sam's Networking Tool - CLI Version
 */

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.SocketException;


public class Main {

	public Main(int choice, String value) {
		switch (choice) {
		case 1:
			NSLookup(value);
			break;
		case 2:
			portScanner(value);
			break;
		case 3:
			ping(value);
			break;
		}
	}

	private void NSLookup(String value) {
		try {
			InetAddress addressHost = InetAddress.getByName(value);
			String domain = addressHost.getHostName();
			// System.out.println("The host name was: " + hostName);
			System.out.println("The IP address for host " + domain + ": " + addressHost.getHostAddress());

		} catch (UnknownHostException ex) {
			System.out.println("Can't find host");
		}
	}
	
	// Method code from javacodex.com
	private void portScanner(String value) {
		// check all 1 -> 65,535 ports
		for (int port = 1; port <= 65535; port++) {
	         try {
	            Socket socket = new Socket();
	            socket.connect(new InetSocketAddress("localhost", port), 1000);
	            socket.close();
	            System.out.println("Port " + port + " is open");
	        } catch (Exception ex) {
	        }
	      }
	   }
	
	private void ping(String value) {
		try {
			String ipAddress = value;
			InetAddress inet = InetAddress.getByName(ipAddress);
			System.out.println("Pinging " + ipAddress);
			if (inet.isReachable(6000)) {
				System.out.println(ipAddress + " is successful in connectivity.");
			}
			else {
				System.out.println(ipAddress + " is unsuccessful in connectivity");
			}
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}

	// choice to user, provide testing for args
	public static void main(String[] args) {
		System.out.println("1 FOR NSLOOKUP\n ");
		new Main(1, "cnn.com");
		new Main(3, "127.0.0.1");
	}
}