import java.io.*;

class test {
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //String str = null;
        int a = 0;

        try {
            //str = in.readLine();
            ProcessBuilder process = new ProcessBuilder();
			process.command("sh", "-c", "stty -echo raw </dev/tty");
			process.start();
            a = in.read();
            while(true){
            	System.out.println("\nline is: " + a);
            	a = in.read();
            	}
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + a);

    }
}
