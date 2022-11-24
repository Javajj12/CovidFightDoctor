package Model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Controller.SpriteSheet;
 
 

public class Vaccine_crate extends GameObject {

	private BufferedImage crate_image;
	
	public Vaccine_crate(int x, int y, ID id,SpriteSheet ss) {//, SpriteSheet ss
		super(x, y, id, ss);//, ss
		this.crate_image = ss.grabImage(6, 2, 32, 32);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.CYAN);
//		g.fillRect(x, y, 32, 32);
		g.drawImage(this.crate_image, x, y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
