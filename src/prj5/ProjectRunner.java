package prj5;

import java.io.IOException;

public class ProjectRunner {
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    public static void main(String[] args) throws IOException {
        InputFileReader filer;

        // Step 1: Read input file from args or default
        if (args.length > 0) {
            filer = new InputFileReader(args[0]);
        } else {
            filer = new InputFileReader("SampleInput1_2023.csv");1
        }

        // Step 2: Show Console or GUI (set these manually for now)
        boolean showConsole = true;
        boolean showGUI = false;

        if (showConsole) {
            // TODO: For intermediate submission - print data to console
            filer.printData();  // Assuming you have a method like this in InputFileReader
        }

        if (showGUI) {
            // TODO: For final submission - launch GUI
            // GUI code will be added later1
        }
    }
}
