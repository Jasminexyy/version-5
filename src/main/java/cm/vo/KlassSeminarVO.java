package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class KlassSeminarVO {
    private Long id;
    private String topic;
    private String intro;
    private Byte isVisible;
    private Byte roundSerial;
    private Byte teamNumLimit;
    private String reportDDL;

}
