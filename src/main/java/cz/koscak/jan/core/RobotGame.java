package cz.koscak.jan.core;

import java.util.ArrayList;
import java.util.List;

import cz.koscak.jan.model.Direction;
import cz.koscak.jan.model.Robot;

public class RobotGame {
	
	private List<Object>[][] map = new ArrayList[12][12];
	private List<Robot> listOfRobots = new ArrayList<Robot>();
	
	public RobotGame() {
		initDefaultMap();
	}

	private void initDefaultMap() {
	
		initEmptyMap();
		
		map[11][11].add("robot1");
		map[9][11].add("beltRL1");
		map[7][11].add("hole1");
		map[5][11].add("fastbeltRL1");
		
		//Robot robot = new Robot(3, 11, Direction.DOWN);
		Robot robot = new Robot(6, 6, Direction.TOP);
		map[6][6].add(robot);
		listOfRobots.add(robot);
		
		map[5][10].add("robotL");
		map[7][10].add("robotT");
		map[9][10].add("robotR");
		map[11][10].add("robotD");
		
	}

	private void initEmptyMap() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				map[i][j] = new ArrayList<Object>();
			}
		}
	}
	
	public List<Object>[][] getMap() {
		
		return map;
		
	}
	
	public void doRobotActions() {
		
		Robot robot = listOfRobots.get(0);
		
		int randomActionNumber = (int) (Math.random() * 2);
		
			if (randomActionNumber == 0) {
			
			int indexOfDirection = (int) (Math.random() * 4);
			
			robot.setDirection(Direction.getMapOfDirections().get(indexOfDirection));
			
			System.out.println(robot.getDirection().getDirectionLetter());
			
			map[robot.getX()][robot.getY()].remove(robot);
			map[robot.getX()][robot.getY()].add(robot);
		
		} else {
		
			moveRobot();
		
		}
		
	}
	
	public void moveRobot() {
	/*	moveRobot(1);
	}
	
	public void moveRobot(int speed) {*/
		
		Robot robot = listOfRobots.get(0);
		Direction direction = robot.getDirection();
		map[robot.getX()][robot.getY()].remove(robot);
		map[robot.getX() + direction.getDx()][robot.getY() + direction.getDy()].add(robot);
		robot.setX(robot.getX() + direction.getDx());
		robot.setY(robot.getY() + direction.getDy());
		
	}
	
}
