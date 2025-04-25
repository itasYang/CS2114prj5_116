package prj5;

// -------------------------------------------------------------------------
/**
 * This is a test class for reach comparator
 *
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025年4月24日
 */
public class InfluencerReachComparatorTest extends student.TestCase {
    private InfluencerReachComparator comparator;
    private Influencer influencerA;
    private Influencer influencerB;

    // ----------------------------------------------------------
    /**
     * set up method
     */
    public void setUp() {
        comparator = new InfluencerReachComparator();

        influencerA = new Influencer("userA", "channelA", "USA", "Tech");
        influencerA.addMonthData("January", 100, 50, 1000, 3000);
        influencerA.addMonthData("February", 50, 50, 1000, 3000);
        influencerA.addMonthData("March", 25, 25, 1000, 4000);
        influencerA.computeEngagementRates();

        influencerB = new Influencer("userB", "channelB", "USA", "Gaming");
        influencerB.addMonthData("January", 50, 0, 500, 1500);
        influencerB.addMonthData("February", 50, 0, 500, 1500);
        influencerB.addMonthData("March", 50, 0, 500, 2000);
        influencerB.computeEngagementRates();
    }


    // ----------------------------------------------------------
    /**
     * this is a test method
     */
    public void testCompareEqualReachRates() {
        assertEquals(0, comparator.compare(influencerA, influencerB));
    }


    // ----------------------------------------------------------
    /**
     * this is a test method
     */
    public void testCompareDifferentReachRates() {
        assertEquals(0, comparator.compare(influencerA, influencerB));
        influencerB = new Influencer("userB", "channelB", "USA", "Gaming");
        influencerB.addMonthData("January", 100, 100, 1000, 1000);
        influencerB.addMonthData("February", 0, 0, 1000, 0);
        influencerB.addMonthData("March", 0, 0, 1000, 0);
        influencerB.computeEngagementRates();

    }


    // ----------------------------------------------------------
    /**
     * this is a test method
     */
    public void testCompareWithInvalidRates() {

        influencerB = new Influencer("userB", "channelB", "USA", "Gaming");
        influencerB.computeEngagementRates();

        assertEquals(-1.0, influencerB.getReachRate(), 0.1);
        assertEquals(-1, comparator.compare(influencerA, influencerB));
        assertEquals(1, comparator.compare(influencerB, influencerA));
    }


    // ----------------------------------------------------------
    /**
     * this is a test method
     */
    public void testCompareBothInvalid() {
        influencerA = new Influencer("userA", "channelA", "USA", "Tech");
        influencerB = new Influencer("userB", "channelB", "USA", "Gaming");
        influencerA.computeEngagementRates();
        influencerB.computeEngagementRates();

        assertEquals(0, comparator.compare(influencerA, influencerB));
    }
}
