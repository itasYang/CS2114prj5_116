package prj5;

import cs2.*;
import java.awt.Color;
import java.io.IOException;
import student.TestableRandom;

public class ChannelViewerWindow {
    private Window window;
    private LinkedList<Influencer> influencers;

    private boolean sortByName;
    private boolean sortByEngagement;
    private boolean january;
    private boolean february;
    private boolean march;
    private boolean firstQuarter;
    private boolean traditionalRate;
    private boolean reachRate;

    public ChannelViewerWindow(LinkedList<Influencer> influencers) {
        // 使用传入的数据，不再硬编码读文件
        this.influencers = influencers;

        // 初始状态
        sortByName        = true;
        sortByEngagement  = false;
        january           = false;
        february          = false;
        march             = false;
        firstQuarter      = true;
        traditionalRate   = true;
        reachRate         = false;

        // 窗口设置
        window = new Window();
        window.setSize(800, 600);
        window.setTitle("Social Media Vis");

        // 顶部按钮
        Button b = new Button("Sort by Channel Name");
        b.onClick(this, "sortByChannelNameClicked");
        window.addButton(b, WindowSide.NORTH);

        b = new Button("Sort by Engagement Rate");
        b.onClick(this, "sortByEngagementRateClicked");
        window.addButton(b, WindowSide.NORTH);

        b = new Button("Quit");
        b.onClick(this, "quitButtonClicked");
        window.addButton(b, WindowSide.NORTH);

        // 左侧按钮
        b = new Button("Traditional Engagement Rate");
        b.onClick(this, "showTraditionalRate");
        window.addButton(b, WindowSide.WEST);

        b = new Button("Reach Engagement Rate");
        b.onClick(this, "showReachRate");
        window.addButton(b, WindowSide.WEST);

        // 右侧按钮
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

        // 初次绘制
        displayChannels();
    }

    // ========== 事件处理 ==========

    public void quitButtonClicked(Button b) {
        System.exit(0);
    }

    public void sortByChannelNameClicked(Button b) {
        sortByName       = true;
        sortByEngagement = false;
        displayChannels();
    }

    public void sortByEngagementRateClicked(Button b) {
        sortByEngagement = true;
        sortByName       = false;
        displayChannels();
    }

    public void showTraditionalRate(Button b) {
        traditionalRate = true;
        reachRate       = false;
        displayChannels();
    }

    public void showReachRate(Button b) {
        reachRate       = true;
        traditionalRate = false;
        displayChannels();
    }

    public void showJanuary(Button b) {
        january      = true;
        february     = false;
        march        = false;
        firstQuarter = false;
        displayChannels();
    }

    public void showFebruary(Button b) {
        january      = false;
        february     = true;
        march        = false;
        firstQuarter = false;
        displayChannels();
    }

    public void showMarch(Button b) {
        january      = false;
        february     = false;
        march        = true;
        firstQuarter = false;
        displayChannels();
    }

    public void showQuarter(Button b) {
        january      = false;
        february     = false;
        march        = false;
        firstQuarter = true;
        displayChannels();
    }

    // ========== 绘制主逻辑 ==========

    private void displayChannels() {
        window.removeAllShapes();

        // 1) 排序
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

        // 2) 计算最大值（等比缩放用）
        double maxVal = 0;
        for (Influencer inf : influencers) {
            double v = valueFor(inf);
            if (v > maxVal) {
                maxVal = v;
            }
        }

        // 3) 绘制条形和标签
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

            // Shape 用于条形
            Shape bar = new Shape(x, y, barW, h, painter());
            window.addShape(bar);

            // 文本标签
            String label = inf.getChannelName() + "\n" +
                           (raw < 0 ? "N/A"
                                    : String.format("%.1f", raw));
            TextShape txt = new TextShape(x, baseY + 15, label);
            window.addShape(txt);

            i++;
        }

        // 4) 标题
        String mLabel;
        if (january)      mLabel = "January";
        else if (february) mLabel = "February";
        else if (march)    mLabel = "March";
        else               mLabel = "Quarter";

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

    /** 根据当前状态取值 */
    private double valueFor(Influencer inf) {
        String monthKey;
        if (january)      monthKey = "January";
        else if (february) monthKey = "February";
        else if (march)    monthKey = "March";
        else               monthKey = "Quarter";

        if (traditionalRate) {
            return inf.getTraditionalRateForMonth(monthKey);
        }
        else {
            return inf.getReachRateForMonth(monthKey);
        }
    }

    /** 随机颜色生成器 */
    private Color painter() {
        TestableRandom rand = new TestableRandom();
        return new Color(
            rand.nextInt(226),
            rand.nextInt(226),
            rand.nextInt(226)
        );
    }
}