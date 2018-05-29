import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConnection extends Thread implements Observer {

    private Logger log = Logger.getLogger(ClientConnection.class.getName());
    private MySocket socket;
    private Messages messages;
    private String nick;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientConnection(MySocket socket, Messages messages, String nick) {
        this.socket = socket;
        this.nick = nick;
        this.messages = messages;

        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        boolean connected = true;
        messages.addObserver(this);
        messages.setMessage(nick + " has connected.");

        while (connected) {
            try {
                // Read message
                message = inputStream.readUTF();
                // Add message
                messages.setMessage(message);
            } catch (IOException ex) {
                connected = false;
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException ex2) {
                    log.log(Level.SEVERE, "Error :" + ex2.getMessage());
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            // Envia el mensaje al cliente
            outputStream.writeUTF(arg.toString());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Error sending message (" + ex.getMessage() + ").");
        }
    }
}