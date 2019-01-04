package cm.service;

import cm.dao.*;
import cm.entity.*;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseService {

	@Autowired
	private CourseDAO courseDAO;
	@Autowired
	private KlassDAO klassDAO;
	@Autowired
	private TeamDAO teamDAO;
	@Autowired
	private RoundScoreDAO roundScoreDAO;
	@Autowired
	private SeminarScoreDAO seminarScoreDAO;
	@Autowired
	private SeminarDAO seminarDAO;
	@Autowired
	private RoundDAO roundDAO;
	@Autowired
	private KlassSeminarDAO klassSeminarDAO;

	@Autowired
    TeacherService teacherService;
	@Autowired
	private ShareTeamDAO shareTeamDAO;
	@Autowired
	private TeacherDAO teacherDAO;

    @Autowired
    private StrategyDAO strategyDAO;

	public List<Course> findCoursesByTeacherId(Long teacherId){
		return courseDAO.listByTeacherId(teacherId);
	}

	public Course findCourseById(Long courseId){
		return courseDAO.getByCourseId(courseId);
	}

	public List<Course> findCoursesByTeacher(Teacher teacher){
		return courseDAO.listByTeacherId(teacher.getId());
	}

	public boolean addCourse(CourseDetailVO course, UserVO teacher){
		Course course1=new Course();
		course1.setCourseName(course.getCourseName());
		course1.setId(course.getId());
		course1.setIntroduction(course.getIntroduction());
		course1.setKlasses(klassDAO.getByCourseId(course.getId()));
		course1.setPresentationPercentage(course.getPresentationPercentage());
		course1.setQuestionPercentage(course.getQuestionPercentage());
		course1.setReportPercentage(course.getReportPercentage());
		course1.setRounds(roundDAO.listByCourseId(course.getId()));
		course1.setTeamEndTime(Timestamp.valueOf(course.getTeamEndTime()));
		course1.setTeamStartTime(Timestamp.valueOf(course.getTeamStartTime()));
		course1.setTeacherId(teacher.getId());
		//默认为主课程
		course1.setTeamMainCourseId(course.getId());
		courseDAO.createCourse(teacher.getId(),course1);
		return true;
	}

	public boolean deleteCourseById(Long courseId){
		courseDAO.deleteByCourseId(courseId);
		return true;
	}

	public Map<RoundScore,List<SeminarScore>> findScoreForStudent(Long courseId, Long klassId, Long studentId){
		Team team=teamDAO.getByCourseIdAndStudentId(courseId,studentId);
//		该学生该课程下（一个队）的所有讨论课成绩
		List<Round> rounds=roundDAO.listByCourseId(courseId);
		//List<RoundScore> roundScores = new LinkedList<RoundScore>();
		List<KlassSeminar> klassSeminars=klassSeminarDAO.listByKlassId(klassId);
		Map<RoundScore,List<SeminarScore>> maps=new HashMap<RoundScore,List<SeminarScore>>();
		//获得所有轮次的成绩
		for(int i=0;i<rounds.size();i++)
		{
			List<SeminarScore> seminarScores=new LinkedList<SeminarScore>();
			RoundScore roundScore=roundScoreDAO.getByRoundIdAndTeamId(rounds.get(i).getId(),team.getId());
			for(int j=0;j<klassSeminars.size();j++)
			{
				KlassSeminar klassSeminar=klassSeminars.get(j);
				//如果读取到的seminar的roundid等于当前round的roundid
				if(seminarDAO.getBySeminarId(klassSeminar.getSeminarId()).getRoundId()==rounds.get(i).getId()) {
					seminarScores.add(
							seminarScoreDAO.getByKlassSeminarIdAndTeamId(klassSeminar.getSeminarId(),team.getId())
					);
				}
			}
			maps.put(roundScore,seminarScores);
			//roundScores.add(roundScore);
		}
		return maps;
	}

	//courseName
	public Map<String, KlassVO> listCourseAndKlassByStudentId(Long studentId)
	{
        List<Course> courses=courseDAO.listByStudentId(studentId);
        Map<String, KlassVO> map=new HashMap<String, KlassVO>();
        for(int i=0;i<courses.size();i++)
        {
            Klass k=klassDAO.getByCourseIdAndStudentId(courses.get(i).getId(),studentId);
            map.put(courses.get(i).getCourseName(),KlassService.klassToKlassVO(k));
        }
        return map;
	}

	public CourseDetailVO getCourseById(Long courseId) {
		Course course = courseDAO.getByCourseId(courseId);
		CourseDetailVO courseDetailVO = new CourseDetailVO();
		courseDetailVO.setCourseName(course.getCourseName());
		courseDetailVO.setIntroduction(course.getIntroduction());
		courseDetailVO.setPresentationPercentage(course.getPresentationPercentage());
		courseDetailVO.setQuestionPercentage(course.getQuestionPercentage());
		courseDetailVO.setReportPercentage(course.getReportPercentage());
		courseDetailVO.setTeamStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(course.getTeamStartTime()));
		courseDetailVO.setTeamEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(course.getTeamEndTime()));
		return courseDetailVO;
	}

	//string是roundname，传给前端
	public List<RoundScoreVO> listScoreForStudent(Long courseId, Long klassId, Long studentId) {
        List<RoundScoreVO> roundScoreVOList=new LinkedList<RoundScoreVO>();
        //获取课程所有讨论课轮次
        List<Round> roundList=roundDAO.listByCourseId(courseId);
		RoundScoreVO roundScoreVO=new RoundScoreVO();
		//当前学生小组
		Team team=teamDAO.getByCourseIdAndStudentId(courseId,studentId);
		for(int i=0;i<roundList.size();i++)
		{
			//每一轮的所有讨论课的分数
			List<SimpleSeminarScoreVO> simpleSeminarScoreVOList=new LinkedList<SimpleSeminarScoreVO>();
			//当前round
			Round round=roundList.get(i);
			RoundScore roundScore=roundScoreDAO.getByRoundIdAndTeamId(round.getId(),team.getId());

			//map,key,roundName,设置讨论课轮次
			String roundName=round.getRoundSerial().toString();
			roundScoreVO.setRoundNumber(new Byte(roundName));
			//获得当轮的总分数
            roundScoreVO.setTotalScore(roundScore.getTotalScore());
            //获得当轮的所有讨论课的
			List<Seminar> seminarList=round.getSeminars();
			//把所有的seminar的分数传到list中
            for(int j=0;j<seminarList.size();j++)
			{
				//通过seminarid和klassid得到当前讨论课的班级
				KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(
						seminarList.get(i).getId(),klassId);
				SeminarScore seminarScore=seminarScoreDAO.getByKlassSeminarIdAndTeamId(klassSeminar.getId(),team.getId());
				SimpleSeminarScoreVO simpleSeminarScoreVO=new SimpleSeminarScoreVO();
				simpleSeminarScoreVO.setReportScore(seminarScore.getReportScore());
				simpleSeminarScoreVO.setQuestionScore(seminarScore.getQuestionScore());
				simpleSeminarScoreVO.setPresentationScore(seminarScore.getPresentationScore());
				simpleSeminarScoreVOList.add(simpleSeminarScoreVO);
			}
			roundScoreVO.setSimpleSeminarScoreVOList(simpleSeminarScoreVOList);
			roundScoreVOList.add(roundScoreVO);
		}
		return roundScoreVOList;
	}

    public List<Course> findCoursesByStudentId(Long studentId)
    {
        return courseDAO.listByStudentId(studentId);
    }


	public List<Course> listCourseByTeacherId(UserVO teacher) {
		List<Course> courses=courseDAO.listByTeacherId(teacher.getId());
		return courses;
	}

	public Map<String, List<SeminarScoreVO>> listScoreForTeacher(Long courseId) {
		//获取该课程下所有轮次
		//轮次-班级，总分-所有讨论课，分分
		List<Round> rounds=roundDAO.listByCourseId(courseId);
		Map<String, List<SeminarScoreVO>> map=new HashMap<String, List<SeminarScoreVO>> ();
		for(int i=0;i<rounds.size();i++)
		{
			Round round=rounds.get(i);
			String roundname=round.getRoundSerial().toString();

			//该课程下所有队伍
			List<Team> teams=teamDAO.listByCourseId(courseId);
			for(int j=0;j<teams.size();j++)
			{
				//获取当前课程所有班级
				Team team=teams.get(j);
				//根据轮次获得所有讨论课
				List<Seminar> seminars=seminarDAO.listByRoundId(round.getId());
				Klass klass=klassDAO.getByKlassId(team.getKlassId());
				List<SeminarScoreVO> seminarScoreVOS=new LinkedList<SeminarScoreVO>();
				for(int k=0;k<seminars.size();k++) {
					//依次获得讨论课下小组的成绩
					//获得该小组班级
					//很多重复的，不过都加一加吧
					SeminarScoreVO seminarScoreVO=new SeminarScoreVO();
					//1-1班级-队伍序号
					seminarScoreVO.setKlassName(klass.getKlassSerial().toString());
					Seminar seminar=seminars.get(k);
					SimpleSeminarScoreVO simpleSeminarScoreVO=new SimpleSeminarScoreVO();
					simpleSeminarScoreVO.setSeminarId(seminar.getId());
					simpleSeminarScoreVO.setSeminarSerial(seminar.getSeminarSerial());
					simpleSeminarScoreVO.setIntroduction(seminar.getIntroduction());
					simpleSeminarScoreVO.setSeminarName(seminar.getSeminarName());
					//这四个才是必不可少的
					SeminarScore seminarScore=seminarScoreDAO.getBySeminarIdAndKlassIdAndTeamId(seminar.getId(),klass.getId(),team.getId());
					simpleSeminarScoreVO.setPresentationScore(seminarScore.getPresentationScore());
					simpleSeminarScoreVO.setQuestionScore(seminarScore.getQuestionScore());
					simpleSeminarScoreVO.setReportScore(seminarScore.getPresentationScore());
					simpleSeminarScoreVO.setTotalScore(seminarScore.getTotalScore());
					seminarScoreVO.setSimpleSeminarScoreVO(simpleSeminarScoreVO);
					seminarScoreVOS.add(seminarScoreVO);
				}
				map.put(roundname,seminarScoreVOS);
			}

		}
		return map;
	}

	public CourseDetailVO getCourseByKlassId(Long klassId) {
		Long courseId=klassDAO.getCourseIdByKlassId(klassId);
		return getCourseById(courseId);
	}

	public CourseVO courseToCourseVO(Course course)
	{
		CourseVO courseVO=new CourseVO();
		courseVO.setName(course.getCourseName());
		courseVO.setId(course.getId());
		courseVO.setTeacherName(teacherDAO.getByCourseId(course.getId()).getTeacherName());
		return courseVO;
	}

	public List<CourseVO> listAllCourse() {
		List<Course> courseList=courseDAO.listAllCourse();
		List<CourseVO> courseVOList=new LinkedList<CourseVO>();
		for(int i=0;i<courseList.size();i++)
		{
			CourseVO courseVO=courseToCourseVO(courseList.get(i));
			courseVOList.add(courseVO);
		}
		return courseVOList;
	}

	public void createShare(Long shareCourseId,Long courseId) {
		Long subteacherId=teacherDAO.getByCourseId(shareCourseId).getId();
		shareTeamDAO.createShareTeamApplication(courseId,shareCourseId,subteacherId,(byte)0x0000001);

	}

