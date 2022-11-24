package View;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//GUI
public class Windows {// bulid instance of a class
	public Windows(int width, int height, String title, Game game) {// constactor
		JFrame jframe = new JFrame(title);
		// set size of frame
		jframe.setPreferredSize(new Dimension(width, height));// set dimension data
		jframe.setMaximumSize(new Dimension(width, height));
		jframe.setMinimumSize(new Dimension(width, height));

		jframe.add(game);// set game link to Canvas in Game
		jframe.setResizable(false);// can't resize a windows
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// set default close
		jframe.setLocationRelativeTo(null);// set middle in windows
		jframe.setVisible(true);
		
		
		 ImageIcon icon1 = new ImageIcon("res/doctor.png");
		    int a=JOptionPane.showConfirmDialog(jframe,"Play Game?", "Covid fight Doctor",
		            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,icon1);  
		   
			if(a==JOptionPane.YES_OPTION){  
				jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				}else if(a==JOptionPane.NO_OPTION){
					System.exit(0);
				}  
	}
	 
}
