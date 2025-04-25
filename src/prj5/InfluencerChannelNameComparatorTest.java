package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This is a test class for the InfluencerChannelNameComparator class.
 * 
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025/04/24
 */
public class InfluencerChannelNameComparatorTest
    extends TestCase
{

    private InfluencerChannelNameComparator comparator;
    private Influencer influencer1;
    private Influencer influencer2;
    private Influencer influencer3;

    /**
     * This is the setUp method for this test class.
     */
    public void setUp()
    {
        comparator = new InfluencerChannelNameComparator();
        influencer1 = new Influencer("user1", "TechChannel", "USA", "Tech");
        influencer2 =
            new Influencer("user2", "LifestyleChannel", "USA", "Lifestyle");
        influencer3 = new Influencer("user3", "GamingChannel", "USA", "Gaming");
    }


    // ----------------------------------------------------------
    /**
     * this is a method used to test compareWithDifferentChannelNames method.
     */
    public void testCompareWithDifferentChannelNames()
    {

        int r31 = comparator.compare(influencer3, influencer1);
        int r32 = comparator.compare(influencer3, influencer2);
        int r21 = comparator.compare(influencer2, influencer1);

        assertTrue(r31 < 0);
        assertTrue(r32 < 0);
        assertTrue(r21 < 0);
    }


    // ----------------------------------------------------------
    /**
     * This is a test method used to test compareWithSameChannelNames
     */
    public void testCompareWithSameChannelNames()
    {

        Influencer influencer4 =
            new Influencer("user4", "TechChannel", "USA", "Tech");

        assertEquals(0, comparator.compare(influencer1, influencer4));
    }


    // ----------------------------------------------------------
    /**
     * This is a test method used to test comparewithdifferentcase method
     */
    public void testCompareWithDifferentCase()
    {
        Influencer upper =
            new Influencer("user1", "TechChannel", "USA", "Tech");
        Influencer lower =
            new Influencer("user2", "techchannel", "USA", "Tech");

        int result1 = comparator.compare(upper, lower);
        int result2 = comparator.compare(lower, upper);

        assertEquals(0, result1);
        assertEquals(0, result2);
    }

}
