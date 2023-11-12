import java.net.*;
import java.io.*;

public class SMTPMultiServerThread extends Thread {
    private final Socket clientTCPSocket;
    private final PrintWriter cSocketOut;
    private final BufferedReader cSocketIn;

    public SMTPMultiServerThread(Socket socket) throws IOException {
        super("TCPMultiServerThread");
        this.clientTCPSocket = socket;
        this.cSocketOut = new PrintWriter(clientTCPSocket.getOutputStream(), true);
        this.cSocketIn = new BufferedReader(new InputStreamReader(clientTCPSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            boolean keepLooping = true;

            while (keepLooping) {
                cSocketOut.println("220 cs3700a@msudenver.edu");

                if (!readCommand("HELO")) {
                    cSocketOut.println("503 5.5.2 Send hello first");
                    continue;
                }

                if (!readCommand("MAIL FROM")) {
                    cSocketOut.println("503 5.5.2 Need mail command");
                    continue;
                }

                if (!readCommand("RCPT TO")) {
                    cSocketOut.println("503 5.5.2 Need rcpt command");
                    continue;
                }

                if (!readCommand("DATA")) {
                    cSocketOut.println("503 5.5.2 Need data command");
                    continue;
                }

                cSocketOut.println("354 Start mail input; end with <CRLF>.<CRLF>");
                System.out.println("Response sent to STMP client: 354 Start mail input; end with <CRLF>.<CRLF>");

                String line;
                while ((line = cSocketIn.readLine()) != null) {
                    if (line.equals(".")) {
                        break;
                    }
                    System.out.println(line);
                }

                cSocketOut.println("250 Message received and to be delivered");
                System.out.println("Response sent to STMP client:  250 Message received and to be delivered");

                if (readCommand("QUIT")) {
                    InetAddress iPAddress = InetAddress.getLocalHost();
                    cSocketOut.println("221 " + iPAddress.getHostAddress() + " closing connection");
                    System.out.println("221 " + iPAddress.getHostAddress() + " closing connection");
                    keepLooping = false;
                }
            }

            cSocketOut.close();
            cSocketIn.close();
            clientTCPSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean readCommand(String expectedCommand) throws IOException {
        while (true) {
            String fromClient = cSocketIn.readLine();
            System.out.println("Received this message from client: " + fromClient);
            String[] parts = fromClient.split(" ");

            if (parts[0].equals(expectedCommand)) {
                return true;
            }
        }
    }
}
