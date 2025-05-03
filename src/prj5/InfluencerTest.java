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
    /**
     * this is a test
     */
    public void testTraditionalRatePerMonth() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();
        assertEquals(15.0, influencer.getTraditionalRateForMonth(
            "January"), 0.001);
        assertEquals((20 + 10) / 110.0 * 100, influencer.
            getTraditionalRateForMonth("February"), 0.001);
        assertEquals((30 + 15) / 120.0 * 100, influencer.
            getTraditionalRateForMonth("March"), 0.001);
    }

    /**
     * this is a test
     */
    public void testReachRatePerMonth() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();
        assertEquals((10 + 5) / 200.0 * 100, influencer.
            getReachRateForMonth("January"), 0.001);
        assertEquals((20 + 10) / 220.0 * 100, influencer.
            getReachRateForMonth("February"), 0.001);
        assertEquals((30 + 15) / 240.0 * 100, influencer.
            getReachRateForMonth("March"), 0.001);
    }
    /**
     * this is a test
     */
    public void testQuarterTraditionalRate() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();

        double expected = (10 + 5 + 20 + 10 + 30 + 15) / 120.0 * 100;
        assertEquals(expected, influencer.
            getTraditionalRateForMonth("Quarter"), 0.001);
    }

    /**
     * this is a test
     */
    public void testQuarterReachRate() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();

        double expected = (10 + 5 + 20 + 10 + 30 + 15) / (
            200 + 220 + 240.0) * 100;
        assertEquals(expected, influencer.
            getReachRateForMonth("Quarter"), 0.001);
    }

    /**
     * this is a test
     */
    public void testInvalidMonthReturnsMinusOne() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();
        assertEquals(-1.0, influencer.
            getTraditionalRateForMonth("April"), 0.001);
        assertEquals(-1.0, influencer.
            getReachRateForMonth("april"), 0.001);
    }

    /**
     * this is a test
     */
    public void testZeroFollowersOrViewsReturnsMinusOne() {
        influencer.addMonthData("January", 10, 5, 100, 200);
        influencer.addMonthData("February", 20, 10, 110, 220);
        influencer.addMonthData("March", 30, 15, 120, 240);
        influencer.computeEngagementRates();
        Influencer empty = new Influencer("u", "c", "country", "topic");
        empty.addMonthData("January", 5, 5, 0, 0);
        empty.computeEngagementRates();

        assertEquals(-1.0, empty.getTraditionalRateForMonth("January"), 0.001);
        assertEquals(-1.0, empty.getReachRateForMonth("January"), 0.001);
    }
}
