package prj5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;


// -------------------------------------------------------------------------
/**
 *  this is a comparator to compare traditional data
 * 
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025/05/03
 */
public class InfluencerTraditionalComparatorTest {

    private Comparator<Influencer> comparator;
    private Influencer highInf;
    private Influencer lowInf;
    private Influencer invalidInf1;
    private Influencer invalidInf2;
    /**
     * this is a setup
     */
    @Before
    public void setUp() {
        comparator = new InfluencerTraditionalComparator();


        highInf = new Influencer("u1", "High", "C", "T");
        highInf.addMonthData("January", 0, 0, 0, 0);
        highInf.addMonthData("February", 0, 0, 0, 0);
        highInf.addMonthData("March", 40, 10, 100, 0);
        highInf.computeEngagementRates();

        lowInf = new Influencer("u2", "Low", "C", "T");
        lowInf.addMonthData("January", 0, 0, 0, 0);
        lowInf.addMonthData("February", 0, 0, 0, 0);
        lowInf.addMonthData("March", 10, 5, 100, 0);
        lowInf.computeEngagementRates();


        invalidInf1 = new Influencer("u3", "Inv1", "C", "T");
        invalidInf1.addMonthData("January", 0, 0, 0, 0);
        invalidInf1.addMonthData("February", 0, 0, 0, 0);
        invalidInf1.addMonthData("March", 5, 5, 0, 0);
        invalidInf1.computeEngagementRates();


        invalidInf2 = new Influencer("u4", "Inv2", "C", "T");
        invalidInf2.addMonthData("March", 8, 2, 0, 0);
        invalidInf2.computeEngagementRates();
    }
    /**
     * this is a test
     */
    @Test
    public void testDescendingOrderValid() {

        assertTrue("High rate should rank before low rate",
                   comparator.compare(highInf, lowInf) < 0);
        assertTrue("Low rate should rank after high rate",
                   comparator.compare(lowInf, highInf) > 0);
    }
    /**
     * this is a test
     */
    @Test
    public void testEqualRates() {
        Influencer eq1 = new Influencer("u5", "E1", "C", "T");
        eq1.addMonthData("March", 20, 10, 100, 0);
        eq1.computeEngagementRates();

        Influencer eq2 = new Influencer("u6", "E2", "C", "T");
        eq2.addMonthData("March", 15, 15, 100, 0);
        eq2.computeEngagementRates();

        assertEquals("Equal rates should compare to 0",
                     0, comparator.compare(eq1, eq2));
    }
    /**
     * this is a test
     */
    @Test
    public void testInvalidAfterValid() {

        assertTrue("Valid should rank before invalid",
                   comparator.compare(highInf, invalidInf1) < 0);
        assertTrue("Invalid should rank after valid",
                   comparator.compare(invalidInf1, lowInf) > 0);
    }
    /**
     * this is a test
     */
    @Test
    public void testBothInvalidEqual() {
        assertEquals("Two invalid influencers should compare equal",
                     0, comparator.compare(invalidInf1, invalidInf2));
    }
}