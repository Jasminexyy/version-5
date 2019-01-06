package cm.vo;

import lombok.Data;

@Data
public class SeminarCreateVO {

    private Byte isVisible;

    private String roundSerial;

    private String seminarName;

    private String introduction;

    private Byte teamNumLimit;

    private String enrollStartTime;

    private String enrollEndTime;
}
