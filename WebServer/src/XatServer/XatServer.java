/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XatServer;

import ChatServer.ChatServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 *
 * @author lluis
 */
public class XatServer extends WebSocketServer{
    
    private ConcurrentHashMap<String, WebSocket> mapa = new ConcurrentHashMap<String, WebSocket>();
    
    public XatServer(int port) throws UnknownHostException {
	super(new InetSocketAddress(port));
    }

    public XatServer(InetSocketAddress address) {
        super(address);
    }
    
    public WebSocket putNick(String nick, WebSocket s){
        if ( !this.mapa.containsKey(nick)){
    		return this.mapa.put(nick, s);
    	}
        return null;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        //conn.send("Welcome to the server!"); //This method sends a message to the new client
        //broadcast( "new connection: " + handshake.getResourceDescriptor() + conn.toString()); //This method sends a message to all clients connected
        //broadcast ( "usr: " + conn.toString());
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        //parsing message, extreiem nick i posem al mapa
        //String[] parser = message.split("&%:");
        //String usr = parser[0];
        
        //if (!this.mapa.containsKey(usr)){
        //    this.mapa.put(usr, conn);
    	//}
        
        broadcast(message);
        System.out.println( conn + ": " + message );
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }
    
    public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocketImpl.DEBUG = true;
		int port = 8081;
		try {
                    port = Integer.parseInt( args[ 0 ] );
		} catch ( Exception ex ) {}
		XatServer xs = new XatServer( port );
		xs.start();
		System.out.println( "ChatServer started on port: " + xs.getPort() );

		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while ( true ) {
			String in = sysin.readLine();
			xs.broadcast( in );
			if( in.equals( "exit" ) ) {
				xs.stop(1000);
				break;
			}
		}
	}
}
