package AI06;
import tankAI.*;
import tools.*;
import java.util.*;
import java.lang.*;
import java.math.*;


public class MyAi extends Ai{

	public MyAi()
	{
		speed=4;
		bullet_speed=6;
	}
	
	@Override
	public void command() {
		// TODO Auto-generated method stub
		double ran=Math.random();
		if(ran<0.91)
		{
			move(getDirection());
		}else if(ran<0.92)
		{
			moveUp();
		}else if(ran<0.93)
		{
			moveDown();
		}else if(ran<0.94)
		{
			moveLeft();
		}else if(ran<0.95)
		{
			moveRight();
		}else
		{
			fire();
		}
	}

}

