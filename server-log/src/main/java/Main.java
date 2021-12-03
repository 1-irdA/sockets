import _1irda.sockets.models.TcpLogger;

public class Main {

    public static void main(String[] args) {
        TcpLogger tcpLogger = new TcpLogger(3244);
        tcpLogger.listen();
    }
}
