package Wumpus.Wumpus;

import java.util.Scanner;

public class Controller {
	
	private Scanner scanner;
	private Board board;
	private Hunter hunter;
	
	public Controller(Board board, Hunter hunter) {
		super();
		this.board = board;
		this.hunter = hunter;
		this.scanner = new Scanner(System.in);
	}
	
	// this function starts the game and continues if the hunter is alive
	public void start() {
		while(hunter.getAlive()) {
			printstatus();
			printNeighbours();
			next();
		}
	}

	// this function let the player choose what to do
	private void next() {	
		System.out.print("What do you wanna do: ");
		System.out.print(" 1:Move ");
		System.out.print(" 2:Throw arrow ");
		System.out.print(" 3:End Game ");
		Integer option = Integer.parseInt(scanner.nextLine());
		switch (option) {
		case 1: move(); //position movement
				break;
		case 2: arrow(); //throw arrow
				break;
		case 3: endgame(); //end the game
				break;
		default: System.out.println("Please enter a correct number"); break;
		}
	}
	
	private void move() {
		System.out.println("Where do you wanna go?   (5: up; 1: left; 3: right; 2: down)");
		Integer option = Integer.parseInt(scanner.nextLine());
		switch (option) {
		//left
		case 1: 
			if (checkBorders(0,-1) && validMovement(0,-1)) //check that you dont cross a border + if you dont go to a special position
				updatePosition(0,-1); //in case is a valid movement, update the position
			else System.out.println("You cannot go this direction");
			break;
		//down
		case 2:
			if (checkBorders(1,0) && validMovement(1,0))
				updatePosition(1,0);
			else System.out.println("You cannot go this direction");
			break;
		//right
		case 3: 
			if (checkBorders(0,1) && validMovement(0,1))
				updatePosition(0,1);
			else System.out.println("You cannot go this direction");
			break;
		//up
		case 5:
			if (checkBorders(-1,0) && validMovement(-1,0))
				updatePosition(-1,0);
			else System.out.println("You cannot go this direction");
			break;
		default: System.out.println("Wrong indication"); break;
		}
	}
	
	private void arrow() {
		
		//Save the original hunter position
		Integer[] originalPosition = new Integer[2];
		Integer originalN = hunter.getPosition()[0];
		Integer originalM = hunter.getPosition()[1];
		
		System.out.println("Where do you throw the arrow?   (5: up; 1: left; 3: right; 2: down)");
		Integer arrow = Integer.parseInt(scanner.nextLine());
		Integer[] pos = hunter.getPosition();
		//Check on all cases the next position on the selected direction till arrive to the wumpus or border
		switch (arrow) {
		//left
		case 1: 
			while (board.getBoard()[pos[0]][pos[1]]==0 || board.getBoard()[pos[0]][pos[1]]==1 || board.getBoard()[pos[0]][pos[1]]==3) {
				pos[1]=pos[1]-1;
			};
			break;
		//down
		case 2:
			while (board.getBoard()[pos[0]][pos[1]]==0 || board.getBoard()[pos[0]][pos[1]]==1 || board.getBoard()[pos[0]][pos[1]]==3) {
				pos[0]=pos[0]+1;
			};
			break;
		//right
		case 3: 
			while (board.getBoard()[pos[0]][pos[1]]==0 || board.getBoard()[pos[0]][pos[1]]==1 || board.getBoard()[pos[0]][pos[1]]==3) {
				pos[1]=pos[1]+1;
			};
			break;
		//up
		case 5:
			while (board.getBoard()[pos[0]][pos[1]]==0 || board.getBoard()[pos[0]][pos[1]]==1 || board.getBoard()[pos[0]][pos[1]]==3) {
				pos[0]=pos[0]-1;
			};
			break;
		default: System.out.println("Wrong indication"); break;
		}
		if (board.getBoard()[pos[0]][pos[1]]==2)
				System.out.println("You hit the wumpus");
		else if (board.getBoard()[pos[0]][pos[1]]==9)
				System.out.println("You hit the wall");
		
		originalPosition[0]=originalN;
		originalPosition[1]=originalM;
		hunter.setPosition(originalPosition);
	}
	
	// this function check if you win according the logic
	private void endgame() {
		//you win if you are alive, with the gold and back to the start position
		if (hunter.isTreasure() && hunter.getAlive() && hunter.getPosition()[0]==(board.getN()-2) && hunter.getPosition()[1]==1) {
			System.out.println("###############################################");
			System.out.println("########      CONGRATULATIONS          ########");
			System.out.println("########     YOU BEAT THE WUMPUS       ########");
			System.out.println("###############################################");
			endgameprint(); // show the map
			hunter.setAlive(false); //to made the game end
		} else { // if not is shown the map
			System.out.println("You do not finish correctly the game");
			endgameprint();
			}
	}
	
