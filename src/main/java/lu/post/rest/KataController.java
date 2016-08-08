package lu.post.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author Fabien Steines
 */

@RestController
public class KataController {

    @CrossOrigin
    @RequestMapping("/square")
    public boolean isSquare(@RequestParam(value="numberOfBlocks")int numberOfBlocks) {
        return Math.ceil(Math.sqrt(numberOfBlocks)) == Math.sqrt(numberOfBlocks);
    }

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

    private boolean isOdd(int number,int[] array) {

        int count = 0;

        for (int element : array) {
            if (element == number) {
                count++;
            }
        }

        return count % 2 != 0;
    }


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

    @CrossOrigin
    @RequestMapping("/gabInPrimes")
    public long[] gabInPrimes(@RequestParam(value="gap") int g,
                                     @RequestParam(value="start") long m,
                                     @RequestParam(value="end") long n) {

        ArrayList<Long> allPrimeNumbers = new ArrayList<>();
        for (long i=m;i<=n;i++) {
            if (isPrimeNumber(i)) {
                allPrimeNumbers.add(i);
            }
        }

        long[] validNumbers = new long[0];
        for (int index = 0; index < allPrimeNumbers.size()-1;index++) {
            if (allPrimeNumbers.get(index+1) - allPrimeNumbers.get(index) == g) {
                addToArray(allPrimeNumbers.get(index),validNumbers);
                addToArray(allPrimeNumbers.get(index+1),validNumbers);
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

        for (int i=0;i<number;i++) {
            if (i % 2 == 0) {
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

}
