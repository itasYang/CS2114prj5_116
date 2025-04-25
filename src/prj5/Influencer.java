package prj5;

// -------------------------------------------------------------------------
/**
 * Influencer class that handle data of influencer
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class Influencer
{

    private String channelName;

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
        String mainTopic)
    {
        this.channelName = channelName;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
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
        int views)
    {
        if (!isValidMonth(month))
        {
            return;
        }
        totalLikesQuarter += likes;
        totalCommentsQuarter += comments;
        totalViewsQuarter += views;
        if ("March".equalsIgnoreCase(month))
        {
            followersMarch = followers;
        }
    }


    private boolean isValidMonth(String month)
    {
        return "January".equalsIgnoreCase(month)
            || "February".equalsIgnoreCase(month)
            || "March".equalsIgnoreCase(month);
    }


    // ----------------------------------------------------------
    /**
     * This is a method used to comput engagement rate.
     */
    public void computeEngagementRates()
    {

        if (followersMarch > 0)
        {
            traditionalRate =
                ((double)(totalLikesQuarter + totalCommentsQuarter)
                    / followersMarch) * 100.0;
        }
        else
        {
            traditionalRate = -1.0;
        }

        if (totalViewsQuarter > 0)
        {
            reachRate = ((double)(totalLikesQuarter + totalCommentsQuarter)
                / totalViewsQuarter) * 100.0;
        }
        else
        {
            reachRate = -1.0;
        }
    }


    // ----------------------------------------------------------
    /**
     * This is a method used to get channel name.
     * 
     * @return String channelName
     */
    public String getChannelName()
    {
        return channelName;
    }


    // ----------------------------------------------------------
    /**
     * this is a method used to get traditional rate.
     * 
     * @return traditionalRate traditionalRate.
     */
    public double getTraditionalRate()
    {
        return traditionalRate;
    }


    // ----------------------------------------------------------
    /**
     * This is a method used to get ReachRate.
     * 
     * @return reachRate reachRate
     */
    public double getReachRate()
    {
        return reachRate;
    }

}
