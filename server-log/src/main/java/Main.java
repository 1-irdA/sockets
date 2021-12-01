import _1irda.socket.models.Analyzer;
import _1irda.socket.models.ListAuth;
import _1irda.sockets.models.TcpLogger;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        TcpLogger tcpLogger = new TcpLogger(3244, analyzer);
        tcpLogger.listen();
    }
}
