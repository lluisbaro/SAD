import java.io.*;

public class Cols {

	public static void main(String[] args) throws IOException{
		int cols, lines;
	
		try{
			//Després de crítiques del Francesc Oller ens ha dir que hem de buscar una manera alternativa de realitzar això sense fer ús del /dev/tty el /bin/sh i el -c
			Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "tput cols </dev/tty"});
			cols = Integer.parseInt(new BufferedReader(new InputStreamReader(p.getInputStream())).readLine());
			System.out.println("COL: " + cols);

			Process pl = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "tput lines </dev/tty"});
			lines = Integer.parseInt(new BufferedReader(new InputStreamReader(pl.getInputStream())).readLine());
			System.out.println("LINES: " + lines);

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
