import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class SnakeHead extends SnakeComponent {
	
	public SnakeHead(int posX, int posY) 
	{
		super(posX, posY);
		
	}

	@Override
	public void draw(Graphics g, int posX, int posY)
	{
		g.setColor(Color.blue);
		((Graphics2D)g).setStroke(new BasicStroke(16.0f));
		g.drawLine(posX, posY, posX, posY);
	}

}
