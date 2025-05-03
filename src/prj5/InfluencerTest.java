package prj5;

import student.TestCase;

public class InfluencerTest extends TestCase {

    private Influencer influencer;

    public void setUp() {
        influencer = new Influencer("test_user", "TestChannel", "USA", "Tech");
    }

    public void testConstructorAndGetters() {
        assertEquals("TestChannel", influencer.getChannelName());
    }

    public void testAddMonthDataAndValidComputation() {
        influencer.addMonthData("January", 100, 50, 0, 1000);
        influencer.addMonthData("February", 150, 75, 0, 1500);
        influencer.addMonthData("March", 200, 100, 1000, 2000);

        influencer.computeEngagementRates();
 
        assertEquals(67.5, influencer.getTraditionalRate(), 0.01);


        assertEquals(15.0, influencer.getReachRate(), 0.01);
    }

    public void testAddMonthDataInvalidMonthIgnored() {
        influencer.addMonthData("April", 100, 50, 2000, 10000);
        influencer.computeEngagementRates();

        assertEquals(-1.0, influencer.getTraditionalRate(), 0.01);
        assertEquals(-1.0, influencer.getReachRate(), 0.01);
    }

    public void testTraditionalRateNAWhenFollowersZero() {
        influencer.addMonthData("January", 100, 50, 0, 1000);
        influencer.addMonthData("February", 150, 75, 0, 1500);
        influencer.addMonthData("March", 200, 100, 0, 2000);
        influencer.computeEngagementRates();


        assertEquals(-1.0, influencer.getTraditionalRate(), 0.01);

        assertEquals(15.0, influencer.getReachRate(), 0.01);
    }

    public void testReachRateNAWhenViewsZero() {
        influencer.addMonthData("January", 100, 50, 0, 0);
        influencer.addMonthData("February", 150, 75, 0, 0);
        influencer.addMonthData("March", 200, 100, 1000, 0);

        influencer.computeEngagementRates();


        assertEquals(67.5, influencer.getTraditionalRate(), 0.01);

        assertEquals(-1.0, influencer.getReachRate(), 0.01);
    }
}
