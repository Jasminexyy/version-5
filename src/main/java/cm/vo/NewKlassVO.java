package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/21
 */
@Data
public class NewKlassVO {
    private Integer grade;

    private Byte klassSerial;

    private String klassTime;

    private String klassLocation;
}
