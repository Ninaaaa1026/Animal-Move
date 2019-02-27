package animalmove;

import javax.swing.*;
public class Stone extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Icon iconStone;
	Stone() {
		iconStone=new ImageIcon("stone.jpg");
		setIcon(iconStone);
	}

}
