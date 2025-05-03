package prj5;

import java.lang.reflect.Field;


// -------------------------------------------------------------------------
/**
 *  this is a influencer traditional comparator test
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class InfluencerTraditionalComparatorTest extends student.TestCase {

    private InfluencerTraditionalComparator comp;

    /**
     * this is a setup
     */
    public void setUp() {
        comp = new InfluencerTraditionalComparator();
    }

    /**
     * this is a test
     */
    private Influencer inf(String channel, double traditionalRate) {
        try {
            Influencer i = new Influencer("user", channel, "US", "topic");
            Field f = Influencer.class.getDeclaredField("traditionalRate");
            f.setAccessible(true);
            f.setDouble(i, traditionalRate);
            return i;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create test influencer", e);
        }
    }

    /**
     * this is a test
     */
    public void testDescending() {
        Influencer high = inf("high", 8.4);
        Influencer low  = inf("low", 3.1);

        assertTrue("high rate should come first", comp.compare(high, low) < 0);
        assertTrue("low rate should come after", comp.compare(low, high) > 0);
    }

    /**
     * First influencer has NaN rate, second valid.
     */
    public void testFirstNaN() {
        Influencer nan  = inf("nan", Double.NaN);
        Influencer good = inf("good", 5.0);

        assertEquals("NaN should be considered larger (sent to end)", 1,
            comp.compare(nan, good));
    }

    /**
     * Second influencer has NaN rate, first valid.
     */
    public void testSecondNaN() {
        Influencer good = inf("good", 6.0);
        Influencer nan  = inf("nan", Double.NaN);

        assertEquals("Valid should come before NaN", -1,
            comp.compare(good, nan));
    }

    /**
     * Both influencers have NaN; expect equality.
     */
    public void testBothNaN() {
        Influencer a = inf("a", Double.NaN);
        Influencer b = inf("b", Double.NaN);

        assertEquals("both NaN should be equal", 0, comp.compare(a, b));
    }
}