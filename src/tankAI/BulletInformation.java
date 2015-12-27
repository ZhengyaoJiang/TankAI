package tankAI;

/**
 * this class carry the information of the bullet，all the fields are read only<br>
 * 这个类中包含子弹的信息,所有字段都是只读的
 */
public class BulletInformation
{
	/**
	 * the x coordinate of the central of the bullet<br>
	 * 子弹的中心点横坐标
	 */
	public final int x;
	/**
	 * the y coordinate of the central of the bullet in the swing painting coordinate system<br>
	 * 在swing绘图坐标系下的子弹中心点的纵坐标
	 */
	final int raw_y;
	/**
	 * the y coordinate of the central of the bullet<br>
	 * 子弹的中心点纵坐标
	 */
	public final int y;
	/**
	 * the velocity on x axis of the bullet<br>
	 * 子弹在x轴方向上的速度
	 */
	public final int vx;
	/**
	 * the velocity on y axis of the bullet in the swing painting coordinate system<br>
	 * 在swing绘图坐标系下子弹在y轴方向上的速度
	 */
	final int raw_vy;
	/**
	 * the velocity on y axis of the bullet<br>
	 * 子弹在y轴方向上的速度
	 */
	public final int vy;
	/**
	 * the damage this bullet can cause<br>
	 * 这颗子弹能造成的伤害
	 */
	public final int power;		
	
	BulletInformation(int x,int raw_y,int vx,int raw_vy,int power)
	{
		this.x=x;
		this.raw_y=raw_y;
		this.y=600-raw_y;
		this.vx=vx;
		this.raw_vy=raw_vy;
		this.vy=-raw_vy;
		this.power=power;
	}
}
