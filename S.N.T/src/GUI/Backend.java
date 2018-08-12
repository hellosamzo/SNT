package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Backend {

	// ping method
	public static void ping(String value) {
		try {
			String ipAddress = value;
			InetAddress inet = InetAddress.getByName(ipAddress);
			if (inet.isReachable(6000)) {
				GUI.pingOut.setText(" ");
				GUI.pingOut.appendText(ipAddress + " is successful in connectivity");
			} else {
				GUI.pingOut.setText(" ");
				GUI.pingOut.appendText(ipAddress + " is unsuccessful in connectivity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void NSLookup(String value) {
		try {
			InetAddress addressHost = InetAddress.getByName(value);
			String domain = addressHost.getHostName();
			System.out.println("HOST NAME: " + domain);
			GUI.nsOut.setText(" ");
			GUI.nsOut.appendText("DNS: " + domain + "," + " IP: " + addressHost.getHostAddress());
			System.out.println("IP ADDRESS: " + addressHost.getHostAddress());
		} catch (UnknownHostException ex) {
			GUI.nsOut.setText(" ");
			GUI.nsOut.appendText("Can't find host");
			System.out.println("Can't find host");
		}
	}

	// need to fix script to work with inputs from java program
	public static void pyWebScrapper() throws IOException {
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
			GUI.scrapOut.setText(" ");
			GUI.scrapOut.appendText(line);
		}
	}

	// Method code from javacodex.com
	public static void portScanner(String value) {
		// check all 1 -> 65,535 ports
		for (int port = 1; port <= 10; port++) {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress("localhost", port), 1000);
				socket.close();
				System.out.println("Port " + port + " is open");
				GUI.portOut.setText(" ");
				GUI.portOut.appendText("Port " + port + " is open");
			} catch (Exception ex) {
				String ex1 = ex.toString();
				GUI.portOut.appendText(ex1);
			}
		}
	}

}
