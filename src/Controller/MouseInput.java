package Controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Bullet;
import Model.GameObject;
import Model.ID;
import View.Camera;
import View.Game;

 

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;
//	private Audio audio;
	 //ควบคุมกระสุนให้กระสุนลดลง
	public MouseInput(Handler handler, Camera camera,Game game,SpriteSheet ss) {  
		this.handler = handler;
		this.camera = camera;
		this.game=game;
		this.ss=ss;
	}
//Event
	public void mousePressed(MouseEvent e) {//set adapter mouse
		int mx = (int) (e.getX() + camera.getX());//get position mouse x&y
		int my = (int) (e.getY() + camera.getY()); 
		// position 500 x and 250 Y
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player&&game.ammo>=1) {//
 
					handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss)); //, ss brings it the center
					game.ammo--;// ลดกระสุนทุกครั้งเมื่อคลิก
					
//					Window.ammo -= 1;

					// Play magic effect
//					audio.play();
//				}
			}
		}
	}
	
}


