/**
 * 
 * @author Sam

 *
 * Sam's Networking Tool - CLI Version
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//import org.apache.log4j.*;


public class Main {

	// change to command line args..
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

	public Main(String choice) {
		switch (choice) {
		case "m":
			manual();
			break;
		}
	}
	
	public Main(int choice) throws IOException {
		switch (choice) {
		case 4:
			pyWebScrapper();
			break;
		}
	}

	private void pyWebScrapper() throws IOException {
		ProcessBuilder builder = new ProcessBuilder(
				// need to change to work on local scripts directory once script complete
	            "cmd.exe", "/c", "cd C:\\Users\\User\\git\\SNT\\S.N.T\\Scripts && main.py");
	        builder.redirectErrorStream(true);
	        Process p = builder.start();
	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line;
	        while (true) {
	            line = r.readLine();
	            if (line == null) { 
	            	break; 
	            	}
	            System.out.println(line);
	     }
	}

	private void NSLookup(String value) {
		try {
			InetAddress addressHost = InetAddress.getByName(value);
			String domain = addressHost.getHostName();
			System.out.println("HOST NAME: " + domain);
			System.out.println("IP ADDRESS: " + addressHost.getHostAddress());
		} catch (UnknownHostException ex) {
			System.out.println("Can't find host");
		}
	}
	
	// Method code from javacodex.com
	private void portScanner(String value) {
		// check all 1 -> 65,535 ports
		for (int port = 1; port <= 65535; port++) {
			// to do - place log4j debug here
	         try {
	            Socket socket = new Socket();
	            socket.connect(new InetSocketAddress("localhost", port), 1000);
	            socket.close();
	            System.out.println("Port " + port + " is open");
	        } 
	         catch (Exception ex) {
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

	private void manual() {
		System.out.println("\nNS LOOKUP - Enter either an IP Address or a host name to retrieve both back.");
		System.out.println("PING - Test connectivity between your machine and the machine you're pinging.");
	}
	
	// choice to user, provide testing for args
	public static void main(String[] args) throws IOException {
		System.out.println("SNT V1.1\n\nOptions:");
		System.out.println("1, IP/DOMAIN - NSLOOKUP");
		System.out.println("2, IP - PORT SCANNER");
		System.out.println("3, IP/DOMAIN - PING");
		System.out.println("4, URL - PYTHON WEB CRAWLER");
		System.out.println("M - Manual\n");
		//new Main(1, "cnn.com");
		new Main(4);
		//new Main(2, "localhost");
		//new Main(3, "8.8.8.8");
	}
}