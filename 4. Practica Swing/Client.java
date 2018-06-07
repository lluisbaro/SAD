import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

final class Client extends JFrame {

    private JButton sendButton;
    private JScrollPane scrollMessages;
    private JScrollPane scrollUsers;
    private JTextArea messagesTA;
    private JTextField messagesTextfield;
    private JLabel usersLabel;
    private JTextArea usersTA;
    private MySocket socket;

    private final int port;
    private final String host;
    private final String user;

    public Client() {
        super("SADxat");

        // Set window elements
        initComponents();
        setVisible(true);
        this.setLocationRelativeTo( null );

        // Config
        Configuracio cf = new Configuracio(this);
        host = cf.getHost();
        port = cf.getPort();
        user = cf.getUser();

        System.out.println("You are connecting to:  " + host + " in port " + port + " and with username: " + user + ".");

        // MySocket
        socket = new MySocket(host, port, user);
        MyServerSocket commonSocket;

        // Send button
        sendButton.addActionListener(new ServerConnection(socket, messagesTextfield, user));
    }

    public void initComponents() {

        scrollMessages = new JScrollPane();
        messagesTA = new JTextArea();
        messagesTextfield = new JTextField();
        scrollUsers = new JScrollPane();
        usersTA = new JTextArea();
        usersLabel = new JLabel();
        sendButton = new JButton();

        messagesTA.setEnabled(false);
        messagesTA.setLineWrap(true);
        messagesTA.setWrapStyleWord(true);

        usersTA.setEnabled(false);
        usersTA.setLineWrap(true);
        usersTA.setWrapStyleWord(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener( new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                if( socket != null ) {
                    socket.close();
                }
                dispose();
            }
        } );
        
        setBackground(new java.awt.Color(255, 204, 255));

        messagesTA.setColumns(20);
        messagesTA.setRows(5);
        scrollMessages.setViewportView(messagesTA);

        usersTA.setColumns(20);
        usersTA.setRows(5);
        scrollUsers.setViewportView(usersTA);

        usersLabel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        usersLabel.setForeground(new java.awt.Color(255, 118, 196));
        usersLabel.setText("Usuaris");

        sendButton.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(scrollMessages)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(scrollUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(usersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(messagesTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(sendButton)
                                                .addGap(0, 23, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        	.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        	.addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                            .addComponent(scrollMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                            .addComponent(usersLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(messagesTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sendButton))
                                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }


    public void listenServer() {
        DataInputStream inputStream = null;
        String message;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (NullPointerException | IOException ex) {  
        }

        boolean connected = true;
        String call = socket.readLine();
        socket.println(user);
        while (!(call = socket.readLine()).matches("done")) {
            usersTA.append(call + System.lineSeparator());
        }
        while (connected) {
            try {
                message = inputStream.readUTF();
                messagesTA.append(message + System.lineSeparator());
                refreshUsersArea(call);
            } catch (IOException | NullPointerException ex) { 
                connected = false;
            }
        }
    }

    private void refreshUsersArea(String call) {
        /*while (!(call = socket.readLine()).matches("done") ) {
            usersTextArea.append(call + System.lineSeparator()); TODO: no va
        }*/
    }


    public static void main(String[] args) {
        Client c = new Client();
        c.listenServer();
    }
}
