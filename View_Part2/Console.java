import java.util.*;
import java.io.*;
import java.lang.*;

public class Console implements Observer {
	private Line line;

	public Console(Line line){
		this.line = line;
	}

	public void update(Observable o, Object arg){
		int seq = ((Integer)arg).intValue();
		//System.out.print(seq);

		switch (seq){
			case Seq_ESC.BACKSPACE:
				System.out.print(String.format("%c[D", 27));
				System.out.print(String.format("%c[P", 27));
				break;
			case Seq_ESC.FLETXA_ESQ:
				System.out.print(String.format("%c[D", 27));
				break;
			case Seq_ESC.FLETXA_DRT:
				System.out.print(String.format("%c[C", Seq_ESC.ESC));
				break;
			case Seq_ESC.HOME:
				System.out.print(String.format("%c[G", Seq_ESC.ESC));
				break;
			case Seq_ESC.FIN:
				System.out.print(String.format("%c["+(this.line.getSize()+1)+"G", Seq_ESC.ESC));
				break;
			case Seq_ESC.SUPR:
				System.out.print(String.format("%c[P", Seq_ESC.ESC));
				break;
		}
	}
}