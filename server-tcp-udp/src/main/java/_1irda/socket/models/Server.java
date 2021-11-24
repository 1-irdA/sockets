package _1irda.socket.models;

public class Server {

    private UdpAuthService updServer;

    private TcpAuthService tcpServer;

    public Server(int port, boolean isManager, Analyzer analyzer) {
        this.updServer = new UdpAuthService(isManager, port, analyzer);
        this.tcpServer = new TcpAuthService(isManager, port, analyzer);
    }

    public void listen() {
        new Thread(tcpServer).start();
        new Thread(updServer).start();
    }
}
