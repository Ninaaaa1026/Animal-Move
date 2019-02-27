package animalmove;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public  class HandleMouse extends JPanel implements MouseListener,ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point[] point;
	ArrayList<Integer>step;
	public int spendTime=0;
	public ArrayList<Integer> record=new ArrayList<Integer>();
	javax.swing.Timer recordTime;
	boolean success=false,countTime=false;
	JTextField showTime;
	HandleMouse(){
		step=new ArrayList<Integer>();
		recordTime=new javax.swing.Timer(1000,this);
		showTime=new JTextField(26);
		showTime.setEditable(false);
		showTime.setHorizontalAlignment(JTextField.CENTER);
		showTime.setFont(new Font("楷体—GB2312",Font.BOLD,18));
		add(new JLabel("您的用时：",JLabel.CENTER));
		add(showTime);
		setBackground(Color.cyan);
	}
	public void setPoint(Point[] point) {
		this.point=point;
	}
	public void initStep() {
		step.clear();
	}
	public void initSpendTime()  {
		spendTime=0;
		showTime.setText(null);
		recordTime.stop();
	}
	public ArrayList<Integer>getStep() {
		return step;
	}
	public void setCountTime(boolean b) {
		countTime=b;
	}
	public void mousePressed(MouseEvent e) {
		URL url1 = getClass().getResource("696.wav");
		AudioClip clip1 = Applet.newAudioClip(url1);
		URL url2 = getClass().getResource("495.wav");
		AudioClip clip2 = Applet.newAudioClip(url2);
		if(countTime)
			recordTime.start();
		else
			showTime.setText("不给您计时");
		Animal animal=null;
		animal=(Animal)e.getSource();
		int w=animal.getBounds().width;
		int h=animal.getBounds().height;
		int m=-1;
		Point startPoint=animal.getAtPoint();
		for(int i=0;i<point.length;i++) {
			if(point[i].equals(startPoint)) {
				m=i;
				break;
			}
		}
		if(animal.getIsLeft()==true&&m<=point.length-2) {
			if(point[m+1].isHaveAnimal()==false) {
				clip1.play();
				animal.setLocation(point[m+1].getX()-w/2, point[m+1].getY()-h);
				animal.setAtPoint(point[m+1]);
				point[m+1].setThisAnimal(animal);
				point[m+1].setIsHaveAnimal(true);
				startPoint.setIsHaveAnimal(false);
				step.add(m);
				step.add(m+1);
			}
			else if(m+1<=point.length-2&&point[m+2].isHaveAnimal()==false) {
				clip1.play();
				animal.setLocation(point[m+2].getX()-w/2, point[m+2].getY()-h);
				animal.setAtPoint(point[m+2]);
				point[m+2].setThisAnimal(animal);
				point[m+2].setIsHaveAnimal(true);
				startPoint.setIsHaveAnimal(false);
				step.add(m);
				step.add(m+2);
			}
			else clip2.play();
		}
		else if(animal.getIsLeft()==false&&m>=1) {
			if(point[m-1].isHaveAnimal()==false) {
				clip1.play(); 
				animal.setLocation(point[m-1].getX()-w/2, point[m-1].getY()-h);
				animal.setAtPoint(point[m-1]);
				point[m-1].setThisAnimal(animal);
				point[m-1].setIsHaveAnimal(true);
				startPoint.setIsHaveAnimal(false);
				step.add(m);
				step.add(m-1);
			}
			else if(m-1>=1&&point[m-2].isHaveAnimal()==false) {
				clip1.play(); 
				animal.setLocation(point[m-2].getX()-w/2, point[m-2].getY()-h);
				animal.setAtPoint(point[m-2]);
				point[m-2].setThisAnimal(animal);
				point[m-2].setIsHaveAnimal(true);
				startPoint.setIsHaveAnimal(false);
				step.add(m);
				step.add(m-2);
			}
			else clip2.play();
		}
	}
	public void actionPerformed(ActionEvent e) {
		spendTime++;
		showTime.setText("您的用时"+spendTime+"秒");
	}
	public boolean isSuccess() {
		boolean boo=true;
		int i=0;
		for(i=0;i<point.length/2;i++) {
			if(point[i].getThisAnimal().getIsLeft()==true||point[i+point.length/2+1].getThisAnimal().getIsLeft()==false) {
				boo=false;
				break;
			}
		}
		return boo;
	}
	public void mouseReleased(MouseEvent e) {
		if(isSuccess()==true) {
			recordTime.stop();
			URL url = getClass().getResource("1761.wav");
			AudioClip clip = Applet.newAudioClip(url);
			clip.play();     
			record.add(spendTime);
			JOptionPane.showMessageDialog(this,"您成功了！用时"+spendTime+"秒！","消息框",JOptionPane.INFORMATION_MESSAGE);
			clip.stop();
		}
		int temp;
		for(int i=1;i<record.size();i++){
			for(int j=0;j<record.size()-i;j++)
			{
				if(record.get(j)>record.get(j+1))
				{
					temp=record.get(j);
					record.set(j,record.get(j+1));
					record.set(j+1,temp);
				}
			}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
}
