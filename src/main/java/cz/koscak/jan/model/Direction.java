package cz.koscak.jan.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
	
	LEFT(0, Directions.LEFT, -1, 0),
	TOP(1, Directions.TOP, 0, -1),
	RIGHT(2, Directions.RIGHT, 1, 0),
	DOWN(3, Directions.DOWN, 0, 1);
	
	private Integer orderIndex;
	private String name;
	private Integer dx;
	private Integer dy;
	
	public static final Map<Integer, Direction> mapOfDirections = new HashMap<Integer, Direction>();
	
	Direction(Integer orderIndex, String name, Integer dx, Integer dy) {
		this.orderIndex = orderIndex;
		this.name = name;
		this.dx = dx;
		this.dy = dy;
	}

	static {
		
		mapOfDirections.put(LEFT.getOrderIndex(), LEFT);
		mapOfDirections.put(TOP.getOrderIndex(), TOP);
		mapOfDirections.put(RIGHT.getOrderIndex(), RIGHT);
		mapOfDirections.put(DOWN.getOrderIndex(), DOWN);
		
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public String getName() {
		return name;
	}

	public Integer getDx() {
		return dx;
	}

	public Integer getDy() {
		return dy;
	}
	
	public String getDirectionLetter() {
		return name.substring(0, 1);
	}

	public static Map<Integer, Direction> getMapOfDirections() {
		return mapOfDirections;
	}
	
}
