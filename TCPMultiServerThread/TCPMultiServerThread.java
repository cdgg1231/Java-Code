/*
 * Server App upon TCP
 * A thread is started to handle every client TCP connection to this server
 * Weiying Zhu
 *
 *  David Gaytan - Mahdi Husayni
 * Homework 3
 * CS 3700
 */ 

import java.net.*;
import java.io.*;
// import for date libraries
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPMultiServerThread extends Thread {
    private Socket clientTCPSocket = null;


    public TCPMultiServerThread(Socket socket) {
        super("TCPMultiServerThread");
        clientTCPSocket = socket;
    }



    public void run(){
        try{
            PrintWriter cSocketOut = new PrintWriter(
                clientTCPSocket.getOutputStream(), true);

                BufferedReader cSocketIn = new BufferedReader(
                    new InputStreamReader(clientTCPSocket.getInputStream()));
                String fromClient = "";


            do{
                // String variables of communication data
                String messageFromClient = "";
                String fileData = "";
                String requestStatus = "";
                String httpVersion = "";
                String fileName = "";
                fromClient = "";

                while ((fromClient = cSocketIn.readLine()) != null) {
                    messageFromClient = messageFromClient + fromClient + "\r\n";

                    if (fromClient.equals("")) {
                        break;
                    }
                }

                System.out.println("Client's message: \n" + messageFromClient);
                String[] messageFromClientSplit = messageFromClient.split("\r\n");

                // Client message
                for(String line:messageFromClientSplit) {
                    String[] clientsLines = line.split(" ");

                    if (line.contains("HTTP")) {
                        httpVersion = clientsLines[2];

                        if (!line.contains("GET")) {
                            requestStatus = "400 Bad Request";
                        }
                        else {
                            fileName = clientsLines[1].substring(1);
                        }
                    }
                }

                // Process client request
                if (requestStatus.equals("")){
                    try {
                        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            fileData = fileData + line + "\r\n";
                        }
                        fileReader.close();
                    }

            
                    catch (Exception e){
                        requestStatus = "404 Not Found";
                    }
                }
                if (requestStatus.equals(""))
                {
                    requestStatus = "200 OK";
                }

                // Want date and time to be formatted as yyyy/MM/dd HH:mm:ss
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                //Initializing string variable that will response message back to the clint
                String outgoingMessage = httpVersion + " " + requestStatus + "\r\n" + "Date: " + dateFormat.format(date)
                        + " MST" + "\r\n" + "Server..." + "\r\n" + "" + "\r\n";

                /*
                 * This line checks if the request status is "200 OK". If so, 
                 * it appends the file data to the outgoingMessage
                 */
                if (requestStatus.equals("200 OK")) {
                    outgoingMessage = outgoingMessage + fileData;
                }

                // Blank lines at the end of the messasge to indicate the end
                outgoingMessage = outgoingMessage + "" + "\r\n" + "" + "\r\n" + "" + "\r\n" + "" + "\r\n";

                //This prints the outgoingMessage to the console 
                System.out.println("\nResponse to Client: ");
                System.out.println(outgoingMessage);

                //Sends the outgoingMessage to the client over the TCP connection using the output stream
                cSocketOut.println(outgoingMessage);


                /*  Wait for client to complete process
                input stream server waits for the client
                 to complete the process before closing the connection
                */
                cSocketIn.readLine();
            } while (fromClient != null);

            cSocketOut.close();
            cSocketIn.close();
            clientTCPSocket.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
