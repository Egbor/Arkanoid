package game.engine.object.component;

import game.engine.math.Point;
import game.engine.math.Vector;

public class Transform extends Component {
	
	private Point position;
	
	public Transform() {
		position = new Point();
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}
	
	public void setPosition(Point position) {
		this.position.x = position.x;
		this.position.y = position.y;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void translate(float x, float y) {
		position.x += x;
		position.y += y;
	}
	
	public void translate(Vector direction) {
		position.x += direction.getDirection().x;
		position.y += direction.getDirection().y;
	}
}
