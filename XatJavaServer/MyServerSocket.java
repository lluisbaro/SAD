/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;
import XatJavaClient.MySocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author jordimaripare
 */
public class MyServerSocket extends ServerSocket {

    public MyServerSocket(int port) throws IOException {
        super(port);
    }

    @Override
    public MySocket accept() throws IOException {
        return (MySocket)super.accept(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
