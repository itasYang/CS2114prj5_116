package prj5;

import java.io.IOException;
import java.text.DecimalFormat;

// -------------------------------------------------------------------------
/**
 * This is the project runner for prj5.
 * 
 * @author Yinhan Wang, Ethan Yang, Boyuan Zhao, Chenghan Yang
 * @version 2025/04/24
 */
public class ProjectRunner {

    // ----------------------------------------------------------
    /**
     * This is the main method for this project.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 1) 选择输入文件
        String filename = (args.length > 0 && !args[0].equalsIgnoreCase("gui"))
                          ? args[0]
                          : "SampleInput1_2023.csv";

        // 2) 读取并构造 Influencer 列表
        InputFileReader reader = new InputFileReader(filename);
        LinkedList<Influencer> influencers = reader.getInfluencers();

        // 3) 决定是否展示控制台 & GUI
        boolean showConsole = true;
        boolean showGUI     = true;

        // 4) 控制台输出
        if (showConsole) {
            DecimalFormat df = new DecimalFormat("#.#");

            // 4.1 按 Channel 名称排序并输出传统参与率
            influencers.insertionSort(new InfluencerChannelNameComparator());
            for (Influencer inf : influencers) {
                System.out.println(inf.getChannelName());
                double tr = inf.getTraditionalRate();
                String out = (tr < 0) ? "N/A" : df.format(tr);
                System.out.println("traditional: " + out);
                System.out.println("==========");
            }

            // 分隔线
            System.out.println("**********");
            System.out.println("**********");

            // 4.2 按 Reach 排序并输出 Reach 参与率
            influencers.insertionSort(new InfluencerReachComparator());
            for (Influencer inf : influencers) {
                System.out.println(inf.getChannelName());
                double rr = inf.getReachRate();
                String out = (rr < 0) ? "N/A" : df.format(rr);
                System.out.println("reach: " + out);
                System.out.println("==========");
            }
        }

        // 5) 启动 GUI
        if (showGUI) {
            new ChannelViewerWindow(influencers);
        }
    }
}
