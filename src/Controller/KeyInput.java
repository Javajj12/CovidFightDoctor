package Controller;

import java.awt.event.KeyAdapter;//class implements in interface to name WindowListener
import java.awt.event.KeyEvent;

import Model.GameObject;
import Model.ID;

public class KeyInput extends KeyAdapter {
	private Handler handler;

	public KeyInput(Handler handler) {// constructor
		this.handler = handler; 
	}

	public void keyPressed(KeyEvent e) {// key adapter
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					handler.setUp(true);
				}
				if (key == KeyEvent.VK_S) {
					handler.setDown(true);
				}
				if (key == KeyEvent.VK_A) {
					handler.setLeft(true);
				}
				if (key == KeyEvent.VK_D) {
					handler.setRight(true);
				}
			}
		}
	}

	// have stable control
	@Override
	public void keyReleased(KeyEvent e) {// release keybord
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					handler.setUp(false);
				}
				if (key == KeyEvent.VK_S) {
					handler.setDown(false);
				}
				if (key == KeyEvent.VK_A) {
					handler.setLeft(false);
				}
				if (key == KeyEvent.VK_D) {
					handler.setRight(false);
				}
			}
		}
	}
}
