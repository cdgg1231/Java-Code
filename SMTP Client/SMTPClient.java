/*
 * David Gaytan & Mahdi Husayni
 */

import java.io.*;
import java.net.*;

public class SMTPClient {
    public static void main(String[] args) throws IOException {

          //declare variables to communicate with the server 
         Socket smtpSocket        = null;
         PrintWriter socketOut   = null;
         BufferedReader socketIn = null;

        String DnsOrIP = "";
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

    try {
        // Step 1: Prompt user for SMTP server hostname
        System.out.print("Enter SMTP server hostname or IP address: ");
        DnsOrIP = userInput.readLine();
        System.out.println("");

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();

        // Step 2: Build TCP connection to SMTP server
        smtpSocket = new Socket(DnsOrIP, 5070);
        System.out.println("RTT connection established in " + (end - start) + " milliseconds");

        socketOut = new PrintWriter(smtpSocket.getOutputStream(), true);
        socketIn = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));

        // Step 3: Wait for and display "220" response from server
        String response = socketIn.readLine();
        System.out.println("Response from SMTP server: " + response);

    } catch (UnknownHostException e) {
        System.err.println("Don't know about host: " + DnsOrIP);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to: " + DnsOrIP);
    }

    
         //             Prompt for User Details 
        
         String from;
         do {
            System.out.print("Enter sender's email address: ");
            from = userInput.readLine().trim();

        } while (from.isEmpty());   

          
            System.out.print("Enter receiver's email address: ");
            String to = userInput.readLine();

          
            System.out.print("Enter email subject: ");
            String subject = userInput.readLine();

           
            System.out.println("Enter email contents (type '.' on a line by itself to end):");
            String line;
            StringBuilder contentsBuilder = new StringBuilder();
            while ((line = userInput.readLine()) != null) {
                if (line.equals(".")) {
                    break;
                }
                contentsBuilder.append(line).append("\r\n");
            }
            String contents = contentsBuilder.toString();


            String smtpServerName = DnsOrIP;
            String fromUser;
            String fromServer;

            fromServer = socketIn.readLine();
        System.out.println("The 220 response from the server is: " + fromServer + "\n");

            // Step 4: Send email message

            try {
                //Helo CMD
           long startTime = System.currentTimeMillis();
           System.out.println("Hello\r\n " + smtpServerName);
           socketOut.println("HELO " + DnsOrIP);
           fromServer = socketIn.readLine();
           System.out.flush();
           String response = socketIn.readLine();
           long endTime = System.currentTimeMillis();
           System.out.println("Response from SMTP server: " + response);
           System.out.println("RTT = " + (endTime - startTime) + " ms");
        

                //MAIL FROM CMD

                         // Send MAIL FROM command
             startTime = System.currentTimeMillis();
             System.out.println("MAIL FROM: <" + from + ">\r\n");
             System.out.flush();
             response = socketIn.readLine();
             endTime = System.currentTimeMillis();
             System.out.println("Response from SMTP server: " + response);
             System.out.println("RTT = " + (endTime - startTime) + " ms");
 
             // Send RCPT TO command
             startTime = System.currentTimeMillis();
             System.out.println("RCPT TO: <" + to + ">\r\n");
             System.out.flush();
             response = socketIn.readLine();
             endTime = System.currentTimeMillis();
             System.out.println("Response from SMTP server: " + response);
             System.out.println("RTT = " + (endTime - startTime) + " ms");
 
             // Send DATA command
             startTime = System.currentTimeMillis();
             System.out.println("DATA\r\n");
             System.out.flush();
             response = socketIn.readLine();
             endTime = System.currentTimeMillis();
             System.out.println("Response from SMTP server: " + response);
             System.out.println("RTT = " + (endTime - startTime) + " ms");
 
             // Send email message
             startTime = System.currentTimeMillis();
             System.out.println("From: <" + from + ">\r\n");
             System.out.println("To: <" + to + ">\r\n");
             System.out.println("Subject: " + subject + "\r\n");
             System.out.println();
             System.out.println(contents + "\r\n.\r\n");
             System.out.println(".");
             System.out.flush();
             response = socketIn.readLine();
             endTime = System.currentTimeMillis();
             System.out.println("Response from SMTP server: " + response);
             System.out.println("RTT = " + (endTime - startTime) + " ms");
        } // try close

        finally{
             System.out.println("If you wish to continue enter 'yes'. If you do not wish to continue, press the ENTER key: ");
             fromUser = userInput.readLine();
 
             if (!fromUser.equals("yes")) {
             }
 
             // Server is timed-out
             socketOut.println("Done with one request");
         }
 
         socketOut.close();
         socketIn.close();
         userInput.close();
         smtpSocket.close();

        
        
        }
}
