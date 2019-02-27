package animalmove;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.filechooser.*;

public class ChangeAnimalWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int amountOfAnimal=6;
	int distance=80;
	String RecordList=new String();
	Animal[] animal;
	Point[] point;
	Stone[] stone;
	HandleMouse handleMouse;
	AutoMoveAnimal autoMoveAnimal;
	File leftImageFile,rightImageFile;
	JButton renew,quit=null;
	JMenuBar bar;
	JMenu menuGrade,menuImage,menuRecord,menuHelp;
	JMenuItem oneGradeItem,twoGradeItem,threeGradeItem;
	JMenuItem leftImage,rightImage,defaultImage,Record;
	JMenuItem helpContent,aboutUs,autoButton;
	JPanel pCenter;
	ChangeAnimalWindow(){
		setTitle("动物换位");
		pCenter=new JPanel();
		pCenter.setBackground(Color.lightGray);
		pCenter.setLayout(null);
		handleMouse=new HandleMouse();
		autoMoveAnimal=new AutoMoveAnimal();
		leftImageFile=new File("dog.jpg");
		rightImageFile=new File("cat.jpg");
		init();
		bar=new JMenuBar();
		menuGrade=new JMenu("选择级别");
		menuImage=new JMenu("选择动物头像（ＪＰＧ，ＧＩＦ）");
		menuRecord = new JMenu("最高纪录");
		Record = new JMenuItem("最高纪录列表");
		oneGradeItem=new JMenuItem("初级");
		twoGradeItem=new JMenuItem("中级");
		threeGradeItem=new JMenuItem("高级");
		leftImage=new JMenuItem("左面动物的头像");
		rightImage=new JMenuItem("右面动物的头像");
		defaultImage=new JMenuItem("左右面动物的默认头像");
		helpContent=new JMenuItem("游戏规则");
		aboutUs=new JMenuItem("关于我们");   
		autoButton=new JMenuItem("自动演示");
		menuHelp=new JMenu("帮助"); 
		menuGrade.add(oneGradeItem);
		menuGrade.add(twoGradeItem);
		menuGrade.add(threeGradeItem);
		menuImage.add(leftImage);
		menuImage.add(rightImage);
		menuImage.add(defaultImage);
		menuRecord.add(Record);
		menuHelp.add(helpContent);                                 
		menuHelp.add(aboutUs);
		menuHelp.add(autoButton);
		bar.add(menuGrade);
		bar.add(menuImage);
		bar.add(menuRecord);
		bar.add(menuHelp);
		setJMenuBar(bar);
		oneGradeItem.addActionListener(this);
		twoGradeItem.addActionListener(this);
		threeGradeItem.addActionListener(this);
		leftImage.addActionListener(this);
		rightImage.addActionListener(this);
		defaultImage.addActionListener(this);
		autoButton.addActionListener(this);	
		Record.addActionListener(this);
		renew=new JButton("重新开始");
		renew.addActionListener(this);
		helpContent.addActionListener(this); 
		aboutUs.addActionListener(this);      
		quit=new JButton("撤销");
		quit.addActionListener(this);
		JPanel north=new JPanel();
		north.add(renew);
		north.add(quit);
		add(north,BorderLayout.NORTH);
		add(pCenter,BorderLayout.CENTER);
		JPanel south=new JPanel();
		south.add(handleMouse);
		add(south,BorderLayout.SOUTH);
		setVisible(true);
		setBounds(60,60,710,300);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void init(){
		animal=new Animal[amountOfAnimal];
		point=new Point[amountOfAnimal+1];
		stone=new Stone[amountOfAnimal+1];
		int space=distance;
		for(int i=0;i<point.length;i++){
			point[i]=new Point(space,100);
			space+=distance;
		}
		for(int i=0;i<animal.length;i++){
			animal[i]=new Animal();
			animal[i].addMouseListener(handleMouse);
			if(i<animal.length/2){
				animal[i].setIsLeft(true);
			}
			else{
				animal[i].setIsLeft(false);
			}
		}
		for(int i=0;i<stone.length;i++){
			stone[i]=new Stone();
			pCenter.add(stone[i]);
			stone[i].setSize(distance*8/9,18);
			int w=stone[i].getBounds().width;
			stone[i].setLocation(point[i].getX()-w/2,point[i].getY());
			point[i].setThisStone(stone[i]);
		}
		for(int i=0;i<animal.length;i++){
			animal[i].setSize(distance*6/7,distance*3/4);
			int w=animal[i].getBounds().width;
			int h=animal[i].getBounds().height;
			pCenter.add(animal[i]);
			if(i<animal.length/2){
				animal[i].setIsLeft(true);
				animal[i].setLeftImage(leftImageFile);
				animal[i].repaint();
				animal[i].setLocation(point[i].getX()-w/2,point[i].getY()-h);
				animal[i].setAtPoint(point[i]);
				point[i].setThisAnimal(animal[i]);
				point[i].setIsHaveAnimal(true);
			}
			else{
				animal[i].setIsLeft(false);
				animal[i].setRightImage(rightImageFile);
				animal[i].repaint();
				animal[i].setLocation(point[i+1].getX()-w/2,point[i+1].getY()-h);
				animal[i].setAtPoint(point[i+1]);
				point[i+1].setThisAnimal(animal[i]);
				point[i+1].setIsHaveAnimal(true);
			}
		}
		handleMouse.setPoint(point);
		handleMouse.setCountTime(true);
		autoMoveAnimal.setPoint(point);
		autoMoveAnimal.setStep();
	}
	public void setAmountOfAnimal(int m){
		if(m>=2&&m%2==0)
			amountOfAnimal=m;
	}
	public void removeAnimalAndStone(){
		for(int i=0;i<point.length;i++){
			if(point[i].getThisAnimal()!=null)
				pCenter.remove(point[i].getThisAnimal());
		}
		for(int i=0;i<stone.length;i++){
			if(point[i].getThisStone()!=null)
				pCenter.remove(point[i].getThisStone());
		}
		pCenter.validate();
		pCenter.repaint();
	}
	public void needDoing(){
		init();
		handleMouse.initStep();
		handleMouse.initSpendTime();
		handleMouse.setCountTime(true);
		autoMoveAnimal.setPoint(point);
	}

public void actionPerformed(ActionEvent e){
	if(e.getSource() == oneGradeItem){
		distance = 80;
		removeAnimalAndStone();
		setAmountOfAnimal(6);
		needDoing();
	}
	else if(e.getSource() == twoGradeItem){
		distance = 70;
		removeAnimalAndStone();
		setAmountOfAnimal(8);
		needDoing();
	}
	else if(e.getSource() == threeGradeItem){
		distance = 60;
		removeAnimalAndStone();
		setAmountOfAnimal(10);
		needDoing();
	}
	else if(e.getSource() == renew){
		removeAnimalAndStone();
		needDoing();
	}
	else if(e.getSource() == autoButton){
		removeAnimalAndStone();
		needDoing();
		handleMouse.setCountTime(false);
		autoMoveAnimal.setStep();
		int x = this.getBounds().x + this.getBounds().width/2;
		int y = this.getBounds().y + this.getBounds().height;
		autoMoveAnimal.setLocation(x,y);
		autoMoveAnimal.setSize(this.getBounds().width/2,120);
		autoMoveAnimal.setVisible(true);
	}
	else if(e.getSource() == quit){
		ArrayList<Integer>step = handleMouse.getStep();
		int length = step.size();
		int start = -1,end = -1;
		if(length >= 2){
			end = step.get(length - 1);
			start = step.get(length - 2);
			step.remove(length - 1);
			step.remove(length - 2);
			Animal ani = point[end].getThisAnimal();
			int w = ani.getBounds().width;
			int h = ani.getBounds().height;
			ani.setLocation(point[start].getX() - w/2,point[start].getY() - h);
			ani.setAtPoint(point[start]);
			point[start].setThisAnimal(ani);
			point[start].setIsHaveAnimal(true);
			point[end].setIsHaveAnimal(false);
		}
	}
	else if(e.getSource() == leftImage){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images","jpg","gif");
		chooser.setFileFilter(filter);
		int state = chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		if(file != null&&state ==JFileChooser.APPROVE_OPTION){
			leftImageFile = file;
			for(int i = 0;i < animal.length;i++){
				if(animal[i].getIsLeft() == true){
					animal[i].setLeftImage(leftImageFile);
					animal[i].repaint();
				}
			}
		}
	}
	else if(e.getSource() == rightImage){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images","jpg","gif");
		chooser.setFileFilter(filter);
		int state = chooser.showOpenDialog(null);
		File file = chooser.getSelectedFile();
		if(file != null&&state ==JFileChooser.APPROVE_OPTION){
			rightImageFile = file;
			for(int i = 0;i < animal.length;i++){
				if(animal[i].getIsLeft() == false){
					animal[i].setRightImage(rightImageFile);
					animal[i].repaint();
				}
			}
		}
	}
	else if(e.getSource() == defaultImage){
		leftImageFile = new File("dog.jpg");
		rightImageFile = new File("cat.jpg");
		for(int i = 0;i < animal.length;i++){
			if(animal[i].getIsLeft() == true) animal[i].setLeftImage(leftImageFile);
			else animal[i].setRightImage(rightImageFile);
			animal[i].repaint();
		}
	}
	else if(e.getSource() == Record){
		if(handleMouse.record.isEmpty())
			JOptionPane.showMessageDialog(null,"您尚没有游戏记录","英雄榜",JOptionPane.INFORMATION_MESSAGE);	
		else
		{
			RecordList="";
			for(int i=0;i<handleMouse.record.size()&&i<10;i++){
				RecordList = RecordList+"第"+(i+1+"")+"名    --------     "+handleMouse.record.get(i)+"秒\n";
			}
			JOptionPane.showMessageDialog(null,RecordList,"英雄榜",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	else if(e.getSource() == aboutUs){
		JOptionPane.showMessageDialog(this,
							"侯     芳   （侯    丹）          2012312279\n" +
							"   喵         （李隆苗）          2012312297\n" +
							"矮     油   （吴霭柳）          2012312303\n" +
							"小毛线   （李劲楠）           2012312306\n"+
	"		                                           From 电商12","我们是金星萝卜小组，又名424小队",JOptionPane.INFORMATION_MESSAGE);
		
	}
	else if(e.getSource() == helpContent){
		JOptionPane.showMessageDialog(this, "Rule one:     左右边的动物分别移到另一边\n"+
										"Rule two:     左边的动物只能往右边跳，右边的只能往左边跳\n"+
										"Rule three:  一次跳跃只能越过一块石头","游戏规则",JOptionPane.INFORMATION_MESSAGE);//具体实现
		
	}
	validate();
	}
	public static void main(String args[]){
		new ChangeAnimalWindow();
	}
}