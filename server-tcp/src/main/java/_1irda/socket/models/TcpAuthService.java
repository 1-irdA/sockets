package _1irda.socket.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpAuthService implements Runnable {

    private final Analyzer analyzer;

    private final boolean isManager;

    private final int port;

    public TcpAuthService(boolean isManager, int port, Analyzer analyzer) {
        this.isManager = isManager;
        this.port = port;
        this.analyzer = analyzer;
    }

    public void listen() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream output = new PrintStream(socket.getOutputStream());
                    String request = "";

                    while (request != null) {
                        /* read client request */
                        request = input.readLine();

                        if (request != null) {
                            /* get response */
                            String response = analyzer.checkCommand(request, isManager);

                            /* send response */
                            output.println(response);
                        }
                    }
                    socket.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        listen();
    }
}
