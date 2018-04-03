/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExerciciClasse;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author jordimaripare
 */
public class WriteInts {

    public static void main(String args[]) {
        try {
            int i;
            Scanner s = new Scanner(System.in);
            DataOutputStream out = new DataOutputStream(System.out);
            while (s.hasNextInt()) {
                i = s.nextInt();
                out.writeInt(i);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
