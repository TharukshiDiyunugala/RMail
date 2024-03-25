package threads;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
 public static void main(String[] args) throws UnknownHostException, IOException {
    String address = "localhost";
    int port = 25;
    int maxRequestsInQueue = 10;

    try {
       System.out.format("Starting SMTP server at %s on port %s\n\n", address, port);
       System.out.println("Created by Adam Presley (c) 2010");
       System.out.println("Visit http://www.adampresley.com!!\n");
       SmtpServer server = new SmtpServer(address, port, maxRequestsInQueue);

       server.start();
    }
    catch (Exception e) {

    }
    
    
 }
}