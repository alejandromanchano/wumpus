package Wumpus.Wumpus;

import java.util.Scanner;

public class WumpusGame {
	
	private Hunter hunter;
	private Board board;
	Scanner scanner = new Scanner (System.in);
	
	public void start() {
		//simple game starter menu
		System.out.println("Welcome to the Wumpus GAME");
		Boolean play = true;
		while (play) {
			initialize();
			newGame();
			System.out.println("Do you wanna play again?");
			System.out.println("type yes or no");
			String response = scanner.nextLine();
			play = response.equals("yes") ? true : response.equals("no") ? false : false; 
		}
	}
	
	private void initialize() {
		
		//Initialize all variables of the game
		System.out.println("How is your Name?");
		String name = scanner.nextLine();
		System.out.println("Thanks "+name+". How many columns have your board?");
		Integer columns = Integer.parseInt(scanner.nextLine());
		System.out.println("Thanks "+name+". How many rows have your board?");
		Integer rows = Integer.parseInt(scanner.nextLine());
		System.out.println("Thanks "+name+". How many arrows do you have?");
		Integer arrows = Integer.parseInt(scanner.nextLine());
		System.out.println("Thanks "+name+". How many holes do you have?");
		Integer holes = Integer.parseInt(scanner.nextLine());
		
		//Set the starting position on the down left corner
		Integer startingPosition[] = new Integer[2];
		startingPosition[0] = rows;
		startingPosition[1] = 1;
		
		//Initialize the hunter alive and without the treasure at the starting position
		this.hunter = new Hunter(name, arrows, true, startingPosition, false);
		
		//Initialize the board with the rows, columns and holes desired
		//rows and columns are +2 to treat the borders
		this.board = new Board(rows+2, columns+2, holes);
	}
	
	private void newGame() {
		//Initialize the controller with the board and the hunter
		Controller controller = new Controller(board, hunter);
		//Begin the game
		controller.start();

	}
	

}
