package Wumpus.Wumpus;

public class Hunter {
	
	private String name;
	private Integer arrows;
	private Boolean alive;
	private Integer[] position;
	private boolean treasure;
	
	public Hunter(String name, Integer arrows, Boolean alive, Integer[] position, Boolean treasure) {
		super();
		this.name = name;
		this.arrows = arrows;
		this.alive = alive;
		this.position = position;
		this.treasure = treasure;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getArrows() {
		return arrows;
	}
	public void setArrows(Integer arrows) {
		this.arrows = arrows;
	}
	public Boolean getAlive() {
		return alive;
	}
	public void setAlive(Boolean alive) {
		this.alive = alive;
	}
	public Integer[] getPosition() {
		return position;
	}
	public void setPosition(Integer[] casilla) {
		this.position = casilla;
	}

	public boolean isTreasure() {
		return treasure;
	}

	public void setTreasure(boolean treasure) {
		this.treasure = treasure;
	}
	
}
