import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String args[]) {
        String[] tempArgs = new String[2];
        tempArgs[0] = "localhost";
        tempArgs[1] = "4999";
        Socket socket;
        if (tempArgs.length < 2)
            System.out.println("Wprowadz adres serwera TCP oraz numer portu");
        else {
            int port = 0;
            try {
                port = Integer.parseInt(tempArgs[1]);
            } catch (NumberFormatException e) {
                System.err.println("Wprowadl pcprawny numer portu: " + e);
                return;
            }
            try {
                socket = new Socket(InetAddress.getByName(tempArgs[0]),port);

                //odbieranie od servera
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader bf = new BufferedReader(in);
                String str;

                //wysylanie do servera
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                //scanner
                Scanner sc = new Scanner(System.in);
                String answer;

                while(( str = bf.readLine()) != null) {

                    System.out.println(str);

                    answer = sc.nextLine();
                    pw.println(answer);
                    pw.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }
}