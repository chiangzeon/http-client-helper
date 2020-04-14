import com.chiang.httpClientHelper.assistant.InstanceAssistant;
import com.chiang.httpClientHelper.context.ThreadContext;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaoYeTest {
    public static void main(String[] args) throws Throwable{
        Map<String,String> header = new HashMap<>();
        header.put("sign","eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJleHAiOjE1ODY3NDk1NzIsImlzcyI6ImNvbW1vbnRlc3RlckNBIiwianRpIjoiZmE5OThlMzYtMzRkZS00ODI1LThiZjMtYzU5NzU3NDEzOTQ2IiwiYXVkIjoiZWludm9pY2UueW9ueW91Y2xvdWQuY29tIiwic3ViIjoicmVpbWJ1cnNlbWVudD3miqXplIAiLCJpYXQiOjE1ODY3NDkyNzIsIm5iZiI6MTU4Njc0OTI2N30=.M7OanMAAjKs12vRjHSQiim71NDt7alhbfNoLeoH4hurtHfqroJyT+D3ddtaR19HmsTIRDko65Wy9PaL8d6jbyemrIDWhVHdh7jiH0FSfxnb9zlZHCaqwZ7XmphhhI2JdHTrjlsxSl3IYdKdv4GSREYbsdA/5rhxl8dm/rAReWv8=");
        List<Map<String,String>> headers = new ArrayList<>();
        headers.add(header);
        ThreadContext.setHeader(headers);
        BaoYe baoYe = InstanceAssistant.newInstance(BaoYe.class);
        /*String parse = baoYe.parse("fe0c6c8c-0724-4d36-a2f5-03dc27a780cb", new File("D:\\微信文件\\WeChat Files\\Clever09294418\\FileStorage\\File\\2020-04\\58567879-上海报业集团.pdf"));
        System.out.println(parse);*/
        BaoYeReqBody baoYeReqBody = new BaoYeReqBody();
        baoYeReqBody.setAppid("fe0c6c8c-0724-4d36-a2f5-03dc27a780cb");
        baoYeReqBody.setFile(new File("D:\\微信文件\\WeChat Files\\Clever09294418\\FileStorage\\File\\2020-04\\58567879-上海报业集团.pdf"));
        String parse1 = baoYe.parse(baoYeReqBody);
        System.out.println(parse1);
    }
}
