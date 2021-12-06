package _1irda.socket.models;

public class Server {

    private final UdpAuthService updServer;

    private final TcpAuthService tcpServer;

    private final ClientLog clientLog;

    public Server(UdpAuthService udpServer, TcpAuthService tcpServer, ClientLog clientLog) {
        this.updServer = udpServer;
        this.tcpServer = tcpServer;
        this.clientLog = clientLog;
    }

    public void listen() {
        tcpServer.start();
        updServer.start();
    }
}
