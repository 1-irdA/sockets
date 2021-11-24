package _1irda.socket.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String host;

    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void loop() {

        Scanner scanner = new Scanner(System.in);
        String data;

        try {
            Socket socket = new Socket(host, port);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream output = new PrintStream(socket.getOutputStream());

            System.out.println("Input sentences or 'stop'" );

            do {
                System.out.print("> " );
                data = scanner.nextLine();

                // send input to server
                if (!data.equalsIgnoreCase("stop" )) {
                    output.println(data);

                    // read server data and print
                    System.out.println(read(input));
                }
            } while (!data.equalsIgnoreCase("stop" ));

            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        scanner.close();
    }

    private String read(BufferedReader input) {
        String data = "";

        try {
            data = input.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return data;
    }
}
