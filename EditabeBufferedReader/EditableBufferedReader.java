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
	public final Line linia;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
		linia = new Line();
	}
	
	public void setRaw(){
		try{
			String[] command = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
		// Execucio amb Runtime	
			Runtime.getRuntime().exec(command);

		// Execucio amb Process
		/*	ProcessBuilder p = new ProcessBuilder();
			p.command("sh", "-c", "stty -echo raw </dev/tty").start();
			p.command(command).start(); */

		// Comprovem que entra en RAW mode
		//	System.out.println("Enter in raw mode");	
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void unsetRaw(){
		try{
			String[] command = {"/bin/sh", "-c", "stty sane </dev/tty"}; // stty sane | stty echo -raw ...
		// Execucio amb Runtime	
			Runtime.getRuntime().exec(command);

		// Execucio amb Process
		/*	ProcessBuilder p = new ProcessBuilder();
			p.command(command).start(); */

		// Comprovem que surt del RAW mode
		//	System.out.println("Enter in cooked mode");	
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int read() throws IOException{
		int car = 0;
		try{
			car = super.read();
			if (car == ESC){
			//	System.out.print("     hi ha ESC");
				if ((car = super.read()) == '['){
				//	System.out.print("      Ha llegit [");
					switch (car = super.read()){
						case 'D':
						//	System.out.print("      Ha llegit FLETXA_ESQ");
							linia.goLeft();
							break;
						case 'C':
						//	System.out.print("      Ha llegit FLETXA_DRT");
							linia.goRight();
							break;
						case '3': // Real es ^[[3~ pero entenem que despres vindra el ~
							System.out.print("      Ha llegit SUPR      ");
							car = super.read();
							linia.supr();
						//	S'ha de tractar el que passa quan es borra tota la linia
							break;
						case 'H':
						//	System.out.print("      Ha llegit HOME");
						//	linia.home();
							//Cridar HOME a línia
							break;
						case '2': // Real es ^[[2~ pero entenem que despres vindra el ~
						//	System.out.print("      Ha llegit INSERT");
							car = super.read();
							//Cridar insert a view
							break;
						case 'F':
						//	System.out.print("      Ha llegit FIN");
							//Cridar FIN
							break;
						default: 
							System.out.print("      Seq de ESC no valida");
					}
					car = -1;
				}

			} else if (car == 127){
				//System.out.print("    delete   ");
				car = -1;
				linia.delete();
				//System.out.print("______"+car);

			}
		//	else System.out.println("No hi ha seq ESC");	// Per comprovar que no hi ha seq ESC
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			return car;
		}
	}
	
	public String readLine() throws IOException{
		// aqui fem el bucle i invoquema setRaw i unsetRaw
		int caracter;
		try{
			this.setRaw();
			caracter = this.read();
			//System.out.print(caracter);
			while(caracter != CR){
				if (caracter != -1){
					linia.addChar((char)caracter);
					System.out.print((char)caracter);
				}
				caracter = this.read();
				//System.out.print("  "+caracter);
			}
		} finally {
			this.unsetRaw();
			System.out.println("****"+linia.getPos());
			return linia.toString(); // mirar metode to String de la classe ArrayList  --> si no es treballa amb frase no printa, veure xq
		//	return frase;
		}
			
	}
	
}
