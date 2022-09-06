package client;

import java.util.ArrayList;

public class BasesClient {
	int[] homeCoords = {441, 180};
	int[] firstCoords = {156, 339};
	int[] secondCoords = {429, 540};
	int[] thirdCoords = {699, 360};
	int[] mountCoords = {441, 360};
	
	int[] homeCoordsField = {homeCoords[0], homeCoords[1] - 30};
	int[] firstCoordsField = {firstCoords[0] - 60, firstCoords[1]};
	int[] secondCoordsField = {secondCoords[0] - 60, secondCoords[1]};
	int[] thirdCoordsField = {thirdCoords[0] - 60, thirdCoords[1]};
	
	int[] homeCoordsBat = {homeCoords[0] + 60, homeCoords[1]};
	int[] firstCoordsBat = {firstCoords[0] + 60, firstCoords[1]};
	int[] secondCoordsBat = {secondCoords[0] + 60, secondCoords[1]};
	int[] thirdCoordsBat = {thirdCoords[0] + 60, thirdCoords[1]};

	
	ArrayList<TeamClient> teams;
	
	public BasesClient(ArrayList<TeamClient> t) {
		teams = t;
	}
}
