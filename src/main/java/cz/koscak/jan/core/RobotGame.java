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
		map[4][11].add("rotateL7");
		map[3][11].add("rotateR7");
		map[2][11].add("fullRepair");
		map[1][11].add("repair");
		map[0][11].add("target1");
		
		//Robot robot = new Robot(3, 11, Direction.DOWN);
		Robot robot = new Robot(6, 6, Direction.TOP);
		map[6][6].add(robot);
		listOfRobots.add(robot);
		
		map[3][10].add("wallT");
		map[5][10].add("robotL");
		map[7][10].add("robotT");
		map[9][10].add("robotR");
		map[11][10].add("robotD");
		
		map[3][9].add("laser3");
		map[3][8].add("laserBeam3");
		map[3][7].add("laserBeam3");
		
		map[3][6].add("wallD");
		
		map[5][0].add("start");
		
		
		
		map[11][0].add("wallL");
		map[10][0].add("laser3R");
		map[9][0].add("laserBeam3R");
		map[8][0].add("laserBeam3R");	
		map[7][0].add("wallR");

		map[11][1].add("wallL");
		map[10][1].add("laserBeam3L");	
		map[9][1].add("laserBeam3L");
		map[8][1].add("laser3L");
		map[7][1].add("wallR");
		
		map[0][5].add("wallD");
		map[0][6].add("laser2T");
		map[0][8].add("laserBeam2T");	
		map[0][7].add("laserBeam2T");
		map[0][9].add("wallT");
		
		map[1][5].add("wallD");
		map[1][6].add("laserBeam1D");	
		map[1][7].add("laserBeam1D");
		map[1][8].add("laser1D");
		map[1][9].add("wallT");
		
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
