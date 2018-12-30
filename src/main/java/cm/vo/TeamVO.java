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
public class TeamVO {

    private Long teamId;

    private String teamName;

    private String teamNumber;

    private CourseKlassVO courseKlass;

    private UserVO leader;

    private List<UserVO> members;

    private Byte valid;

    public void setTeamNumber(Byte klassSerial,Byte teamSerial) {
        this.teamNumber = String.valueOf(klassSerial)+"-" +String.valueOf(teamSerial);
    }
}
