package _1irda.sockets.models;

import _1irda.socket.models.logger.JsonLogger;
import _1irda.socket.models.logger.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpLogger {

    private final int port;

    public TcpLogger(int port) {
        this.port = port;
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
                            Log log = new Log(request.split(" "));
                            JsonLogger.log(log.getHost(),
                                    log.getPort(),
                                    log.getProto(),
                                    log.getType(),
                                    log.getLogin(),
                                    log.getResult());
                            output.println("Success log");
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
}