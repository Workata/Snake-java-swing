import java.util.Random;

public abstract class ObjectOnPanel {
	
	protected int posX, posY;
	
	public void setPosX(int posX)
	{
		this.posX = posX;
	}
	
	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
	public void setRandPosX(int width)
	{
		posX = getRandomCoord(width);
	}
	
	public void setRandPosY(int height)
	{
		posY = getRandomCoord(height);
	}
	
	public int getRandomCoord(int range)
	{
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(range-10) + 10;
		return randomInt;
	}

}
