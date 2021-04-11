/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biginteger;

/**
 *
 * @author Napapis
 */
public class UsingBigInteger {

    public static void main(String[] args) {
        BigInteger b0 = new BigInteger("-123");
        System.out.println("b0=" + b0);
        
        BigInteger b1 = new BigInteger(-44);
        System.out.println("b1=" + b1);
        
        BigInteger b2;
        b2 = b0.multiply(b1);
        System.out.println("b2=" + b0+"*"+b1+"="+b2);
        
        BigInteger b3 = new BigInteger(b2); 
        System.out.println("b3=" + b3);
       
    }
}
