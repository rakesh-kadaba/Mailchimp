# Checksum
- This readme is written as part of the two part assignment to describe the problem, technical requirements, alternate solutions or any other information necessary to understand the design and implementation of the given problem. It contains following sections for each question:
  - Question
  - Requirements
  - Input format
  - Algorithm
  - Design and Implementation details
  - Edge cases
- A javadoc is also created for the Checksum java file which gives brief information about the  methods in it. It can be found in Mailchimp/javadoc/com/mailchimp/Checksum.html file
- To use this for custom test case, you can call the static methods Checksum.checksum() or Checksum.evenlyDivisible() with the file name which contains the table as a parameter
- The example test cases provided in the document are in thw test set
## Part 1
### Question
In this problem, you will build an application which calculates the checksum of a data set. The input comes in the form of a table. For each row in the table, determine the difference between the largest value and the smallest value; the checksum is the sum of all those differences.

### Requirements
- calculate the row checksum: Difference between maximum and minimum value of row data 
- calculate the table checksum: sum of row checksums

### Input format
- Data is in a file in tabular format

### Algorithm
- Have a variable checkSum initialized to zero
- Iterate through each row, if there are no columns in a particular row, just go to next row in the table
- In each row, assign the first element as min and max value
- Then go from second element to last element and keep updating min and max values accordingly
- Add (max-min) to checkSum
- return checkSum once all the data is processed

### Design and Implementation Details
- The application is broken down into two reusable methods:
  - getData: Helper method to read the data from a file and convert it to tabular format (2d-array)
  - calculateCheckSum: the core logic as mentioned in the algorithm to calculate checksum
- Test cases have been added which evaluate the correctness of the algorithm and edge cases using JUnit


### Edge Cases
- Empty Row : If a particular line in file has no data, then ignoring the line which implies the row checksum as 0 
    - Alternative : A custom Exception can be thrown indicating one of the rows is empty if input is expected to have no empty row
- Empty spaces within a row : This case is considered a happy path since there can be extra white space between number as shown in the example below
  - 2 4 &nbsp;&nbsp;&nbsp;&nbsp;       6 8
  - Alternatively, This extra space can be considered as sad path if necessary
- Non-Integer values in a row: If a row contains any characters other than numbers [0-9], it is considered a sad path and an Exception is thrown
  - Example 1: 2 4 a  3
  - Example 2: 2.5 3 4
  - Note: Double values are considered as erroneous values in the input
- Overflow - If checksum exceeds the maximum value of long data type(9223372036854775807)
  - This case is not handled at this moment, to keep the application simple.
  - One approach to handle this could be to use MOD operation with a large prime number (10^9+7)
  - Other approach could be to use bigger data type, like maybe BigInteger, if needed.

## Part 2
### Question
In this problem, The goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. Find these numbers on each line, divide them, then add each line's result.

### Requirements
- Find the two numbers in each row where one evenly divides the other and get the quotient.
- Add all such quotients.

### Input format
- Data is in a file in tabular format

### Algorithm
- Have a variable sum initialized to zero
- Iterate through each row, if there are no columns in a particular row, just go to next row in the table
- In each row, creat a variable ans and assign it to null signifying that no pair is found until now
- Then have 2 for loops over all the elements of the current row 
  - Outer loop: j from 0 to current row length - 1
  - Inner loop: k from 0 to current row length - 1
  - If j==k then continue; else check if kth element fully divides jth element
    - If yes, assign row[j]/row[k] to ans if ans is previously null; or take min of ans and row[j]/row[k]
    - If no, then just continue
- If ans is not null, add it to sum
- Return sum once all data is processed

### Design and Implementation Details
- The application is broken down into two reusable methods:
  - getData: Helper method to read the data from a file and convert it to tabular format (2D-Array)
  - evenlyDivisible: the core logic as explained in the algorithm
- Test cases have been added which evaluate the correctness of the algorithm and edge cases using JUnit

### Edge Cases
- Empty Row : If a particular line in file has no data, then the line is ignored
  - Alternative : A custom Exception can be thrown indicating one of the rows is empty
- Empty spaces within a row : This case is considered a happy path since there can be extra white space between number as shown in the example below
  - 2 4 &nbsp;&nbsp;&nbsp;&nbsp;       6 8
  - Alternative: This extra space can be considered malformed data if input has strict definition
- Non-Integer values in a row: If a row contains any characters other than numbers [0-9], it is considered a sad path and an Exception is thrown
  - Example 1: 2 4 a  3
  - Example 2: 2.5 3 4
  - Note: Double values are considered as erroneous values in the input
- Divide by 0: If a 0 is present in the row then that number is skipped to avoid any division by 0 attempt.
  - Alternative: Throw an Exception that the data is erroneous if 0 is not expected in the row.
- No divisible pair: If no divisible pair is found, then the contribution of that row is considered as 0
  - Example: 3 5 7 -> no divisible pair
  - Alternative 1: Throw a custom exception if input has a strict requirement of having at least one pair satisfying the requirement
- More than 2 pairs of divisible numbers: If more than one pair of numbers are found which generate whole number when divided, then selecting the minimum of them.
  - Example: 3 6 9 -> 2 is selected (6/3==2)
  - Alternative 1: Take maximum of the quotients
  - Alternative 2: Collect the possible solutions in each row and have that as input of part 1
  - Alternative 3: Throw a custom exception if input has a strict requirement of having only one pair satisfying the requirement
- Overflow - If checksum exceeds the maximum value of long data type(9223372036854775807)
  - This case is not handled at this moment, to keep the application simple.
  - One approach to handle this could be to use MOD operation with a large prime number (10^9+7)
  - Other approach could be to use bigger data type, like maybe BigInteger, if needed.
  
If there are any concerns or clarifications please mail: rakeshkumar5165@gmail.com

  



