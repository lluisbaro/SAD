package ChatServer;

/*
 * Copyright (c) 2010-2018 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class ChatServer extends WebSocketServer {
    
    private ConcurrentHashMap<String, WebSocket> mapa = new ConcurrentHashMap<String, WebSocket>();

	public ChatServer( int port ) throws UnknownHostException {
		super( new InetSocketAddress( port ) );
	}

	public ChatServer( InetSocketAddress address ) {
		super( address );
	}
        
    //canvis
	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		//conn.send("Benvingut a la sala!"); //This method sends a message to the new client
		//broadcast( "new connection: " + handshake.getResourceDescriptor() + conn.toString()); //This method sends a message to all clients connected
		System.out.println( conn.hashCode() + " entered the room!" + conn.toString());
        //System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
	}
        
	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
		broadcast(conn.getAttachment() + " ha deixat el Xat &%:");
        System.out.println(conn.getAttachment() + " ha deixat el Xat");
		//System.out.println( conn + " has left the room!" + reason);
        this.mapa.remove(conn.getAttachment());
	}

	@Override
	public void onMessage( WebSocket conn, String message ) {
		//parsing message, extreiem nick i posem al mapa
        Pattern hasNick = Pattern.compile("&%hasNick'(.*?)'");
        Matcher nickm = hasNick.matcher(message);
        if(nickm.find()){
			System.out.println(nickm.group(1));
            if(mapa.containsKey(nickm.group(1))){
                conn.send("&%BAD_NICKNAME");
            } else{
                conn.send("&%OK");
                conn.setAttachment(nickm.group(1));
                this.mapa.put(nickm.group(1), conn);
                broadcast(nickm.group(1)+" ha entrat al Xat!"+"&%:");
            }
        } else{
            broadcast(message);
            System.out.println( conn + ": " + message );
        }  
	}
        
	@Override
	public void onMessage( WebSocket conn, ByteBuffer message ) {
		broadcast( message.array() );
		System.out.println( conn + ": " + message );
	}

	public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocketImpl.DEBUG = true;
		int port = 8080; // 843 flash policy port
		try {
			port = Integer.parseInt( args[ 0 ] );
		} catch ( Exception ex ) {
		}
		ChatServer s = new ChatServer( port );
		s.start();
		System.out.println( "ChatServer started on port: " + s.getPort() );
                
		BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
		while ( true ) {
			String in = sysin.readLine();
			s.broadcast( in );
			if( in.equals( "exit" ) ) {
				s.stop(1000);
				break;
			}
		}
	}
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if( conn != null ) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	@Override
	public void onStart() {
		System.out.println("Server started!");
	}

}
