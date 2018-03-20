import java.io.*;
import java.lang.*;
import java.util.*;

public class Line extends Observable{

	private int pos;
	private ArrayList<Character> line;
	private boolean insert;

	public Line(){
		pos = 0;
		line = new ArrayList<Character>();
		insert = false;
	}

	public int getPos(){
		return this.pos;
	}

	public int getSize(){
		return this.line.size();
	}

	public void addChar(char car){
		setChanged();
		if (this.pos<this.line.size()){
			if (this.insert){
				this.line.set(this.pos,car);
			} else{
				notifyObservers(SpecialKeys.INSERT);
				//System.out.print(String.format("%c[1@", 27));
				this.line.add(this.pos,car);
			}
		} else { 
			this.line.add(this.pos,car);
		}
		this.pos++;
	}

	public void delete(){
		setChanged();
		if (this.pos > 0){
			this.pos--;
			this.line.remove(this.pos);
			notifyObservers(SpecialKeys.BACKSPACE);
			//System.out.print(String.format("%c[D", 27));
			//System.out.print(String.format("%c[P", 27));
		}
	}

	public void supr(){
		setChanged();
		if (this.pos >= 0 && this.pos < this.line.size()){
			this.line.remove(this.pos);
			notifyObservers(SpecialKeys.SUPRIMIR);
			//System.out.print(String.format("%c[P", 27));
		}
	}

	public void goRight(){
		setChanged();
		if (this.pos < line.size()){
			this.pos++;
			notifyObservers(SpecialKeys.FLETXA_DRT);
		//	System.out.print(String.format("%c[C", 27));
		}
	}

	public void goLeft(){
		setChanged();
		if (pos > 0){
			this.pos--;
			notifyObservers(SpecialKeys.FLETXA_ESQ);
		//	System.out.print(String.format("%c[D", 27));
		}
	}

	public void home(){
		setChanged();
		this.pos = 0;
		notifyObservers(SpecialKeys.HOME);
	//	System.out.print(String.format("%c[G", 27));
	}

	public void fin(){
		setChanged();
		this.pos = this.line.size();
		notifyObservers(SpecialKeys.FIN);
	//	System.out.print(String.format("%c["+(this.line.size()+1)+"G", 27));
	}

	public void insert(){
		this.insert = !this.insert;
	}

	public String toString(){
		String str = this.line.get(0).toString();
		for (int i=1;i<this.line.size(); i++){
			str = str+this.line.get(i).toString();
		}
		return str;
	}

}
