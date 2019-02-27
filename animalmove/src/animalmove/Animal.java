package animalmove;

 import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Animal extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point point;
	boolean isLeft;
	Toolkit tool;
	File leftImage,rightImage;
	Animal() {
		tool=getToolkit();
	}
	public void setLeftImage(File f) {
		leftImage=f;
	}
	public void setRightImage(File f) {
		rightImage=f;
	}
	public void setIsLeft(boolean boo) {
		isLeft=boo;
	}
	public boolean getIsLeft() {
		return isLeft;
	}
	public void setAtPoint(Point p) {
		point=p;
	}
	public Point getAtPoint() {
		return point;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w=getBounds().width;
		int h=getBounds().height;
		try{
			if(isLeft==true) {
				Image image=tool.getImage(leftImage.toURI().toURL());
				g.drawImage(image,0,0,w,h,this);
			}
			else {
				Image image=tool.getImage(rightImage.toURI().toURL());
				g.drawImage(image,0,0,w,h,this);
			}
		}
		catch(Exception exp) { }
	}
}
