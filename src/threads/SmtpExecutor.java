package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class SmtpExecutor extends Thread {
 private Socket __socket;

 private BufferedReader __inStream;
 private PrintWriter __outStream;

 public SmtpExecutor(Socket socket, Logger log) throws IOException {
    __socket = socket;
    __inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    __outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
 }

 @Override
 public void run() {
    println("220 SMTP Server TinyMailServer ready and waiting.");

    try {
       do {
          String input = "";

          try {
             input = __inStream.readLine();

             if (input == null)
                break;
          }
          catch (Exception e) {
             e.printStackTrace();
          }

          /*
* Parse commands. For now we just ignore the command
* and say all is ok.
*/
          StringTokenizer tokenizer = new StringTokenizer(input, " :");
          String command = "";

          command = tokenizer.nextToken().toUpperCase();

          if (command.compareTo("DATA") == 0) {
             doCommand_DATA();
             continue;
          }

          if (command.compareTo("RCPT") == 0) {
             println("250 OK, RCPT received");
             continue;
          }

          if ((command.compareTo("MAIL") == 0) || (command.compareTo("SEND") == 0)) {
             println("250 OK, MAIL/SEND received");
             continue;
          }

          if ((command.compareTo("HELO") == 0) || (command.compareTo("EHLO") == 0)) {
             println ("250 OK, Howdy ya'll!");
             continue;
          }

          if (command.compareTo("RSET") == 0) {
             println("250 OK, RSET received");
             continue;
          }

          if (command.compareTo("QUIT") == 0) {
             println("221 SMTP Server TinyMailServer closing transmission channel");
             break;
          }
       } while (true);
    }
    catch (Exception e) {
       e.printStackTrace();
    }
    finally {
       try {
          __socket.close();
       }
       catch (Exception e) {
       }
    }
 }

 private void println(String s) {
    __outStream.println(s);
    __outStream.flush();

    System.out.println(s);
 }

 private void doCommand_DATA() throws IOException {
    println("354 Send me data yo. End with .");
    System.out.println("Mail:");

    do {
       String input = __inStream.readLine();
       if (input.equals(".")) {
          println("250 OK");
          break;
       }
       else {
          System.out.println(input);
       }
    } while (true);
 }
}