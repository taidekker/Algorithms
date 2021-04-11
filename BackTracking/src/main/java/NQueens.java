
/**
 * Napapis Dekker CSD 436
 * Assignment 7 - Recursive Backtracking
 * ---NQueens Class---
 * reference : https://java.achchuthan.org/2012/02/n-queens-problem-in-java.html
 */
import java.util.ArrayList;

public class NQueens {

    final private int size;
    private int k = 1;
    private final ArrayList<ArrayList<Pair>> solveNQueens;
    int[] nQueens;
    private final int[] checkRankFile;
   // private final int[] checkMajorDiagonal;
    private final int[] checkMinorDiagonal;
    
    //A single public constructor that accepts an int parameter specifying n
    public NQueens(int n) {
        this.size = n;
        solveNQueens = new ArrayList<>();
        this.nQueens = new int[n];
        this.checkRankFile = new int[n];
     //   this.checkMajorDiagonal = new int[2 * n - 1];
        this.checkMinorDiagonal = new int[2 * n - 1];
    }

    /**
     * A non-static public method named Calculate that performs a recursive backtracking algorithm
     * to calculate the solutions of n queens on a chessboard of size of n x n.
     * @param n
     * @return 
     */  
    public int Calculate(int n) {
        if (n == size) {
            ArrayList<Pair> solve = new ArrayList<>();
            for (int i = 0; i < nQueens.length; i++) {
                int row = i;
                int col = nQueens[i];
                solve.add(new Pair(row, col, size));
            }
            solveNQueens.add(solve);
            return 1;
        }

        int count = 0;
        for (int file = 0; file < size; file++) {
            if (getSolutions(n, file)) {
                //place a queen
                nQueens[n] = file;
                checkRankFile[file]++;
                checkMinorDiagonal[n - file + size - 1]++;
                checkMinorDiagonal[n + file]++;

                count += Calculate(n + 1);
                //remove a queen
                checkRankFile[file]--;
                checkMinorDiagonal[n - file + size - 1]++;
               checkMinorDiagonal[n + file]++;
            }
        }

        return count;
    }

    /**
     * a non-static public method named getSolution 
     * that returns all of the solutions found by the recursive backtracking algorithm.
     * @param rank
     * @param file
     * @return 
     */
    public boolean getSolutions(int rank, int file) {
        for (int i = 0; i < rank; i++) {
            //checking for spots which already placing. Retuens false .
            if (nQueens[i] == file || (i - rank) == (nQueens[i] - file) || (i - rank) == (file - nQueens[i])) {
                return false;
            }
        }
        return true;//returns true if a queen can be placed.
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        for (ArrayList<Pair> solved : this.solveNQueens) {
            string.append("\nSolution # ").append(this.k++).append("\n");
            for (Pair pair : solved) {
                string.append(pair).append("\n");
            }
            string.append("\n");
        }
        return string.toString();
    }

}