	//this function updates the hunter position with the given direction
	private void updatePosition(int i, int j) {
		Integer[] position = hunter.getPosition();
		position[0]=position[0]+i;
		position[1]=position[1]+j;
		hunter.setPosition(position);
		System.out.println("Good Movement!");
	}

	// this function return if its a valid movement and print the state after moving to that position
	private boolean validMovement(int n, int m) {
		if (board.getBoard()[hunter.getPosition()[0]+n][hunter.getPosition()[1]+m]==1) {   // status 1 is Holes
			System.out.println("You have fallen on a hole...............   :(");
			endgameprint();
			System.out.println("############    GAME   OVER    ##########");
			hunter.setAlive(false);
			return false;
		} else if (board.getBoard()[hunter.getPosition()[0]+n][hunter.getPosition()[1]+m]==2) { // status 2 is Wumpus
			System.out.println("The wumpus catch you............... :(");
			endgameprint();
			System.out.println("############    GAME   OVER    ##########");
			hunter.setAlive(false);
			return false;
		} else if (board.getBoard()[hunter.getPosition()[0]+n][hunter.getPosition()[1]+m]==3) { // status 3 is Gold
			System.out.println("############ You have found the GOLD!!  :)   ############");
			hunter.setTreasure(true);
			return true;
		} else return true;
	}

	// this function check if you are trying to cross the border of the board
	private boolean checkBorders(Integer n, Integer m) {
		//false for outside the board, true on the board
		return (board.getBoard()[hunter.getPosition()[0]+n][hunter.getPosition()[1]+m]==9) ? false : true; 
	}
	
	// this function print the status of your position with all the board hidden
	private void printstatus() {
		System.out.println("This is where you are");
		String separator="";
		for (int i=1; i<board.getM()-1; i++) separator += "------";
		
		System.out.println(separator);
		for (int i=1; i<board.getM()-1; i++) {
			for (int j=1; j<board.getN()-1; j++) {
				if (hunter.getPosition()[0] == i && hunter.getPosition()[1] == j)
					System.out.print("|  O  ");
				else System.out.print("|  ?  ");
			}
			System.out.println("|");
			System.out.println(separator);
		}
	}
	
	//this function prints what the hunter feels around 
	private void printNeighbours() {
		System.out.println("Next to you there is: ");	
		//Initialize the positions to check
		Integer[] rowsToCheck = new Integer[4];
		Integer[] columnsToCheck = new Integer[4];
		//up
		rowsToCheck[0]=-1;
		columnsToCheck[0]=0;
		//down
		rowsToCheck[1]=1;
		columnsToCheck[1]=0;
		//left
		rowsToCheck[2]=0;
		columnsToCheck[2]=-1;
		//left
		rowsToCheck[3]=0;
		columnsToCheck[3]=1;
		
		//check the positions
		for (int i=0; i<4; i++) {
			if (checkBorders(rowsToCheck[i], columnsToCheck[i]))
				switch (board.getBoard()[hunter.getPosition()[0]+rowsToCheck[i]][hunter.getPosition()[1]+columnsToCheck[i]]) {
				case 1: // for Holes
					System.out.println("- salvage wind");
					break;
				case 2: // for Wumpus
					System.out.println("- smelly things");
					break;
				case 3: // for Gold
					System.out.println("- bright treasure");
					break;
				default: break;
				}
		}
	}

	//this function prints the real board with all the elements
	private void endgameprint() {
		//generate separator
		String separator="";
		for (int i=1; i<board.getM()-1; i++) separator += "------";
		
		System.out.println(separator);
		
		for (int i=1; i<board.getM()-1; i++) {
			for (int j=1; j<board.getN()-1; j++) {
				if (board.getBoard()[i][j]==1)
					System.out.print("|  H  ");
				else if (board.getBoard()[i][j]==2)
					System.out.print("|  W  ");
				else if (board.getBoard()[i][j]==3)
					System.out.print("|  G  ");
				else System.out.print("|     ");
			}
			System.out.println("|");
			System.out.println(separator);
		}
		System.out.println("H for hole");
		System.out.println("W for wumpus");
		System.out.println("G for gold");
			
	}

}
