package Model;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Controller.Animation;
import Controller.Handler;
import Controller.SpriteSheet;

public class Covid extends GameObject {
	private Handler handler;
 
	// private int persuitTimer;
	
	private BufferedImage[] covid_image=new BufferedImage[3];//add covid img
	Animation anim;
	Random r = new Random();
	int choose = 0;
	int hp = 100;
	// private boolean isMoving;

	public Covid(int x, int y, ID id, Handler handler,SpriteSheet ss) {
		super(x, y, id, ss);//, ss
		// this.persuitTimer = 0;
		this.hp = 100;
		this.handler = handler;
		
		this.covid_image[0]=ss.grabImage(5, 1, 32, 32); //col, row, width, height in covid
		this.covid_image[1]=ss.grabImage(4, 1, 32, 32); //col, row, width, height in covid
		this.covid_image[2]=ss.grabImage(6, 1, 32, 32); //col, row, width, height in covid
		
		anim = new Animation(39,covid_image[0],covid_image[1],covid_image[2]);

		
		// this.isMoving = false;
		// this.enemy_image = ss.grabImage(4, 1, 32, 32);
	}

	@Override
	public void tick() {
		this.x += this.velX;// velocity x
		this.y += this.velY;// velocity y

		choose = r.nextInt(10);// random 0-9
		for (int i = 0; i < this.handler.object.size(); i++) {
			GameObject tempObject = this.handler.object.get(i);
			

			if (tempObject.getId() == ID.Block) {
				if (this.getBoundsBig().intersects(tempObject.getBounds())) {
					x += (velX * 5) * -1;
					y += (velY * 5) * -1;
					velX *= -1;
					velY *= -1;
 
				} else if (choose == 0) {
					velX = (r.nextInt(4 - -4) + -4);
					velY = (r.nextInt(4 - -4) + -4);
				}
			}
			if (tempObject.getId() == ID.Bullet) {
				if (this.getBounds().intersects(tempObject.getBounds())) {// covid intersects bullet
					this.hp -= 50;
					this.handler.removeObject(tempObject);
				}
			}
			
		}
		anim.runAnimation();
		if (this.hp <= 0) {// death covid
			this.handler.removeObject(this);
		}

		 
	}
	 

	@Override
	public void render(Graphics g) {
		// g.drawImage(this.enemy_image, x, y, null);
//		g.setColor(Color.yellow);
//		g.fillRect(x, y, 32, 32);
//			g.drawImage(covid_image, x, y, null);
		anim.drawAnimation(g, x, y, 0);
		// Graphics2D g2d = (Graphics2D) g;
		//
		// g.setColor(Color.GREEN);
		// g2d.draw(getBoundsBig());

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Rectangle getBoundsBig() {
		return new Rectangle(x - 16, y - 16, 64, 64);
	}
}
