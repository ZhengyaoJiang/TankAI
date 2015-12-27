package tankAI;

/**
 * this class carry the information of the bullet��all the fields are read only<br>
 * ������а����ӵ�����Ϣ,�����ֶζ���ֻ����
 */
public class BulletInformation
{
	/**
	 * the x coordinate of the central of the bullet<br>
	 * �ӵ������ĵ������
	 */
	public final int x;
	/**
	 * the y coordinate of the central of the bullet in the swing painting coordinate system<br>
	 * ��swing��ͼ����ϵ�µ��ӵ����ĵ��������
	 */
	final int raw_y;
	/**
	 * the y coordinate of the central of the bullet<br>
	 * �ӵ������ĵ�������
	 */
	public final int y;
	/**
	 * the velocity on x axis of the bullet<br>
	 * �ӵ���x�᷽���ϵ��ٶ�
	 */
	public final int vx;
	/**
	 * the velocity on y axis of the bullet in the swing painting coordinate system<br>
	 * ��swing��ͼ����ϵ���ӵ���y�᷽���ϵ��ٶ�
	 */
	final int raw_vy;
	/**
	 * the velocity on y axis of the bullet<br>
	 * �ӵ���y�᷽���ϵ��ٶ�
	 */
	public final int vy;
	/**
	 * the damage this bullet can cause<br>
	 * ����ӵ�����ɵ��˺�
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
