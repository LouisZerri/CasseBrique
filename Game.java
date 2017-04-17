package CasseBrique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener {
	
	private boolean play = false;
	public int score = 0;
	public int briques = 21;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	private int balleX = 120;
	private int balleY = 350;
	private int balledirX = -1;
	private int balledirY = -2;
	
	private MapGenerator map;
	
	public Game(){
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g){
		//Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//Drawing map
		map.draw((Graphics2D)g);
		
		//Borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//Scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//Paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(balleX, balleY, 20, 20);
		
		if(briques == 0){
			play = false;
			balledirX = 0;
			balledirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You won, Scores : "+score, 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to restart ", 230, 350);
		}
		
		if(balleY > 570){
			play = false;
			balledirX = 0;
			balledirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Scores: "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Press Enter to restart ", 230, 350);
			
		}
		
		g.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(new Rectangle(balleX, balleY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
			balledirY = -balledirY;
		}
		
		for(int i = 0; i < map.map.length; i++){
			 for(int j = 0; j < map.map[0].length; j++){
				 if(map.map[i][j] > 0){
					 int briqueX = j * map.briquewidth + 80;
					 int briqueY = i * map.briqueheight + 50;
					 int briquewidth = map.briquewidth;
					 int briqueheight = map.briqueheight;
					 
					 Rectangle rect = new Rectangle(briqueX, briqueY, briquewidth, briqueheight);
					 Rectangle ballerect = new Rectangle(balleX, balleY, 20, 20);
					 
					 Rectangle briquerect = rect;
					 
					 if(ballerect.intersects(briquerect)){
						 map.setvalue(0, i, j);
						 briques--;
						 score += 5;
					 
					 
						 if(balleX + 19 <= briquerect.x || balleX + 1 >= briquerect.x + briquerect.width) {
							 balledirX = -balledirX;
						 } else {
							 balledirY = -balledirY;
						 }
					 }

				 }
			 }
		}
		
		
		if(play){
			balleX += balledirX;
			balleY += balledirY;
			
			if(balleX < 0){
				balledirX = -balledirX;
			} 
			if(balleY < 0){
				balledirY = -balledirY;
			}
			if(balleX > 670){
				balledirX = -balledirX;
			}
			
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600){
				playerX = 600;
			} else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10){
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play){
				play = true;
				balleX = 120;
				balleY = 350;
				balledirX = -1;
				balledirY = -2;
				playerX = 310;
				score = 0;
				briques = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
		}
	}
	
	public void moveRight(){
		play = true;
		playerX += 20;
	}
	
	public void moveLeft(){
		play = true;
		playerX -= 20;
	}
	
	

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}
