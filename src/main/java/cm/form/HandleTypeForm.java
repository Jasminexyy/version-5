package cm.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/24
 */
@Data
public class HandleTypeForm {
    @JsonProperty("handletype")
    private String handleType;

    public Boolean getResult() {
        final String accept="accept";
        final String refuse="refuse";
        if (accept.equals(this.handleType)) {
            return true;
        }
        else if (refuse.equals(this.handleType)) {
            return false;
        }
        return null;
    }
}
