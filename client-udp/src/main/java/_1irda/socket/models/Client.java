package _1irda.socket.models;

import _1irda.socket.enums.Status;

import java.net.*;
import java.util.Scanner;

public class Client {

    private final int port;

    private final String dest;

    private DatagramSocket socket;

    public Client(String dest, int port) {
        this.port = port;
        this.dest = dest;
    }

    private void send(byte[] bytes, InetAddress destination) {
        try {
            socket.send(new DatagramPacket(bytes, bytes.length, destination, port));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String extractReceivedData(DatagramPacket reception) {
        return new String(reception.getData(), 0, reception.getLength());
    }

    public void loop() {
        Scanner scanner = new Scanner(System.in);
        byte[] buffer = new byte[1024];
        String data = "";

        try {
            socket = new DatagramSocket();
            DatagramPacket reception = new DatagramPacket(buffer, buffer.length);
            InetAddress destination = InetAddress.getByName(dest);
            User user = new User("");

            System.out.println("Input sentences or 'stop' : ");

            while (!data.equalsIgnoreCase("stop")) {
                System.out.print("> ");

                /* read input */
                data = scanner.nextLine();

                if (!data.equalsIgnoreCase(("stop"))) {

                    byte[] payload = buildPayload(data, user);

                    /* get bytes and send data */
                    send(payload, destination);

                    /* wait and receive server data */
                    socket.receive(reception);

                    Response response = new Response(extractReceivedData(reception));

                    /* set username if response if 'GOOD' (only on auth) */
                    if (response.getStatus().equals(Status.GOOD.getValue())) {
                        user.setUsername(response.getUsername());
                    }
                    System.out.println(response);

                    /*
                     * replace buffer size at max
                     * updated during receive data client
                     */
                    reception.setLength(buffer.length);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private byte[] buildPayload(String input, User user) {
        return new StringBuilder()
                .append(input)
                .append(" ")
                .append("token:")
                .append(user.getUsername())
                .toString()
                .getBytes();
    }
}
