import com.chiang.httpClientHelper.annotation.Client;
import com.chiang.httpClientHelper.annotation.Param;
import com.chiang.httpClientHelper.annotation.RequestInfo;
import com.chiang.httpClientHelper.standard.HttpClientHelper;

import static com.chiang.httpClientHelper.constant.Constants.APPLICATION_X_WWW_FORM_URLENCODED;
import static com.chiang.httpClientHelper.constant.Constants.POST;

@Client(path = "http://testqdy.beautyfarm.com.cn")
public interface TestBeautyFarm extends HttpClientHelper {

    @RequestInfo(uri = "/occ-stock/stock/transfer-out-bills/findByParentid")
    String test(@Param("id") String id, @Param("search_AUTH_APPCODE") String search_AUTH_APPCODE);


    @RequestInfo(method = POST, uri = "/uitemplate_web/iref_ctr/filterRefJSON", contentType = APPLICATION_X_WWW_FORM_URLENCODED)
    String filterRefJSON(@Param(value = "content", required = false) String content,
                         @Param(value = "isNotLeafParam", required = false) String isNotLeafParam,
                         @Param(value = "pk_val", required = false) String pk_val,
                         @Param(value = "filterPks", required = false) String filterPks,
                         @Param(value = "refModelUrl", required = false) String refModelUrl,
                         @Param(value = "pk_org", required = false) String pk_org,
                         @Param(value = "transmitParam", required = false) String transmitParam,
                         @Param(value = "refName", required = false) String refName,
                         @Param(value = "refCode", required = false) String refCode,
                         @Param(value = "refModelClassName", required = false) String refModelClassName,
                         @Param(value = "refModelHandlerClass", required = false) String refModelHandlerClass,
                         @Param(value = "cfgParam", required = false) String cfgParam,
                         @Param(value = "clientParam", required = false) String clientParam,
                         @Param(value = "refClientPageInfo.currPageIndex", required = false) String refClientPageInfoCurrPageIndex
    );
}
