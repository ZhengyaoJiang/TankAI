package tankAI;
import java.util.*;

/**
 * you can access information of the game and give commands to your tank by extend this class,but 
 * the command will not be executed until next tick <br>
 * 你可以通过继承这个类来获得游戏中的信息，并给自己的坦克发布命令。但是这些命令要到命令发布后的下一个
 * 游戏刻才会被真正执行
 */
public abstract class Ai {
	private Tank mytank;
	private World world;
	/**
	 * the speed of the tank,the faster your tank is ,the less hp your tank will have.
	 * the default value is 3,you can choose a value from 2\3\4,the hp of the tank equals 7-speed<br>
	 * you can only change the number validly in the constructor<br>
	 * 坦克的移动速度，移动速度越快坦克的血量（耐久度）也就越低，默认为3，可选值为2\3\4。
	 * 坦克血量等于7-坦克速度。<br>
	 * 只有在构造器（即 public MyAi()）中改变这个数值才是有效的，否则不会影响坦克实际的速度。
	 */
	public int speed=3;
	/**
	 * the speed of the bullet,the faster your bullet is ,the less damage your bullet will cause.
	 * the default value is 5,you can choose a value from 5\6\7\8\9. the damage of the bullet equals
	 * 5-(speed/2), the fractional part will be cut, so it's better to choose an odd value.<br>
	 * you can only change the number validly in the constructor<br>
	 * 子弹的速度，子弹速度越快子弹所能造成伤害越低，默认为5，可选值为5\6\7\8\9，
	 * 子弹伤害等于5-（子弹速度/2），用去尾法去掉小数部分，故选用奇数速度会更为有利<br>
	 * 只有在构造器（即 public MyAi()）中改变这个数值才是有效的，否则不会影响子弹实际的速度
	 */
	public int bullet_speed=5;
	private int temp_x;
	private int temp_y;
	private Direction temp_direction;
	private int temp_hp;
	private ArrayList<TankInformation> temp_ti;
	private ArrayList<BulletInformation> temp_bi;
	
	
	final void initialize(World w,Tank t)
	{
		if(world==null)
		{
			world=w;
			mytank=t;
		}
	}
	/**
	 * @return the X coordinate of your tank<br>
	 * 返回你的坦克的横坐标
	 */
	protected final int getX()
	{
		return temp_x;
	}
	/**
	 * @return the Y coordinate of your tank<br>
	 * 返回你的坦克的纵坐标
	 */
	protected final int getY()
	{
		return temp_y;
	}
	/**
	 * @return the Hp of your tank<br>
	 * 返回你坦克的血量（耐久度）
	 */
	protected final int getHp()
	{
		return temp_hp;
	}
	/**
	 * @return the direction that your tank face to<br>
	 * 返回你坦克面朝的方向
	 */
	protected final Direction getDirection()
	{
		return temp_direction;
	}
	/**
	 * let your tank move down next tick. if your tank does not face down,it will veer instead
	 * of moving<br>
	 * 让你的坦克在下一个游戏刻向下移动。如果你的坦克不是面朝下方，下一游戏刻它将会转向而不是移动
	 */
	protected final void moveDown()
	{
		mytank.move(Direction.DOWN);
	}
	/**
	 * let your tank move up next tick. if your tank does not face up,it will veer instead
	 * of moving<br>
	 * 让你的坦克在下一个游戏刻向上移动。如果你的坦克不是面朝上方，下一游戏刻它将会转向而不是移动
	 */
	protected final void moveUp()
	{
		mytank.move(Direction.UP);
	}
	/**
	 * let your tank move left next tick. if your tank does not face left,it will veer instead
	 * of moving<br>
	 * 让你的坦克在下一个游戏刻向左移动。如果你的坦克不是面朝左方，下一游戏刻它将会转向而不是移动
	 */
	protected final void moveLeft()
	{
		mytank.move(Direction.LEFT);
	}
	/**
	 * let your tank move right next tick. if your tank does not face right,it will veer instead
	 * of moving<br>
	 * 让你的坦克在下一个游戏刻向右移动。如果你的坦克不是面朝右方，下一游戏刻它将会转向而不是移动
	 */
	protected final void moveRight()
	{
		mytank.move(Direction.RIGHT);
	}
	/**
	 * the move command ,if the move direction is not consist with the recent direction ,
	 * tank will veer in the last tick instead of moving <br>
	 * 移动命令，如果移动方向和当前坦克朝向不一致，坦克会在下一游戏刻转向而不是移动
	 * @param d :the direction you want to move if the direction is Direction.STOP the tank
	 * will not move <br>
	 * 参数d表示移动的方向，如果传入的Direction枚举为STOP，则不会发生任何效果
	 */
	protected final void move(Direction d)//提供两种移动方式
	{
		if(d!=Direction.STOP)
			mytank.move(d);
	}
	/**
	 * fire to the direction your tank face to. the fire has a cool-down time of 50 ticks, that means if
	 * your tank is cooling your command will not be executed <br>
	 * 朝坦克面向的方向开火，开火动作有50个游戏刻的冷却时间，这意味着如果你的坦克正在开火冷却中，你的开火
	 * 命令不会被执行
	 */
	protected final void fire()
	{
		mytank.fire();
	}
	
	/**
	 * @return an arraylist of all the information of bullets in the scene<br>
	 *  返回一个包含所有子弹信息的arraylist
	 */
	protected final ArrayList<BulletInformation> Buttles()
	{
		
		return temp_bi;
	}
	/**
	 * @return an arraylist of all the information of tanks except yours in the scene<br>
	 *  返回一个包含除你之外所有坦克的信息的arraylist
	 */
	protected final ArrayList<TankInformation> Tanks()
	{
		
		return temp_ti;		
	}
	/**
	 * if your ai don't think too much, the command method will be called every tick<br>
	 * 如果你的ai计算量不是太大的话，这个command命令每个游戏刻都会被调用
	 */
	protected void command(){};//覆盖抽象方法command以对坦克进行控制，每帧调用一次
		
	final void run()
	{		
		synchronized(world) { 
			temp_hp=mytank.getHp();
			temp_x=mytank.getX();
			temp_y=600-mytank.getY();
			temp_direction=mytank.getDirection();
			temp_ti=world.getTanksInformation();
			for(int i=0;i<temp_ti.size();i++)
			{
				TankInformation ti =temp_ti.get(i);
				if(ti.NO==mytank.getNO())
				{
					temp_ti.remove(ti);
					i--;
				}
			}
			temp_bi=world.getButtlesInformation();
		}
		command();
	}

}
