package threads;

import java.net.*;
import java.util.logging.Logger;
import java.io.*;

public class SmtpServer extends Thread {
 private String address;
 private int port;
 private int maxRequestsInQueue;

 private ServerSocket connection;
 private Logger log;

 public SmtpServer(String Address, int Port, int MaxRequestsInQueue) throws UnknownHostException, IOException {
    address = Address;
    port = Port;
    maxRequestsInQueue = MaxRequestsInQueue;

    /*
* Prepare to bind to an address and port.
*/
    connection = new ServerSocket(port, maxRequestsInQueue, InetAddress.getByName(address));

    /*
* Start our thread's processing.
*/
    start();
 }

 @Override
 public void run() {
    try {
       System.out.println("SMTP Listener started.");

       do {
          /*
* Attempt to start listening for incoming connections.
*/
          try {
             System.out.println("Accepting connections.");
             Socket socket = connection.accept();

             Thread executor = new SmtpExecutor(socket, log);
             executor.start();
          }
          catch (IOException e) {
             System.out.format("An exception occured while attempting to listen for connections: %s", e.getMessage());
             e.printStackTrace();
             break;
          }

       } while (true);
    }
    catch (Throwable e) {
       System.out.format("An exception occured during the listener loop: %s", e.getMessage());
    }
 }
}