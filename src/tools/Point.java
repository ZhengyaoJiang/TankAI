package tools;
/**
 *this class is used to represent a point in the scene ,it also provide some useful methods related
 *to some geometry problem<br>
 *�����������ʾ�����е�һ���㣬��ͬʱ�ṩһЩ�뼸�������йصķ���
 */
public class Point {
	public int x;
	public int y;
	public Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	/**
	 * this method judge if the tank represented by this point is out of boundary<br>
	 * ������������ж���������ʾ��̹���Ƿ񳬳��˱߽�
	 * @return if the tank is out of the boundary ,then return true else return false<br>
	 * ���̹�˳����߽�ͷ���true�����򷵻�false
	 */
	public boolean isTankOut()
	{
		if(x-20<0||x+35>900||y-60<0||y+20>600)
			return true;
		else
			return false;
	}
}
