

package startProject;

//Code consists mainly 3 classes
//1 . TicTacToe 
//2 . Player
//    a)HumanPlayer       
//    b)AIPlayer
//3 . Demo 

import java.util.Random;
import java.util.Scanner;

//GAME LOGIC
class TicTacToe
{
static char[][] board;


public TicTacToe()     //<====initialization
{
	board = new char[3][3];
	// empty char is inserted
	initBoard();
	
}

void initBoard()
{
	for (int i=0;i<board.length;i++)
	{
		for(int j=0;j<board[i].length;j++)
		{
			board[i][j]=' ';
		}
	}
}

static void displayBoard()   //<==creating board
{ 
	System.out.println();
	
	
	System.out.println("-------------");     
	for (int i=0;i<board.length;i++)
	{   
		System.out.print("| ");
		for(int j=0;j<board[i].length;j++)
		{
			System.out.print(board[i][j]+" | ");
		}
		System.out.println();
		System.out.println("-------------");
		
	}
	
}

//placing mark code begins
static void placeMark(int row,int col,char mark)
{
	if (row >= 0 && row <= 2 && col >= 0 && col <=2 )
	{
		board[row][col]=mark;
	}
	else
	{
		System.out.println("invalid output");
	}
}


//checking win conditions
// [1]  checking win along column wise

static  boolean checkColumnWin()
{
	for (int j=0;j<2;j++)
   {
	  if (    board[0][j]!=' '      &&
	    board[0][j] == board[1][j]  &&        // board[0][0] == board[1][0]
	    board[1][j] == board[2][j])           // board[1][0] == board[2][0])
	    {
		  return true;
	    }
	    
    }
	return false;
}

// [2]  check for row win
static boolean checkRowWin()
{
	for(int i=0;i<=2;i++)
	{
		if (board[i][0] != ' '   &&
			board[i][0] == board[i][1]  &&    //board[0][0] == board[0][1]
			board[i][1] == board[i][2] )    //  board[0][1] == board[0][2]
		{
			return true;
		}
	} return false;
	
}

// [3]  check for diagonal win

static boolean checkDiagWin()
{
	for (int k=0;k<=2;k++)
	{
		if (   board[0][0] !=' '  &&
			  board[0][0] == board[1][1]  &&  board[1][1] == board[2][2] 
				                   ||
			   board[0][2] !=' ' && 
			   board[0][2] == board[1][1]  &&  board[1][1] == board[2][0])   
		{
			return true;
		}
	}
	return false;
}

//DROW condition
static boolean checkDrow()
{
	for(int i=0;i<=2;i++)
	{
		for(int j=0;j<=2;j++)
		{
			if(board[i][j] ==' ')
			{
				return  false;
			}
		}
	}
	return true;
}
}

//create a Parent class for both Ai and Human
//create a human player

abstract class Player
{
    String name;
	  char mark;
	  
	  abstract void makeMove();
	  
	  boolean isValidMove(int row,int col)
  {
	if (row >=0 && row <=2 && col >=0 && col <=2)
	{
		if (TicTacToe.board[row][col] == ' ')
		{
			return true;
		}
	}
	return false;
  }
}

class HumanPlayer extends Player
{
	
	
	HumanPlayer(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	
	void makeMove()        //<--to make move first check if it is valid move or not
	{
		int row,col;
		do {
			Scanner sn = new Scanner(System.in); 
			System.out.println("Enter ROW & COLUMN number below");
			row =sn.nextInt();
			col=sn.nextInt();
			
			
		}while(! isValidMove(row,col));
		
		TicTacToe.placeMark(row,col,mark);  //<<===is it valid move place ,, mark
		}
	
	
	
}

//create a AI player
class AIPlayer extends Player
{
   
   AIPlayer1(String name,char mark)
   {
	   this.name=name;
	   this.mark=mark;
   }
   
   void  makeMove()
   {
	   int row;
	   int col;
	   
	   do {
		     Random r = new Random();
		     row = r.nextInt(3);
		     col = r.nextInt(3);
		     
	   }while(! isValidMove(row,col));
	   TicTacToe.placeMark(row,col,mark);
   }
}

//main method
public class demo
{

public static void main(String[] args)
{
	TicTacToe t= new TicTacToe();
	
	Scanner scan = new Scanner(System.in);
	
	System.out.println("GAME STARTS HERE.....!");
	
	System.out.print("enter player 1 : ");
	String name1 = scan.nextLine();
	System.out.print("enter player 2 : ");
	String name2 = scan.nextLine();
	
	
	System.out.println("enter 1 for two player match or 2 for versus AI");
	int choise =scan.nextInt();

///-----------T W O  PLAYER Logic Implimentation
	if(choise==1)
	{
		HumanPlayer p1 =new HumanPlayer(name1,'X');      //< == creating two players
		HumanPlayer p2 =new HumanPlayer(name2,'O');
		
		Player cp;  //<==  to switch current player
		
		cp=p1;
		
		while(true)
		
		{
			System.out.println(cp.name + " turn -->");
			cp.makeMove();
			
			TicTacToe.displayBoard();
			
			if (TicTacToe.checkColumnWin() || TicTacToe.checkDiagWin()   //<===if any win occurs
					|| TicTacToe.checkRowWin())
			{
				System.out.println(cp.name +"  $$$$$  WON THE GAME $$$$");
				if (cp==p1)
				{   
					cp=p2;
					System.out.println(cp.name +"  !!!!  LOSE THE GAME ...");
				}
				else
				{
					cp=p1;
					System.out.println(cp.name +" !!!!  LOSE THE GAME...");
				}
				
				break;                                                    /// Stop loop is anyone won
			}
			
			else if (TicTacToe.checkDrow())
			{
				System.out.println("------GAME IS DROW------ ");
				break;
			}
			else
			{
				if (cp==p1)
				{
					cp=p2;   //switch user
					
				}
				else
				{
					cp=p1;
				}
			}
		

	    }
	}
 ///-----------VERSUS AI(Computer)
	else            
	{
		HumanPlayer p1 =new HumanPlayer(name1,'X');      //< == creating two players
		AIPlayer p2 =new AIPlayer(name2,'O');
		
		Player cp;  //<==  to switch current player
		
		cp=p1;
		
		while(true)
		
		{
			System.out.println(cp.name + " turn -->");
			cp.makeMove();
			
			TicTacToe.displayBoard();
			
			if (TicTacToe.checkColumnWin() || TicTacToe.checkDiagWin()   //<===if any win occurs
					|| TicTacToe.checkRowWin())
			{
				System.out.println(cp.name +"  $$$$$  WON THE GAME $$$$");
				if (cp==p1)
				{   
					cp=p2;
					System.out.println(cp.name +"  !!!!  LOSE THE GAME ...");
				}
				else
				{
					cp=p1;
					System.out.println(cp.name +" !!!!  LOSE THE GAME...");
				}
				
				break;                                                    /// Stop loop if anyone won
			}
			
			else if (TicTacToe.checkDrow())
			{
				System.out.println("------GAME IS DROW------ ");
				break;
			}
			else
			{
				if (cp==p1)
				{
					cp=p2;   //switch user
					
				}
				else
				{
					cp=p1;
				}
			}
   	}
       }				
  }
}