import java.io.*;
import java.lang.*;


public class EditableBufferedReader extends BufferedReader{
	//declarem variables CTE
	public static final int ESC = 27;
	public static final int CSI = 0x9B;
	public static final int CR = 13;
	public static final int FLETXA_ESQ = -2;
	public static final int FLETXA_DRT = -3;
	public static final int HOME = -4;
	public static final int FIN = -5;
	public static final int INSERT = -6;
	public static final int SUPR = -7;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
	}
	
	public void setRaw(){
		try{

			String[] command = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
		// Runtime.exec()	
			Runtime.getRuntime().exec(command);

		//ProcessBuilder 
		//	ProcessBuilder p = new ProcessBuilder();
		//	p.command("sh", "-c", "stty -echo raw </dev/tty").start();
		//	p.command(command).start();
		//	System.out.println("Enter in raw mode");	// Comprovem que entra en RAW mode
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void unsetRaw(){
		try{

			String[] command = {"/bin/sh", "-c", "stty sane </dev/tty"}; // stty sane | stty echo -raw ...
		//	Runtime.exec()
		//	Runtime.getRuntime().exec(command);

		//	ProcessBuilder
			ProcessBuilder p = new ProcessBuilder();
			p.command(command).start();
		//	System.out.println("Enter in cooked mode");	// Comprovem que surt del RAW mode
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int read() throws IOException{
		int car = -1;
		
		try{
			if ( (car = super.read()) == ESC){
				System.out.println("hi ha ESC");
				if ((car = super.read()) == '['){
					System.out.println("Ha llegit [");
					switch (car = super.read()){
							case 'D':
								System.out.println("Ha llegit FLETXA_ESQ");
								car = FLETXA_ESQ;

								break;
							case 'C':
								System.out.println("Ha llegit FLETXA_DRT");
								car = FLETXA_DRT;
								break;
							case '3': // Real es ^[[3~ pero entenem que despres vindra el ~
								System.out.println("Ha llegit SUPR");
								car = SUPR;
								break;
							case 'H':
								System.out.println("Ha llegit HOME");
								car = HOME;
								break;
							case '2': // Real es ^[[2~ pero entenem que despres vindra el ~
								System.out.println("Ha llegit INSERT");
								car = INSERT;
								break;
							case 'F':
								System.out.println("Ha llegit FIN");
								car = FIN;
								break;
							default: 
								System.out.println("Seq de ESC no valida");
								car = super.read();
						}
				}

			}
		//	else System.out.println("No hi ha seq ESC");	// Per comprova que no hi ha seq ESC
			

		} catch (IOException e){
			e.printStackTrace();
		} finally{
			return car;
		}
	}
	
	public String readLine() throws IOException{

		// aqui fem el bucle i invoquema setRaw i unsetRar
		int caracter;
		Line line = new Line();
		String frase = null;

		try{
			this.setRaw();
			caracter = this.read();
			line.addChar((char)caracter);
			frase = String.valueOf(caracter);
			System.out.print((char)caracter); 	//Comprova el primer caracter

			while( caracter != CR){

				
				switch (caracter){
					case FLETXA_ESQ:
					System.out.println("ESQ");
					line.goLeft();
					break;
					case FLETXA_DRT:
					System.out.println("DRETA");
					break;
					case SUPR:
					System.out.println("SUPR");
					break;
					case HOME:
					System.out.println("HOME");
					break;
					case INSERT:
					System.out.println("INSERT");
					break;
					case FIN:
					System.out.println("FIN");
					break;
				}
				caracter = this.read();
				if (caracter != CR){ 
					line.addChar((char)caracter);
					frase = frase + String.valueOf(caracter);
					System.out.print((char)caracter);
				}
			}
			this.unsetRaw();
		} finally {}
		try{
		//	return line.toString();
			return frase;
		}
		finally{}
	}
	
}