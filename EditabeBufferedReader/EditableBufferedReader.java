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
	private final Line linia;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
		linia = new Line();
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
		int car = 0;
		try{
			/*car=super.read();
			System.out.println(car);*/  //Per comprovar que printa cada cosa
			
			if ((car = super.read()) == ESC){
				//System.out.print("     hi ha ESC");
				if ((car = super.read()) == '['){
					//System.out.print("      Ha llegit [");
					switch (car = super.read()){
						case 'D':
							//System.out.print("      Ha llegit FLETXA_ESQ");
							linia.goLeft();
							break;
						case 'C':
							//System.out.print("      Ha llegit FLETXA_DRT");
							linia.goRight();
							break;
						case '3': // Real es ^[[3~ pero entenem que despres vindra el ~
							System.out.print("      Ha llegit SUPR");
							//linia.delete();
							break;
						case 'H':
							System.out.print("      Ha llegit HOME");
							//Cridar HOME a línia
							break;
						case '2': // Real es ^[[2~ pero entenem que despres vindra el ~
							System.out.print("      Ha llegit INSERT");
							//Cridar insert a view
							break;
						case 'F':
							System.out.print("      Ha llegit FIN");
							//Cridar FIN
							break;
						default: 
							System.out.print("      Seq de ESC no valida");
					}
					//car = 'D';
					car = 12;
				}

			}
		//	else System.out.println("No hi ha seq ESC");	// Per comprovar que no hi ha seq ESC
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			return car;
		}
	}
	
	public String readLine() throws IOException{
		// aqui fem el bucle i invoquema setRaw i unsetRar
		int caracter;
		String frase = null;
		try{
			this.setRaw();
			caracter = this.read();
			System.out.print(caracter);
			while(caracter != CR){
				if (caracter != 12){
					linia.addChar((char)caracter);
					System.out.print((char)caracter);
					frase = frase+(char)caracter;
				}
				caracter = this.read();
				System.out.print("  "+caracter);
			}
		} finally {
			this.unsetRaw();
			return frase;
		}
			
	}
	
}
