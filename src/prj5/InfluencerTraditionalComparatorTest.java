package prj5;

import java.lang.reflect.Field;
import student.TestCase;

/**
 * Unit tests for {@link InfluencerReachComparator}.
 *
 * The comparator should order influencers in descending order of
 * <em>reach engagement rate</em> and treat any negative rate as "invalid"
 * (i.e., those influencers are pushed to the bottom of a sort).
 */
public class InfluencerReachComparatorTest extends TestCase {

    private InfluencerReachComparator comp;

    @Override
    public void setUp() {
        comp = new InfluencerReachComparator();
    }

    /**
     * Helper that builds an Influencer with a given reach rate.  We use
     * reflection because reachRate is a private field without a setter and the
     * constructor only loads values from a CSV in the main program.
     */
    private Influencer makeInf(String channel, double reachRate) {
        try {
            Influencer inf = new Influencer("user", channel, "US", "tech");
            Field f = Influencer.class.getDeclaredField("reachRate");
            f.setAccessible(true);
            f.setDouble(inf, reachRate);
            return inf;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to build test influencer", e);
        }
    }

    /**
     * Both influencers have valid (non‑negative) reach rates.  The one with the
     * <b>higher</b> rate should come <b>before</b> the other (i.e., comparator
     * returns &lt; 0 when first &gt; second).
     */
    public void testDescendingOrder() {
        Influencer high = makeInf("high", 7.5);
        Influencer low  = makeInf("low", 4.2);

        int result = comp.compare(high, low);
        assertTrue("higher rate should sort first", result < 0);

        // Symmetry check (low vs high)
        result = comp.compare(low, high);
        assertTrue("lower rate should sort after", result > 0);
    }

    /**
     * If the first influencer has a negative reach rate and the second does
     * not, the comparator should return +1 (first sorts after second).
     */
    public void testFirstNegative() {
        Influencer invalid = makeInf("invalid", -1.0);
        Influencer valid   = makeInf("valid",   5.0);

        assertEquals("negative rate should be treated as greater (go to end)",
            1, comp.compare(invalid, valid));
    }

    /**
     * If the second influencer has a negative reach rate and the first does
     * not, the comparator should return -1 (first sorts before second).
     */
    public void testSecondNegative() {
        Influencer valid   = makeInf("valid",   5.0);
        Influencer invalid = makeInf("invalid", -2.0);

        assertEquals("valid rate should come before negative rate",
            -1, comp.compare(valid, invalid));
    }

    /**
     * When both reach rates are negative, the comparator should return 0 (they
     * are considered equal for ordering purposes).
     */
    public void testBothNegative() {
        Influencer a = makeInf("a", -3.0);
        Influencer b = makeInf("b", -0.5);

        assertEquals("both negative should be treated as equal", 0,
            comp.compare(a, b));
    }
}
