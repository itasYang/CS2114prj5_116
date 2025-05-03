package prj5;

import cs2.*;
import java.awt.Color;
import student.TestableRandom;

// -------------------------------------------------------------------------
/**
 *  this is a GUI to show the graph of data.
 * 
 * @author Yinhan Wang, Ethan Yang, Chenghan Yang, Boyuan Zhao
 * @version 2025.4.24
 */
public class GUIWindow {
    private Window window;
    private LinkedList<Influencer> influencers;

    private boolean sortByName;
    private boolean sortByEngagement;
    private boolean january;
    private boolean february;
    private boolean march;
    private boolean traditionalRate;

    // ----------------------------------------------------------
    /**
     * this is a constructor
     * @param influencers
     *      is data in influencers
     */
    public GUIWindow(LinkedList<Influencer> influencers) {

        this.influencers = influencers;


        sortByName        = true;
        sortByEngagement  = false;
        january           = false;
        february          = false;
        march             = false;
        traditionalRate   = true;


        window = new Window();
        window.setSize(800, 600);
        window.setTitle("Social Media Vis");

        Button b = new Button("Sort by Channel Name");
        b.onClick(this, "sortByChannelNameClicked");
        window.addButton(b, WindowSide.NORTH);

        b = new Button("Sort by Engagement Rate");
        b.onClick(this, "sortByEngagementRateClicked");
        window.addButton(b, WindowSide.NORTH);

        b = new Button("Quit");
        b.onClick(this, "quitButtonClicked");
        window.addButton(b, WindowSide.NORTH);

        b = new Button("Traditional Engagement Rate");
        b.onClick(this, "showTraditionalRate");
        window.addButton(b, WindowSide.WEST);

        b = new Button("Reach Engagement Rate");
        b.onClick(this, "showReachRate");
        window.addButton(b, WindowSide.WEST);

        b = new Button("January");
        b.onClick(this, "showJanuary");
        window.addButton(b, WindowSide.EAST);

        b = new Button("February");
        b.onClick(this, "showFebruary");
        window.addButton(b, WindowSide.EAST);

        b = new Button("March");
        b.onClick(this, "showMarch");
        window.addButton(b, WindowSide.EAST);

        b = new Button("First Quarter (Jan-Mar)");
        b.onClick(this, "showQuarter");
        window.addButton(b, WindowSide.EAST);


        displayChannels();
    }



    // ----------------------------------------------------------
    /**
     * is a button to quit
     * @param b
     *      is a button
     */
    public void quitButtonClicked(Button b) {
        System.exit(0);
    }
    /**
     * is a button to sort by channel name
     * @param b
     *      is a button
     */
    public void sortByChannelNameClicked(Button b) {
        sortByName       = true;
        sortByEngagement = false;
        displayChannels();
    }
    /**
     * is a button to sort by engagement rate
     * @param b
     *      is a button
     */
    public void sortByEngagementRateClicked(Button b) {
        sortByEngagement = true;
        sortByName       = false;
        displayChannels();
    }
    /**
     * is a button to show traditional rate
     * @param b
     *      is a button
     */
    public void showTraditionalRate(Button b) {
        traditionalRate = true;

        displayChannels();
    }
    /**
     * is a button to show reach rate
     * @param b
     *      is a button
     */
    public void showReachRate(Button b) {

        traditionalRate = false;
        displayChannels();
    }
    /**
     * is a button to show January
     * @param b
     *      is a button
     */
    public void showJanuary(Button b) {
        january      = true;
        february     = false;
        march        = false;

        displayChannels();
    }
    /**
     * is a button to show February
     * @param b
     *      is a button
     */
    public void showFebruary(Button b) {
        january      = false;
        february     = true;
        march        = false;

        displayChannels();
    }
    /**
     * is a button to show March
     * @param b
     *      is a button
     */
    public void showMarch(Button b) {
        january      = false;
        february     = false;
        march        = true;

        displayChannels();
    }
    /**
     * is a button to show quarter
     * @param b
     *      is a button
     */
    public void showQuarter(Button b) {
        january      = false;
        february     = false;
        march        = false;

        displayChannels();
    }



    private void displayChannels() {
        window.removeAllShapes();


        if (sortByName) {
            influencers.insertionSort(new InfluencerChannelNameComparator());
        }
        else if (sortByEngagement) {
            if (traditionalRate) {
                influencers.insertionSort(
                    new InfluencerTraditionalComparator());
            }
            else {
                influencers.insertionSort(
                    new InfluencerReachComparator());
            }
        }

        double maxVal = 0;
        for (Influencer inf : influencers) {
            double v = valueFor(inf);
            if (v > maxVal) {
                maxVal = v;
            }
        }

        int barW   = 80;
        int gap    = 30;
        int baseY  = 450;
        int chartH = 300;
        int startX = 50;

        int i = 0;
        for (Influencer inf : influencers) {
            double raw  = valueFor(inf);
            double disp = raw < 0 ? 0 : raw;
            int h  = maxVal > 0 ? (int)(disp / maxVal * chartH) : 0;
            int x  = startX + i * (barW + gap);
            int y  = baseY - h;


            Shape bar = new Shape(x, y, barW, h, painter());
            window.addShape(bar);

            String label = inf.getChannelName() + "\n" +
                           (raw < 0 ? "N/A"
                                    : String.format("%.1f", raw));
            TextShape txt = new TextShape(x, baseY + 15, label);
            window.addShape(txt);

            i++;
        }

        String mLabel;
        if (january)      
        {
            mLabel = "January";
        }
        else if (february) 
        {
            mLabel = "February";
        }
        else if (march)   
        {
            mLabel = "March";
        }
        else
        {
            mLabel = "Quarter";
        }

        String rateLabel = traditionalRate
                         ? "Traditional Engagement Rate"
                         : "Reach Engagement Rate";

        TextShape title = new TextShape(
            50, 50,
            mLabel + "\n" + rateLabel +
            "\nSorting by " + (sortByName
                              ? "Channel Name"
                              : "Engagement Rate")
        );
        window.addShape(title);
    }

    private double valueFor(Influencer inf) {
        String monthKey;
        if (january)
        {
            monthKey = "January";
        }
            
        else if (february)
        {
            monthKey = "February";
        }
        else if (march) 
        {    
            monthKey = "March";
        }
        else   
        {
            monthKey = "Quarter";
        }

        if (traditionalRate) 
        {
            return inf.getTraditionalRateForMonth(monthKey);
        }
        else 
        {
            return inf.getReachRateForMonth(monthKey);
        }
    }

    private Color painter() {
        TestableRandom rand = new TestableRandom();
        return new Color(
            rand.nextInt(226),
            rand.nextInt(226),
            rand.nextInt(226)
        );
    }
}