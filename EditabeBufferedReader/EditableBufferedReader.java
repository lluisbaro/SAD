import java.io.*;


public class EditableBufferedReader extends BufferedReader{
	//declarem variables CTE
	private volatile boolean flagBarret = false;
	private volatile boolean flagComandMode  = false;
	private 
	public static final int FLETXA_ESQ;
	public static final int FLETXA_DRT;
	public static final int INICIO;
	public static final int FIN;
	public static final int INSERT;
	public static final int SUPR;
	
	public EditableBufferedReader(InputStreamReader in){
		super(in);
	}
	
	public void setRaw(){
		try{
		
			ProcessBuilder process = new ProcessBuilder();
			process.command("sh", "-c", "stty -echo raw </dev/tty");
			process.start();
			System.out.println("Enter in raw mode");
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void unsetRaw(){
		try{
		
			ProcessBuilder process = new ProcessBuilder();
			process.command("sh", "-c", "stty echo -raw </dev/tty");
			process.start();
			System.out.println("Enter in cooked mode");
			
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int read() throws IOException{
		char car = (char)super.read();
		switch(car){
			case('^'):
				this.flagBarret = true;
				break;
			case('['):
				if(this.flagBarret)
					this.flagCommandMode = true;
				this.flagBarret = false;
				break;
		if(this.flagCommandMode){
			//Borrar el barret de line és a dir borrar la última entrada
			
			//Llegir les dues següents entrades que corresponen a les comandes:
			String comanda = super.read()+super.read();
			switch(comanda){
				case "[D":
					//Mètode de line per tirar el cursor enrere
					break;
				case "[C":
					//Mètode de line per tirar el cursor endavant
					break;
				case "OH":
					//Mètode de line que posa el cursor a l'inici de línia
					break;
				case "OF":
					//mètode que line posa el cursor al final de la línia
					break;
				case "2~":
					//mètide que implementa insert
					break;
				case "":
					//Trobar com es rep el backspace
					break;
			}
			this.flagCommandMode = false;
			


		}
	}
	
	public String readLine() throws IOException{
		// Aqui fem el setRaw i unsetRaw
		try{
			while(true){
				this.read();
			}
			return super.readLine();
		} finally{
			unsetRaw();
		}
	}
	
}

