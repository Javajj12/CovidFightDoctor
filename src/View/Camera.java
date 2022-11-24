package View;
import Model.GameObject;

public class Camera {
	private float x;
	private float y;

	public Camera(float x, float y) {// float x,float y
		this.x = 0;
		this.y = 0;
	}

	public void tick(GameObject object) {
		// this is : x = object.getX() + 1000/12;
		this.x += ((object.getX() - this.x) - 1000 / 2) * 0.05f;
		this.y += ((object.getY() - this.y) - 563 / 2) * 0.05f;

		// set camera top bottom
		if (this.x <= 0) {
			this.x = 0;
		}
		if (this.x >= 1032 + 30) {// 563 + 48
			this.x = 1032 + 30;
		}
		if (this.y <= 0) {
			this.y = 0;
		}
		if (this.y >= 563 + 62) {// Windows.HEIGHT 1032 + 52
			this.y = 563 + 62;
		}

	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
