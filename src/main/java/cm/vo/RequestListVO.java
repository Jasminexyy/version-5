package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class RequestListVO {

    @JsonProperty("request")
    private RequestTypeVO requestTypeVO;

    @JsonProperty("historyRequest")
    private RequestTypeVO historyRequestTypeVO;
}
