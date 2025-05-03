package prj5;

import cs2.*;
import java.awt.Color;
import java.io.IOException;
import java.nio.channels.Channel;
import student.TestableRandom;

public class ChannelViewerWindow
{

    private Window window;
    private LinkedList<Influencer> influencers;
    private boolean sortByName;
    private boolean sortByEngagement;
    private boolean januray;
    private boolean february;
    private boolean march;
    private boolean firstQuarter;
    private boolean traditionalRate;
    private boolean engagementRate;
    private Shape ch1_shape;
    private Shape ch2_shape;
    private Shape ch3_shape;
    private Shape ch4_shape;
    private Channel ch1;
    private Channel ch2;
    private Channel ch3;
    private Channel ch4;
    private Button quitButton;
    private Button sortByChannelNameButton;
    private Button sortByEngagementRateButton;
    private Button traditionalEngagementRateButton;
    private Button reachEngagementRateButton;
    private Button januaryButton;
    private Button februaryButton;
    private Button marchButton;
    private Button firstQuarterButton;

    public ChannelViewerWindow()
        throws IOException
    {
        sortByName = true;
        sortByEngagement = false;
        januray = false;
        february = false;
        march = false;
        firstQuarter = true;
        traditionalRate = true;
        engagementRate = false;

        window = new Window();
        window.setSize(800, 600);
        window.setTitle("Social Media Vis");

        ch1_shape = new Shape(50, 100, painter());
        ch2_shape = new Shape(150, 100, painter());
        ch3_shape = new Shape(250, 100, painter());
        ch4_shape = new Shape(150, 100, painter());

        InputFileReader reader = new InputFileReader("SocialMediaData.csv");
        influencers = reader.getInfluencers();

        sortByChannelNameButton = new Button("Sort by Channel Name");
        sortByChannelNameButton.onClick(this, "sortByChannelNameClicked");
        window.addButton(sortByChannelNameButton, WindowSide.NORTH);

        sortByEngagementRateButton = new Button("Sort by Engagement Rate");
        sortByEngagementRateButton.onClick(this, "sortByEngagementRateClicked");
        window.addButton(sortByEngagementRateButton, WindowSide.NORTH);

        quitButton = new Button("Quit");
        quitButton.onClick(this, "quitButtonClicked");
        window.addButton(quitButton, WindowSide.NORTH);

        traditionalEngagementRateButton =
            new Button("Traditional Engagement Rate");
        traditionalEngagementRateButton.onClick(this, "showTraditionalRate");
        window.addButton(traditionalEngagementRateButton, WindowSide.WEST);

        reachEngagementRateButton = new Button("Reach Engagement Rate");
        reachEngagementRateButton.onClick(this, "showReachRate");
        window.addButton(reachEngagementRateButton, WindowSide.WEST);

        januaryButton = new Button("January");
        januaryButton.onClick(this, "showJanuary");
        window.addButton(januaryButton, WindowSide.EAST);

        februaryButton = new Button("February");
        februaryButton.onClick(this, "showFebruary");
        window.addButton(februaryButton, WindowSide.EAST);

        marchButton = new Button("March");
        marchButton.onClick(this, "showMarch");
        window.addButton(marchButton, WindowSide.EAST);

        firstQuarterButton = new Button("First Quarter (Jan-Mar)");
        firstQuarterButton.onClick(this, "showQuarter");
        window.addButton(firstQuarterButton, WindowSide.EAST);
        displayChannels();
    }


    public void quitButtonClicked(Button b)
    {
        System.exit(0);
    }


    public void sortByChannelNameClicked(Button b)
    {
        sortByName = true;
        sortByEngagement = false;
        displayChannels();
    }


    public void sortByEngagementRateClicked(Button b)
    {
        sortByEngagement = true;
        sortByName = false;
        displayChannels();

    }


    public void showTraditionalRate(Button b)
    {
        traditionalRate = true;
        engagementRate = false;
        displayChannels();

    }


    public void showReachRate(Button b)
    {
        engagementRate = true;
        traditionalRate = false;
        displayChannels();
    }


    public void showJanuary(Button b)
    {
        januray = true;
        february = false;
        march = false;
        firstQuarter = false;

        displayChannels();
    }


    public void showFebruary(Button b)
    {
        januray = false;
        february = true;
        march = false;
        firstQuarter = false;
        displayChannels();
    }


    public void showMarch(Button b)
    {
        januray = false;
        february = false;
        march = true;
        firstQuarter = false;
        displayChannels();
    }


    public void showQuarter(Button b)
    {
        januray = false;
        february = false;
        march = false;
        firstQuarter = true;
        displayChannels();
    }


    private Color painter()
    {
        TestableRandom random = new TestableRandom();
        int r = random.nextInt(226);
        int g = random.nextInt(226);
        int b = random.nextInt(226);
        Color color = new Color(r, g, b);
        return color;
    }


    private void displayChannels()
    {
        window.removeAllShapes();

        if (januray)
        {

        }
        else if (february)
        {

        }
        else if (march)
        {
        }
        else
        {
        }
        if (sortByName)
        {
        }
        else
        {
        }
        if (traditionalRate)
        {

        }
        else
        {
        }

    }

}
