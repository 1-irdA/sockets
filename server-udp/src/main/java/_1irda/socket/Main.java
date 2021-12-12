package _1irda.socket;

import _1irda.socket.models.ClientLog;
import _1irda.socket.models.db.ListAuth;
import _1irda.socket.models.UdpAuthService;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        ClientLog clientLog = new ClientLog("localhost", 3244);
        UdpAuthService authService = new UdpAuthService(40000, analyzer, clientLog);
        authService.listen();
    }
}
