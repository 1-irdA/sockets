package _1irda.socket.models;

import java.net.InetAddress;

public class UdpRequest {

    private final String payload;

    private final InetAddress inetAddress;

    private final int port;

    public UdpRequest(String payload, InetAddress inetAddress, int port) {
        this.payload = payload;
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public String getPayload() {
        return payload;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getPort() {
        return port;
    }
}
