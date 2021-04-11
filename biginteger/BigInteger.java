package biginteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Napapis Dekker Assignment 1 - Iterative Algorithms CSD 436 
 *
 */
public class BigInteger {

    private List<Byte> digits;
    private boolean isNegative;

    // default constructor
    public BigInteger() {
        digits = new ArrayList<>();
        digits.add((byte) 0);
        isNegative = false;
    }

    // construct a big integer from a String of digits
    public BigInteger(String strDigits) {
        if (strDigits == null || strDigits.length() == 0) {
            throw new IllegalArgumentException();
        }
        int firstDigitIndex = 0;
        if (strDigits.charAt(firstDigitIndex) == '-') {
            isNegative = true;
            firstDigitIndex++;
        } else {
            isNegative = false;
        }
        digits = new ArrayList<>();

        //for (int i = firstDigitIndex; i < strDigits.length(); i++){ 
        //reverse string
        for (int i = strDigits.length() - 1; i >= firstDigitIndex; i--) {
            byte digit = (byte) ((int) strDigits.charAt(i) - '0');

            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException();
            }
            digits.add(digit);
        }
    }

    // construct a big integer from an integer
    public BigInteger(int nDigits) {
        if (nDigits == 0) {
            digits = new ArrayList<>();
            digits.add((byte) 0);
            return;
        } else if (nDigits < 0) {
            isNegative = true;
            nDigits *= -1;
        }

        digits = new ArrayList<>();

        while (nDigits > 0) {
            byte d = (byte) (nDigits % 10);

            if (d < 0 || d > 9) {
                throw new IllegalArgumentException();
            }

            digits.add(d);
            nDigits /= 10;
        }
    }

    // construct a big integer from a  big integer
    public BigInteger(BigInteger srcBi) {
        digits = new ArrayList<>();
        isNegative = srcBi.isNegative;
        for (Byte b : srcBi.digits) {
            digits.add(b);
        }
    }

    // construct a big integer from a list of digits
    // with no error checking
    private BigInteger(List<Byte> srcDigits) {
        if (srcDigits == null) {
            throw new IllegalArgumentException();
        }
        this.digits = srcDigits;
    }

    // add two big integers and return a reference to the big integer sum
    public BigInteger add(BigInteger bi) {

        Iterator<Byte> itSmall, itLarge;

        if (bi.digits.size() > this.digits.size()) {
            itLarge = bi.digits.iterator();
            itSmall = this.digits.iterator();
        } else {
            itLarge = this.digits.iterator();
            itSmall = bi.digits.iterator();
        }

        byte carry = 0, rdigit;
        List<Byte> resultDigits = new ArrayList<>();

        while (itSmall.hasNext()) {
            rdigit = (byte) (itSmall.next() + itLarge.next() + carry);

            if (rdigit > 9) {
                rdigit -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            resultDigits.add(rdigit);
        }

        while (itLarge.hasNext()) {
            rdigit = (byte) (itLarge.next() + carry);

            if (rdigit > 9) {
                rdigit -= 10;
                carry = 1;
            } else {
                carry = 0;
            }

            resultDigits.add(rdigit);
        }

        if (carry == 1) {
            resultDigits.add(carry);
        }

        return new BigInteger(resultDigits);
    }

    // output a string representation of the big integer
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte d : digits) {
            sb.append(d);
        }
        if (isNegative) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
//multiply( BigInteger bi 
//return a reference to a newly constructed BigInteger that contains the project of "this" and "bi"
//The original values stored in "this" and "bi" must not be changed by the method.
    public BigInteger multiply(BigInteger bi) {
       
            List<Byte> itSmall, itLarge;
            BigInteger sumNum = new BigInteger();

            if (bi.digits.size() > this.digits.size()) {
                itLarge = bi.digits;
                itSmall = this.digits;
            } else {
                itLarge = this.digits;
                itSmall = bi.digits;
            }
            byte carry = 0, rdigit;
            int smallIndex, largeIndex,index;
            for (smallIndex = 0; smallIndex < itSmall.size(); smallIndex++) {
                List<Byte> resultDigits = new ArrayList<>();
                index = smallIndex;
                for (largeIndex = 0; itLarge.size() > largeIndex; largeIndex++) {
                    rdigit = (byte) (itSmall.get(smallIndex) * itLarge.get(largeIndex) + carry);
                    if (rdigit > 9) {
                        carry = (byte) (rdigit / 10);
                        rdigit = (byte) (rdigit % 10);
                    } else {
                        carry = 0;
                    }

                    resultDigits.add(rdigit);
                }
                if (carry > 0) {
                    resultDigits.add(carry);
                }
                IntStream.range(0, index).forEach((int i) -> {
                    resultDigits.add(0, (byte) 0);
                });
             
                BigInteger temp = new BigInteger(resultDigits);
                sumNum = sumNum.add(temp);

            }
            //check for negative number
            sumNum.isNegative = (this.isNegative == true && bi.isNegative == false)
                    || (this.isNegative == false && bi.isNegative == true);

            return new BigInteger(sumNum);
        
    }
}
