package prj5;
import java.util.Comparator;
public class InfluencerReachComparator
    implements Comparator<Influencer>
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................

    public int compare(Influencer a, Influencer b) {
        double ra = a.getReachRate();
        double rb = b.getReachRate();

        if (ra < 0 && rb < 0) return 0;
        if (ra < 0) return 1;
        if (rb < 0) return -1;

        return Double.compare(rb, ra);
    }
}
