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
public class RoundVO {
    @JsonProperty("id")
    private Long roundId;

    @JsonProperty("order")
    private Byte roundNumber;

    private Byte presentationScoreMethod;
    private Byte reportScoreMethod;
    private Byte questionScoreMethod;

    @JsonProperty("seminars")
    private List<SeminarVO> seminarVOList;

    @JsonProperty("classRounds")
    private List<KlassRoundVO> klassRoundVOList;

    @JsonProperty("teamScores")
    private List<TeamScoreVO> teamScoreVOList;
}
