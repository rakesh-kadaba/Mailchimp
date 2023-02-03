package com.mailchimp;

public class ChecksumMain {

    public static void main(String[] args) throws Exception{
        //use your absolute file path which contains the table
        String filePath = "/Users/rakeshkumarsanathkumarkadaba/Documents/test/dummy.txt";
        long checksum = Checksum.calculateCheckSum(filePath);
        System.out.println("Checksum is " + checksum);
        long evenlyDivisibleSum = Checksum.evenlyDivisible(filePath);
        System.out.println("Evenly divisible sum is " + evenlyDivisibleSum);
    }
}
