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


}
