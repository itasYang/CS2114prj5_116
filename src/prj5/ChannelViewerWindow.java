package prj5;

import cs2.*;
import java.nio.channels.Channel;

public class ChannelViewerWindow
{
    private Window window;
    private Shape ch1_shape;
    private Shape ch2_shape;
    private Shape ch3_shape;
    private Shape chn4_shape;
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
    {
        window = new Window("Social Media Vis");

        quitButton = new Button("Quit");

        sortByChannelNameButton = new Button("Sort by Channel Name");

        sortByEngagementRateButton = new Button("Sort by Engagement Rate");
        traditionalEngagementRateButton =
            new Button("Traditional Engagement Rate");

        reachEngagementRateButton = new Button("Reach Engagement Rate");

        januaryButton = new Button("January");
        februaryButton = new Button("February");
        marchButton = new Button("March");
        firstQuarterButton = new Button("First Quarter (Jan-March)");
    }

}
