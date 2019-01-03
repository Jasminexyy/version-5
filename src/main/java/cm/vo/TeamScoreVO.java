package cm.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class TeamScoreVO {
    private Long teamId;
    private String teamNumber;
    private BigDecimal roundScore;
    private List<SimpleSeminarScoreVO> seminarScoreVOList;

    public void setTeamNumber(Byte klassSerial,Byte teamSerial) {
        this.teamNumber = String.valueOf(klassSerial)+"-" +String.valueOf(teamSerial);
    }
}
