/**
 * 
 * @author Sam

 *
 * Sam's Networking Tool - CLI Version
 */


import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
	
	public Main(int choice, String value) {
		switch(choice)
		{
		case 1: 
			
			NSLookup(value); break;
		}
	}

	// currently only domain -> ip. Need to do reverse also.
	private void NSLookup(String value) {
		try {
			InetAddress addressHost = InetAddress.getByName(value);
			String domain = addressHost.getHostName();
			//System.out.println("The host name was: " + hostName);
            System.out.println("The IP address for host " + domain + ": " + addressHost.getHostAddress());
            
        } catch(UnknownHostException ex) {
            
            System.out.println("Can't find host");
        }
    }
		
	
	public static void main(String[] args) {
		System.out.println("1 FOR NSLOOKUP\n ");
        new Main(1, "cnn.com");
    }
	
	
}
