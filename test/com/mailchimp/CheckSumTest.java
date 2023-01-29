package com.mailchimp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CheckSumTest {
    static String PATH; // The path where all test files are present

    @BeforeAll
    protected static void setUp(){
        Path currentRelativePath = Paths.get("");
        String absPath = currentRelativePath.toAbsolutePath().toString();
        PATH = absPath + "/test/resources/";
    }

    @Test
    public void checkSum_smallInputNoEdgeCases_normalBehavior() throws Exception{
        long checksum = Checksum.calculateCheckSum(PATH + "test1.txt");
        assertEquals(18l,checksum);
    }

    @Test
    public void checkSum_oneRowEmpty_skipRow() throws Exception{
        long checksum = Checksum.calculateCheckSum(PATH + "test5.txt");
        assertEquals(14l,checksum);
    }

    @Test
    public void checkSum_oneRowExtraSpacesBetween_ignoreExtraSpaces() throws Exception{
        long checksum = Checksum.calculateCheckSum(PATH + "test6.txt");
        assertEquals(14l,checksum);
    }

    @Test
    public void checkSum_largeInputNoEdgeCases_normalBehavior() throws Exception{
        long checksum = Checksum.calculateCheckSum(PATH + "test2.txt");
        assertEquals(41919l,checksum);
    }

    @Test
    public void checkSum_rowHasNonInteger_throwsException(){
        assertThrows(NumberFormatException.class, () -> {
            Checksum.calculateCheckSum(PATH + "NonInteger.txt");
        });
    }

    @Test
    public void checkSum_rowHasNumericNonInteger_throwsException() {
        assertThrows(NumberFormatException.class, () -> {
            Checksum.calculateCheckSum(PATH + "NumericNonInteger.txt");
        });
    }

    @Test
    public void checkSum_inputWithNegativeNumbers_normalBehavior() throws Exception{
        long sum = Checksum.calculateCheckSum(PATH + "NegativeNumbers.txt");
        assertEquals(38l,sum);
    }

    @Test
    public void evenlyDivisible_noPairInOneRow_noPairRowIgnored() throws Exception{
        long checksum = Checksum.evenlyDivisible(PATH + "NoPairRow.txt");
        assertEquals(2l,checksum);
    }

    @Test
    public void evenlyDivisible_multiplePairsInOneRow_multiplePairsRowPickSmallest() throws Exception{
        long checksum = Checksum.evenlyDivisible(PATH + "MultiplePairRow.txt");
        assertEquals(3l,checksum);
    }

    @Test
    public void evenlyDivisible_largeInputCoverMultipleCases_normalBehavior() throws Exception{
        long sum = Checksum.evenlyDivisible(PATH + "test2.txt");
        assertEquals(303l,sum);
    }

    @Test
    public void evenlyDivisible_inputWithZero_ignoreZero() throws Exception{
        long sum = Checksum.evenlyDivisible(PATH + "InputWithZero.txt");
        assertEquals(4l,sum);
    }

    @Test
    public void evenlyDivisible_inputWithNegativeNumbers_normalBehavior() throws Exception{
        long sum = Checksum.evenlyDivisible(PATH + "NegativeNumbers.txt");
        assertEquals(-5l,sum);
    }
}
