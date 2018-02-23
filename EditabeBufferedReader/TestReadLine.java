import java.io.*;

class TestReadLine {
    public static void main(String[] args) {
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        //ha d'estar a readline
        //in.setRaw(); //entrem a raw mode per capturar caracters
        
        try {
            str = in.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nline is: " + str);
        //in.unsetRaw(); //sortim mode raw per tornar estat normal
    }
}
