package prj5;

import cs2.*;
import java.io.IOException;
import java.nio.channels.Channel;

public class ChannelViewerWindow
{
    private Window window;
    private LinkedList<Influencer> influencers;
    private boolean sortByChannel = true;
    private boolean showTraditional = true;
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
        window = new Window();
        window.setSize(800, 600);
        window.setTitle("Social Media Vis");
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
    }


    public void quitButtonClicked(Button b)
    {
        System.exit(0);
    }


    public void sortByChannelNameClicked(Button b)
    {
        sortByChannel = true;
        // 等刷新方法
    }


    public void sortByEngagementRateClicked(Button b)
    {
        sortByChannel = false;
        // 等刷新方法

    }


    public void showTraditionalRate(Button b)
    {
        showTraditional = true;
        // 等刷新方法
        ;
    }


    public void showReachRate(Button b)
    {
        showTraditional = false;
        // 等刷新方法

    }


    public void showJanuary(Button b)
    {
    }


    public void showFebruary(Button b)
    {
    }


    public void showMarch(Button b)
    {
    }


    public void showQuarter(Button b)
    {
    }

}
