package prj5;

// -------------------------------------------------------------------------
/**
 * Influencer class that handle data of influencer
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class Influencer {

    @SuppressWarnings("unused")
    private String username;
    private String channelName;
    @SuppressWarnings("unused")
    private String country;
    @SuppressWarnings("unused")
    private String mainTopic;

    private int totalLikesQuarter = 0;
    private int totalCommentsQuarter = 0;
    private int totalViewsQuarter = 0;
    private int followersMarch = 0;

    private double traditionalRate;
    private double reachRate;

    // ----------------------------------------------------------
    /**
     * Create a new Influencer object.
     * 
     * @param username
     *            username of influencer
     * @param channelName
     *            channel name of influencer
     * @param country
     *            country
     * @param mainTopic
     *            main topic of influencer
     */
    public Influencer(
        String username,
        String channelName,
        String country,
        String mainTopic) {
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
    }


    // ----------------------------------------------------------
    /**
     * add month data
     * 
     * @param month
     *            which Month
     * @param likes
     *            number of likes
     * @param comments
     *            number of comments
     * @param followers
     *            number of followers
     * @param views
     *            number of views
     */
    public void addMonthData(
        String month,
        int likes,
        int comments,
        int followers,
        int views) {
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
        return "January".equalsIgnoreCase(month) || "February".equalsIgnoreCase(
            month) || "March".equalsIgnoreCase(month);
    }


    // ----------------------------------------------------------
    /**
     * compute engagement
     */
    public void computeEngagementRates() {

        if (followersMarch > 0) {
            traditionalRate = ((double)(totalLikesQuarter
                + totalCommentsQuarter) / followersMarch) * 100.0;
        }
        else {
            traditionalRate = -1.0; // N/A
        }

        if (totalViewsQuarter > 0) {
            reachRate = ((double)(totalLikesQuarter + totalCommentsQuarter)
                / totalViewsQuarter) * 100.0;
        }
        else {
            reachRate = -1.0; // N/A
        }
    }


    // ----------------------------------------------------------
    /**
     * get channel name
     * 
     * @return channelName
     *         name of channel
     */
    public String getChannelName() {
        return channelName;
    }


    // ----------------------------------------------------------
    /**
     * get traditional rate
     * 
     * @return traditionalRate
     *         traditional rate
     */
    public double getTraditionalRate() {
        return traditionalRate;
    }


    // ----------------------------------------------------------
    /**
     * get reach rate
     * 
     * @return reachRate
     *         the reach rate
     */
    public double getReachRate() {
        return reachRate;
    }

}
