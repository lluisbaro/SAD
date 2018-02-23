public class Line{
	public int index;
	public ArrayList<char> bus = new ArrayList<char>();
	
	//Constructor
	public Line(){
		index = 0;
	}
	
	public void write(char car){
		this.bus.add(index, car);
		this.index++;
	}
	
	public void delete(){
		this.index--;
		this.bus.remove(this.index);
	}
	
	public void goLeft(){
		if(this.index>0)
			this.index--;
	}
	public void goRight(){
		if(this.index>(bus.size-1))
			this.index++;
	}
	public void supr(){
		this.bus.remove(this.index);
		this.index--;
	}
	

}
