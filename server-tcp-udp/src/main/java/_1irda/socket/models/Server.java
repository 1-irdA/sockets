package _1irda.socket.models;

public class Server {

    private final UdpAuthService updServer;

    private final TcpAuthService tcpServer;

    public Server(int port, Analyzer analyzer) {
        this.updServer = new UdpAuthService(port, analyzer);
        this.tcpServer = new TcpAuthService(port, analyzer);
    }

    public void listen() {
        new Thread(tcpServer).start();
        new Thread(updServer).start();
    }
}
