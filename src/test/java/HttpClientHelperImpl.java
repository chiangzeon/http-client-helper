import com.chiang.httpClientHelper.assistant.InstanceAssistant;


public class HttpClientHelperImpl implements HttpClientHelper2 {

    public String baidu() {
        return "访问百度";
    }

    public static void main(String[] args) throws Throwable {
        HttpClientHelper2 httpClientHelper2 = InstanceAssistant.newInstance(HttpClientHelper2.class);
        System.out.println(httpClientHelper2.baidu());

    }
}
