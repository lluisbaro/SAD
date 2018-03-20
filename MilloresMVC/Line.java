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
		}
	}

	public void supr(){
		setChanged();
		if (this.pos >= 0 && this.pos < this.line.size()){
			this.line.remove(this.pos);
			notifyObservers(SpecialKeys.SUPRIMIR);
		}
	}

	public void goRight(){
		setChanged();
		if (this.pos < line.size()){
			this.pos++;
			notifyObservers(SpecialKeys.FLETXA_DRT);
		}
	}

	public void goLeft(){
		setChanged();
		if (pos > 0){
			this.pos--;
			notifyObservers(SpecialKeys.FLETXA_ESQ);
		}
	}

	public void home(){
		setChanged();
		this.pos = 0;
		notifyObservers(SpecialKeys.HOME);
	}

	public void fin(){
		setChanged();
		this.pos = this.line.size();
		notifyObservers(SpecialKeys.FIN);
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
