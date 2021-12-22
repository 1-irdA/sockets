package _1irda.socket;

import _1irda.socket.models.*;
import _1irda.socket.models.db.ListAuth;
import _1irda.sockets.models.TcpLogger;

public class Main {

    /**
     * Server port
     */
    private static final int SERVER_PORT = 40000;

    /**
     * Logger port
     */
    private static final int LOGGER_PORT = 3244;

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        ClientLog clientLog = new ClientLog("localhost", LOGGER_PORT);
        UdpAuthService udpAuthService = new UdpAuthService(SERVER_PORT, analyzer, clientLog);
        TcpAuthService tcpAuthService = new TcpAuthService(SERVER_PORT, analyzer, clientLog);
        TcpLogger tcpLogger = new TcpLogger(LOGGER_PORT);
        Server server = new Server(udpAuthService, tcpAuthService, tcpLogger);
        server.listen();
    }
}
