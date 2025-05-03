package prj5;

import java.lang.reflect.Field;
import java.util.Iterator;


/**
 * Basic GUI‑logic tests for {@link ChannelViewerWindow}.
 *
 * We do <b>not</b> verify visual output; instead we focus on the data side:
 * <ul>
 *   <li>initial alphabetic sort</li>
 *   <li>switching to engagement‑rate sort (traditional)</li>
 *   <li>switching metric to reach‑rate and re‑sorting</li>
 * </ul>
 * The CS2 <code>Window</code> draws from the same <code>Influencer</code> list the
 * whole program manipulates, so checking the <em>order</em> of that list after
 * each interaction is a reliable proxy for what the chart would show.
 */
public class ChannelViewerWindowTest extends student.TestCase {

    /** Helper to seed private rate fields via reflection. */
    private Influencer make(String channel, double tradRate, double reachRate) {
        try {
            Influencer inf = new Influencer("usr", channel, "US", "misc");
            Field tr = Influencer.class.getDeclaredField("traditionalRate");
            Field rr = Influencer.class.getDeclaredField("reachRate");
            tr.setAccessible(true);
            rr.setAccessible(true);
            tr.setDouble(inf, tradRate);
            rr.setDouble(inf, reachRate);
            return inf;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Extracts channel names from the linked list in order. */
    private String[] names(LinkedList<Influencer> list) {
        String[] out = new String[list.size()];
        int i = 0;
        for (Iterator<Influencer> it = list.iterator(); it.hasNext(); ) {
            out[i++] = it.next().getChannelName();
        }
        return out;
    }

    public void testSortingWorkFlow() throws Exception {
        LinkedList<Influencer> data = new LinkedList<>();
        
        data.add(make("gamma", 5.0, 3.0));
        data.add(make("alpha", 8.0, 2.0));
        data.add(make("beta",  4.0, 9.0));

        ChannelViewerWindow win = new ChannelViewerWindow(data);

       
        assertEquals("Initial sort by name",
            new String[]{"alpha", "beta", "gamma"}, names(data));

       
        win.sortByEngagementRateClicked(null); 
        assertEquals("Traditional rate descending",
            new String[]{"alpha", "gamma", "beta"}, names(data));
      
        win.showReachRate(null);
       
        assertEquals("Reach rate descending after toggle",
            new String[]{"beta", "gamma", "alpha"}, names(data));
    }
}
