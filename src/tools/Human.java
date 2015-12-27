package tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import tankAI.Ai;
import tankAI.Direction;

/**
 * you can let a MyAi class extend this class in order to control this tank use your 
 * keyboard, it can only be used in your own test. use WASD to move ,J to fire<br>
 * 让一个MyAi类继承这个类，就能用键盘控制相应的坦克，这只能在你自己的测试过程中被使用。用WASD
 * 来移动，J键开火
 */
public class Human extends Ai implements KeyListener{

	Direction cdirection=Direction.STOP;
	boolean fire=false;
	protected final void command() {
		// TODO Auto-generated method stub
		move(cdirection);				
		System.out.println(getX());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			cdirection=Direction.UP;
		}else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			cdirection=Direction.DOWN;
		}else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			cdirection=Direction.LEFT;
		}else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			cdirection=Direction.RIGHT;
		}else if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			fire();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()!=KeyEvent.VK_J)
			cdirection=Direction.STOP;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
