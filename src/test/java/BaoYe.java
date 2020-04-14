import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.Param;
import com.chiang.httpClientHelper.annotation.ParamBody;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.standard.HttpClientHelper;

import java.io.File;

import static com.chiang.httpClientHelper.constant.Constants.MULTIPART_FORM_DATA;
import static com.chiang.httpClientHelper.constant.Constants.POST;

@Client(path = "https://fapiao.yonyoucloud.com")
public interface BaoYe extends HttpClientHelper {
    @RequestInfo(uri = "/input-tax/api/invoice/pdf/parse", method = POST, contentType = MULTIPART_FORM_DATA)
    String parse(@Param("appid") String appid, @Param("file") File file);


    @RequestInfo(uri = "/input-tax/api/invoice/pdf/parse", method = POST, contentType = MULTIPART_FORM_DATA)
    String parse(@ParamBody BaoYeReqBody yeReqBody);
}
