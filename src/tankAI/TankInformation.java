package tankAI;

/**
 * this class carry the information of the tank��all the fields are read only<br>
 * ������а���̹�˵���Ϣ,�����ֶζ���ֻ����
 */
public class TankInformation
{
	/**
	 * the x coordinate of the central of the tank<br>
	 * ̹�˵����ĵ������
	 */
	public final int x;
	/**
	 * the y coordinate of the central of the tank in the swing painting coordinate system<br>
	 * ��swing��ͼ����ϵ�µ�̹�����ĵ��������
	 */
	final int raw_y;
	/**
	 * the y coordinate of the central of the tank<br>
	 * ̹�˵����ĵ�������
	 */
	public final int y;
	/**
	 * the direction that the tank face to<br>
	 * ̹���泯�ķ���
	 */
	public final Direction direction;
	/**
	 * the Hp of the tank<br>
	 * ̹�˵�ǰ��Ѫ�����;öȣ�
	 */
	public final int hp;
	/**
	 * the speed of this tank<br>
	 * ̹�˵��ƶ��ٶ�
	 */
	public final int speed;
	/**
	 * the serial number of the tank
	 * ̹�˵ı��,��Ӧ����AI0X�е�0X
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
