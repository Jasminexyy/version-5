package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class RequestTypeVO {

    List<SimpleRequestVO>teamRequestVOList;

    List<SimpleRequestVO>seminarRequestVOList;

    List<SimpleRequestVO>validRequestVOList;
}
