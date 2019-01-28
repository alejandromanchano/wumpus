package Wumpus.Wumpus;

import java.util.Random;

public class Board {
	
	private Integer[][] tablero;
	private Integer n;
	private Integer m;
	
	
	public Board(Integer n, Integer m, Integer holes) {
		super();
		this.n = n;
		this.m = m;
		this.tablero = generateBoard(n, m, holes);
	}
	
	private Integer[][] generateBoard(Integer n, Integer m, Integer holes) {
		Integer board[][] = new Integer[n][m];
		for (int i=0; i<n; i++)
			for (int j=0; j<m; j++)
				board[i][j] = (i==0 | i==n-1 | j==0 | j==m-1) ? 9 : 0; 
		
		Random random = new Random(System.currentTimeMillis());
		int nRandom;
		int mRandom;
		
		//set the holes
		for (int i=0;i<holes; i++) {
			do {
				nRandom = random.nextInt(n-2)+1;
				mRandom = random.nextInt(m-2)+1;
			} while (board[nRandom][mRandom]==1 && nRandom != n-1 && mRandom != 1);
			board[nRandom][mRandom] = 1 ;
		}
		
		//set the wumpus
		do {
			nRandom = random.nextInt(n-2)+1;
			mRandom = random.nextInt(m-2)+1;
		} while (board[nRandom][mRandom]==1); //to not overwrite holes
		board[nRandom][mRandom] = 2 ;
			
		//set the gold
		do {
			nRandom = random.nextInt(n-2)+1;
			mRandom = random.nextInt(m-2)+1;
		} while (board[nRandom][mRandom]==1 | board[nRandom][mRandom]==2); //to not overwrite holes or wumpus
		board[nRandom][mRandom] = 3 ;
		
		return board;
	}

	public Integer[][] getBoard() {
		return tablero;
	}


	public Integer getN() {
		return n;
	}


	public Integer getM() {
		return m;
	}
	
}
