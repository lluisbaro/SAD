/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;
import XatJavaClient.MySocket;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jordimaripare
 */
public class MyServerSocket extends ServerSocket {

    public MyServerSocket(int port) {
    	super();
        InetAddress addr = InetAddress.getLocalHost();
        InetSocketAddress addrSocket = new InetSocketAddress(addr, port);
        super.bind(addrSocket);
    }

    @Override
    public MySocket accept() throws IOException {
    	try{
    		return (MySocket)super.accept(); //To change body of generated methods, choose Tools | Templates.
    	} catch (IOException e){
    		Logger.getLogger(MyServerSocket.class.getName()).log(Level.SEVERE, null, e);
    		return null; // Si no s'accepta retorna null
    	}
    }
    
    
}
