package _1irda.socket.models;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpAuthService implements Runnable {

    private DatagramSocket socket;

    private boolean isManager;

    private int port;

    private Analyzer analyzer;

    public UdpAuthService(boolean isManager, int port, Analyzer analyzer) {
        this.isManager = isManager;
        this.port = port;
        this.analyzer = analyzer;
    }

    private Request receive() throws IOException {
        DatagramPacket dgram = null;
        String reception = null;
        byte[] buffer = new byte[1024];

        dgram = new DatagramPacket(buffer, buffer.length);
        socket.receive(dgram);
        reception = new String(dgram.getData(), 0, dgram.getLength());

        return new Request(reception, dgram.getAddress(), dgram.getPort());
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
                    Request request = receive();

                    /* extract data and check */
                    String result = this.analyzer.checkCommand(request.getPayload(), isManager);

                    send(result, request.getAddress(), request.getPort());
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
