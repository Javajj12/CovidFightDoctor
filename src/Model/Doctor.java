package Model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import Controller.Animation;
import Controller.Handler;
import Controller.SpriteSheet;
import View.Game;

public class Doctor extends GameObject {// set class child to GameObject คือ class แม่
	Handler handler;
	Game game;
	// add imag
	private BufferedImage[] doctor_image = new BufferedImage[3];// add animation basic

	Animation anim;
	
	public Doctor(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);// ,ss
		this.handler = handler;
		this.game = game;

		doctor_image[0] = ss.grabImage(1, 1, 32, 48);// col, row, width, height
		doctor_image[1] = ss.grabImage(2, 1, 32, 48);// col, row, width, height
		doctor_image[2] = ss.grabImage(3, 1, 32, 48);// col, row, width, height

		anim = new Animation(3, doctor_image[0], doctor_image[1], doctor_image[2]);

	}
	//setting everyting in my game
	public void tick() {
		this.x += velX;
		this.y += velY;
		collision();// การชน
		// movement
		if (handler.isUp()) {
			this.velY = -2;
		} else if (!handler.isDown()) {
			this.velY = 0;
		}
		if (handler.isDown()) {
			this.velY = 2;
		} else if (!handler.isUp()) {
			this.velY = 0;
		}
		if (handler.isLeft()) {
			this.velX = -2;
		} else if (!handler.isRight()) {
			this.velX = 0;
		}
		if (handler.isRight()) {
			this.velX = 2;
		} else if (!handler.isLeft()) {
			this.velX = 0;
		}
		anim.runAnimation();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (this.getBounds().intersects(tempObject.getBounds())) {// intersects crack
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.Vaccine_crate) {
				if (this.getBounds().intersects(tempObject.getBounds())) {

					game.ammo += 30;// 10
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.Covid) {//hp lower loop
				if (this.getBounds().intersects(tempObject.getBounds())) {
					game.hp--;
				}
			}
			 if (tempObject.getId() == ID.Door) {
			 if (this.getBounds().intersects(tempObject.getBounds())) {
			 JOptionPane.showMessageDialog(null, "Congragulations, you won!");
			 System.exit(0);
			 }
			 }
		}

	}

	public void render(Graphics g) {
		// g.setColor(Color.blue);
		// g.fillRect(x, y, 32, 48);
		if (velX == 0 && velY == 0) {// add animation condition
			g.drawImage(doctor_image[0], x, y, null);// img, x, y, observer
		} else {
			anim.drawAnimation(g, x, y, 0);
		}
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, 32, 48);
	}

}
