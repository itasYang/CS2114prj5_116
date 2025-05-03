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

        boolean aInvalid = rateA < 0;
        boolean bInvalid = rateB < 0;


        if (aInvalid && bInvalid) {
            return 0;
        }

        if (aInvalid) {
            return 1;
        }

        if (bInvalid) {
            return -1;
        }

        return Double.compare(rateB, rateA);
    }
}