//	public void updateCourse(CourseVO course) {
//		Course course1=new Course();
//
//	}

//    public TeamNeedVO getTeamStrategyByCourseId(Long courseId){
//	    TeamNeedVO teamNeedVO = new TeamNeedVO();
//		List<TeamStrategy> teamStrategyList = strategyDAO.listTeamStrategyByCourseId(courseId);
//		for(TeamStrategy teamStrategy:teamStrategyList) {
//
//            Long strategyId = teamStrategy.getStrategyId();
//			if(teamStrategy.getStrategyName().equals("TeamAndStrategy")) {
//
//                List<TeamAndStrategyVO> teamAndStrategyVOList = new ArrayList<>();
//				List<TeamAndStrategy> teamAndStrategyList = strategyDAO.listTeamAndStrategyByStrategyId(strategyId);
//				for(TeamAndStrategy teamAndStrategy:teamAndStrategyList) {
//
//                    TeamAndStrategyVO teamAndStrategyVO = new TeamAndStrategyVO();
//                    if(teamAndStrategy.getStrategyName().equals("MemberLimitStrategy")) {
//
//                        MemberLimitStrategyVO memberLimitStrategyVO = new MemberLimitStrategyVO();
//                        MemberLimitStrategy memberLimitStrategy = strategyDAO.getMemberLimitStrategyById(strategyId);
//
//                        memberLimitStrategyVO.setMaxMember(memberLimitStrategy.getMaxMember());
//                        memberLimitStrategyVO.setMinMember(memberLimitStrategy.getMinMember());
//
//                        teamAndStrategyVO.setMemberLimitStrategyVO(memberLimitStrategyVO);
//                    }
//                    if(teamAndStrategy.getStrategyName().equals("TeamOrStrategy")) {
//
//                        List<TeamOrStrategyVO> teamOrStrategyVOList = new ArrayList<>();
//                        List<TeamOrStrategy> teamOrStrategyList = strategyDAO.listTeamOrStrategyByStrategyId(strategyId);
//                        for(TeamOrStrategy teamOrStrategy:teamOrStrategyList) {
//
//                            TeamOrStrategyVO teamOrStrategyVO = new TeamOrStrategyVO();
//                            List<CourseMemberLimitStrategyVO> courseMemberLimitStrategyVOList = new ArrayList<>();
//                            if(teamOrStrategy.getStrategyName().equals("CourseMemberLimitStrategy")) {
//
//                                CourseMemberLimitStrategyVO courseMemberLimitStrategyVO = new CourseMemberLimitStrategyVO();
//                                CourseMemberLimitStrategy courseMemberLimitStrategy = strategyDAO.getCourseMemberLimitStrategyById(strategyId);
//
//                                courseMemberLimitStrategyVO.setCourseId(courseMemberLimitStrategy.getCourseId());
//                                courseMemberLimitStrategyVO.setMaxMember(courseMemberLimitStrategy.getMaxMember());
//                                courseMemberLimitStrategyVO.setMinMember(courseMemberLimitStrategy.getMinMember());
//
//                                courseMemberLimitStrategyVOList.add(courseMemberLimitStrategyVO);
//                            }
//
//                            teamOrStrategyVO.setCourseMemberLimitStrategyVOList(courseMemberLimitStrategyVOList);
//                            teamOrStrategyVOList.add(teamOrStrategyVO);
//                        }
//                        teamAndStrategyVO.setTeamOrStrategyVOList(teamOrStrategyVOList);
//                    }
//
//                    teamAndStrategyVOList.add(teamAndStrategyVO);
//                }
//                teamNeedVO.setTeamAndStrategyVOList(teamAndStrategyVOList);
//			}
//			if(teamStrategy.getStrategyName().equals("ConflictCourseStrategy")){
//
//                List<ConflictCourseStrategyVO> conflictCourseStrategyVOList = new ArrayList<>();
//                List<ConflictCourseStrategy> conflictCourseStrategyList = strategyDAO.listConflictCourseStrategyByStrategyId(strategyId);
//                for(ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
//
//                    ConflictCourseStrategyVO conflictCourseStrategyVO = new ConflictCourseStrategyVO();
//
//                    conflictCourseStrategyVO.setCourseId(conflictCourseStrategy.getCourseId());
//                    //课程名
//                    conflictCourseStrategyVO.setCourseName(courseDAO.getByCourseId(conflictCourseStrategy.getCourseId()).getCourseName());
//                    conflictCourseStrategyVOList.add(conflictCourseStrategyVO);
//                }
//                teamNeedVO.setConflictCourseStrategyVOList(conflictCourseStrategyVOList);
//			}
//		}
//		return teamNeedVO;
//	}
}
