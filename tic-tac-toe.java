package tictactoe;

import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String input = "_________";
        String[][] matrix = makeMatrix(input);
        printMatrix(matrix);
        boolean work = true;
        int n = 0;
        while (work) {
                //work = checkValid(matrix);
                System.out.print("Enter the coordinates: ");
            if (!scanner.hasNextInt()) {
                String garbage = scanner.next();
                System.out.println("You should enter numbers!");
            } else {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                work = true;
                if (x > matrix.length || y > matrix.length || x < 1 || y < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    if (!matrix[matrix.length - y][x - 1].equals(" ")) {
                        System.out.println("This cell is occupied! Choose another one!");
                        n--;
                    } else {
                        if (n % 2 == 0) {
                            work = addX(x, y, matrix);
                        } else {
                            work = addO(x, y, matrix);
                        }
                        n++;
                    }
                }
            }
        }
    }
   /* public static boolean checkValid(String[][] matrix) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        boolean work = true;
        if ( x>matrix.length || y> matrix.length || x<1 || y<1) {
            System.out.println("Coordinates should be from 1 to 3!");
        }
        else {
            if (!matrix[matrix.length - y][x - 1].equals(" ")) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                if (n % 2 == 0) {
                    work = addX(x, y, matrix);
                } else {
                    work = addO(x, y, matrix);
                }

            }
        }
        return work;


    }

    */
    public static boolean addX(int x, int y, String[][] matrix){
        matrix[matrix.length-y][x-1]= "X";
        printMatrix(matrix);
        return gameChecker(matrix);

    }
    public static boolean addO(int x, int y, String[][] matrix){
        matrix[matrix.length-y][x-1]= "O";
        printMatrix(matrix);
        return gameChecker(matrix);
    }
    public static void printMatrix(String[][] matrix){
        System.out.println("---------");

        for ( int x = 0 ; x < matrix.length ; x++){
            System.out.print("| ");
            for (int g = 0 ; g < matrix[0].length; g++) {
                System.out.print(matrix[x][g] + " ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("---------");

    }
    public static String[][] makeMatrix(String input) {
        String[] list = input.split("");
        String[][] matrix = new String[3][3];

        int lenOfSquare = 3 ;

        int k = 0;
        for ( int i = 0; i<lenOfSquare ; i++ ) {
            for ( int x = 0 ; x < lenOfSquare ; x++) {
                if (list[k].equals("_")) {
                    matrix[i][x] = " ";
                } else {
                    matrix[i][x] = list[k];
                }
                k++;
            }
        }
        return matrix;
    }
    public static boolean gameChecker(String[][] matrix) {
        //check row win
        int checkerX = 0;
        int checkerO = 0;
        int counterX = 0;
        int counterO = 0;
        int lenOfSquare = 3 ;
        String answer = "";
        boolean work = true;

        for ( int i = 0; i<lenOfSquare ; i++ ) {
            for ( int x = 0 ; x < lenOfSquare ; x++) {
                if (matrix[i][x].equals("X")) { checkerX++; counterX++; }
                else { checkerX=0; }

                if (matrix[i][x].equals("O")) { checkerO++; counterO++; }
                else { checkerO=0; }
            }
            if (!answer(checkerO,checkerX,answer,counterO,counterX)) {
                work = false;
                break;
            }
            checkerO=0; checkerX = 0;
            for ( int b = 0 ; b<lenOfSquare ; b++) {
                if (matrix[b][i].equals("X")) { checkerX++; }
                else { checkerX=0; }

                if (matrix[b][i].equals("O")) { checkerO++; }
                else { checkerO=0; }
            }
            if (!answer(checkerO,checkerX,answer,counterO,counterX)) {
                work = false;
                break;
            }
            checkerO=0; checkerX = 0;

            if (  (matrix[0][0].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][2]))  ||
                    (matrix[0][2].equals(matrix[1][1]) && matrix[1][1].equals(matrix[2][0]))   ) {
                if (matrix[1][1].equals("X")) {
                    checkerX = 3;
                }
                else if (matrix[1][1].equals("O")) {
                    checkerO = 3;
                }
                if (!answer(checkerO,checkerX,answer,counterO,counterX)) {
                    work = false;
                    break;
                }
                checkerO=0; checkerX = 0;
            }
        }
        return  work;
    }
    public static boolean answer(int checkerO, int checkerX, String answer, int counterO, int counterX) {
        boolean work = true;
        if (checkerO==3) {
                answer = "O wins";
            System.out.println(answer);
                work=false;
        } else if (checkerX==3) {
                answer = "X wins";
            System.out.println(answer);
                work=false;
        } else  if ( ((counterO ==5 && counterX==4)|| (counterO == 4 && counterX == 5)) &&
                !(answer.equals("X wins") || answer.equals("O wins"))  ) {
            answer = "Draw";
            System.out.println(answer);
            work=false;
        }
        return work;
    }

}
