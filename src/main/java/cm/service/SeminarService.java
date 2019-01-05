package cm.service;

import cm.dao.*;
import cm.entity.*;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SeminarService {
    @Autowired
    private SeminarDAO seminarDAO;
    @Autowired
    private KlassSeminarDAO klassSeminarDAO;
    @Autowired
    private AttendanceDAO attendanceDAO;
    @Autowired
    private SeminarScoreDAO seminarScoreDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private KlassDAO klassDAO;
    @Autowired
    private RoundDAO roundDAO;
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private QuestionDAO questionDAO;


    public KlassSeminar findKlassSeminarById(Long klassId, Long seminarId){
        return klassSeminarDAO.getBySeminarIdAndKlassId(seminarId,klassId);
    }


    public Seminar TransferSeminarInfoToSeminar(SeminarInfoVO seminarInfoVO,CourseDetailVO course){
        Seminar seminar=new Seminar();
        Timestamp startTime=Timestamp.valueOf(seminarInfoVO.getEnrollStartTime());
        Timestamp endTime=Timestamp.valueOf(seminarInfoVO.getEnrollEndTime());
        seminar.setCourseId(course.getId());
        seminar.setSeminarName(seminarInfoVO.getSeminarName());
        seminar.setEnrollEndTime(endTime);
        seminar.setEnrollStartTime(startTime);
        seminar.setIntroduction(seminarInfoVO.getIntroduction());
        seminar.setIsVisible(seminarInfoVO.getIsVisible());
        seminar.setMaxTeam(seminarInfoVO.getTeamNumLimit());

        return seminar;
    }
    public void addSeminar(SeminarInfoVO seminarInfoVO,CourseDetailVO courseDetailVO){
        Seminar seminar=TransferSeminarInfoToSeminar(seminarInfoVO,courseDetailVO);
        seminarDAO.createSeminar(seminar);
    }

    public void updateSeminar(SeminarInfoVO seminarModifyVO,CourseDetailVO courseDetailVO){
        Seminar seminar=TransferSeminarInfoToSeminar(seminarModifyVO,courseDetailVO);
        seminarDAO.modifySeminar(seminar);
    }

    //删除讨论课除了Seminar表应该还有其他？？？
    public void deleteSeminarById(Long seminarId){
        List<KlassSeminar> klassSeminars=klassSeminarDAO.listBySeminarId(seminarId);
        for (int i=0;i<klassSeminars.size();i++)
            klassSeminarDAO.deleteByKlassSeminarId(klassSeminars.get(i).getId());
        seminarDAO.deleteBySeminarId(seminarId);
    }

    public List<Attendance> findAttendanceById(Long klassSeminarId){
        return attendanceDAO.listByKlassSeminarId(klassSeminarId);
    }

//    public List<SeminarScore> findSeminarScoreById(Long klassSeminarId){
//        return seminarScoreDAO.listByKlassSeminarId(klassSeminarId);
//    }

    public boolean enroll(Long klassSeminarId,AttendanceVO attendance,Long studentId){
        KlassSeminar klassSeminar=klassSeminarDAO.getByKlassSeminarId(klassSeminarId);
        Team team=teamDAO.getByKlassIdAndStudentId(klassSeminar.getKlassId(),studentId);
        Attendance attendance1=new Attendance();
        attendance1.setTeamOrder(Integer.valueOf(attendance.getTeamOrder()));
        attendance1.setIsPresent(attendance.getIsPresent());
        attendance1.setReportUrl(null);
        attendanceDAO.addAttendance(attendance1,klassSeminarId,team.getId());
        return true;
    }

    public boolean unenroll(Long klassSeminarId,Long studentId){
        Attendance attendance=attendanceDAO.getByKlassSeminarIdAndTeamId(klassSeminarId,studentId);
        attendanceDAO.deleteAttendanceByAttendanceId(attendance.getId());
        return true;
    }


    public Map<String, KlassVO> listCourseAndKlass(UserVO student) {
        //通过学生id获得所有选课
        List<Course> courses=courseDAO.listByStudentId(student.getId());
        Map<String, KlassVO> map=new HashMap<String, KlassVO>();
        for(int i=0;i<courses.size();i++)
        {
            //获得当前选课的班级
            Klass k=klassDAO.getByCourseIdAndStudentId(courses.get(i).getId(),student.getId());
            KlassVO klassVO=new KlassVO();
            klassVO.setKlassId(k.getId());
            klassVO.setKlassName(k.getGrade(),k.getKlassSerial());
            map.put(courses.get(i).getCourseName(),klassVO);
        }
        return map;
    }

    public SeminarInfoVO getKlassSeminarByEachId(Long klassId, Long seminarId) {
        KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(seminarId,klassId);
        SeminarInfoVO seminarInfoVO=getSeminarInfo(klassSeminar.getId());
        return seminarInfoVO;
    }

    public SeminarModifyVO getSeminarModifyVO(Long klassId, Long seminarId) {
        KlassSeminar klassSeminar=findKlassSeminarById(klassId,seminarId);
        SeminarModifyVO seminarModifyVO=new SeminarModifyVO();
        Long courseId=klassDAO.getCourseIdByKlassId(klassId);
        Course course=courseDAO.getByCourseId(courseId);
        Seminar seminar=seminarDAO.getBySeminarId(seminarId);
        seminarModifyVO.setCourseName(course.getCourseName());
        seminarModifyVO.setSeminarName(seminar.getSeminarName());
        seminarModifyVO.setIntroduction(seminar.getIntroduction());
        seminarModifyVO.setIsVisible(seminar.getIsVisible());
        seminarModifyVO.setEnrollStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(seminar.getEnrollStartTime()));
        seminarModifyVO.setEnrollEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(seminar.getEnrollEndTime()));
        seminarModifyVO.setTeamNumLimit(seminar.getMaxTeam());
        seminarModifyVO.setRoundSerial(roundDAO.getByRoundId(seminar.getRoundId()).getRoundSerial());

        //获得当前讨论课的所有klass的dll
        List<KlassSeminar> klassSeminars=klassSeminarDAO.listBySeminarId(seminarId);
        List<KlassSeminarReportDDL> reportDDLList=new LinkedList<KlassSeminarReportDDL>();
        //一个个把所有班级的dll放到list中
        for(int i=0;i<klassSeminars.size();i++) {
            KlassSeminarReportDDL klassSeminarReportDDL=new KlassSeminarReportDDL();
            klassSeminarReportDDL.setReportDDL(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(klassSeminars.get(i).getReportDdl()));
            reportDDLList.add(klassSeminarReportDDL);
        }
        seminarModifyVO.setReportDDLList(reportDDLList);
        return seminarModifyVO;
    }
    
    public AttendanceListVO getAttendanceList(Long klassSeminarId) {
        List<AttendanceVO> attendanceVOS=new LinkedList<AttendanceVO>();
        List<Attendance> attendanceList=attendanceDAO.listByKlassSeminarId(klassSeminarId);
        for(int i=0;i<attendanceList.size();i++)
        {
            Attendance attendance=attendanceList.get(i);
            AttendanceVO attendanceVO=new AttendanceVO();
            attendanceVO.setTeamOrder(attendance.getTeamOrder());
            attendanceVOS.add(attendanceVO);
        }
        AttendanceListVO attendanceListVO=new AttendanceListVO();
        attendanceListVO.setAttendanceList(attendanceVOS);
        return attendanceListVO;
    }

    public AttendanceVO getAttendance(Long klassId, Long seminarId, Long studentId) {
        KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(klassId,seminarId);
        AttendanceVO attendanceVO=new AttendanceVO();
        Attendance attendance=attendanceDAO.getByKlassSeminarIdAndKlassIdAndStudentId(klassSeminar.getId(),seminarId,studentId);
        attendanceVO.setAttendanceId(attendance.getId());
        attendanceVO.setTeamOrder(attendance.getTeamOrder());
        attendanceVO.setIsPresent(attendance.getIsPresent());
        Klass klass=klassDAO.getByKlassId(klassId);
        attendanceVO.setKlassName(klass.getGrade(),klass.getKlassSerial());
        return attendanceVO;
    }

    public SeminarInfoVO getSeminarInfo(Long klassId, Long seminarId) {
        KlassSeminar klassSeminar=findKlassSeminarById(klassId,seminarId);
        System.out.println(klassSeminar.getId());
        return getSeminarInfo(klassSeminar.getId());
    }

    public SeminarInfoVO getSeminarInfo(Long klassSeminarId) {
        SeminarInfoVO seminarInfoVO=new SeminarInfoVO();
KlassSeminar klassSeminar=klassSeminarDAO.getByKlassSeminarId(klassSeminarId);
        seminarInfoVO.setSeminarId(klassSeminarId);
        //通过seminarid找到seminar然后得到intro
        Seminar seminar=seminarDAO.getBySeminarId(klassSeminar.getSeminarId());
        System.out.println(seminar.getId());
        System.out.println(seminar.getRoundId());
        Round round=roundDAO.getByRoundId(seminar.getRoundId());
        seminarInfoVO.setIntroduction(seminar.getIntroduction());
        seminarInfoVO.setRoundSerial(round.getRoundSerial());
        seminarInfoVO.setSeminarName(seminar.getSeminarName());
        seminarInfoVO.setSeminarSerial(seminar.getSeminarSerial());
seminarInfoVO.setCourseName(courseDAO.getByCourseId(klassDAO.getCourseIdByKlassId(klassSeminar.getKlassId())).getCourseName());
seminarInfoVO.setSeminarStatus(klassSeminar.getStatus());
        seminarInfoVO.setAttendanceListVO(getAttendanceList(klassSeminarId));
        return seminarInfoVO;
    }

    /**
     * 学生讨论课查看成绩
     * @param klassId
     * @param klassSeminarId
     * @param studentId
     * @return
     */
    public SeminarScoreVO getSeminarScore(Long klassId, Long klassSeminarId, Long studentId) {
        SeminarScoreVO seminarScoreVO=new SeminarScoreVO();
        //为了得到seminarid
        KlassSeminar klassSeminar=klassSeminarDAO.getByKlassSeminarId(klassSeminarId);
        //通过klassid找到courseid
        Long courseId=klassDAO.getCourseIdByKlassId(klassId);
        Course course=courseDAO.getByCourseId(courseId);
        //获得班级为了名字
        Klass klass=klassDAO.getByKlassId(klassId);
        //获得attendence
        Attendance attendance=attendanceDAO.getBySeminarIdAndKlassIdAndStudentId(
                klassSeminar.getSeminarId(),klassSeminar.getKlassId(),studentId);
        //获得讨论课
        Seminar seminar=seminarDAO.getBySeminarId(klassSeminar.getSeminarId());
        //获得讨论课分数,为了SimpleSeminarScoreVO
        SeminarScore seminarScore=seminarScoreDAO.getByKlassSeminarIdAndTeamId(
                klassSeminarId,teamDAO.getTeamIdByStudentIdAndKlassId(studentId,klassId));

        seminarScoreVO.setCourseName(course.getCourseName());
        seminarScoreVO.setKlassName(klass.getGrade(),klass.getKlassSerial());
        seminarScoreVO.setTeamOrder(attendance.getTeamOrder());

        SimpleSeminarScoreVO simpleSeminarScoreVO=new SimpleSeminarScoreVO();
        simpleSeminarScoreVO.setSeminarName(seminar.getSeminarName());
        //获得seminar分数
        simpleSeminarScoreVO.setReportScore(seminarScore.getReportScore());
        simpleSeminarScoreVO.setQuestionScore(seminarScore.getQuestionScore());
        simpleSeminarScoreVO.setPresentationScore(seminarScore.getPresentationScore());
       //其他有的没的
        simpleSeminarScoreVO.setSeminarName(seminar.getSeminarName());
        simpleSeminarScoreVO.setIntroduction(seminar.getIntroduction());
        simpleSeminarScoreVO.setSeminarId(seminar.getId());
        simpleSeminarScoreVO.setSeminarSerial(seminar.getSeminarSerial());
        //集合1次
        seminarScoreVO.setSimpleSeminarScoreVO(simpleSeminarScoreVO);
        return seminarScoreVO;
    }

    public Long getPresentatingTeamId(Long klassSeminarId) {
        List<Attendance> attendanceList=attendanceDAO.listByKlassSeminarId(klassSeminarId);
        for(int i=0;i<attendanceList.size();i++)
            if(attendanceList.get(i).getIsPresent().equals(1))
            {
                return attendanceDAO.getTeamIdByAttendanceId(attendanceList.get(i).getId());
            }
        return attendanceList.get(0).getId();
    }

    public SeminarInfoVO getSeminarInfoING(CourseDetailVO course) {
        List<Round> roundList=roundDAO.listByCourseId(course.getId());
        for(int i=0;i<roundList.size();i++) {
            Round round=roundList.get(i);
            List<Seminar> seminars = seminarDAO.listByRoundId(round.getId());
            for(int j=0;j<seminars.size();j++)
            {
                Seminar seminar=seminars.get(i);
                //通过seminarid获取班级讨论课
                List<KlassSeminar> klassSeminars=klassSeminarDAO.listBySeminarId(seminar.getId());
                for(int k=0;k<klassSeminars.size();k++) {
                    {
                        KlassSeminar klassSeminar = klassSeminars.get(i);
                        if (klassSeminar.getStatus().equals(1)) {
                            SeminarInfoVO seminarInfoVO =getSeminarInfo(klassSeminar.getId());
                            return seminarInfoVO;
                        }
                    }
                }
            }
        }
        //什么讨论课都不在进行
        return null;
    }

    public KlassSeminarVO getKlassSeminarVO(long klassId, long seminarId) {
    KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(seminarId,klassId);
    KlassSeminarVO klassSeminarVO=new KlassSeminarVO();
    Seminar seminar=seminarDAO.getBySeminarId(klassSeminar.getSeminarId());
    Round round=roundDAO.getByRoundId(seminar.getRoundId());
    klassSeminarVO.setTopic(seminar.getSeminarName());
    klassSeminarVO.setRoundSerial(round.getRoundSerial());
    klassSeminarVO.setIntro(seminar.getIntroduction());
    klassSeminarVO.setIsVisible(seminar.getIsVisible());
    klassSeminarVO.setId(klassSeminar.getId());
    klassSeminarVO.setReportDDL(klassSeminar.getReportDdl().toString());
    klassSeminarVO.setTeamNumLimit(seminar.getMaxTeam());
    return klassSeminarVO;
    }

    //attendanceId和reportscore
    public void scoreReport(Map<Long,BigDecimal> attendanceId_score_map,SeminarInfoVO seminarInfoVO) {
        for(Long key :attendanceId_score_map.keySet())
        {
    //获得当前team
    Team team=teamDAO.getByAttendanceId(key);
    SeminarScore seminarScore=new SeminarScore();
    seminarScore.setReportScore(attendanceId_score_map.get(key));
    KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(seminarInfoVO.getSeminarId(),seminarInfoVO.getKlassId());
    seminarScoreDAO.updateSeminarScore(seminarScore,klassSeminar.getId(),team.getId());
        }
    }

    public boolean uploadPPT(Long klassSeminarId, MultipartFile file,AttendanceVO attendanceVO) {
        if(file.getContentType().equals("ppt")) {
            attendanceDAO.updatePptByAttendanceId(attendanceVO.getAttendanceId(), file.getName(), file.getResource().toString());
        return true;
        }else
            return false;
    }



    //将展示分数存入数据库
    //presentationscore,questionvo.id,questionscore
    public void scoreSeminar(Map<BigDecimal, Map<Long, BigDecimal>> score, SeminarInfoVO seminarInfoVO) {
        BigDecimal presentationscore;
        Long questionId=new Long(0);
        BigDecimal questionscore=new BigDecimal(0);
        SeminarScore seminarScore=new SeminarScore();
        Long klassId=seminarInfoVO.getKlassId();
        Long seminarId=seminarInfoVO.getSeminarId();
        Long klassSeminarId=klassSeminarDAO.getBySeminarIdAndKlassId(seminarId,klassId).getId();

        for (Map.Entry<BigDecimal, Map<Long, BigDecimal>> entry : score.entrySet()) {
                presentationscore=entry.getKey();
                seminarScore.setPresentationScore(presentationscore);
                for(Map.Entry<Long, BigDecimal> question : entry.getValue().entrySet())
                {
                    //如果分数更高，更新提问分数
                   if(question.getValue().intValue()>questionscore.intValue())
                   {
                       questionscore=question.getValue();
                       questionId=question.getKey();
                   }
                }
                seminarScore.setQuestionScore(questionscore);
                Long teamId=questionDAO.getTeamIdByQuestionId(questionId);
                seminarScoreDAO.updateSeminarScore(seminarScore,klassSeminarId,teamId);
        }
    }
}
