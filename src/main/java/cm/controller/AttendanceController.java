package cm.controller;

import cm.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@MessageMapping("/cm/seminar/")
public class AttendanceController {

    @Autowired
    private static AttendanceService attendanceService;

    /*@MessageMapping("/nextAttendance")
    @SendTo("/topic/broadcast")
    public WebSocketVO nextAttendance(@RequestBody SeminarInfoVO seminarInfoVO) {
        WebSocketVO webSocketVO=attendanceService.nextAttendance(seminarInfoVO.getSeminarId(),
                seminarInfoVO.getKlassId());
        return webSocketVO;
    }*/
}
