import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection implements ActionListener {

    private Logger log = Logger.getLogger(ServerConnection.class.getName());
    private MySocket socket;
    private JTextField tfMessage;
    private String user;
    private DataOutputStream outputStream;

    public ServerConnection(MySocket socket, JTextField tfMessage, String user) {
        this.socket = socket;
        this.tfMessage = tfMessage;
        this.user = user;
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (NullPointerException | IOException ex) {
            log.log(Level.SEVERE, "The socket has not created correctly.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            outputStream.writeUTF(user + ": " + tfMessage.getText());
            tfMessage.setText("");
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Error trying to send a message:" + ex.getMessage());
        }
    }
}
