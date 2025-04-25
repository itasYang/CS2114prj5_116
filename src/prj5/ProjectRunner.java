package prj5;

import java.io.IOException;
import java.text.DecimalFormat;

// -------------------------------------------------------------------------
/**
 * This is the project runner for prj5.
 * 
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025/04/24
 */
public class ProjectRunner
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * This is the main method for this project.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException
    {
        InputFileReader reader = (args.length > 0)
            ? new InputFileReader(args[0])
            : new InputFileReader("SampleInput1_2023.csv");

        LinkedList<Influencer> list = reader.getInfluencers();

        boolean showConsole = true;
        boolean showGUI = false;

        if (showConsole)
        {
            DecimalFormat df = new DecimalFormat("#.#");

            list.insertionSort(new InfluencerChannelNameComparator());
            for (Influencer inf : list)
            {
                System.out.println(inf.getChannelName());
                double tr = inf.getTraditionalRate();
                String out = (tr < 0) ? "N/A" : df.format(tr);
                System.out.println("traditional: " + out);
                System.out.println("==========");
            }

            System.out.println("**********");
            System.out.println("**********");

            list.insertionSort(new InfluencerReachComparator());
            for (Influencer inf : list)
            {
                System.out.println(inf.getChannelName());
                double rr = inf.getReachRate();
                String out = (rr < 0) ? "N/A" : df.format(rr);
                System.out.println("reach: " + out);
                System.out.println("==========");
            }
        }

    }
}
