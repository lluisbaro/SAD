import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySocket {

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String nick;

    public MySocket(int port) {
        try {
            socket = new Socket("localhost", port);
            init();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MySocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            this.init();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MySocket(String host, int port, String nick) {
        try {
            socket = new Socket(host, port);
            this.nick = nick;
            this.init();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MySocket(Socket socket) {
        this.socket = socket;
        init();
    }

    public void init() {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public String readLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void close() {
        this.printWriter.close();
        try {
            this.bufferedReader.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(MySocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void println(String response) {
        this.printWriter.println(response);
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}
