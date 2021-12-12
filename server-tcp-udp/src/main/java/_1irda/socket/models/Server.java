package _1irda.socket.models;

import _1irda.sockets.models.TcpLogger;

public class Server {

    private final UdpAuthService updServer;

    private final TcpAuthService tcpServer;

    private final TcpLogger tcpLogger;

    private final ClientLog clientLog;

    public Server(UdpAuthService udpServer, TcpAuthService tcpServer, TcpLogger tcpLogger, ClientLog clientLog) {
        this.updServer = udpServer;
        this.tcpServer = tcpServer;
        this.tcpLogger = tcpLogger;
        this.clientLog = clientLog;
    }

    public void listen() {
        tcpServer.start();
        updServer.start();
        tcpLogger.start();
    }
}
