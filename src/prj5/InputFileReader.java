package prj5;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for reading input files and generating formatted output.
 */
public class InputFileReader {

    private static final String[] VALID_MONTHS = { "January", "February",
        "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December" };

    private static final DecimalFormat FORMATTER = new DecimalFormat("#.#");

    /**
     * Reads data from a CSV file, processes first-quarter influencer stats,
     * and writes output to a file.
     * 
     * @param inputFileName
     *            the name of the input file
     * @param outputFileName
     *            the name of the output file
     */
    public void processFile(String inputFileName, String outputFileName) {
        Scanner inStream = new Scanner(new File(inputFileName));
        inStream.nextLine(); // Skip header

        ArrayList<UserView> allData = new ArrayList<>();

        while (inStream.hasNextLine()) {
            String line = inStream.nextLine().replaceAll(" ", "");
            String[] values = line.split(",");

            if (!isValidMonth(values[0])) {
                continue;
            }

            String month = values[0];
            String username = values[1];
            String channel = values[2];
            String country = values[3];
            String mainTopic = values[4];
            int likes = toInt(values[5]);
            int posts = toInt(values[6]);
            int followers = toInt(values[7]);
            int comments = toInt(values[8]);
            int views = toInt(values[9]);

        }

        // Filter for Jan–Mar data only
        ArrayList<UserView> q1Data = new ArrayList<>();
        for (UserView data : allData) {
            if (data.getMonth().equals("January") || data.getMonth().equals(
                "February") || data.getMonth().equals("March")) {
                q1Data.add(data);
            }
        }

        // Summarize data by channel name
        HandlingTheData aggregator = new HandlingTheData();

        try (PrintWriter writer = new PrintWriter(new File(outputFileName))) {
            for (UserView summary : aggregator.getSortedByChannelName()) {
                writer.println(summary.getChannelName());
                writer.println("traditional: " + FORMATTER.format(summary
                    .getTraditionalEngagement()));
                writer.println("==========");
            }

            writer.println("**********");
            writer.println("**********");

            for (UserView summary : aggregator.getSortedByReachEngagement()) {
                writer.println(summary.getChannelName());
                writer.println("reach: " + FORMATTER.format(summary
                    .getReachEngagement()));
                writer.println("==========");
            }
        }
        catch (Exception e) {
            System.err.println("Error writing to output file: " + e
                .getMessage());
        }
    }


    /**
     * Checks if the provided month is valid.
     */
    private boolean isValidMonth(String month) {
        for (String valid : VALID_MONTHS) {
            if (valid.equalsIgnoreCase(month)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Converts a string to integer, returning 0 if invalid.
     */
    private int toInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch (Exception e) {
            return 0;
        }
    }
}
