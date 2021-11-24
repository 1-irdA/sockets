package _1irda.socket.models;

import java.net.InetAddress;

public class Request {

    private String payload;

    private InetAddress address;

    private int port;

    public Request(String payload, InetAddress address, int port) {
        this.payload = payload;
        this.address = address;
        this.port = port;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
