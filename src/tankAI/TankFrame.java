package tankAI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

class TankFrame extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TankFrame frame=new TankFrame();
		World world=new World(frame);
		world.setLayout(new GridLayout()); 
		frame.add(world);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(900, 600);
		world.setBackground(Color.black);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();   
		Dimension frameSize = frame.getSize();      
		if (frameSize.width > displaySize.width)  
		frameSize.width = displaySize.width;           
		if (frameSize.height > displaySize.height)  
		frameSize.height = displaySize.height;          
		frame.setLocation((displaySize.width - frameSize.width) / 2,  
		(displaySize.height - frameSize.height) / 2);
		frame.setVisible(true);                
	}

}
