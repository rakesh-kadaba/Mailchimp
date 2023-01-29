package com.mailchimp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Checksum {
    final static String DELIMITER = "\\s+"; //Delimiter used to split the rows

    // private default constructor so that objects of this class are not created
    private Checksum(){
    }

    /**
     * A static method that returns checksum of the table in the given file.
     *
     * @param  fileName  the name of the file which contains the table
     * @return      the checksum of the table in the given file
     * @throws Exception if input is null or if input is malformed(non-integer values)
     */
    public static long calculateCheckSum(String fileName) throws Exception{
        List<List<Long>> data = getData(fileName);
        if(data == null){
            throw new Exception("Input is null");
        }
        int rows = data.size();
        long checkSum = 0;
        for(int i=0;i<rows;i++){
            List<Long> row = data.get(i);
            int cols = row.size();
            if(cols == 0){
                continue;
            }
            long min = row.get(0);
            long max = row.get(0);
            // calculating min and max for each row
            for(int j=1;j<cols;j++){
                min = Math.min(min,row.get(j));
                max = Math.max(max,row.get(j));
            }
            checkSum = checkSum + (max-min);
        }
        return checkSum;
    }

    /**
     * A static method that returns sum of the quotients of evenly divisible pair of each row in the table
     *
     * @param  fileName  the name of the file which contains the table
     * @return      the sum of the quotients of evenly divisible pair of each row in the table or 0 if nothing is found
     *  @throws Exception if input is null or if input is malformed(non-integer values)
     */
    public static long evenlyDivisible(String fileName) throws Exception{
        List<List<Long>> data = getData(fileName);
        if(data == null){
            throw new Exception("Input is null");
        }
        int rows = data.size();
        long sum = 0;
        for(int i=0;i<rows;i++){
            List<Long> row = data.get(i);
            int cols = row.size();
            if(cols == 0){
                continue;
            }
            Long ans = null; // null signifies no divisible pair is found yet
            for(int j=0;j<cols;j++){
                for(int k=0;k<cols;k++) {
                    if(j==k)
                        continue;
                    long num1 = row.get(j);
                    long num2 = row.get(k);
                    //Avoiding divide by 0
                    if(num2==0)
                        continue;
                    //Checks if num2 divides num1 evenly
                    if(num1%num2==0){
                        if(ans==null){
                            ans = num1/num2;
                        }
                        else{
                            ans = Math.min(ans,num1/num2);
                        }
                    }
                }
            }
            if(ans==null) // no pair found
                continue;
            sum = sum + ans;
        }
        return sum;
    }

    /**
     * A public helper method that returns the data in the file in the form of 2d-array. If the file is not found, logs the error message and returns empty 2d-array.
     * @param  fileName  the name of the file which contains the table
     * @return      2d-array which contains the contents of the file
     * @throws NumberFormatException if input contains non-integer values
     */
    public static List<List<Long>> getData(String fileName) throws Exception{
        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            List<List<Long>> data = new ArrayList<>();
            while (sc.hasNext()) {
                String rowLine = sc.nextLine().trim();
                //Ignoring empty rows
                if(rowLine.isEmpty()){
                    continue;
                }
                String[] rowData = rowLine.split(DELIMITER);
                List<Long> row = new ArrayList<>();
                for(int j=0;j<rowData.length;j++){
                    row.add(Long.parseLong(rowData[j].trim()));
                }
                data.add(row);
            }
            return data;
        }

        catch(NumberFormatException e){
            System.out.println("Input contains non-integer values");
            System.out.println(e.getMessage());
            throw e;
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
