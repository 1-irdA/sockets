import _1irda.socket.models.ClientLog;
import _1irda.socket.models.db.ListAuth;
import _1irda.socket.models.TcpAuthService;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        ClientLog clientLog = new ClientLog("localhost", 3244);
        TcpAuthService service = new TcpAuthService(40000, analyzer, clientLog);
        service.listen();
    }
}
