package prj5;

// -------------------------------------------------------------------------
/**
 * test class for influencer
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class InfluencerTest extends student.TestCase {

    private Influencer influencer;

    // ----------------------------------------------------------
    /**
     * set up new influncer
     */
    public void setUp() {
        influencer = new Influencer("test_user", "TestChannel", "USA", "Tech");
    }


    // ----------------------------------------------------------
    /**
     * test constructor and getter
     */
    public void testConstructorAndGetters() {
        assertEquals("TestChannel", influencer.getChannelName());
    }


    // ----------------------------------------------------------
    /**
     * test valid month data
     */
    public void testAddMonthDataValid() {
        influencer.addMonthData("January", 100, 50, 0, 1000);
        influencer.addMonthData("February", 150, 75, 0, 1500);
        influencer.addMonthData("March", 200, 100, 1000, 2000);

        influencer.computeEngagementRates();

        assertEquals(67.5, influencer.getTraditionalRate(), 0.01);

        assertEquals(15.0, influencer.getReachRate(), 0.01);
    }


    // ----------------------------------------------------------
    /**
     * test invalid month data
     */
    public void testAddMonthDataInvalid() {
        influencer.addMonthData("April", 100, 50, 2000, 10000);
        influencer.computeEngagementRates();

        assertEquals(-1.0, influencer.getTraditionalRate(), 0.01);
        assertEquals(-1.0, influencer.getReachRate(), 0.01);
    }


    // ----------------------------------------------------------
    /**
     * test reach rate when followers is zero
     */
    public void testTraditionalRateFollowers() {
        influencer.addMonthData("January", 100, 50, 0, 1000);
        influencer.addMonthData("February", 150, 75, 0, 1500);
        influencer.addMonthData("March", 200, 100, 0, 2000);
        influencer.computeEngagementRates();

        assertEquals(-1.0, influencer.getTraditionalRate(), 0.01);

        assertEquals(15.0, influencer.getReachRate(), 0.01);
    }


    // ----------------------------------------------------------
    /**
     * test reach rate when view is zero
     */
    public void testReachRateViews() {
        influencer.addMonthData("January", 100, 50, 0, 0);
        influencer.addMonthData("February", 150, 75, 0, 0);
        influencer.addMonthData("March", 200, 100, 1000, 0);

        influencer.computeEngagementRates();

        assertEquals(67.5, influencer.getTraditionalRate(), 0.01);

        assertEquals(-1.0, influencer.getReachRate(), 0.01);
    }
}
