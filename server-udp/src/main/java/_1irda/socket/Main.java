package _1irda.socket;

import _1irda.socket.models.db.ListAuth;
import _1irda.socket.models.UdpAuthService;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        UdpAuthService authService = new UdpAuthService(40000, analyzer);
        authService.listen();
    }
}
