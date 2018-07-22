package flappyBird;

import Highscore.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JFrame;



public class FlappyBird implements ActionListener, KeyListener
{

	public static FlappyBird flappyBird;
	public final int WIDTH = 1200, HEIGHT = 800, GROUND = HEIGHT - 80, GRASS = GROUND - 20;
	
	private int count;
	private boolean newHighscore;
	public boolean gameOver;
	
	public boolean started;
	
	public Renderer renderer; 
	public Bird bird;
	public ArrayList<Pipe> columns;
	
	public Random rand;
	
	public int ticks, yMotion;
	
	public FlappyBird() 
	{
		count = 0;
		ticks = 0;
		
		JFrame jframe = new JFrame();
		Timer timer = new Timer(60, this);
		renderer = new Renderer();
		rand = new Random();
		
		jframe.add(renderer);
		jframe.addKeyListener(this);
		
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		
		jframe.setVisible(true);
		
		bird = new Bird(WIDTH/2 - 300, HEIGHT /2 - 10, 20, 20, HEIGHT - 120, this);
		columns = new ArrayList<Pipe>();
		
		addColumn();
		timer.start();
	}
	
	public void countUp() {
		count++;
		System.out.println(count);
	}
	
	
	public void paintColumn(Graphics g, Rectangle column) 
	{
		g.setColor(Color.GREEN.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	
	public void addColumn() 
	{
		int space = 300;
		int width = 75;
		int height = 50 + rand.nextInt(300);
		
		columns.add(new Pipe(WIDTH + width + 500, HEIGHT - height - 100, width, height, this, true));
		columns.add(new Pipe(WIDTH + width + 500, 0, width, HEIGHT - height - space, this, false));
		
	}
	
	
	public boolean gameOver() {
		if(bird.y >= bird.getLimit()) {
			return true;
		}
		for(Pipe pipe : columns) {
			if(pipe.birdInside(bird))	return true;
		}
		return false;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		renderer.repaint();		
	}
	
	
	public void repaint(Graphics g) 
	{
		
		ticks++;
		
		//Background
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Floor(darker yellow)
		g.setColor(Color.yellow.darker());
		g.fillRect(0, GROUND,  WIDTH, 80);
		
		//Grass
		g.setColor(Color.green.darker());
		g.fillRect(0, GRASS, WIDTH, GROUND - GRASS);
		
		if(!started) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 1, 100));
			g.drawString("Click space to start!" , 100, HEIGHT/2 - 50);
				
			gameOver = true;
			return;
		}
		
		//The bird
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		
		for(Pipe column : columns) {
			paintColumn(g, column);
		}
				
		if(!gameOver)	bird.update();
		
		
		
		//The pipes
		for(int i = 0; i < columns.size(); i++) {
	
			Pipe pipe = columns.get(i);
			
			paintColumn(g, pipe);
			if(!gameOver)	pipe.update();
							
			if(pipe.x + pipe.width < 0) {
				System.out.println("Removed, Array Lenght: " + columns.size());
				columns.remove(pipe);
			}
				
			if(gameOver()) {
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 100));
				
				int highscore = Highscore.returnHighscore();
				
				if(highscore < count || newHighscore) {
					newHighscore = true;
					g.drawString("New Highscore!" , WIDTH/2 - 350, HEIGHT/2 - 100);
					Highscore.writeHighscore(count);
				}else {
					g.drawString(("Your Highscore is " + highscore), WIDTH/2 - 500, HEIGHT/2 - 100);
				}
				
				
				g.drawString("Game over!" , WIDTH/2 - 250, HEIGHT/2 - 200);
				g.drawString(("Your score is: " + count), WIDTH/2 - 350, HEIGHT/2);
					
				System.out.println("Game over");
				gameOver = true;
			}	
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			if(gameOver) {
				started = true;
				gameOver = false;
				newHighscore = false;
				count = 0;
				bird.reset();
				System.out.println(columns.size());
				while(columns.size() != 0) {
					Pipe pipe = columns.get(0);
					columns.remove(pipe);
				}
				addColumn();
			}
			System.out.println("Jump");
			bird.jump();
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	public static void main(String[] args) 
	{
		
		flappyBird = new FlappyBird();
		
	}
}
