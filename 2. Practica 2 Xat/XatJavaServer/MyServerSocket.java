/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaServer;
import XatJavaClient.MySocket;
import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author jordimaripare
 */
public class MyServerSocket extends ServerSocket {

    public MyServerSocket(int port) throws IOException {
    	try{
    		super(port);
    	} catch (IOException e){
    		e.printStackTrace();
    	}
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
