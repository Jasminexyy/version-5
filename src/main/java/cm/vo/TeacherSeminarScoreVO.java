package cm.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TeacherSeminarScoreVO {

    /**
     * attendance
     */
    private String teamName;

    private List<SimpleSeminarScoreVO> simpleSeminarScoreVO;

    public void setTeamName(Byte klassSerial, Byte teamSerial) {
        this.teamName = String.valueOf(klassSerial)+"("
                +String.valueOf(teamSerial)+")";
    }

    private BigDecimal totalScore;
}
