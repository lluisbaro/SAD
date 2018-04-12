/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatJava;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Jordi Marias Parella
 */
public class Client {
    private InetAddress ip;
    private MySocket s;
    private InputThreadClient input;
    private OutputThreadClient output;
    
    public Client() throws UnknownHostException, IOException{
        byte[] ipAddr = new byte[]{127, 0, 0, 1};
        this.ip = InetAddress.getByAddress(ipAddr);
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
