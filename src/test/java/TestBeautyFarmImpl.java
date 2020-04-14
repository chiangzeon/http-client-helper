import com.chiang.httpClientHelper.assistant.InstanceAssistant;

public class TestBeautyFarmImpl {
    public static void main(String[] args) throws Throwable{
        TestBeautyFarm testBeautyFarm = InstanceAssistant.newInstance(TestBeautyFarm.class);
        String transferOutBill = testBeautyFarm.test("0xLfLzxvnh7RoLD09o2E", "transferOutBill");
        System.out.println(transferOutBill);
        String filterRefJSON = testBeautyFarm.filterRefJSON(null,
                "undefined",
                null,
                null,
                "%2Focc-base%2Fperson-ref%2F",
                null,
                null,
                "%2525E5%2525BA%252593%2525E7%2525AE%2525A1%2525E5%252591%252598",
                null,
                null,
                null,
                "%7B%22ctx%22%3A%22%2Fuitemplate_web%22%2C%22refName%22%3A%22%E5%BA%93%E7%AE%A1%E5%91%98%22%7D",
                "%7B%22AUTH_refcod%22%3A%22transferOutBill%22%2C%22AUTH_refdim%22%3A%22storekeeper%22%2C%22EQ_isEnable%22%3A%221%22%7D",
                "0");
        System.out.println(filterRefJSON);
    }
}
