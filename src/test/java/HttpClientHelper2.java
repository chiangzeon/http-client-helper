import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.standard.HttpClientHelper;

@Client(path = "http://www.baidu.com")
public interface HttpClientHelper2 extends HttpClientHelper {

    @RequestInfo(uri = "/")
    String baidu();
}
