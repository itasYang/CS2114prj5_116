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
public class ProjectRunner {

    // ----------------------------------------------------------
    /**
     * This is the main method for this project.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String filename = (args.length > 0 && !args[0].equalsIgnoreCase("gui"))
                          ? args[0]
                          : "SampleInput1_2023.csv";


        InputFileReader reader = new InputFileReader(filename);
        LinkedList<Influencer> influencers = reader.getInfluencers();


        boolean showConsole = true;
        boolean showGUI     = true;


        if (showConsole) {
            DecimalFormat df = new DecimalFormat("#.#");


            influencers.insertionSort(new InfluencerChannelNameComparator());
            for (Influencer inf : influencers) {
                System.out.println(inf.getChannelName());
                double tr = inf.getTraditionalRate();
                String out = (tr < 0) ? "N/A" : df.format(tr);
                System.out.println("traditional: " + out);
                System.out.println("==========");
            }


            System.out.println("**********");
            System.out.println("**********");


            influencers.insertionSort(new InfluencerReachComparator());
            for (Influencer inf : influencers) {
                System.out.println(inf.getChannelName());
                double rr = inf.getReachRate();
                String out = (rr < 0) ? "N/A" : df.format(rr);
                System.out.println("reach: " + out);
                System.out.println("==========");
            }
        }


        if (showGUI) {
            new ChannelViewerWindow(influencers);
        }
    }
}
