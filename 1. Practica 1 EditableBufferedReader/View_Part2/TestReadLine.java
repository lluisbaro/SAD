import java.io.*;
import java.lang.*;

class TestReadLine {
    public static void main(String[] args) {
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
       // int car = 0;
        
        try {
            str = in.readLine();
          //  car = in.read();
        } catch(IOException e) {
            System.out.println("Line is empty");
            e.printStackTrace();
        }
        System.out.println("\nline is: " + str);
        //System.out.println("\nline is: " + car);
    }
}
