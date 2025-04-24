package prj5;
public class Influencer
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    private String username;
    private String channelName;
    private String country;
    private String mainTopic;

    private int totalLikesQuarter = 0;
    private int totalCommentsQuarter = 0;
    private int totalViewsQuarter = 0;
    private int followersMarch = 0;

    private double traditionalRate;
    private double reachRate;

    public Influencer(String username, String channelName, String country, String mainTopic) {
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
    }


    public void addMonthData(String month, int likes, int comments, int followers, int views) {
        if (!isValidMonth(month)) {
            return;
        }
        totalLikesQuarter += likes;
        totalCommentsQuarter += comments;
        totalViewsQuarter += views;
        if ("March".equalsIgnoreCase(month)) {
            followersMarch = followers;
        }
    }

    private boolean isValidMonth(String month) {
        return "January".equalsIgnoreCase(month)
            || "February".equalsIgnoreCase(month)
            || "March".equalsIgnoreCase(month);
    }


    public void computeEngagementRates() {

        if (followersMarch > 0) {
            traditionalRate = ( (double)(totalLikesQuarter + totalCommentsQuarter)
                                / followersMarch ) * 100.0;
        } else {
            traditionalRate = -1.0;  // N/A
        }

        if (totalViewsQuarter > 0) {
            reachRate = ( (double)(totalLikesQuarter + totalCommentsQuarter)
                          / totalViewsQuarter ) * 100.0;
        } else {
            reachRate = -1.0;  // N/A
        }
    }

    public String getChannelName() {
        return channelName;
    }
    public double getTraditionalRate() {
        return traditionalRate;
    }
    public double getReachRate() {
        return reachRate;
    }

}
