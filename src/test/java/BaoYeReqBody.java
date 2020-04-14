import lombok.Data;

import java.io.File;
import java.io.Serializable;
@Data
public class BaoYeReqBody implements Serializable {

    private String appid;

    private File file;


}
