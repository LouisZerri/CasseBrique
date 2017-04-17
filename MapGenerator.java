package CasseBrique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int briquewidth;
	public int briqueheight;
	
	public MapGenerator(int l, int c){
		 map = new int[l][c];
		 
		 for(int i = 0; i < map.length; i++){
			 for(int j = 0; j < map[0].length; j++){
				 map[i][j] = 1;
			 }
		 }
		 
		 briquewidth = 540/c;
		 briqueheight = 150/l;
	}
	
	public void draw(Graphics2D g){
		for(int i = 0; i < map.length; i++){
			 for(int j = 0; j < map[0].length; j++){
				 if(map[i][j] > 0){
					 g.setColor(Color.white);
					 g.fillRect(j * briquewidth + 80, i * briqueheight + 50, briquewidth, briqueheight);
				 
					 g.setStroke(new BasicStroke(3));
					 g.setColor(Color.black);
					 g.drawRect(j * briquewidth + 80, i * briqueheight + 50, briquewidth, briqueheight);
					 
				 }
				 
			 }
		 }
	}
	

	public void setvalue(int value, int row, int col) {
		map[row][col] = value;
	}

}
