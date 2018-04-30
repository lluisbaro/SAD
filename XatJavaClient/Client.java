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
    private MySocket s;
    private InputThreadClient input;
    private OutputThreadClient output;
    private String nick;
    
    public Client(InetAddress ip, int port, String n ){
        this.nick = n;
        this.s = new MySocket(ip, port);
        this.input = new InputThreadClient(this);
        this.output = new OutputThreadClient(this);
    }
    public InputThreadClient getInput() {
        return input;
    }

    public OutputThreadClient getOutput() {
        return output;
    }

    public MySocket getS() {
        return s;
    }

    public String getNick() {
        return nick;
    }
    
    public void close() throws IOException{
        this.s.close();
    }

    /* Hauriem de demanar al client les dades no? adress, port, nickname 
        fer un main(String[] args)
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        print("Adress:");
        String ip = in.readline();
        print("port:");
        port = readline
        print("nick:");
        nick = readline
        s = new Mysocket(ip,port);
    */

    // Cridar a INPutThread 
    // Cridar OutputThread

        //Penso que els podriem posar directament aqui com
/*
        (new Thread(){
            public void run() {
                try{
                    while(!null)
                        print linia
                } catch (IOException e) {
                }
            }
        }).start();

        ....

        */






}
