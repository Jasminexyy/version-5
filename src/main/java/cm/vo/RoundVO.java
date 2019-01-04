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
    private Long roundId;

    private Byte roundNumber;

    private Byte presentationScoreMethod;
    private Byte reportScoreMethod;
    private Byte questionScoreMethod;

    private List<SeminarVO> seminarVOList;

    private List<KlassRoundVO> klassRoundVOList;

    private List<TeamScoreVO> teamScoreVOList;
}
