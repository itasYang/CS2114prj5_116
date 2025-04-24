package prj5;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class InputFileReader

{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    private LinkedList<Influencer> influencers = new LinkedList<>();

    public InputFileReader(String filename) throws IOException {
        Scanner in = new Scanner(Paths.get(filename));
        if (in.hasNextLine()) in.nextLine();

        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] v = line.split(",");
            String month = v[0];
            String username = v[1];
            String channel = v[2];
            String country = v[3];
            String topic = v[4];
            int likes    = toInt(v[5]);
            int posts    = toInt(v[6]);
            int followers= toInt(v[7]);
            int comments = toInt(v[8]);
            int views    = toInt(v[9]);

            Influencer inf = findOrCreate(username, channel, country, topic);
            inf.addMonthData(month, likes, comments, followers, views);
        }
        in.close();


        for (Influencer inf : influencers) {
            inf.computeEngagementRates();
        }
    }

    private Influencer findOrCreate(String user, String ch, String ctry, String top) {
        for (Influencer inf : influencers) {
            if (inf.getChannelName().equalsIgnoreCase(ch)) {
                return inf;
            }
        }
        Influencer ni = new Influencer(user, ch, ctry, top);
        influencers.add(ni);
        return ni;
    }

    private int toInt(String s) {
        try { return Integer.parseInt(s.trim()); }
        catch (Exception e) { return 0; }
    }

    public LinkedList<Influencer> getInfluencers() {
        return influencers;
    }
}
