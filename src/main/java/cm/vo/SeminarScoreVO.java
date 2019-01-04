package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class SeminarScoreVO {
    /**
     * course
     */
    private String courseName;

    /**
     * klass
     */
    private String klassName;
    /**
     * attendance
     */
    private Byte teamOrder;

    private SimpleSeminarScoreVO simpleSeminarScoreVO;

    public void setKlassName(Integer grade, Byte klassSerial) {
        this.klassName = String.valueOf(grade)+"("
                +String.valueOf(klassSerial)+")";
    }

    public void setTeamOrder(Integer teamOrder) {
        byte a= (byte) teamOrder.intValue();
        this.teamOrder=a;
    }
}
