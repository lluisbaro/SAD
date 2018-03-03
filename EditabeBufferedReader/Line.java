import java.io.*;
import java.lang.*;
import java.util.*;

public class Line{

	private int pos;
	private ArrayList<Character> line;

	public Line(){
		pos = 0;
		line = new ArrayList<Character>();
	}

	public int getPos(){
		return this.pos;
	}

	public void addChar(char car){
		this.line.add(this.pos,car);
		this.pos++;
	}

	public void delete(){
		this.pos--;
		this.line.remove(this.pos);
	}

	public void supr(){
		this.line.remove(this.pos);
		this.pos--;
	}

	public void goRight(){
		if (this.pos > (line.size() - 1)){
			this.pos++;
		}
	}

	public void goLeft(){
		if (pos > 0){
			this.pos--;
		}
	}

	public void home(){
		this.pos = 0;
	}

	public String toString(){
	//	El for comença a 0
		
		String str = null;
		for (int i = 0 ; i < this.line.size() ; i++){
			str = str + this.line.get(i).toString();
		}
		
		/*
		String str = this.line.get(0).toString();
		for (int i=1;i<this.line.size(); i++){
			str = str+this.line.get(i).toString();
		}
		*/
		return str;
	}


}
