package _1irda.socket.models;

import _1irda.socket.models.communication.Request;
import _1irda.socket.models.communication.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientLog {

    private final String host;

    private final int port;

    public ClientLog(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Send to log server
     * @param sock client
     * @param proto protocol
     * @param request user request
     * @param response server response
     */
    public void send(Socket sock, String proto, Request request, Response response) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream output = new PrintStream(socket.getOutputStream());
            String toLog = sock.getInetAddress().toString() +
                    " " +
                    sock.getPort() +
                    " " +
                    proto +
                    " " +
                    request.getCommand() +
                    " " +
                    request.getToken() +
                    " " +
                    response.getStatus();

            output.println(toLog);

            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
