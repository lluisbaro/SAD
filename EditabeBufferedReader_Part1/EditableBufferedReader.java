import java.io.*;
import java.lang.*;


public class EditableBufferedReader extends BufferedReader{
	//Declaracio de constants universals
	public static final int ESC = 27;
	public static final int CR = 13;
	public static final int FLETXA_ESQ = 'D';
	public static final int FLETXA_DRT = 'C';
	public static final int HOME = 'H';
	public static final int FIN = 'O';
	public static final int INSERT = 2;
	public static final int SUPRIMIR = 3;
	public static final int BACKSPACE = 127;
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
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public int read() throws IOException{
		int car = 0;
		try{
			car = super.read();
			if (car == ESC){
				if ((car = super.read()) == '['){
					switch (car = super.read()){
						case FLETXA_ESQ:
							car = SpecialKeys.FLETXA_ESQ;
							break;
						case FLETXA_DRT: 
							car = SpecialKeys.FLETXA_DRT;
							break;
						case SUPRIMIR: 
							car = super.read();
							car = SpecialKeys.SUPRIMIR;
							break;
						case HOME:
							car = SpecialKeys.HOME;
							break;
						case INSERT:
							car = super.read();
							car = SpecialKeys.INSERT;
							break;
						case FIN: 
							car = SpecialKeys.FIN;
							break;
						default:
					}
				}
			} else if (car == BACKSPACE){
				car = SpecialKeys.BACKSPACE;
			}
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
				/*if (caracter != -1){
					linia.addChar((char)caracter);
					System.out.print((char)caracter);
				}*/
				switch(caracter){
					case SpecialKeys.FLETXA_ESQ:
						linia.goLeft();
						break;
					case SpecialKeys.FLETXA_DRT:
						linia.goRight();
						break;
					case SpecialKeys.SUPRIMIR:
						linia.supr();
						break;
					case SpecialKeys.HOME:
						linia.home();
						break;
					case SpecialKeys.INSERT:
						linia.insert();
						break;
					case SpecialKeys.FIN:
						linia.fin();
						break;
					case SpecialKeys.BACKSPACE:
						linia.delete();
						break;
					default:
						linia.addChar((char)caracter);
						System.out.print((char)caracter);
				}
				caracter = this.read();
			}
		} finally {
			this.unsetRaw();
			return linia.toString(); 
		}
	}
}