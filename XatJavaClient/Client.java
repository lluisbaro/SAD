/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJavaClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jordi Marias Parella
 */
public class Client {
    private InetAddress ip;
    private MySocket s;
    private InputThreadClient input;
    private OutputThreadClient output;
    
    public Client(){
        byte[] ipAddr = new byte[]{127, 0, 0, 1};        
        try {
            this.ip = InetAddress.getByAddress(ipAddr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.s = new MySocket(this.ip, 5000);
        this.input = new InputThreadClient(this.s.getPrintWriter());
        this.output = new OutputThreadClient(this.s.getBufferedReader());
    }
    public InputThreadClient getInput() {
        return input;
    }

    public OutputThreadClient getOutput() {
        return output;
    }
    
    public void close() throws IOException{
        this.s.close();
    }
}
