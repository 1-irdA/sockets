package _1irda.socket;

import _1irda.socket.models.Client;

public class Main {

    public static void main(String[] args) {
        Client client = new Client("localhost", 40000);
        client.loop();
    }
}
