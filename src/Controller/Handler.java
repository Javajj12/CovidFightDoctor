package Controller;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.LinkedList;

import Model.GameObject;

// ตัวจัดเหตุการณ์
//run loop updete all game obj
public class Handler {// this going handle our obj
	public LinkedList<GameObject> object = new LinkedList<GameObject>();// arraylist of obj

	private boolean up = false, down = false, right = false, left = false;

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void tick() {// all of the game obj
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();

		}

	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			// Thread.sleep(20);

			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject gameObject) {// add obj

		object.add(gameObject);

		//

	}

	public void removeObject(GameObject gameObject) {// remove obj

		object.remove(gameObject);
	}
}
