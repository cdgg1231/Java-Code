/*
 * David Gaytan - Mahdi Husayni
 * Homework 3
 * CS 3700
 * Dr. Zhu
 */

import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String[] args) throws IOException{

        //declare variables to communicate with the server 
        Socket tcpSocket        = null;
        PrintWriter socketOut   = null;
        BufferedReader socketIn = null;

        /*      REMOVE THE IF STATEMENT
         *   if (args.length != 1) {
             System.out.println("Usage: java TCPClient <hostname>");
             return;
        }
         */

        /*
        The IP address or DNS will come from the BufferReader which is null as a global 
        then it will equal the systemInput that user will enter
         */ 

         // Initialize empty string for IP_Address_Or_DNS
        String DNS_Or_IPServer = "";
        BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            // User enters IP or DNS
            System.out.print("Input the DNS name/ip of your HTTP Server: ");
            DNS_Or_IPServer = systemInput.readLine();
            System.out.println("");

            // Round-Trip Time (RTT) time for small packet to travel from client to server and back
            // Timer to count time needed for each RTT cycle
            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();

            
            tcpSocket = new Socket (DNS_Or_IPServer, 5070);
            System.out.println("RTT connection established in " + (end-start) + " milliseconds");

            socketOut = new PrintWriter(tcpSocket.getOutputStream(),true);
            socketIn = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
        }

        // catch error on DNS OR IP Address
        catch (UnknownHostException e) {
            System.err.println("Don't know that host: " + DNS_Or_IPServer);
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "  + DNS_Or_IPServer);
            System.exit(1);
        }

        String fromServer;
        String fromUser;

        boolean wishToContinue = true;

    

        while (wishToContinue){
            System.out.println("Enter HTTP method type (GET, HEAD, POST, PUT, DELETE): ");
            String httpMethodType = systemInput.readLine();

            System.out.println("Enter htm file name (ex. CS3700.htm): ");
            String htmFileName = systemInput.readLine();

            System.out.println("Enter the http version: ");
            String httpVersion = systemInput.readLine();

            System.out.println("Input the User-Agent (ex. Safari or Chrome): ");
            String userAgent = systemInput.readLine();

            /*
             * 4. Use the above inputs from user to construct ONE HTTP request message and send it to the HTTP server 
             * program over the TCP connection. Your HTTP request message only needs to include the following lines. 
             * (Hint: At the end of each line including the last empty line, a “\r\n” is needed.
             */
            String outBoundMessage = httpMethodType + " /" + htmFileName + " " + "HTTP/" + httpVersion + "\r\n" +
                    "Host: " + DNS_Or_IPServer + "\r\n" + "User-Agent: " + userAgent + "\r\n" + "" + "\r\n";

            System.out.println("\nMessage to server: \n" + outBoundMessage);
            try {
                String serverData = "";
                long start = System.currentTimeMillis();
                socketOut.println(outBoundMessage);


                while ((fromServer = socketIn.readLine()) != null) {
                    serverData = serverData + fromServer + "\r\n";
                    if (serverData.contains("\r\n\r\n\r\n\r\n")) {
                        break;
                    }
                }
                long end = System.currentTimeMillis();

                System.out.println("Response from Server: \n" + serverData);
                System.out.println("RTT of HTTP query was " + (end-start) + " milliseconds");
                String[] responseFromServer = serverData.split("\r\n");

                boolean hithtmlData  = false;
                boolean statusTwoHundred = false;
                String htmlMessageSavedOntoFile = "";
                String dataBeforeHtml = "";


                for(String line: responseFromServer) {
                    if(line.contains("200 OK")) {
                        statusTwoHundred = true;
                    }

                    if (!hithtmlData) {
                        dataBeforeHtml = dataBeforeHtml + line + "\r\n";
                    }

                    if (line.equals("") && statusTwoHundred) {
                        hithtmlData = true;
                    }
                }

                if (!statusTwoHundred) {
                    System.out.println("NO HTML DATA WILL BE SAVED AS FILE");
                }


                System.out.println("The header and status lines sent from server: \n" + dataBeforeHtml);

                if (statusTwoHundred) {
                    htmlMessageSavedOntoFile = serverData.replace(dataBeforeHtml,"");
                    String[] splitDataFromFourBlankLines = htmlMessageSavedOntoFile.split("\r\n\r\n\r\n\r\n", 2);
                    String htmlData = splitDataFromFourBlankLines[0] + "\r\n";
                    System.out.println("\nHTML DATA THAT WILL BE SAVED AS FILE\n" + htmlData);

                    PrintWriter printHtmlFile = new PrintWriter(htmFileName);

                    String[] htmlDataArray = htmlData.split("\r\n");
                    for(String lines: htmlDataArray) {
                        printHtmlFile.println(lines);
                    }

                    printHtmlFile.close();
                }
            }
            catch (Exception e) {
                System.out.println("Server does NOT reply anything.");
                wishToContinue = false;
            }

            System.out.println("If you wish to continue enter 'yes'. If you do not wish to continue, press the ENTER key: ");
            fromUser = systemInput.readLine();

            if (!fromUser.equals("yes")) {
                wishToContinue = false;
            }

            // Server is timed-out
            socketOut.println("Done with one request");
        }
        //socketOut.print("Bye"); //added line

        socketOut.close();
        socketIn.close();
        systemInput.close();
        tcpSocket.close();
    }
}
