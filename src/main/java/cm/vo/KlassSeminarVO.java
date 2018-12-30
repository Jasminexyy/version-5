package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class KlassSeminarVO {
    @JsonProperty("classSeminarId")
    private Long id;
    private String topic;
    private String intro;
    private Byte status;
    private Byte order;
    private Byte teamNumLimit;
    private String reportDDL;

}
