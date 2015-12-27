package tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import tankAI.Ai;
import tankAI.Direction;

/**
 * you can let a MyAi class extend this class in order to control this tank use your 
 * keyboard, it can only be used in your own test. use WASD to move ,J to fire<br>
 * ��һ��MyAi��̳�����࣬�����ü��̿�����Ӧ��̹�ˣ���ֻ�������Լ��Ĳ��Թ����б�ʹ�á���WASD
 * ���ƶ���J������
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
