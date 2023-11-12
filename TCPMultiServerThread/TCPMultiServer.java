/*
 * David Gaytan - Mahdi Husayni
 * Homework 3
 * CS 3700
 * Server App upon TCP
 * A thread is created for each connection request from a client
 * So it can handle Multiple Client Connections at the same time
 * Weiying Zhu
 */

import java.net.*;
import java.io.*;

public class TCPMultiServer {
    public static void main(String[] args) throws IOException{

        // Instantiate TCP Socket
        ServerSocket serverTCPSocket = null;
        boolean listening = true;

        try{
            
            serverTCPSocket = new ServerSocket(5070);

        } catch (IOException e) {
            //Return message stating that given port number is not accessible
            System.err.println("Could not connect with the port " + serverTCPSocket.getLocalPort());
            System.exit(-1);
        }


        while (listening){
            new TCPMultiServerThread(serverTCPSocket.accept()).start();
        }
        serverTCPSocket.close();
    }
}
