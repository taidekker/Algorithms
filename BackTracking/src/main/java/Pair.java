
/**
 * Napapis Dekker CSD 436
 * Assignment 7 - Recursive Backtracking
 * ----Pair Class--
 * 
 * A row is called "rank"; a column is called a "file".
 * Files are labeled from left to right starting with "A".
 * The ranks are numbered from bottom to top starting with 1 and increase by 1 until reaching the first row
 * Implement position as a Pair.
 * 
 * reference: https://www.programmersought.com/article/36457368960/
 */
public class Pair {

    public String rank;
    public String file;

    public Pair(int file, int rank, int n) {

        char charF = (char) ((char) 'A' + file);
        this.file = "" + charF;
        this.rank = "" + (n - rank);
    }

    @Override
    public String toString() {
        //The first element in the Pair is file, the second element is rank
        String string = "( " + this.file + ", " + this.rank + " )";
        return string;
    }

}
