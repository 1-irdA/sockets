package _1irda.socket.models;

import _1irda.sockets.models.TcpLogger;

public class Server {

    /**
     * Udp server
     */
    private final UdpAuthService updServer;

    /**
     * Tcp server
     */
    private final TcpAuthService tcpServer;

    /**
     * Tcp logger
     */
    private final TcpLogger tcpLogger;

    public Server(UdpAuthService udpServer, TcpAuthService tcpServer, TcpLogger tcpLogger) {
        this.updServer = udpServer;
        this.tcpServer = tcpServer;
        this.tcpLogger = tcpLogger;
    }

    /**
     * Multithreaded action
     */
    public void listen() {
        tcpServer.start();
        updServer.start();
        tcpLogger.start();
    }
}
