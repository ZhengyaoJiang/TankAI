package tools;
/**
 *this class is used to represent a point in the scene ,it also provide some useful methods related
 *to some geometry problem<br>
 *这个类用来表示场景中的一个点，它同时提供一些与几何问题有关的方法
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
	 * 这个方法用来判断由这个点表示的坦克是否超出了边界
	 * @return if the tank is out of the boundary ,then return true else return false<br>
	 * 如果坦克超出边界就返回true，否则返回false
	 */
	public boolean isTankOut()
	{
		if(x-20<0||x+35>900||y-60<0||y+20>600)
			return true;
		else
			return false;
	}
}
