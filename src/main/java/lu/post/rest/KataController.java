package lu.post.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Fabien Steines
 */

@RestController
public class KataController {

    /*
    * Square - solved
    * */

    /**
     *
     * @param numberOfBlocks
     * @return
     */
    @CrossOrigin
    @RequestMapping("/square")
    public boolean isSquare(@RequestParam(value="numberOfBlocks")int numberOfBlocks) {
        return Math.ceil(Math.sqrt(numberOfBlocks)) == Math.sqrt(numberOfBlocks);
    }

    /*
        Needle in the Haystack - solved
     */

    /**
     * @param haystack
     * @return
     */
    @CrossOrigin
    @RequestMapping("/findNeedle")
    public String findNeedle(@RequestParam(value="haystack")Object[] haystack) {

        int counter = 0;
        boolean found = false;
        while (!found && counter < haystack.length) {
            found = haystack[counter].equals("needle");
            if (!found) {
                counter++;
            }
        }

        if (found) {
            return String.format("found the needle at position %d",counter);
        } else {
            return "No needle in Array";
        }

    }

    /*
    * Find Odd - solved
    * */

    /**
     *
     * @param A
     * @return
     */
    @CrossOrigin
    @RequestMapping("/findOdd")
    public int findIt(@RequestParam(value="pool")int[] A) {

        int counter = 0;
        boolean found = false;

        while (!found && counter < A.length) {
            found = isOdd(A[counter],A);
            if (!found) {
                counter++;
            }
        }

        return A[counter];
    }

    /**
     *
     * @param number
     * @param array
     * @return
     */
    private boolean isOdd(int number,int[] array) {

        int count = 0;

        for (int element : array) {
            if (element == number) {
                count++;
            }
        }

        return count % 2 != 0;
    }

    /*
        Song Decode - solved
     */

    /**
     *
     * @param input
     * @return
     */
    @CrossOrigin
    @RequestMapping("/songDecode")
    private String songDecoder(@RequestParam(value="song") String input) {
        String[] songParts = input.split("WUB");
        String output = "";
        for (String current : songParts) {
            if (!current.isEmpty()) {
                if (output.isEmpty()) {
                    output = String.format("%s",current);
                } else {
                    output += String.format(" %s",current);
                }
            }
        }
        return output;
    }


    /*
    * ACCUM - Solved
    * */

    /**
     *
     * @param s
     * @return
     */

    @CrossOrigin
    @RequestMapping("/accum")
    public String accum( @RequestParam(value="input") String s) {

        String output = "";
        int max = s.length();

        for (int index = 0;index < max;index++) {
            for (int rep = 0;rep < index+1;rep++) {
                if (rep == 0) {
                    output += String.valueOf(s.charAt(index)).toUpperCase();
                } else {
                    output += String.valueOf(s.charAt(index)).toLowerCase();
                }
            }

            if (index != max - 1) {
                output += "-";
            }
        }

        return output;
    }


    /*
    * GAB IN PRIMES - unsolved
    * */

    /**
     *
     * @param g
     * @param m
     * @param n
     * @return
     */
    @CrossOrigin
    @RequestMapping("/gabInPrimes")
    public long[] gabInPrimes(@RequestParam(value="gap") int g,
                                     @RequestParam(value="start") long m,
                                     @RequestParam(value="end") long n) {

        long[] allPrimeNumbers = new long[0];
        for (long i=m;i<=n;i++) {
            if (isPrimeNumber(i)) {
                allPrimeNumbers = addToArray(i,allPrimeNumbers);
            }
        }

        long[] validNumbers = new long[0];
        for (int index = 0; index < allPrimeNumbers.length-1;index++) {
            if (allPrimeNumbers[index+1] - allPrimeNumbers[index] == g) {
                validNumbers = addToArray(allPrimeNumbers[index],validNumbers);
            }
        }

        return validNumbers.length > 0 ? validNumbers : null;
    }

    /**
     *
     * @param number
     * @return
     */
    private boolean isPrimeNumber(long number) {

        int numberOfDiv = 0;

        for (int i=1;i<number;i++) {
            if (number % i == 0) {
                numberOfDiv++;
            }
        }

        return numberOfDiv == 1;
    }

    /**
     *
     * @param value
     * @param array
     * @return
     */
    private long[] addToArray(long value,long[] array) {
        long[] newArray = new long[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[newArray.length - 1] = value;
        return newArray;
    }

    /*
    * PrinterError - unsolved - for now ;)
    * */

    /**
     *
     * @param s
     * @return
     */
    @CrossOrigin
    @RequestMapping("/printerError")
    public static String printerError(@RequestParam(value = "input") String s) {

        char start = 'a';
        char end = 'm';
        int count = s.length();
        int countInvalid = 0;

        for (int i=0;i<count;i++) {

            char current = s.charAt(i);
            if (!(start <= current && current <= end)) {
                countInvalid ++;
            }
        }

        return String.format("%d/%d",countInvalid,count);
    }

}
