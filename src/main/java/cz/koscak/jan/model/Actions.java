package cz.koscak.jan.model;

import java.util.ArrayList;
import java.util.List;

public class Actions {
	
	public static final String MOVE_1 = "Move 1";
	public static final String MOVE_2 = "Move 2";
	public static final String MOVE_3 = "Move 3";
	public static final String MOVE_BACK = "Move back";
	public static final String ROTATE_LEFT = "Rotate left";
	public static final String ROTATE_RIGHT = "Rotate right";
	public static final String MAKE_U_TURN = "Make U turn";
	
	public static final List<String> listOfActions = new ArrayList<String>();
	
	static {
		
		listOfActions.add(MOVE_1);
		listOfActions.add(MOVE_2);
		listOfActions.add(MOVE_3);
		listOfActions.add(MOVE_BACK);
		listOfActions.add(ROTATE_LEFT);
		listOfActions.add(ROTATE_RIGHT);
		listOfActions.add(MAKE_U_TURN);
		
	}
	
}
