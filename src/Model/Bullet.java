package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Controller.Handler;
import Controller.SpriteSheet;

public class Bullet extends GameObject {

	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler, int mx, int my,SpriteSheet ss) {//,SpriteSheet ss
		super(x, y, id, ss);//,ss
		this.handler = handler;
		this.velX = (mx - x) / 10;// velocity x
		this.velY = (my - y) / 10;// velocity y
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		// for (int i = 0; i < handler.object.size(); i++) {
		// GameObject tempObject = handler.object.get(i);
		//
		// if (tempObject.getId() == ID.Block) {
		// if (this.getBounds().intersects(tempObject.getBounds())) {
		// handler.removeObject(this);
		// }
		// }
		// }
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);// yellow.darker()
		g.fillOval(x, y, 7, 7);// 15, 15
	}

    @Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 7, 7);// 15, 15
	}

}
