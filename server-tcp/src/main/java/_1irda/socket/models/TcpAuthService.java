package _1irda.socket.models;

import _1irda.socket.models.communication.Request;
import _1irda.socket.models.communication.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpAuthService extends Thread {

    private final Analyzer analyzer;

    private final int port;

    private final ClientLog clientLog;

    public TcpAuthService(int port, Analyzer analyzer, ClientLog clientLog) {
        this.port = port;
        this.analyzer = analyzer;
        this.clientLog = clientLog;
    }

    public void listen() {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream output = new PrintStream(socket.getOutputStream());
                    String clientData = "";

                    while (clientData != null) {
                        /* read client request */
                        clientData = input.readLine();

                        if (clientData != null) {
                            /* build request */
                            Request request = new Request(clientData);

                            /* get response */
                            String data = analyzer.checkCommand(request.toString());

                            Response response = new Response(data);

                            clientLog.send(socket.getInetAddress(), socket.getPort(), "TCP", request, response);

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
