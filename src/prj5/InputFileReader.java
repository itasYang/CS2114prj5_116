package prj5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * The InputFileReader class is responsible for reading influencer data from a
 * CSV input file and storing it in a list of Influencer objects
 * 
 * @author Ethan Yang, Yinhan Wang, Boyuan Zhao, Chenghan Yang
 * @version 2025/05/03
 */
public class InputFileReader

{
    private LinkedList<Influencer> influencers = new LinkedList<>();

    // ----------------------------------------------------------
    /**
     * Create a new InputFileReader object.
     * 
     * @param filename
     *            file name of input
     * @throws IOException
     *             Io exception
     * 
     */
    public InputFileReader(String filename) throws IOException {
        Scanner in = new Scanner(Paths.get(filename));
        if (in.hasNextLine())
            in.nextLine();

        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty())
                continue;
            String[] v = line.split(",");
            String month = v[0];
            String username = v[1];
            String channel = v[2];
            String country = v[3];
            String topic = v[4];
            int likes = toInt(v[5]);
            @SuppressWarnings("unused")
            int posts = toInt(v[6]);
            int followers = toInt(v[7]);
            int comments = toInt(v[8]);
            int views = toInt(v[9]);

            Influencer inf = findOrCreate(username, channel, country, topic);
            inf.addMonthData(month, likes, comments, followers, views);
        }
        in.close();

        for (Influencer inf : influencers) {
            inf.computeEngagementRates();
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieves an existing Influencer object with the specified channel name
     * from the list of influencers, or creates a new one if no match is found.
     */
    private Influencer findOrCreate(
        String user,
        String ch,
        String ctry,
        String top) {
        for (Influencer inf : influencers) {
            if (inf.getChannelName().equalsIgnoreCase(ch)) {
                return inf;
            }
        }
        Influencer ni = new Influencer(user, ch, ctry, top);
        influencers.add(ni);
        return ni;
    }


    // ----------------------------------------------------------
    /**
     * change string to int
     * 
     * @return int
     *         0 if not able to parseint, otherwise return the integer
     */
    private int toInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        }
        catch (Exception e) {
            return 0;
        }
    }


    // ----------------------------------------------------------
    /**
     * return influencers
     * 
     * @return inlfuencers
     *         return influencers
     */
    public LinkedList<Influencer> getInfluencers() {
        return influencers;
    }
}
