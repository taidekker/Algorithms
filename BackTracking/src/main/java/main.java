/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author napapis
 */
public class main {
     public static void main(String[] args) {

        NQueens queen = new NQueens(10);
        queen.Calculate(0);

        System.out.print(queen.toString());

    }
    
}
