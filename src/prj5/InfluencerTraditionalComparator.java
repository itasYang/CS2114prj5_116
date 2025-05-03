package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 *  Comparison with data calculated by traditional methods
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.5.3
 */
public class InfluencerTraditionalComparator implements Comparator<Influencer> {
    @Override
    public int compare(Influencer a, Influencer b) {
        double rateA = a.getTraditionalRate();
        double rateB = b.getTraditionalRate();


        if (Double.isNaN(rateA)) {
            return Double.isNaN(rateB) ? 0 : 1;
        }
        if (Double.isNaN(rateB)) {
            return -1;
        }

        return Double.compare(rateB, rateA);
    }
}