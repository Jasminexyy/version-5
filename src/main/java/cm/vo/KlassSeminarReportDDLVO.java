package cm.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KlassSeminarReportDDLVO {

    private String klassName;

    private Long klassId;

    private Long seminarId;

    private String reportDDL;

    public void setKlassName(Integer grade, Byte klassSerial) {
        this.klassName = String.valueOf(grade)+"(" +String.valueOf(klassSerial)+")";
    }
}
