package _1irda.socket.models;

import _1irda.socket.models.communication.Request;
import _1irda.socket.models.communication.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpAuthService extends Thread {

    private DatagramSocket socket;

    private final int port;

    private final Analyzer analyzer;

    private final ClientLog clientLog;

    public UdpAuthService(int port, Analyzer analyzer, ClientLog clientLog) {
        this.port = port;
        this.analyzer = analyzer;
        this.clientLog = clientLog;
    }

    private UdpRequest receive() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket dgram = new DatagramPacket(buffer, buffer.length);
        socket.receive(dgram);
        String reception = new String(dgram.getData(), 0, dgram.getLength());
        return new UdpRequest(reception, dgram.getAddress(), dgram.getPort());
    }

    private void send(String msg, InetAddress address, int port) throws IOException {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(bytes, bytes.length, address, port));
    }

    public void listen() {
        try {
            socket = new DatagramSocket(port);

            try {
                while (true) {
                    /* wait en receive */
                    UdpRequest updRequest = receive();

                    /* extract command login password token  */
                    Request request = new Request(updRequest.getPayload());

                    /* extract data and check */
                    String result = this.analyzer.checkCommand(updRequest.getPayload());

                    Response response = new Response(result);

                    clientLog.send(updRequest.getInetAddress(),
                            updRequest.getPort(),
                            "UDP",
                            request,
                            response);

                    send(result, updRequest.getInetAddress(), updRequest.getPort());
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            socket.close();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        listen();
    }
}
