package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class KlassForm {

    private Integer grade;

    private Byte klassSerial;

    @JsonProperty("time")
    private String klassTime;

    @JsonProperty("classroom")
    private String klassLocation;
}
