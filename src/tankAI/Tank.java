package tankAI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Tank {
	private int NO;
	private boolean life=true;
	private int x;
	private int y;
	private int speed=3;
	private int buttle_speed=6;
	private int hp;
	private int max_hp;
	private Direction direction=Direction.STOP;
	private Direction last_direction=Direction.STOP;
	private boolean fire_now;
	private Ai myai;
	private World world;
	private int fire_cd;
	private int attack_point=0;
	private int live_point=0;	

	Tank(int x,int y,Direction d,Ai ai,World w,int number)
	{
		world=w;
		myai=ai;
		this.direction =d;
		last_direction=direction;
		this.x=x;
		this.y=y;
		if(ai.speed<5&&ai.speed>1)
		{
			this.speed =ai.speed;
			max_hp=7-speed;
			hp=max_hp;
		}
		if(ai.bullet_speed<10&&ai.bullet_speed >4)
		{
			this.buttle_speed=ai.bullet_speed ;
		}
		myai.initialize(w,this);
		NO=number;
	}
	
	int getLivePoint() {
		return live_point;
	}

	void setLivePoint(int live_point) {
		this.live_point = live_point;
	}
	
	void attackPointUp(int point)
	{
		attack_point+=point;
	}
	
	int getAttackPoint()
	{
		return attack_point;
	}
	
	int getNO()
	{
		return NO;
	}
	
	int getX()
	{
		return x;
	}
	
	int getY()
	{
		return y;
	}
	
	Ai getAi()
	{
		return myai;
	}
	
	int getHp()
	{
		return hp;
	}
	
	Direction getDirection()
	{
		return last_direction;
	}
	
	void move(Direction d)//提供两种移动方式
	{
		direction=d;
	}
	
	void fire()
	{
		fire_now=true;
	}
	
	TankInformation getInformation()
	{
		return new TankInformation(x,y,direction,hp,speed,NO);
	}
	
	ArrayList<TankInformation> getOtherTanks()
	{
		ArrayList<TankInformation> tis=world.getTanksInformation();
		for(int i=0;i<tis.size();i++)
		{
			TankInformation ti =tis.get(i);
			if(ti.NO==getNO())
			{
				tis.remove(ti);
				i--;
			}
		}
		return tis;
	}
	

	
	private void startFire()
	{
		if(fire_now&&fire_cd==0)
		{
			fire_cd=50;
			int dx=0;
			int dy=0;
			switch(last_direction)
			{
			//创建一颗炮弹，声效爆炸特效等
			case UP:
				dy=-30;
				break;
			case DOWN:
				dy=30;
				break;
			case LEFT:
				dx=-30;
				break;
			case RIGHT:
				dx=30;
				break;
			}
			world.addButtle(new Bullet(x+dx,y+dy,dx/25*buttle_speed,dy/25*buttle_speed,this));
		}
		fire_now=false;
	}
		
	private void startMove()
	{
		if(direction!=Direction.STOP )
		{
			if(last_direction!=direction)
			{
				last_direction=direction;
				return;
			}
		}
		
		if(canmove())
		{
			switch(direction)
			{
			case UP:
				y-=speed;
				break;
			case DOWN:
				y+=speed;
				break;
			case LEFT:
				x-=speed;
				break;
			case RIGHT:
				x+=speed;
				break;
			}
		}
		direction=Direction.STOP;
	}
	
	private boolean canmove()
	{
		int xmove=x;
		int ymove=y;
		boolean can=true;
		switch(direction)
		{
		case UP:
			ymove-=speed;
			break;
		case DOWN:
			ymove+=speed;
			break;
		case LEFT:
			xmove-=speed;
			break;
		case RIGHT:
			xmove+=speed;
			break;
		}
		if(xmove-20<0||xmove+35>900||ymove-20<0||ymove+60>600)
			can=false;
		
		for(TankInformation ti:getOtherTanks())
		{
			if(Math.abs(xmove-ti.x)<40&&Math.abs(ymove-ti.raw_y)<40&&ti.hp>0)
				can=false;
		}
		
		return can;		
	}
				
	private void explode()//爆炸动画效果伤害效果
	{
		
	}
	/**
	 * @return if the tank still alive return true else return false
	 *        如果坦克仍然未被破坏就返回true 否则返回false
	*/
	boolean gethurt(int damage)
	{
		hp-=damage;
		if(hp<=0)
		{
			life=false;
			explode();
			return false;
		}else
		{
			return true;
		}
	}
		
	void run()
	{
		if(life)
		{
			if(fire_cd>0)
			{
				fire_cd--; 
			}			
			startMove();
			startFire();
		}
	}
		
	void paint(Graphics g)//传入画笔,以坦克中心点为X/Y
	{
		if(life)
		{
			if((float)hp/max_hp>0.8f)
			{
				g.setColor(Color.green);
			}else if((float)hp/max_hp>0.5f)
			{
				g.setColor(Color.yellow);
			}
			else if(hp>1)
			{
				g.setColor(Color.orange);
			}else
			{
				g.setColor(Color.red);
			}
			
			float length=40/max_hp;
			for(int i=0;i<hp;i++)//画血条
			{
				g.fill3DRect((int)(x-20+i*length), y-30, (int)length, 5, false);
			}
										
			g.setColor(Color.blue );
			g.fill3DRect(x-10, y-10, 20, 20,true);
			
			switch(last_direction)
			{
			case UP:
				g.fill3DRect(x-20, y-20, 10, 40,true);
				g.fill3DRect(x+10, y-20, 10, 40,true);
				g.fill3DRect(x-2, y-20, 6, 20,true);
				break;
			case DOWN:
				g.fill3DRect(x-20, y-20, 10, 40,true);
				g.fill3DRect(x+10, y-20, 10, 40,true);
				g.fill3DRect(x-2, y, 6, 20,true);
				break;
			case RIGHT:
				g.fill3DRect(x-20, y-20, 40, 10,true);
				g.fill3DRect(x-20, y+10, 40, 10,true);
				g.fill3DRect(x, y-2,20, 6,true);
				break;
			case LEFT:
				g.fill3DRect(x-20, y-20, 40, 10,true);
				g.fill3DRect(x-20, y+10, 40, 10,true);
				g.fill3DRect(x-20, y-2,20, 6,true);
				break;
			}
			g.setColor(Color.white);//坦克编号
			g.drawString(""+NO, x-4,y+2);		
		}
	}	
	
	//方便分离权限
	
}

