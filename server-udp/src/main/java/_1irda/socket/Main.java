package _1irda.socket;

import _1irda.socket.models.ListAuth;
import _1irda.socket.models.UdpAuthService;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        UdpAuthService authService = new UdpAuthService(true,40000, analyzer);
        authService.listen();
    }
}
