package _1irda.socket;

import _1irda.socket.models.ListAuth;
import _1irda.socket.models.Server;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        Server server = new Server(40000, analyzer);
        server.listen();
    }
}
