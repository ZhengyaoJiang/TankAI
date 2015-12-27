package tankAI;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int power;
	private Tank shooter;


	Bullet(int x,int y,int vx,int vy,Tank tank)
	{
		this.x=x;
		this.y=y;
		this.vx=vx;
		this.vy=vy;
		shooter=tank;
		power=5-Math.abs((vx+vy)/2);
	}
	
	Tank getShooter() {
		return shooter;
	}
	
	BulletInformation getInformation()
	{
		return new BulletInformation(x,y,vx,vy,power);
	}
	
	void paint(Graphics g)
	{
		g.setColor(Color.yellow);
		g.fillOval(x-2, y-2, 4, 4);
	}
	
	void run()
	{
		x+=vx;
		y+=vy;
	}

}
