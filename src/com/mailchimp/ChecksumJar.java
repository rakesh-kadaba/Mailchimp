package com.mailchimp;

public class ChecksumJar {
    final static String CHECKSUM = "checksum";
    final static String EVENLY_DIVISIBLE = "evenlyDivisible";

    public static void main(String[] args) throws Exception{
        String action = args[0];
        String filePath = args[1];
        if(CHECKSUM.equals(action)){
            long checksum = Checksum.calculateCheckSum(filePath);
            System.out.println("Checksum is " + checksum);
        }
        else if(EVENLY_DIVISIBLE.equals(action)){
            long evenlyDivisibleSum = Checksum.evenlyDivisible(filePath);
            System.out.println("Evenly divisible sum is "+ evenlyDivisibleSum);
        }
        else{
            System.out.println("Action can be only 'checksum' or 'evenlyDivisible'");
            System.out.println("Please try again");
        }
    }
}
