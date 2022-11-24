package View;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import Controller.BufferedImageLoader;
import Controller.Handler;
import Controller.KeyInput;
import Controller.MouseInput;
import Controller.SpriteSheet;
import Model.Block;
import Model.Covid;
import Model.Doctor;
import Model.Door;
 
import Model.ID;
import Model.Vaccine_crate;

public class Game extends Canvas implements Runnable {
	//inheritance 

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	
	private SpriteSheet ss;
 
	// this is main class
	private BufferedImage level = null;
	//add
	private BufferedImage sprite_sheet = null;
	
	private BufferedImage floor = null;
	
	public int ammo =20; //ammo 20 นัด
	
	public int hp =100;//health point
	
	public Game() {
		new Windows(1000, 563, "FIGHT OF COVID-19", this);
		start();
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
	

		BufferedImageLoader loader = new BufferedImageLoader();// new instant
		
		level = loader.loadImage("/Mapgame4.png"); // add image path
		this.sprite_sheet = loader.loadImage("/Doctorcovid3.png");
		 
		ss = new SpriteSheet(sprite_sheet);
		
		floor = ss.grabImage(4, 2, 32, 32);
		
		this.addMouseListener(new MouseInput(handler, camera,this, ss));//,ss

		loadLevel(level);

		// handler.addObject(new Doctor(100,100,ID.Player,handler));

		// handler.addObject(new Box(100,100,ID.Block));
		// handler.addObject(new Box(200,100));
	}


	private void start() {// start thread
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	private void stop() {// stop thread
		isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {//run thread
		// 60 time second 2000 fps

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;//60.0
		double ns =  1000000000/ amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
		 
			render();
			frames++;
			tick();
			// updates ++ ;
			delta--;
		}
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			frames = 0;
		}
		stop();

	}// game loop form minecreft
	public void tick() {//ตัว manage game tick & render
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
//			 if (handler.object.get(i).getId() == ID.Covid) {
//			 covid++;
//			 }
		}

		handler.tick();//main tick method
		if(hp<=0) {
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}

	}

	// set java graphic class to draw anything
	public void render() {// render everything in game
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {// before see 1 frame after back 2 frame
			this.createBufferStrategy(3);// set frame
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		// 2d render
		////////////// Drawing this/////////////
//		g.setColor(Color.red);// color backgrand
//		g.fillRect(0, 0, 1000, 563);

		g2d.translate(-camera.getX(), -camera.getY());
		
		 for(int xx =0;xx<30*72;xx+=32) {
			 for(int yy=0;yy<30*72;yy+=32) {
				 g.drawImage(floor, xx, yy, null);
			 }
		 }

		handler.render(g);// show our handle or not render all of our obj
		g2d.translate(camera.getX(), camera.getY());
		
		g.setColor(Color.gray);
		g.fillRect(5,5,200,32);//(x, y, width, height)
		
		g.setColor(Color.green);
		g.fillRect(5,5,hp*2,32);//(x, y, width, height)
		
		g.setColor(Color.black);
		g.drawRect(5,5,200,32);//(x, y, width, height)
		
		g.setColor(Color.darkGray);
		g.drawString("   Vaccine Ammo:"+ammo,5,50);//str, x, y
		
		////////////////// Game///////////////
		g.dispose();
		bs.show();
	}

	// Loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				// set color link my game
				if (red == 255 && blue != 255 && green != 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));//,ss addObject update and render
				}
				if (green == 255 && blue != 255 && red != 255) {
					handler.addObject(new Covid(xx * 32, yy * 32, ID.Covid, handler, ss));//,ss
				}
				if (blue == 255 && green != 255 && red != 255) {
					handler.addObject(new Doctor(xx * 32, yy * 32, ID.Player, handler,this, ss));//,ss
				}
				 if (blue == 255 && green == 255 && red != 255) {
				 handler.addObject(new Vaccine_crate(xx * 32, yy * 32, ID.Vaccine_crate, ss));//,ss
				 }
				 if (blue == 255 && green == 255 && red == 255) {
				 handler.addObject(new Door(xx * 32, yy * 32, ID.Door, ss));
				 }
			}
		}
	}

	public static void main(String[]args) {
		new Game();
	}

}
