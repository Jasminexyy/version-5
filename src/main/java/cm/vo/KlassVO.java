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
public class KlassVO {
    private Integer grade;

    private Byte klassSerial;

    private String klassTime;

    private String klassLocation;

    private Long klassId;

    private String klassName;

    public void setKlassName(Integer grade, Byte klassSerial) {
        this.klassName = String.valueOf(grade)+"(" +String.valueOf(klassSerial)+")";
    }

    private List<UserVO> studentVOList;
}
