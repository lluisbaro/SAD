import java.io.*;
//import EditableBufferedReader.java;


class Cols {

	public static void main(String[] args){
		int cols=0, lines=0;
	
		try{
		//	EditableBufferedReader.setRaw();
			
			// Alternativa 1
			
			/*Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "tput cols </dev/tty"});
			cols = Integer.parseInt(new BufferedReader(new InputStreamReader(p.getInputStream())).readLine());
			System.out.println("COL: " + cols);

			Process pl = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "tput lines </dev/tty"});
			lines = Integer.parseInt(new BufferedReader(new InputStreamReader(pl.getInputStream())).readLine());
			System.out.println("LINES: " + lines);*/
			
			// Alternativa 2		/*Codi Definitiu per a trobar les columnes */
			
		    cols = Integer.parseInt(System.getenv("COLUMNS"));	// s'ha d'exportar la variable fer > export COLUMNS
		    lines = Integer.parseInt(System.getenv("LINES"));
		    
        
        	// Alternativa 3 		/*Codi per mitjançant les SEQ_ESC*/
       		//CSI Ps ; Ps ; Ps 
        	//CSI 18 ; height ; width t 
		    // report size of text area in chars: CSI 18 t
		    // should return ESC [ 8 ; rmax ; cmax t
		    
		    //System.out.print("\033[18t");
		
			// Alternativa 3
			// ESC [ 8 ; rmax ; cmax t
			/*
			Scanner sc = new Scanner(System.in);
			sc.skip("\033\\[8;\\d+;(\\d+)t");
			cols = Integer.parseInt(sc.match().group(1));
			*/
			
			// Alternativa 4
			/*
			Scanner sc = new Scanner(System.in);
			sc.skip("\033\\[8;").useDelimiter(";");
			sc.nextInt(); // skip rmax
			sc.skip(";").useDelimiter("t");
			cols = sc.nextInt();
			sc.skip("t");
			*/
			
			// Alternativa 5
			/*
			String str = readAll(new BufferedReader(new InputStreamReader(System.in)));
			String cmax = str.substring(
				str.indexOf(";", str.indexOf(";") + 1) + 1,
				str.length() - 1);
			cols = Integer.parseInt(cmax);
			*/
			
			// Alternativa 6	//alguna amb metodes de pel mig que no he copiat
			// ESC [ 8 ; rmax ; cmax t
			/*
			BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
			
		    */
        
		} finally {
	//	EditableBufferedReader.unsetRaw();
		}
		// Printem les variables
		System.out.print("\nColumnes: "+cols+"\nLinies: "+lines+"\n\n");
	}
}
