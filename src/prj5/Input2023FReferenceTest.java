package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import student.testingsupport.annotations.Hint;

/**
 * Test text output for Music Visualization Project
 *
 * @author CS 2114
 * @version Fall 2023
 */
public class Input2023FReferenceTest
    extends student.TestCase
{
    private String fileName;
    private Scanner fileData;

    /**
     * sets up any needed variables for test methods
     */
    public void setUp()
    {

        fileData = null;
    }
    
    /**
     * Test the program with data the students haven't seen Gathers the output
     * from StdOut and compares it to the expect output (stored in
     * InfluencerOutput_2.txt)
     */
    @Hint("The main method is not working properly with different input files")
    public void testMainFuzzyEqualsDifferentInput()
        throws java.io.IOException
    {

        ProjectRunner.main(new String[] { "SampleInput1_2023.csv" });

        fileName = "SampleOutput1_2023.txt";

        String InfluencerOutput_2 = "";
        fileData = null;
        try
        {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }

        while (fileData.hasNextLine())
        {
            InfluencerOutput_2 += fileData.nextLine() + "\n";
        }

        assertFuzzyEquals(
            "Output not as expected for other input files " + ".csv",
            InfluencerOutput_2,
            systemOut().getHistory());

    }


    /**
     * Test the program with data the students haven't seen Gathers the output
     * from StdOut and compares it to the expect output (stored in
     * InfluencerOutput_2.txt)
     */
    @Hint("The main method is not working properly with the input file from the instructions")
    public void testMainFuzzyEqualsSameInput()
        throws java.io.IOException
    {

        ProjectRunner.main(new String[] { "SampleInput2_2023.csv" });

        fileName = "SampleOutput2_2023.txt";

        String InfluencerOutput_2 = "";
        fileData = null;
        try
        {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }

        while (fileData.hasNextLine())
        {
            InfluencerOutput_2 += fileData.nextLine() + "\n";
        }

        assertFuzzyEquals(
            "Output not as expected for other input files " + ".csv",
            InfluencerOutput_2,
            systemOut().getHistory());

    }
    
    /**
     * Test the program with data the students haven't seen Gathers the output
     * from StdOut and compares it to the expect output (stored in
     * InfluencerOutput_2.txt)
     */
    @Hint("The main method is not working properly with the input file from the instructions")
    public void testMainFuzzyEqualsSameInputLast()
        throws java.io.IOException
    {

        ProjectRunner.main(new String[] { "SampleInput3_2023.csv" });

        fileName = "SampleOutput3_2023.txt";

        String InfluencerOutput_2 = "";
        fileData = null;
        try
        {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }

        while (fileData.hasNextLine())
        {
            InfluencerOutput_2 += fileData.nextLine() + "\n";
        }

        assertFuzzyEquals(
            "Output not as expected for other input files " + ".csv",
            InfluencerOutput_2,
            systemOut().getHistory());

    }



    
    
    /**
     * Test the program with the data provided for students to use
     */
    @Hint("The main method is not working properly with input file "
        + "SampleInput1_2022.csv")
    public void testMain01()
        throws java.io.IOException
    {

        ProjectRunner.main(new String[] { "SampleInput1_2023.csv" });

        fileName = "SampleOutput1_2023.txt";

        String expectedOutput = "";
        fileData = null;
        try
        {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }

        while (fileData.hasNextLine())
        {
            expectedOutput += fileData.nextLine() + "\n";
        }

        String testOutput = systemOut().getHistory();
        String[] outputDividedOnNewline = testOutput.split("\n");
        String[] expectedDividedOnNewline = expectedOutput.split("\n");
        if (outputDividedOnNewline.length != expectedDividedOnNewline.length)
        {
            fail(
                "Output was the wrong number of lines!  Expected: "
                    + expectedDividedOnNewline.length + " but got "
                    + outputDividedOnNewline.length);
        }
        // to mark when we shift from traditional calculation to reach
        boolean isTraditional = true;
        for (int i = 0; i < expectedDividedOnNewline.length; i++)
        {
            String expected = expectedDividedOnNewline[i];
            String testResult = outputDividedOnNewline[i];
            // to indicate we're on to the reach calculations
            if (expected.equals("**********"))
            {
                isTraditional = false;
            }
            if (!(expected.equals(testResult)))
            {
                // clear explination if there's a mismatch on dividing lines
                if (expected.equals("=========="))
                {
                    fail(
                        "Malformed line to divide channels.  Should be: ========== (10 '=' signs)");
                }
                else if (expected.equals("**********"))
                {
                    fail(
                        "Malformed line to divide calculations.  Should be ********** (10 '*' signs)\"))");

                    // I want to offer different feedback about math errors vs
                    // malformed strings
                }
                else if (expected.contains(":"))
                {
                    String[] expectedSplit = expected.split(":", 2);
                    String[] testResultSplit = testResult.split(":", 2);
                    if (!expectedSplit[0].equals(testResultSplit[0]))
                    {
                        fail(
                            "Incorrect label!  Expected the word: "
                                + expectedSplit[0] + " but got "
                                + testResultSplit[0]
                                + "make sure there's also a ': ' between label and number");
                    }
                    if (!expectedSplit[1].equals(testResultSplit[1]))
                    {
                        System.out.println(
                            "Expected: " + expectedSplit[1] + " but got "
                                + testResultSplit[1] + " on " + expectedSplit[0]) ;
                        fail(
                            "Math error for a calculation on "
                                + expectedSplit[0]);

                    }

                }// end of ifs about a line with a colon
                if (isTraditional == true)
                {
                    fail(
                        "expected " + expected + " but got " + testResult
                            + " in traditional reporting");
                }
                else
                {
                    fail(
                        "expected " + expected + " but got " + testResult
                            + " in reach reporting");
                }

            }

        }

    }


    @Hint("The main method is not working properly with a the input file provided in instructions")
    public void testMain02()
        throws java.io.IOException
    {

        ProjectRunner.main(new String[] { "SampleInput1_2023.csv" });

        fileName = "SampleOutput1_2023.txt";

        String expectedOutput = "";
        fileData = null;
        try
        {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());
        }

        while (fileData.hasNextLine())
        {
            expectedOutput += fileData.nextLine() + "\n";
        }

        String testOutput = systemOut().getHistory();
        String[] outputDividedOnNewline = testOutput.split("\n");
        String[] expectedDividedOnNewline = expectedOutput.split("\n");
        if (outputDividedOnNewline.length != expectedDividedOnNewline.length)
        {
            fail(
                "Output was the wrong number of lines!  Expected: "
                    + expectedDividedOnNewline.length + " but got "
                    + outputDividedOnNewline.length);
        }
        // to mark when we shift from traditional calculation to reach
        boolean isTraditional = true;
        for (int i = 0; i < expectedDividedOnNewline.length; i++)
        {
            String expected = expectedDividedOnNewline[i];
            String testResult = outputDividedOnNewline[i];
            // to indicate we're on to the reach calculations
            if (expected.equals("**********"))
            {
                isTraditional = false;
            }
            if (!(expected.equals(testResult)))
            {
                // clear explination if there's a mismatch on dividing lines
                if (expected.equals("=========="))
                {
                    fail(
                        "Malformed line to divide channels.  Should be: ========== (10 '=' signs)");
                }
                else if (expected.equals("**********"))
                {
                    fail(
                        "Malformed line to divide calculations.  Should be ********** (10 '*' signs)\"))");

                    // I want to offer different feedback about math errors vs
                    // malformed strings
                }
                else if (expected.contains(":"))
                {
                    String[] expectedSplit = expected.split(":", 2);
                    String[] testResultSplit = testResult.split(":", 2);
                    if (!expectedSplit[0].equals(testResultSplit[0]))
                    {
                        fail(
                            "Incorrect label!  Expected the word: "
                                + expectedSplit[0] + " but got "
                                + testResultSplit[0]
                                + "make sure there's also a ': ' between label and number");
                    }
                    if (!expectedSplit[1].equals(testResultSplit[1]))
                    {
                        System.out.println(
                            "Expected: " + expectedSplit[1] + " but got "
                                + testResultSplit[1]);
                        fail(
                            "Math error for a calculation on "
                                + expectedSplit[0]
                                + " for this submission make sure you're only recording data for January - April");

                    }

                } // end of ifs about a line with a colon
                if (isTraditional == true)
                {
                    fail(
                        "expected " + expected + " but got " + testResult
                            + " in traditional reporting");
                }
                else
                {
                    fail(
                        "expected " + expected + " but got " + testResult
                            + " in reach reporting");
                }

            }

        }

    }

}
