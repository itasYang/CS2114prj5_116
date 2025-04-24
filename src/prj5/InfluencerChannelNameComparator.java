package prj5;

import java.util.Comparator;

public class InfluencerChannelNameComparator
    implements Comparator<Influencer>
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    @Override
    public int compare(Influencer a, Influencer b) {
        return a.getChannelName().compareToIgnoreCase(b.getChannelName());
    }
}
