package flappyBird;
import java.awt.Rectangle;

public class Bird extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int acceleration;
	private int GRAVITATION = -1;
	private int limit;
	private FlappyBird flappyBird;
	private int startY;
	
	public Bird(int x, int y, int width, int height, int limit, FlappyBird flappyBird) {
		super(x, y, width, height);
		startY = y;
		acceleration = 0;
		this.limit = limit;
		this.flappyBird = flappyBird;
	}
	
	public int getLimit() {
		return limit;
	}
	
	public void jump() {
		acceleration = -20;
	}
	
	public void reset() {
		super.y = startY;
	}
	
	public void update() {
		if(flappyBird.gameOver) {
			acceleration = 0;
			return;
		}
			
		acceleration -= GRAVITATION;
		if(super.y + acceleration >= limit) {
			y = limit;
		}
		else 
		{
			y += acceleration;
		}
	}
}
