import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Server {

    private static Logger log = Logger.getLogger(Server.class.getName());
    ReentrantLock reentrantLock = new ReentrantLock();

    private static int port = 8080;
    private static MyServerSocket serverSocket;
    private static MySocket socket;
    private static String nick;
    private static List<String> users;
    private static List<MySocket> sockets;

    public static void main(String[] args) {

        Messages messages = new Messages();
        users = new ArrayList<>();
        sockets = new ArrayList<>();

        try {
            serverSocket = new MyServerSocket(port);

            while (true) {
                log.info("Wait for connections");
                socket = serverSocket.accept();
                socket.println("nick");
                nick = socket.readLine();
                users.add(nick);
                sockets.add(socket);
                for (MySocket s : sockets) {
                    for(String user : users){
                        s.println(user);
                    }
                    s.println("done");
                }
                log.info("Connection accepted");

                ClientConnection cc = new ClientConnection(socket, messages, nick);
                cc.start();
            }
        } finally {
            socket.close();
            serverSocket.close();
        }
    }
}
