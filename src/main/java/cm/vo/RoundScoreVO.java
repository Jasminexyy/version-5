package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class RoundScoreVO{
    private Long roundId;

    private Byte roundNumber;

    /**
     * 此轮分数
     */
    private BigDecimal totalScore;

    private List<SimpleSeminarScoreVO> SimpleSeminarScoreVOList;
}
