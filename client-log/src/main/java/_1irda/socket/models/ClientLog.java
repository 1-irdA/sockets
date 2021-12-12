package _1irda.socket.models;

import _1irda.socket.models.communication.Request;
import _1irda.socket.models.communication.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
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
     * @param address client address
     * @param clientPort client port
     * @param proto protocol
     * @param request request type
     * @param response response type
     */
    public void send(InetAddress address, int clientPort, String proto, Request request, Response response) {
        try {
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream output = new PrintStream(socket.getOutputStream());
            String toLog = new StringBuilder()
                    .append(address.toString())
                    .append(" ")
                    .append(clientPort)
                    .append(" ")
                    .append(proto)
                    .append(" ")
                    .append(request.getCommand())
                    .append(" ")
                    .append(request.getToken())
                    .append(" ")
                    .append(response.getStatus())
                    .toString();

            output.println(toLog);

            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
