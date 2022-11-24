package Model;
import java.awt.Graphics;
import java.awt.Rectangle;

import Controller.SpriteSheet;
//interface
	//abstract class
public abstract class GameObject {//  
	protected int x, y;// set location obj
	protected float velX = 0, velY = 0;// moving X & Y
	protected ID id;// get id
	//protected SpriteSheet ss;//add spritesheet
	protected SpriteSheet ss;
 
//ผ่าน parameter game object
	//strcture in object
	public GameObject(int x, int y, ID id,SpriteSheet ss) {// constactor 
		this.x = x;
		this.y = y;
		this.id = id;
		this.ss=ss;
 
	}

	public abstract void tick();// update position
	//abstract in class

	public abstract void render(Graphics g);// draw obj

	public abstract Rectangle getBounds();// can do collision detection
	// genarate get set
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
