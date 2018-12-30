package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class KlassVO {
    @JsonProperty("id")
    private Long klassId;

    @JsonProperty("name")
    private String klassName;

    public void setKlassName(Integer grade, Byte klassSerial) {
        this.klassName = String.valueOf(grade)+"(" +String.valueOf(klassSerial)+")";
    }
}
