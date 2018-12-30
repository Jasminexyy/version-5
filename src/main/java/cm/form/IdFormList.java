package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class IdFormList {
    @JsonProperty("idList")
    private List<IdForm>idFormList;
}
