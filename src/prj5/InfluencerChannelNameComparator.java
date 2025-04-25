package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * This class is used to sort Influencer objects by channel name.
 * 
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025年4月24日
 */
public class InfluencerChannelNameComparator
    implements Comparator<Influencer>
{
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    @Override
    public int compare(Influencer a, Influencer b)
    {
        return a.getChannelName().compareToIgnoreCase(b.getChannelName());
    }
    
}
