import _1irda.socket.models.ListAuth;
import _1irda.socket.models.TcpAuthService;
import _1irda.socket.models.Analyzer;

public class Main {

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(new ListAuth());
        TcpAuthService service = new TcpAuthService(40000, analyzer);
        service.listen();
    }
}
