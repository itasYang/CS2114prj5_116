package prj5;

// -------------------------------------------------------------------------
/**
 * Influencer class that handle data of influencer
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class Influencer {

    private String channelName;

    private int totalLikesQuarter = 0;
    private int totalCommentsQuarter = 0;
    private int totalViewsQuarter = 0;
    private int followersMarch = 0;

    private double traditionalRate;
    private double reachRate;


    private int likesJan = 0;
    private int commentsJan = 0;
    private int viewsJan = 0;
    private int followersJan = 0;

    private int likesFeb = 0;
    private int commentsFeb = 0;
    private int viewsFeb = 0;
    private int followersFeb = 0;

    private int likesMar = 0;
    private int commentsMar = 0;
    private int viewsMar = 0;


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
        this.channelName = channelName;
    }

    // ----------------------------------------------------------
    /**
     * Adds monthly engagement data
     * to the influencer's quarterly totals if the provided month is valid.
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

        if ("January".equalsIgnoreCase(month)) {
            likesJan = likes;
            commentsJan = comments;
            viewsJan = views;
            followersJan = followers;
        }
        else if ("February".equalsIgnoreCase(month)) {
            likesFeb = likes;
            commentsFeb = comments;
            viewsFeb = views;
            followersFeb = followers;
        }
        else if ("March".equalsIgnoreCase(month)) {
            likesMar = likes;
            commentsMar = comments;
            viewsMar = views;

        }
    }

    // ----------------------------------------------------------
    /**
     * helper method to test if is the first quater valid month
     */
    private boolean isValidMonth(String month) {
        return "January".equalsIgnoreCase(month)
            || "February".equalsIgnoreCase(month)
            || "March".equalsIgnoreCase(month);
    }

    // ----------------------------------------------------------
    /**
     * This is a method used to compute engagement rate.
     */
    public void computeEngagementRates() {

        if (followersMarch > 0) {
            traditionalRate = ((double)(totalLikesQuarter
                + totalCommentsQuarter) / followersMarch) * 100.0;
        }
        else {
            traditionalRate = -1.0;
        }

        if (totalViewsQuarter > 0) {
            reachRate = ((double)(totalLikesQuarter + totalCommentsQuarter)
                / totalViewsQuarter) * 100.0;
        }
        else {
            reachRate = -1.0;
        }
    }

    // ----------------------------------------------------------
    /**
     * This is a method used to get channel name.
     * 
     * @return String channelName
     */
    public String getChannelName() {
        return channelName;
    }

    // ----------------------------------------------------------
    /**
     * this is a method used to get traditional rate.
     * 
     * @return traditionalRate traditionalRate.
     */
    public double getTraditionalRate() {
        return traditionalRate;
    }

    // ----------------------------------------------------------
    /**
     * This is a method used to get ReachRate.
     * 
     * @return reachRate reachRate
     */
    public double getReachRate() {
        return reachRate;
    }


    public double getTraditionalRateForMonth(String month) {
        int likes = 0, comments = 0, followers = 0;
        switch (month.toLowerCase()) {
            case "january":
                likes     = likesJan;
                comments  = commentsJan;
                followers = followersJan;
                break;
            case "february":
                likes     = likesFeb;
                comments  = commentsFeb;
                followers = followersFeb;
                break;
            case "march":
                likes     = likesMar;
                comments  = commentsMar;
                followers = followersMarch;
                break;
            case "quarter":
                likes     = likesJan + likesFeb + likesMar;
                comments  = commentsJan + commentsFeb + commentsMar;
                followers = followersMarch;
                break;
            default:
                return -1.0;
        }
        if (followers <= 0) {
            return -1.0;
        }
        return (likes + comments) / (double)followers * 100.0;
    }


    public double getReachRateForMonth(String month) {
        int likes = 0, comments = 0, views = 0;
        switch (month.toLowerCase()) {
            case "january":
                likes    = likesJan;
                comments = commentsJan;
                views    = viewsJan;
                break;
            case "february":
                likes    = likesFeb;
                comments = commentsFeb;
                views    = viewsFeb;
                break;
            case "march":
                likes    = likesMar;
                comments = commentsMar;
                views    = viewsMar;
                break;
            case "quarter":
                likes    = likesJan + likesFeb + likesMar;
                comments = commentsJan + commentsFeb + commentsMar;
                views    = viewsJan + viewsFeb + viewsMar;
                break;
            default:
                return -1.0;
        }
        if (views <= 0) {
            return -1.0;
        }
        return (likes + comments) / (double)views * 100.0;
    }

}
