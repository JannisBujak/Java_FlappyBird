package flappyBird;

import java.awt.Rectangle;
public class Pipe extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int SPEED = 15;
	private FlappyBird flappyBird;
	
	private boolean spawnedSth;
	private boolean onLeftOfBird;
	private boolean countThis;
	
	public Pipe(int x, int y, int width, int height, FlappyBird flappyBird, boolean spawn) {
		super(x, y, width, height);
		this.flappyBird = flappyBird;
		spawnedSth = spawn;
		countThis = spawn;
	}
	
	public void update() {
		super.x -= SPEED;
		
		
		if(!spawnedSth) {
			if(super.x - SPEED <= flappyBird.WIDTH && super.x >= flappyBird.WIDTH) {
				flappyBird.addColumn();
				spawnedSth = true;
				System.out.println("Spawned pipe");
			}
		}
		
		if(super.x <= flappyBird.bird.x && !onLeftOfBird && countThis) {
			flappyBird.countUp();
			this.onLeftOfBird = true;
			countThis = false;
		}
		
	 }

	public boolean birdInside(Bird bird) {
		if(bird.x > super.x && bird.x < super.x + super.width) {
			
			if(bird.y > super.y && bird.y < super.y + super.height)	return true;
			if(bird.y > super.y && bird.y < super.y)	return true;
			if(bird.y < 0)	return true;
			
		}
		
		if(bird.x + bird.width > super.x && bird.x + bird.width < super.x + super.width) {
			
			if(bird.y > super.y && bird.y < super.y + super.height)	return true;
			if(bird.y > super.y && bird.y < super.y)	return true;
			if(bird.y < 0)	return true;
			
		}
		
		return false;
	}
	
}
