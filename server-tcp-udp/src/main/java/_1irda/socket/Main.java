package _1irda.socket;

import _1irda.socket.models.*;
import _1irda.socket.models.db.ListAuth;

public class Main {

    /**
     * Listened port
     */
    private static final int PORT = 40000;

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        ClientLog clientLog = new ClientLog("localhost", 3244);
        UdpAuthService udpAuthService = new UdpAuthService(PORT, analyzer, clientLog);
        TcpAuthService tcpAuthService = new TcpAuthService(PORT, analyzer, clientLog);
        Server server = new Server(udpAuthService, tcpAuthService, clientLog);
        server.listen();
    }
}
