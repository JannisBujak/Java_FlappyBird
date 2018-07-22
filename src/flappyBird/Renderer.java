package flappyBird;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Renderer extends JPanel
{
	private static final long serialVersionUID = 1L;
	private FlappyBird flappyBird;
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		FlappyBird.flappyBird.repaint(g);
	}
	
}
