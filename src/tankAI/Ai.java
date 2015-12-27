package tankAI;
import java.util.*;

/**
 * you can access information of the game and give commands to your tank by extend this class,but 
 * the command will not be executed until next tick <br>
 * �����ͨ���̳�������������Ϸ�е���Ϣ�������Լ���̹�˷������������Щ����Ҫ������������һ��
 * ��Ϸ�̲Żᱻ����ִ��
 */
public abstract class Ai {
	private Tank mytank;
	private World world;
	/**
	 * the speed of the tank,the faster your tank is ,the less hp your tank will have.
	 * the default value is 3,you can choose a value from 2\3\4,the hp of the tank equals 7-speed<br>
	 * you can only change the number validly in the constructor<br>
	 * ̹�˵��ƶ��ٶȣ��ƶ��ٶ�Խ��̹�˵�Ѫ�����;öȣ�Ҳ��Խ�ͣ�Ĭ��Ϊ3����ѡֵΪ2\3\4��
	 * ̹��Ѫ������7-̹���ٶȡ�<br>
	 * ֻ���ڹ��������� public MyAi()���иı������ֵ������Ч�ģ����򲻻�Ӱ��̹��ʵ�ʵ��ٶȡ�
	 */
	public int speed=3;
	/**
	 * the speed of the bullet,the faster your bullet is ,the less damage your bullet will cause.
	 * the default value is 5,you can choose a value from 5\6\7\8\9. the damage of the bullet equals
	 * 5-(speed/2), the fractional part will be cut, so it's better to choose an odd value.<br>
	 * you can only change the number validly in the constructor<br>
	 * �ӵ����ٶȣ��ӵ��ٶ�Խ���ӵ���������˺�Խ�ͣ�Ĭ��Ϊ5����ѡֵΪ5\6\7\8\9��
	 * �ӵ��˺�����5-���ӵ��ٶ�/2������ȥβ��ȥ��С�����֣���ѡ�������ٶȻ��Ϊ����<br>
	 * ֻ���ڹ��������� public MyAi()���иı������ֵ������Ч�ģ����򲻻�Ӱ���ӵ�ʵ�ʵ��ٶ�
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
	 * �������̹�˵ĺ�����
	 */
	protected final int getX()
	{
		return temp_x;
	}
	/**
	 * @return the Y coordinate of your tank<br>
	 * �������̹�˵�������
	 */
	protected final int getY()
	{
		return temp_y;
	}
	/**
	 * @return the Hp of your tank<br>
	 * ������̹�˵�Ѫ�����;öȣ�
	 */
	protected final int getHp()
	{
		return temp_hp;
	}
	/**
	 * @return the direction that your tank face to<br>
	 * ������̹���泯�ķ���
	 */
	protected final Direction getDirection()
	{
		return temp_direction;
	}
	/**
	 * let your tank move down next tick. if your tank does not face down,it will veer instead
	 * of moving<br>
	 * �����̹������һ����Ϸ�������ƶ���������̹�˲����泯�·�����һ��Ϸ��������ת��������ƶ�
	 */
	protected final void moveDown()
	{
		mytank.move(Direction.DOWN);
	}
	/**
	 * let your tank move up next tick. if your tank does not face up,it will veer instead
	 * of moving<br>
	 * �����̹������һ����Ϸ�������ƶ���������̹�˲����泯�Ϸ�����һ��Ϸ��������ת��������ƶ�
	 */
	protected final void moveUp()
	{
		mytank.move(Direction.UP);
	}
	/**
	 * let your tank move left next tick. if your tank does not face left,it will veer instead
	 * of moving<br>
	 * �����̹������һ����Ϸ�������ƶ���������̹�˲����泯�󷽣���һ��Ϸ��������ת��������ƶ�
	 */
	protected final void moveLeft()
	{
		mytank.move(Direction.LEFT);
	}
	/**
	 * let your tank move right next tick. if your tank does not face right,it will veer instead
	 * of moving<br>
	 * �����̹������һ����Ϸ�������ƶ���������̹�˲����泯�ҷ�����һ��Ϸ��������ת��������ƶ�
	 */
	protected final void moveRight()
	{
		mytank.move(Direction.RIGHT);
	}
	/**
	 * the move command ,if the move direction is not consist with the recent direction ,
	 * tank will veer in the last tick instead of moving <br>
	 * �ƶ��������ƶ�����͵�ǰ̹�˳���һ�£�̹�˻�����һ��Ϸ��ת��������ƶ�
	 * @param d :the direction you want to move if the direction is Direction.STOP the tank
	 * will not move <br>
	 * ����d��ʾ�ƶ��ķ�����������Directionö��ΪSTOP���򲻻ᷢ���κ�Ч��
	 */
	protected final void move(Direction d)//�ṩ�����ƶ���ʽ
	{
		if(d!=Direction.STOP)
			mytank.move(d);
	}
	/**
	 * fire to the direction your tank face to. the fire has a cool-down time of 50 ticks, that means if
	 * your tank is cooling your command will not be executed <br>
	 * ��̹������ķ��򿪻𣬿�������50����Ϸ�̵���ȴʱ�䣬����ζ��������̹�����ڿ�����ȴ�У���Ŀ���
	 * ����ᱻִ��
	 */
	protected final void fire()
	{
		mytank.fire();
	}
	
	/**
	 * @return an arraylist of all the information of bullets in the scene<br>
	 *  ����һ�����������ӵ���Ϣ��arraylist
	 */
	protected final ArrayList<BulletInformation> Buttles()
	{
		
		return temp_bi;
	}
	/**
	 * @return an arraylist of all the information of tanks except yours in the scene<br>
	 *  ����һ����������֮������̹�˵���Ϣ��arraylist
	 */
	protected final ArrayList<TankInformation> Tanks()
	{
		
		return temp_ti;		
	}
	/**
	 * if your ai don't think too much, the command method will be called every tick<br>
	 * ������ai����������̫��Ļ������command����ÿ����Ϸ�̶��ᱻ����
	 */
	protected void command(){};//���ǳ��󷽷�command�Զ�̹�˽��п��ƣ�ÿ֡����һ��
		
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
