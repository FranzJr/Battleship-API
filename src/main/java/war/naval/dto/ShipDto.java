package war.naval.dto;

public class ShipDto {

	private int x;

	private int y;

	private boolean ship;

	public ShipDto() {
	}

	public ShipDto(int x, int y, boolean ship) {
		this.x = x;
		this.y = y;
		this.ship = ship;
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

	public boolean isShip() {
		return ship;
	}

	public void setShip(boolean ship) {
		this.ship = ship;
	}

}
