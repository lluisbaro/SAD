import java.io.*;
import java.lang.*;


public class cols extends Runtime{

	public static void main(String[] args) throws IOException{
	
		try{
			//ProcessBuilder p = new ProcessBuilder("src/cols.sh");
			//p.command("sh", "-c", "stty size </dev/tty");
			//p.start();

			Process p = Runtime.getRuntime().exec("stty size"); 
			
			/*System.out.println("sdf");
			ProcessBuilder process = new ProcessBuilder();
			process.command("sh", "-c", "stty size </dev/tty");
			process.start();
			System.out.println("Enter in raw mode");
			*/
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	
}

