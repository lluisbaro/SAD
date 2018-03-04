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
		System.out.print("-----"+pos+ "-----");
	}

	public void delete(){ // esta malament xq ha de tenir un return
		System.out.print("--ESTA AQUI-------");
		if (pos>0){
					System.out.print("--ARRIBA AQUI??-------");

		this.line.remove(this.pos);
		System.out.print("--ARRIBA AQUI??-------");
		}
		//returns line.toString();
		/*
		System.out.print("-------"+pos+ "-------");
		if (this.pos > 0){
			System.out.print("--ESTA AQUI-------");
			this.line.remove(this.pos);
			System.out.print("--ESTA AQUI-------");
			this.pos--;
			System.out.print("-------"+pos+ "-------");
		}*/
	}

	public void supr(){
		if (pos >= 0){
			this.line.remove(this.pos +1);
			this.pos--;
		}
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
		String str = this.line.get(0).toString();
		for (int i=1;i<this.line.size(); i++){
			str = str+this.line.get(i).toString();
		}
		return str;
	}


}
