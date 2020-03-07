import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


class AnimationFlow extends Thread{
	
	Panel animation;
	
	public AnimationFlow(Panel animation)
	{
		this.animation=animation;
	}
	
	public void run()
	{
        while (!animation.gameOver) 
        {
        	int speed = 1;
            if(animation.up == true)
            {
				animation.tempPosX=animation.positionX;
				animation.tempPosY=animation.positionY;
				animation.positionY-=speed;
            }
            if(animation.down == true)
            {
				animation.tempPosX=animation.positionX;
				animation.tempPosY=animation.positionY;
				animation.positionY+=speed;
            }
            if(animation.left == true)
            {
				animation.tempPosX=animation.positionX;
				animation.tempPosY=animation.positionY;
				animation.positionX-=speed;
            }
            if(animation.right == true)
            {
				animation.tempPosX=animation.positionX;
				animation.tempPosY=animation.positionY;
				animation.positionX+=speed;
            }
            
            animation.repaint();
            try 
            {
                Thread.sleep(12L);		
            }
            catch (InterruptedException ex) {}
        }
	}
}


public class Panel extends JPanel implements KeyListener {
	

	private static final long serialVersionUID = 1L;
	
	public int positionX=300, positionY=300, tempPosX,tempPosY,tempPosX2,tempPosY2,score=0;
	public boolean up=false,down=false,left=false,right=false,gameOver=false;
	
	//Nazwa pliku gdzie bedzie automatyczny zapis/odczyt przy zamknieciu/otwarciu
	public static final String SNAKE_SCORE_FILE = "snakeScore.bin";
	public List<Integer> snakeScore = new LinkedList<Integer>();
	
	List<Food> allFood = new LinkedList<Food>();
	List<SnakeComponent> allSnake = new LinkedList<SnakeComponent>();
	
	SnakeHead head =  new SnakeHead(positionX,positionY);
	
	JLabel scoreLabel = new JLabel("0");
	Font  scoreFont  = new Font(Font.MONOSPACED, Font.BOLD,  30);
	
	Border panelBorder = BorderFactory.createStrokeBorder(new BasicStroke(5.0f), Color.black);
	
	public Panel()
	{
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
		
		setBorder(panelBorder); 
		
		scoreLabel.setFont(scoreFont);
		add(scoreLabel);
		
		AnimationFlow animationFlow = new AnimationFlow(this);			//utworzenie watku, ktory co 20 milisekund wywoluje repaint() -> zapewnia plynnosc animacji
		animationFlow.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		//int counter=0;
		super.paintComponent(g);
		
		head.setPosX(positionX);
		head.setPosY(positionY);
		
		if(allFood.size()<10)
		{	
			Food food = new Food(this.getWidth()-10,this.getHeight()-10);
			allFood.add(food);
		}
		
		
		//Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
		//JVM couldn’t determine what element should’ve come next in for loop
		
		for(Iterator<Food> foodIterator = allFood.iterator();foodIterator.hasNext();)	//for(Food food : allFood)
		{
			
			Food food = foodIterator.next();
			int offset =5;
			if( (head.getPosX()-offset <= food.getPosX() && food.getPosX() <= head.getPosX()+offset)  && (head.getPosY()-offset <= food.getPosY() && food.getPosY() <= head.getPosY()+offset) )
			{
				score++;
				scoreLabel.setText(Integer.toString(score));
				
				foodIterator.remove();		//allFood.remove(food);
				
				SnakeComponent body = new SnakeComponent(-100,-100); 
				allSnake.add(body);

			}
		}
		
		for(SnakeComponent body : allSnake)
		{
			if(body.getPosX()==head.getPosX() && body.getPosY()==head.getPosY()) //gameover
			{
				gameOverFun();
			}
				
			if(body.getPosX()!=-100)
			{
				tempPosX2 = body.getPosX();
				tempPosY2 = body.getPosY();
				body.setPosX(tempPosX);
				//System.out.println(" tempPosX:"+tempPosX);
				body.setPosY(tempPosY);
				tempPosX=tempPosX2;
				tempPosY=tempPosY2;
			}
			else
			{
				body.setPosX(tempPosX);
				body.setPosY(tempPosY);
			}
			
		}
		

		
		for(Food food : allFood)
		{
			food.draw2(g, food.getPosX(), food.getPosY());
		}
		for(SnakeComponent body : allSnake)
		{
			//counter++;
			//System.out.println("ID: "+counter+" Pozycja Body X: "+body.getPosX()+" Y: "+body.getPosY()+" Head X: "+head.getPosX()+" Y: "+head.getPosY());
			body.draw(g, body.getPosX(),body.getPosY());
		}
		head.draw(g,head.getPosX(), head.getPosY());
		
		//wyjscie za border
		if(head.getPosX() == 0 || head.getPosY() == 0 || head.getPosX() == this.getWidth() || head.getPosY() == this.getHeight()) gameOverFun();
	}
	
	
	


	@Override
	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W && !down)
		{
			up=true;
			down=false;
			right=false;
			left=false;		
		}
		if(event.getKeyCode() == KeyEvent.VK_A && !right)
		{
			up=false;
			down=false;
			right=false;
			left=true;
		}
		if(event.getKeyCode() == KeyEvent.VK_D && !left)
		{
			up=false;
			down=false;
			right=true;
			left=false;
		}
		if(event.getKeyCode() == KeyEvent.VK_S && !up)
		{
			up=false;
			down=true;
			right=false;
			left=false;
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent event) 
	{
		
	}
	
	public void save() 
	{
		try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(SNAKE_SCORE_FILE)))
		{
			outputStream.writeObject(snakeScore);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameOverFun()
	{
		snakeScore.add(score);
		save();
		
		GameOverWindowDialog gameOverWindow = new GameOverWindowDialog(this,score);
		gameOver=true;
		return;
	}
	
	public void restart()
	{
		allFood.removeAll(allFood);
		allSnake.removeAll(allSnake);
		score=0;
		scoreLabel.setText("0");
		positionX = 300;
		positionY = 300;
		up=false;
		down=false;
		left=false;
		right=false;
		head = new SnakeHead(positionX,positionY);
		
		if(gameOver == true) //jezeli przegralismy wczesniej grre to trzeba znowu rozpoczac watek
		{
			AnimationFlow animationFlow = new AnimationFlow(this);			//utworzenie watku, ktory co 20 milisekund wywoluje repaint() -> zapewnia plynnosc animacji
			animationFlow.start();
			gameOver = false;
		}
		
	}
	
	

}
