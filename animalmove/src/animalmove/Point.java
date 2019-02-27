package animalmove;


public class Point {
	int x,y;
	boolean haveAnimal;
	Animal animal=null;
	Stone stone;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean isHaveAnimal(){
		return haveAnimal;
	}
	public void setIsHaveAnimal(boolean boo){
		haveAnimal=boo;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean equals(Point p){
		if(p.getX()==this.getX()&&p.getY()==this.getY())
			return true;
		else
			return false;
	}
	public void setThisAnimal(Animal animal){
		this.animal=animal;
	}
	public Animal getThisAnimal(){
		return animal;
	}
public void setThisStone(Stone stone){
	this.stone=stone;
}
public Stone getThisStone(){
	return stone;
}
}
