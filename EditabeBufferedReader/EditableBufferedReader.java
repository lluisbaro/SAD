import java.io.*;


public class EditableBufferedReader extends BufferedReader{
	//declarem variables CTE
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
		//API Runtime.exec()
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
		
		try{

			car = new int[10];

			if (car[0] == ) {
				
			})
		
		
		
		
		
			int car[10] = 0;
		
			if ( car[4] == "^[[D" ) return FLETXA_ESQ;
		
			if
		
			^[[L
		
			return car [0];
		
			if ( car == "fletxa esquerra") return FLETXA_ESQ;
		
			//llegir dades
			gg
		
			//guardar 
		
			//comparar el llegit
		
			if ( a == [[D)
			fes fletxa esq
		
	
			return super.read();
			
			
		} catch (IOException e){
			e.printStackTrace();
		}		
	}
	
	public String readLine() throws IOException{

		// aqui fem el bucle i invoquema setRaw i unsetRar
		try{
			setRaw();
		
			while(car = this.read()){
				this.read();

				car = 0;
			}
			return super.readLine();
		} finally{
			unsetRaw();
		}
	
	}
	
}

