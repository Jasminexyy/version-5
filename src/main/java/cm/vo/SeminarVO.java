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
public class SeminarVO{

    private Long seminarId;

    @JsonProperty("topic")
    private String seminarTopic;

    private Byte seminarOrder;

    private List<KlassVO> klassVOList;
}
