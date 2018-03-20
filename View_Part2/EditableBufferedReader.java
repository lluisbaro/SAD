import java.io.*;
import java.lang.*;


public class EditableBufferedReader extends BufferedReader{
	/*
		ESC parser:
		RIGHT:	ESC [ C
		LEFT:	ESC [ D
		HOME:	ESC O H, ESC [ 1 ~ (keypad)
		END:	ESC O F, ESC [ 4 ~ (keypad)
		INS:	ESC [ 2 ~
		DEL:	ESC [ 3 ~
	*/
	public static final int CR = 13;
	public static final int ESC = 27;
        public static final int INSERT = 50;//'2'
	public static final int SUPRIMIR = 51;//'3'
	public static final int FLETXA_DRT = 67; //C
	public static final int FLETXA_ESQ = 68; //D
        public static final int FIN = 70; //F
	public static final int HOME = 72; //H
	public static final int O = 79; //O
        public static final int BRACKET = 91;//[
	public static final int BACKSPACE = 127;
	/* Variables no Estàtiques */
        public final Line linia;
        public final Console console;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
		this.linia = new Line();
                this.console = new Console(this.linia);
                this.linia.addObserver(this.console);
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
			if ((car = super.read()) == ESC){
				if ((car = super.read()) == BRACKET){
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
							car = SpecialKeys.ALTRES;
							break;
					}
				}else if(car == O){
                                    switch (car = super.read()){
                                        case HOME:
                                            car = SpecialKeys.HOME;
                                            break;
                                        case FIN: 
                                            car = SpecialKeys.FIN;
                                            break;
                                        default:
                                            car = SpecialKeys.ALTRES;
                                            break;
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
			while(caracter != CR && caracter != -1){ //preguntarli com llegir ctrl+D
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
					case SpecialKeys.ALTRES:
						break;
					default:
						linia.addChar((char)caracter);
						System.out.print((char)caracter);
				}
				caracter = this.read();
			}
		} finally {
			this.unsetRaw();
			linia.home();	//	Per tornar al inici del terminal
			return linia.toString(); 
		}
	}
}
