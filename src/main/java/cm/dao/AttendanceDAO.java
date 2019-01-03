package cm.dao;

import cm.entity.Attendance;
import cm.mapper.AttendanceMapper;
import cm.mapper.QuestionMapper;
import cm.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/23
 */
@Component
public class AttendanceDAO {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public Attendance getByKlassSeminarIdAndKlassIdAndStudentId(Long klassSeminarId, Long klassId, Long studentId){

        return attendanceMapper.getByKlassSeminarIdAndKlassIdAndStudentId(klassSeminarId, klassId, studentId);
    }

    public Attendance getBySeminarIdAndKlassIdAndStudentId(Long seminarId, Long klassId, Long studentId){

        return attendanceMapper.getBySeminarIdAndKlassIdAndStudentId(seminarId, klassId, studentId);
    }

    public Attendance getByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId){

        return attendanceMapper.getByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }
    public Attendance getByKlassSeminarIdAndTeamOrder(Long klassSeminarId,Integer teamOrder){

        return attendanceMapper.getByKlassSeminarIdAndTeamOrder(klassSeminarId, teamOrder);
    }

    public Attendance getByAttendanceId(Long attendanceId){

        return attendanceMapper.getByAttendanceId(attendanceId);
    }

    public Attendance getByAttendanceIdAndStudentId(Long attendanceId,Long studentId){

        return attendanceMapper.getByAttendanceIdAndStudentId(attendanceId, studentId);
    }

    public int updatePptByAttendanceId(Long attendanceId,String pptName,String pptUrl){

        return attendanceMapper.updatePptByAttendanceId(attendanceId,pptName,pptUrl);
    }

    public int updateReportByAttendanceId(Long attendanceId,String reportName,String reportUrl){

        return attendanceMapper.updateReportByAttendanceId(attendanceId,reportName,reportUrl);
    }

    public void deleteAttendanceByAttendanceId(Long attendanceId){
        questionMapper.deleteByAttendanceId(attendanceId);
        attendanceMapper.deleteByAttendanceId(attendanceId);
        File reportFile=new File(reportPathInServer+attendanceId);
        File pptFile=new File(pptPathInServer+attendanceId);
        FileUtil.deleteFile(reportFile);
        FileUtil.deleteFile(pptFile);
    }

    public int addAttendance(Attendance attendance,Long klassSeminarId,Long teamId){
        return attendanceMapper.addAttendance(attendance,klassSeminarId,teamId);
    }

    public List<Attendance>listByKlassSeminarId(Long klassSeminarId){
        return attendanceMapper.listByKlassSeminarId(klassSeminarId);
    }

    public List<Attendance>listByTeamId(Long teamId){

        return attendanceMapper.listByTeamId(teamId);
    }

    public Long getTeamIdByAttendanceId(Long attendanceId){

        return  attendanceMapper.getTeamIdByAttendanceId(attendanceId);
    }

    public Long getKlassSeminarIdByAttendanceId(Long attendanceId){

        return attendanceMapper.getKlassSeminarIdByAttendanceId(attendanceId);
    }

    public String getReportUrlByAttendanceId(Long attendanceId){

        return  attendanceMapper.getReportUrlByAttendanceId(attendanceId);
    }

    public String getPptUrlByAttendanceId(Long attendanceId){

        return attendanceMapper.getPptUrlByAttendanceId(attendanceId);
    }

    public int startAttendance(Long attendanceId){

        return attendanceMapper.startAttendanceById(attendanceId);
    }

    public int endAttendance(Long attendanceId){

        return attendanceMapper.endAttendanceById(attendanceId);
    }

    public List<Attendance> listByRoundIdAndTeamId(Long roundId,Long teamId){

        return attendanceMapper.listByRoundIdAndTeamId(roundId,teamId);
    }

    @Value("${pptPathInServer}")
    private String pptPathInServer;

    @Value("${reportPathInServer}")
    private String reportPathInServer;
}
