package tankAI;

/**
 * this class carry the information of the tank，all the fields are read only<br>
 * 这个类中包含坦克的信息,所有字段都是只读的
 */
public class TankInformation
{
	/**
	 * the x coordinate of the central of the tank<br>
	 * 坦克的中心点横坐标
	 */
	public final int x;
	/**
	 * the y coordinate of the central of the tank in the swing painting coordinate system<br>
	 * 在swing绘图坐标系下的坦克中心点的纵坐标
	 */
	final int raw_y;
	/**
	 * the y coordinate of the central of the tank<br>
	 * 坦克的中心点纵坐标
	 */
	public final int y;
	/**
	 * the direction that the tank face to<br>
	 * 坦克面朝的方向
	 */
	public final Direction direction;
	/**
	 * the Hp of the tank<br>
	 * 坦克当前的血量（耐久度）
	 */
	public final int hp;
	/**
	 * the speed of this tank<br>
	 * 坦克的移动速度
	 */
	public final int speed;
	/**
	 * the serial number of the tank
	 * 坦克的编号,对应包名AI0X中的0X
	 */
	public final int NO;		
	TankInformation(int x,int raw_y,Direction direction,int hp,int speed,int NO)
	{
		this.x=x;
		this.raw_y=raw_y;
		this.y=600-raw_y;
		this.direction=direction;
		this.hp=hp;
		this.speed=speed;
		this.NO=NO;
	}
}
