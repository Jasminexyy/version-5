package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class SeminarForm {

    @JsonProperty("topic")
    private String seminarName;
    @JsonProperty("intro")
    private String introduction;
    @JsonProperty("order")
    private Byte seminarSerial;
    @JsonProperty("visible")
    private Byte isVisible;
    @JsonProperty("roundOrder")
    private Byte roundSerial;
    @JsonProperty("teamNumLimit")
    private Byte maxTeam;
    @JsonProperty("signupStartTime")
    private String enrollStartTime;
    @JsonProperty("signupEndTime")
    private String enrollEndTime;
    private Long courseId;

    @JsonProperty("reportDDL")
    private String reportDdl;

}
