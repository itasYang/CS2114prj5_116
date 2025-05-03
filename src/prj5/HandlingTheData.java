package prj5;
public class HandlingTheData
{
    private List<InfluencerQuarterData> dataList;

    private static final Set<String> VALID_MONTHS = new HashSet<>(Arrays.asList(
        "January","February","March",
        "April","May","June",
        "July","August","September",
        "October","November","December"
    ));
    static final DecimalFormat DF = new DecimalFormat("#.#");
    

}
