import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SnakeComponent extends ObjectOnPanel{
	
	public SnakeComponent()
	{
		
	}
	
	public SnakeComponent(int posX, int posY)
	{
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public void draw(Graphics g, int posX, int posY)
	{
		g.setColor(Color.black);
		((Graphics2D)g).setStroke(new BasicStroke(16.0f));
		g.drawLine(posX, posY, posX, posY);
	}

}
