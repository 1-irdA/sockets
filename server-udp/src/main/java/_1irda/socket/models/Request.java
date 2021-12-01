package _1irda.socket.models;

import java.net.InetAddress;

public class Request {

    /**
     * Data
     */
    private String payload;

    /**
     * Address to respond
     */
    private InetAddress address;

    /**
     * Port to respond
     */
    private int port;

    public Request(String payload, InetAddress address, int port) {
        this.payload = payload;
        this.address = address;
        this.port = port;
    }

    public String getPayload() {
        return payload;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
