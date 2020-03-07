import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Food extends ObjectOnPanel{
	
	public Food(int width, int height)
	{
		this.setRandPosX(width);
		this.setRandPosY(height);
	}
	
	public void draw(Graphics g, int posX, int posY)
	{
		g.setColor(Color.green);
		((Graphics2D)g).setStroke(new BasicStroke(7.0f));
		g.drawLine(posX, posY, posX, posY);
	}
	
	public void draw2(Graphics g, int posX, int posY)
	{
		int r =7;
		int x = posX;
		int y = posY;
		
		g.setColor(Color.red);
		g.fillOval(x-r, y-r, 2*r, 2*r);   
		g.setColor(Color.red);
		g.drawOval(x-r, y-r, 2*r, 2*r);
	}

}
