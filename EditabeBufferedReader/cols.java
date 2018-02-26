import java.io.*;

public class cols{

	public static void main(String[] args) throws IOException{
		int cols, lines;
	
		try{

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