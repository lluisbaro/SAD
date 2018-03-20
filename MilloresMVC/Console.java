import java.util.*;
import java.io.*;
import java.lang.*;

public class Console implements Observer {
	private Line line;

	public Console(Line line){
		this.line = line;
	}
        @Override
	public void update(Observable o, Object arg){
		int seq = ((Integer)arg).intValue();
		//System.out.print(seq);

		switch (seq){
			case SpecialKeys.BACKSPACE:
				System.out.print(String.format("%c[D", 27));
				System.out.print(String.format("%c[P", 27));
				break;
			case SpecialKeys.FLETXA_ESQ:
				System.out.print(String.format("%c[D", 27));
				break;
			case SpecialKeys.FLETXA_DRT:
				System.out.print(String.format("%c[C", 27));
				break;
			case SpecialKeys.HOME:
				System.out.print(String.format("%c[G", 27));
				break;
			case SpecialKeys.FIN:
				System.out.print(String.format("%c["+(this.line.getSize()+1)+"G", 27));
				break;
			case SpecialKeys.SUPRIMIR:
				System.out.print(String.format("%c[P", 27));
				break;
			case SpecialKeys.INSERT:
				System.out.print(String.format("%c[1@", 27));
				break;
		}
	}
}