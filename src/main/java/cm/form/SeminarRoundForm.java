package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class SeminarRoundForm {
    @JsonProperty("order")
    private Byte roundSerial;
}
