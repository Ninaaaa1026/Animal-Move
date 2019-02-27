package animalmove;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AutoMoveAnimal extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point[] point;
	ArrayList<Integer>step;
	JButton bStart,bStop,bContinue,bClose;
	javax.swing.Timer time;
	int i,M,middle,startPoint,m=0;
	int[] a;
	AutoMoveAnimal() {
		step = new ArrayList<Integer>();
		setModal(true);
		setTitle("自动演示动物换位");
		time = new javax.swing.Timer(1000, this);
		time.setInitialDelay(10);
		bStart = new JButton("演示");
		bStop = new JButton("暂停");
		bStop.setEnabled(false);
		bContinue = new JButton("继续");
		bContinue.setEnabled(false);
		bClose = new JButton("关闭");
		bStart.addActionListener(this);
		bStop.addActionListener(this);
		bContinue.addActionListener(this);
		bClose.addActionListener(this);
		setLayout(new FlowLayout());
		add(bStart);
		add(bStop);
		add(bContinue);
		add(bClose);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent e){
								time.stop();
								i = startPoint;
								m = 0;
								bStart.setEnabled(true);
								bStop.setEnabled(false);
								bContinue.setEnabled(false);
								setVisible(false);
							}
											});
	}
	public void setPoint(Point[] point){
		this.point = point;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == time){
			int start,end;
			if(m <= step.size() - 2){
				start = step.get(m);
				end = step.get(m+1);
				autoMoveAnimal(start,end);
			}
			m += 2;
			if(m > step.size() - 1){
				time.stop();
				m = 0;
				bStart.setEnabled(true);
				bStop.setEnabled(false);
				bContinue.setEnabled(false);
				setVisible(false);
			}
		}
		else if(e.getSource() == bStart){
			time.start();
			bStart.setEnabled(false);
			bStop.setEnabled(true);
		}
		else if(e.getSource() == bStop){
			time.stop();
			bContinue.setEnabled(true);
		}
		else if(e.getSource() == bContinue){
			time.restart();
		}
		else if(e.getSource() == bClose){
			time.stop();
			i = startPoint;
			m = 0;
			bStart.setEnabled(true);
			bStop.setEnabled(false);
			bContinue.setEnabled(false);
			setVisible(false);
		}
	}
	private void autoMoveAnimal(int start,int end){
		Animal ani = point[start].getThisAnimal();
		int w = ani.getBounds().width;
		int h = ani.getBounds().height;
		ani.setLocation(point[end].getX() - w/2,point[end].getY() - h);
		ani.setAtPoint(point[end]);
		point[end].setThisAnimal(ani);
		point[end].setIsHaveAnimal(true);
		point[start].setIsHaveAnimal(false);
	}



private int get(int i,int[] a){
	int c=-1;
	if(i>=0&&i<=a.length-1)
		c=a[i];
	else
		c=-1;
	return c;
}

public void setStep(){
	a=new int[point.length];
	M=point.length-1;
	middle=(point.length)/2;
	startPoint=middle-1;
	i=startPoint;
	a[middle]=0;
	step=new ArrayList<Integer>();
	for(int k=0;k<=middle-1;k++)
		a[k]=1;
	for(int k=middle+1;k<a.length;k++)
		a[k]=2;
	while(true){
		if(get(i,a)==1){
			if(get(i+1,a)==0){
				if(get(i+2,a)==2||get(i+2,a)==-1){
					if(get(i+3,a)==2||get(i+3,a)==-1){
						if(i+2<=M){
							int temp=a[i+2];
							a[i+2]=a[i+1];
							a[i+1]=temp;
							step.add(i+2);
							step.add(i+1);
						}
						else{
							int temp=a[i+1];
							a[i+1]=a[i];
							a[i]=temp;
							step.add(i);
							step.add(i+1);
						}
					}
					else{
						int temp=a[i+1];
						a[i+1]=a[i];
						a[i]=temp;
						step.add(i);
						step.add(i+1);
					}
					}
				else{
					boolean ok=true;
					for(int k=Math.min(i+3, M);k<=M;k++)
					if(get(k,a)==2)
						ok=false;
					if(ok){
						int temp=a[i+1];
						a[i+1]=a[i];
						a[i]=temp;
						step.add(i);
						step.add(i+1);
					}
				}
				}
			else{
				if(get(i+2,a)==0){
					if(get(i+3,a)==2||get(i+3,a)==-1){
						int temp=a[i+2];
						a[i+2]=a[i];
						a[i]=temp;
						step.add(i);
						step.add(i+2);
					}
					else{
						boolean ok=true;
						for(int k=Math.min(i+4, M);k<=M;k++)
						if(get(k,a)==2)
							ok=false;
						if(ok){
							int temp=a[i+2];
							a[i+2]=a[i];
							a[i]=temp;
							step.add(i);
							step.add(i+2);
						}
					}
				}
			}
			}
		if(get(i,a)==2){
			if(get(i-1,a)==0){
						if(get(i-2,a)==1||get(i-2,a)==-1){
							if(get(i-3,a)==1||get(i-3,a)==-1){
								if(i-2>=0){
									int temp=a[i-1];
									a[i-1]=a[i-2];
									a[i-2]=temp;
									step.add(i-2);
									step.add(i-1);
								}
								else{
									int temp=a[i-1];
									a[i-1]=a[i];
									a[i]=temp;
									step.add(i);
									step.add(i-1);
								}
							}
							else{
								int temp=a[i-1];
								a[i-1]=a[i];
								a[i]=temp;
								step.add(i);
								step.add(i-1);
							}
						}
						else{
							boolean ok=true;
							for(int k=0;k<=i-2;k++)
							if(get(k,a)==1)
								ok=false;
							if(ok){
								int temp=a[i-1];
								a[i-1]=a[i];
								a[i]=temp;
								step.add(i);
								step.add(i-1);
						}
					}
			 	}
			else{
				if(get(i-2,a)==0){
					if(get(i-3,a)==1||get(i-3,a)==-1){
						int temp=a[i-2];
						a[i-2]=a[i];
						a[i]=temp;
						step.add(i);
						step.add(i-2);
			}
			else{
				boolean ok=true;
				for(int k=Math.min(i-4, 0);k>=0;k--)
				if(get(k,a)==1)
					ok=false;
				if(ok){
					int temp=a[i-2];
					a[i-2]=a[i];
					a[i]=temp;
					step.add(i);
					step.add(i-2);	
			}
			}
		}
		}
	}
		i++;
		if(i==M+1) i=0;
		if(isComplete()) break;
	}

}
private boolean isComplete(){
	boolean boo=true;
	int i=0;
	for(i=0;i<point.length/2;i++){
		if(get(i,a)==1||get(i,a)==0||get(i+point.length/2+1,a)==2||get(i+point.length/2+1,a)==0){
			boo=false;
			break;
		}
		}
	return boo;
	}
}
