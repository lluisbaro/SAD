import java.io.*;
import java.lang.*;
import java.util.*;

public class Line{

	public int pos;
	public ArrayList<Character> line;

	public Line(){
		pos = 0;
		line = new ArrayList<Character>();
	}

	public int getPos(){
		return this.pos;
	}

	public void addChar(char car){
		this.line.add(this.pos,car);
		this.pos ++;
	}

	public void delete(){
		this.pos --;
		this.line.remove(this.pos);
	}

	public void supr(){
		this.line.remove(this.pos);
		this.pos --;
	}

	public void goLeft(){
		if (this.pos > (line.size() - 1)){
			this.pos++;
		}
	}

	public void goRight(){
		if (pos > 0){
			this.pos --;
		}
	}


}