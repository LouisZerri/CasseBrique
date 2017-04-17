package CasseBrique;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame object = new JFrame(); //instance JFrame
		Game gameplay = new Game(); //instance GamePlay
		object.setBounds(10,10,700,600); //Dimension de la fenetre
		object.setTitle("Casse Brique"); //Titre de la fenetre
		object.setResizable(false); //Non redimensionable
		object.setVisible(true); //Creation d'une fenêtre
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.add(gameplay);
	}

}