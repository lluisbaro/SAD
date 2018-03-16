import java.io.*;
import java.lang.*;


public class EditableBufferedReader extends BufferedReader{
	
	public final Line linia;
	public final Console console;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
		this.linia = new Line();
		this.console = new Console(this.linia);
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
		linia.addObserver(console);
		int car = -1;
		try{
			car = super.read();
			if (car == Seq_ESC.ESC){ 	//	System.out.print("     hi ha ESC");
				if ((car = super.read()) == '['){	//	System.out.print("      Ha llegit [");
					switch (car = super.read()){
						case 'D': // 	ESQUERRA	//	System.out.print("      Ha llegit FLETXA_ESQ");
							linia.goLeft();
							break;
						case 'C': //	DRETA		//	System.out.print("      Ha llegit FLETXA_DRT");
							linia.goRight();
							break;
						case '3': //	SUPRIMIR	Real es ^[[3~ pero entenem que despres vindra el ~	//	System.out.print("      Ha llegit SUPR      ");
							car = super.read();
							linia.supr();
							break;
						case 'H': //	HOME 		//	System.out.print("      Ha llegit HOME");
							linia.home();
							break;
						case '2': // 	insert 		Real es ^[[2~ pero entenem que despres vindra el ~	//	System.out.print("      Ha llegit INSERT");
							car = super.read();
							linia.insert();
							break;
						case 'F': //	FIN 		//	System.out.print("      Ha llegit FIN");
							linia.fin();
							break;
						default: 
							System.out.print("Seq de ESC no valida");
							break;
					}
					car = Seq_ESC.OK;
				}

			} else if (car == Seq_ESC.BACKSPACE){			//System.out.print("    delete   ");
				car = Seq_ESC.OK;
				linia.delete();
			}
		//	else System.out.println("No hi ha seq ESC");	// Per comprovar que no hi ha seq ESC
		} catch (IOException e){
			e.printStackTrace();
		} finally{
			return car;
		}
	}
	
	public String readLine() throws IOException{
		int caracter;
		try{
			this.setRaw();
			caracter = this.read();
		//	System.out.print(caracter);
			while(caracter != Seq_ESC.CR){
				if (caracter != Seq_ESC.OK){
					linia.addChar((char)caracter);
					System.out.print((char)caracter);
				}
				caracter = this.read();
		//		System.out.print("  "+caracter);
			}
		} finally {
			this.unsetRaw();
			linia.home();	//	Per tornar al inici del terminal
			System.out.print("\n*****"+linia.getSize());
			linia.home();	//	Per tornar al inici del terminal
			return linia.toString(); 
		}
			
	}
	
}
