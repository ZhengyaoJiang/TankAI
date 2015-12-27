package tankAI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.*;

import javax.swing.*;

import tools.Human;
import tools.Point;

class World extends JPanel{
	private boolean start;
	private int dead_tank_number=0;
	private GameState game_state=GameState.Battle;
	private List<Tank> tanks=new ArrayList<Tank>();
	private List<Tank> die_tanks=new ArrayList<Tank>();
	private List<Bullet> bullets=Collections.synchronizedList(new LinkedList<Bullet>());
	World(JFrame jf)
	{
		List<Point> tank_position=Arrays.asList(new Point(100,100),new Point(400,150),
				new Point(700,200),new Point(150,400),new Point(450,450),new Point(750, 500));
		Class<?> ai_class=null;
		int tank_amount=0;
		for(int i=1;i<100;i++)
		{
			String number_string="";
			if(i<10)
				number_string="0"+i;
			else
				number_string=Integer.toString(i);
			try
			{
				ai_class=Class.forName("AI"+number_string+".MyAi");
				Ai ai=(Ai)ai_class.newInstance();
				Point p=tank_position.get(tank_amount);
				if(p.y<300)
					tanks.add(new Tank(p.x,p.y,Direction.UP ,ai,this,i));
				else
					tanks.add(new Tank(p.x,p.y,Direction.DOWN ,ai,this,i));
				if(ai instanceof Human)
				{
					jf.addKeyListener((Human) ai);
				}
				tank_amount++;
			}catch(ClassNotFoundException e)
			{
				continue;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
		dead_tank_number=0;
		startThread();
	}
	private void startThread()
	{
		MainThread mt=new MainThread();
		mt.setPriority(10);
		mt.start();		
		PaintThread pt=new PaintThread();
		pt.start();
		for(Tank t:tanks)
		{
			AiThread at=new AiThread(t.getAi(),mt);
			at.setPriority(6);
			at.start();
		}
	}
	void addButtle(Bullet b)
	{
		bullets.add(b);
	}
	
	void removeButtle(Bullet b)
	{
		bullets.remove(b);
	}
	
	
	boolean isStart()
	{
		return start;
	}
	
	ArrayList<TankInformation> getTanksInformation()
	{
		ArrayList<TankInformation> al=new ArrayList<TankInformation>();
		for(Tank t:tanks)
    	{
    		al.add(t.getInformation());
    	}
		return al;		
	}
	
	ArrayList<BulletInformation> getButtlesInformation()
	{
		ArrayList<BulletInformation> al=new ArrayList<BulletInformation>();
		for(Bullet b:bullets)
    	{
    		al.add(b.getInformation());
    	}
		return al;		
	}
	
	@Override
    public void paint(Graphics g) {
	    super.paint(g);
	    if(game_state==GameState.Battle)
	    {
	    	for(Tank t:tanks)
	    	{
	    		t.paint(g);
	    	}
	    	synchronized(this) { 
		    	for(Bullet b:bullets)
		    	{
		    		b.paint(g);
		    	}
	    	}
	    }else
	    {
	    	g.setColor(Color.WHITE);
	    	Collections.sort(tanks, new Comparator<Tank>()
			{
				public int compare(Tank t1, Tank t2) {
					return (t2.getLivePoint()+t2.getAttackPoint())-(t1.getLivePoint()+t1.getAttackPoint());
				}
			});
	    	for(int i=0;i<tanks.size();i++)
	    	{
	    		Tank t=tanks.get(i);
	    		g.setFont(new Font(Font.DIALOG,Font.BOLD,20));
	    		g.drawString("Tank Number : "+t.getNO()+"   Live Point : "+t.getLivePoint()+
	    				"   Attack Point : "+t.getAttackPoint()+"  Total Point : "+
	    				(t.getLivePoint()+t.getAttackPoint())+"   Rank : "+(i+1)
	    				, 100, 80+i*80);
	    	}
	    }
    }		
	
	private class MainThread extends Thread
	{
		
		synchronized void waitAction() throws InterruptedException
		{
			wait();
		}
		
		synchronized void finishAction()
		{
			notifyAll();
		}
		
		private void judgehit()
		{
			for(Tank t:tanks)
			{
				if(t.getHp()>0)
				{
					for(int i=0;i<bullets.size();i++)
					{
						BulletInformation bi=bullets.get(i).getInformation();
						if(Math.abs(bi.x-t.getX())<22&&Math.abs(bi.raw_y-t.getY())<22)
						{
							if(t.gethurt(bi.power)==false)
								t.setLivePoint((dead_tank_number++)*3);
							if(tanks.size()-dead_tank_number<=1)
							{
								for(Tank t2:tanks)
								{
									if(t2.getHp()>0)
									{
										t2.setLivePoint(dead_tank_number*3);
									}
								}
								game_state=GameState.Over;
							}
							bullets.get(i).getShooter().attackPointUp(bi.power);;
							bullets.remove(i);
							i--;
						}					
					}
				}
			}
		}
		
		private void judgeout()
		{
			for(int i=0;i<bullets.size();i++)
			{
				BulletInformation bi=bullets.get(i).getInformation();
				if(bi.x<0||bi.x>900||bi.raw_y>600||bi.raw_y<0)
				{
					bullets.remove(i);
					//System.out.println("remove");
					i--;
				}
			}
		}
		
		public void run()
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(game_state==GameState.Battle)
			{				 
				synchronized(World.this) { 
					judgehit();
					judgeout();
					for(Bullet b:bullets)
			    	{
			    		b.run();			    		
			    	}
					for(Tank t:tanks)
					{
						t.run();
					}
					finishAction();
				}

				try {
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class PaintThread extends Thread
	{
		public void run()
		{
			while(game_state==GameState.Battle)
			{				
				repaint();
				try {
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(game_state==GameState.Over)
			{
				repaint();
			}
		}
	}
	
	private class AiThread extends Thread
	{
		private Ai ai;
		private MainThread mt;
		AiThread(Ai ai,MainThread mt)
		{
			this.ai=ai;
			this.mt=mt;
		}
		public void run()
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while(game_state==GameState.Battle)
			{	
				try {
					mt.waitAction();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ai.run();
			}
		}
	}
}

enum GameState
{
	Battle,
	Over
}