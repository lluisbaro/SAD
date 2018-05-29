import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Configuracio extends JDialog{
    
    private final JTextField userTF;
    private final JTextField hostTF;
    private final JTextField portTF;
    
    public Configuracio(JFrame frame){
        super(frame, "Configuracio", true);
        
        JLabel userLabel = new JLabel("User:");
        JLabel hostLabel = new JLabel("Host:");
        JLabel portLabel = new JLabel("Port:");
        
        userTF = new JTextField();
        hostTF = new JTextField("localhost");
        portTF = new JTextField("8080");
                
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                setVisible(false); // amaga la finestra un cop acceptat
            }
        });   
        
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 20, 0, 20);

        gbc.gridx = 0; gbc.gridy = 0;
        c.add(userLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        c.add(hostLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        c.add(portLabel, gbc);

        gbc.ipadx = 100;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1; gbc.gridy = 0;
        c.add(userTF, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        c.add(hostTF, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        c.add(portTF, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 20, 20);
        c.add(ok, gbc);

        this.pack();
        this.setLocationRelativeTo( null );
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // Deshabilitem el boto closewindow
        this.setVisible(true);
    }
    
    public String getUser() {
        return userTF.getText();
    }

    public String getHost() {
        return hostTF.getText();
    }

    public int getPort() {
        return Integer.parseInt(portTF.getText());
    }
    
    public String getUri(){
        return "ws://" + hostTF.getText() + ":" + portTF.getText();
    }
            
    /*public static void main( String[] args ) {
        JFrame frame = null;
        Configuracio cf = new Configuracio(frame);
    }*/
}
