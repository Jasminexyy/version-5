package cm.service;

import cm.dao.*;
import cm.entity.*;
import cm.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
		System.out.println("进入listCourseAndKlassByStudentId方法");
        List<Course> courses=courseDAO.listByStudentId(studentId);
        System.out.println(courses.size()+"大小");
        Map<String, KlassVO> map=new HashMap<String, KlassVO>();
        for(int i=0;i<courses.size();i++)
        {
        	System.out.println(courses.get(i).getCourseName());
            Klass k=klassDAO.getByCourseIdAndStudentId(courses.get(i).getId(),studentId);
            map.put(courses.get(i).getCourseName(),KlassService.klassToKlassVO(k));
        }
        return map;
	}

	public CourseDetailVO getCourseById(Long courseId) {
		Course course = courseDAO.getByCourseId(courseId);
		CourseDetailVO courseDetailVO = new CourseDetailVO();
		courseDetailVO.setId(courseId);
		courseDetailVO.setCourseName(course.getCourseName());
		courseDetailVO.setIntroduction(course.getIntroduction());
		courseDetailVO.setPresentationPercentage(course.getPresentationPercentage());
		courseDetailVO.setQuestionPercentage(course.getQuestionPercentage());
		courseDetailVO.setReportPercentage(course.getReportPercentage());
		courseDetailVO.setTeamStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(course.getTeamStartTime()));
		courseDetailVO.setTeamEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(course.getTeamEndTime()));
		return courseDetailVO;
	}

	void panduan(SimpleSeminarScoreVO simpleSeminarScoreVO,SeminarScore seminarScore)
	{
		if(seminarScore.getPresentationScore()==null)
			simpleSeminarScoreVO.setPresentationScore(new BigDecimal(0));
		else
			simpleSeminarScoreVO.setPresentationScore(seminarScore.getPresentationScore());
		if(seminarScore.getQuestionScore().equals(""))
			simpleSeminarScoreVO.setQuestionScore(new BigDecimal(0));
		else
			simpleSeminarScoreVO.setQuestionScore(seminarScore.getQuestionScore());
		if(seminarScore.getReportScore().equals(""))
			simpleSeminarScoreVO.setReportScore(new BigDecimal(0));
		else
			simpleSeminarScoreVO.setReportScore(seminarScore.getReportScore());
	}

	public List<RoundScoreVO> listScoreForStudent(Long klassId, Long studentId) {
		//通过klassId获得courseId
		Long courseId=klassDAO.getCourseIdByKlassId(klassId);
		System.out.println("courseId:"+courseId);
        List<RoundScoreVO> roundScoreVOList=new LinkedList<RoundScoreVO>();
        //获取课程所有讨论课轮次
        List<Round> roundList=roundDAO.listByCourseId(courseId);
        //如果轮次为空，当前课程没有轮次
        if(roundList.size()==0)
        	return null;
        System.out.println("all "+roundList.size()+" round");

		//当前学生小组
		Team team=teamDAO.getByCourseIdAndStudentId(courseId,studentId);
		if(team==null)
			return null;
		System.out.println("当前学生小组"+team.getId()+"组");
		for(int i=0;i<roundList.size();i++)
		{
			RoundScoreVO roundScoreVO=new RoundScoreVO();
			//每一轮的所有讨论课的分数
			List<SimpleSeminarScoreVO> simpleSeminarScoreVOList=new LinkedList<SimpleSeminarScoreVO>();
			//当前round
			Round round=roundList.get(i);
			RoundScore roundScore=roundScoreDAO.getByRoundIdAndTeamId(round.getId(),team.getId());

			//map,key,roundName,设置讨论课轮次
			String roundName=round.getRoundSerial().toString();
			roundScoreVO.setRoundNumber(new Byte(roundName));
			System.out.println(roundName);
			//获得当轮的总分数
            roundScoreVO.setTotalScore(roundScore.getTotalScore());
            //获得当轮的所有讨论课的
			List<Seminar> seminarList=round.getSeminars();
			System.out.println("all seminars:"+seminarList.size());
			//把所有的seminar的分数传到list中
            for(int j=0;j<seminarList.size();j++)
			{
				//通过seminarid和klassid得到当前讨论课的班级
				KlassSeminar klassSeminar=klassSeminarDAO.getBySeminarIdAndKlassId(
						seminarList.get(j).getId(),klassId);
				System.out.println("klassSeminar id:"+klassSeminar.getId());
				SeminarScore seminarScore=seminarScoreDAO.getByKlassSeminarIdAndTeamId(klassSeminar.getId(),team.getId());
				SimpleSeminarScoreVO simpleSeminarScoreVO=new SimpleSeminarScoreVO();

				panduan(simpleSeminarScoreVO,seminarScore);

				simpleSeminarScoreVO.setSeminarName(seminarDAO.getBySeminarId(seminarList.get(j).getId()).getSeminarName());
				simpleSeminarScoreVOList.add(simpleSeminarScoreVO);
			}
			roundScoreVO.setSimpleSeminarScoreVOList(simpleSeminarScoreVOList);
            System.out.println("the "+roundName+" round put");
			roundScoreVOList.add(roundScoreVO);
		}
		for(int i=0;i<roundScoreVOList.size();i++)
			System.out.println(roundScoreVOList.get(i).getRoundNumber());
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

	public Map<String, List<TeacherSeminarScoreVO>> listScoreForTeacher(Long courseId) {
		//获得当前课程下所有小组
		List<Team> teamList=teamDAO.listByCourseId(courseId);
		//获得当前课程所有轮次
		List<Round> roundList=roundDAO.listByCourseId(courseId);
		Map<String, List<TeacherSeminarScoreVO>> map=new HashMap<>();

		//开始循环吧！
		for(int i=0;i<roundList.size();i++)//4
		{
			//每个轮次对应一串TeacherSeminarScoreVO list
			List<TeacherSeminarScoreVO> teacherSeminarScoreVOList=new LinkedList<>();
			//当前轮次
			Round round=roundList.get(i);
			String roundname=round.getRoundSerial().toString();
			//当前轮次下所有讨论课
			List<Seminar> seminarList=seminarDAO.listByRoundId(round.getId());
			for(int j=0;j<teamList.size();j++) {//26
				TeacherSeminarScoreVO teacherSeminarScoreVO=new TeacherSeminarScoreVO();
				//获取每一个课程下的班级
				Team team=teamList.get(j);
				System.out.println(j);
				//通过roungid和teamid得到当前轮次下当前班级的讨论课总成绩
				RoundScore roundScore=roundScoreDAO.getByRoundIdAndTeamId(round.getId(),team.getId());
				//通过小组id得到班级id
				Long klassId=teamDAO.getKlassIdByTeamId(team.getId());
				System.out.println("klassid:"+klassId);
				Klass klass=klassDAO.getByKlassId(klassId);

				//当前轮次总分数get
				teacherSeminarScoreVO.setTotalScore(roundScore.getTotalScore());

				List<SimpleSeminarScoreVO> simpleSeminarScoreVOList=new LinkedList<>();
				for(int k=0;k<seminarList.size();k++)
				{
					Seminar seminar=seminarList.get(k);
					KlassSeminar klassSeminar=
							klassSeminarDAO.getBySeminarIdAndKlassId(seminar.getId(),klassId);
					System.out.println("klassSeminar"+klassSeminar);
					SeminarScore seminarScore=
							seminarScoreDAO.getByKlassSeminarIdAndTeamId(klassSeminar.getId(),team.getId());
					SimpleSeminarScoreVO simpleSeminarScoreVO=new SimpleSeminarScoreVO();
					simpleSeminarScoreVO.setSeminarName(seminar.getSeminarName());
					if(seminarScore==null)
					{
						simpleSeminarScoreVO.setPresentationScore(new BigDecimal(0));
						simpleSeminarScoreVO.setQuestionScore(new BigDecimal(0));
						simpleSeminarScoreVO.setReportScore(new BigDecimal(0));
					}else {
						panduan(simpleSeminarScoreVO, seminarScore);
					}

					simpleSeminarScoreVOList.add(simpleSeminarScoreVO);
				}
				teacherSeminarScoreVO.setSimpleSeminarScoreVO(simpleSeminarScoreVOList);
				teacherSeminarScoreVOList.add(teacherSeminarScoreVO);
			}
			map.put(roundname,teacherSeminarScoreVOList);
			for(int m=0;m<teacherSeminarScoreVOList.size();m++){
				TeacherSeminarScoreVO t=teacherSeminarScoreVOList.get(m);
				List<SimpleSeminarScoreVO>tmp=t.getSimpleSeminarScoreVO();
				for(int a=0;a<tmp.size();a++){
					System.out.println(tmp.get(a).getPresentationScore());
					System.out.println(tmp.get(a).getQuestionScore());
					System.out.println(tmp.get(a).getReportScore());
				}
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

    public TeamNeedVO getTeamStrategyByCourseId(Long courseId){
	    TeamNeedVO teamNeedVO = new TeamNeedVO();
		List<TeamStrategy> teamStrategyList = strategyDAO.listTeamStrategyByCourseId(courseId);
		for(TeamStrategy teamStrategy:teamStrategyList) {

			TeamAndStrategyVO teamAndStrategyVO = new TeamAndStrategyVO();
            Long strategyId = teamStrategy.getStrategyId();
			if(teamStrategy.getStrategyName().equals("TeamAndStrategy")) {
				List<TeamAndStrategy> teamAndStrategyList = strategyDAO.listTeamAndStrategyByStrategyId(strategyId);
				for(TeamAndStrategy teamAndStrategy:teamAndStrategyList) {

                    if(teamAndStrategy.getStrategyName().equals("MemberLimitStrategy")) {

                        MemberLimitStrategyVO memberLimitStrategyVO = new MemberLimitStrategyVO();
                        MemberLimitStrategy memberLimitStrategy = strategyDAO.getMemberLimitStrategyById(strategyId);

                        memberLimitStrategyVO.setMaxMember(memberLimitStrategy.getMaxMember());
                        memberLimitStrategyVO.setMinMember(memberLimitStrategy.getMinMember());

                        teamAndStrategyVO.setMemberLimitStrategyVO(memberLimitStrategyVO);
                    }
                    if(teamAndStrategy.getStrategyName().equals("TeamOrStrategy")) {

                        List<TeamOrStrategyVO> teamOrStrategyVOList = new ArrayList<>();
                        List<TeamOrStrategy> teamOrStrategyList = strategyDAO.listTeamOrStrategyByStrategyId(strategyId);
                        for(TeamOrStrategy teamOrStrategy:teamOrStrategyList) {

                            TeamOrStrategyVO teamOrStrategyVO = new TeamOrStrategyVO();
                            List<CourseMemberLimitStrategyVO> courseMemberLimitStrategyVOList = new ArrayList<>();
                            if(teamOrStrategy.getStrategyName().equals("CourseMemberLimitStrategy")) {

                                CourseMemberLimitStrategyVO courseMemberLimitStrategyVO = new CourseMemberLimitStrategyVO();
                                CourseMemberLimitStrategy courseMemberLimitStrategy = strategyDAO.getCourseMemberLimitStrategyById(strategyId);

                                courseMemberLimitStrategyVO.setCourseId(courseMemberLimitStrategy.getCourseId());
								courseMemberLimitStrategyVO.setCourseName(courseDAO.getByCourseId(courseMemberLimitStrategy.getCourseId()).getCourseName());
                                courseMemberLimitStrategyVO.setMaxMember(courseMemberLimitStrategy.getMaxMember());
                                courseMemberLimitStrategyVO.setMinMember(courseMemberLimitStrategy.getMinMember());

                                courseMemberLimitStrategyVOList.add(courseMemberLimitStrategyVO);
                            }

                            teamOrStrategyVO.setCourseMemberLimitStrategyVOList(courseMemberLimitStrategyVOList);
                            teamOrStrategyVOList.add(teamOrStrategyVO);
                        }
                        teamAndStrategyVO.setTeamOrStrategyVOList(teamOrStrategyVOList);
                    }

                }
                teamNeedVO.setTeamAndStrategyVO(teamAndStrategyVO);
			}
			if(teamStrategy.getStrategyName().equals("ConflictCourseStrategy")){

                List<ConflictCourseStrategyVO> conflictCourseStrategyVOList = new ArrayList<>();
                List<ConflictCourseStrategy> conflictCourseStrategyList = strategyDAO.listConflictCourseStrategyByStrategyId(strategyId);
                for(ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {

                    ConflictCourseStrategyVO conflictCourseStrategyVO = new ConflictCourseStrategyVO();

                    conflictCourseStrategyVO.setCourseId(conflictCourseStrategy.getCourseId());
                    //课程名
                    conflictCourseStrategyVO.setCourseName(courseDAO.getByCourseId(conflictCourseStrategy.getCourseId()).getCourseName());
                    conflictCourseStrategyVOList.add(conflictCourseStrategyVO);
                }
                teamNeedVO.setConflictCourseStrategyVOList(conflictCourseStrategyVOList);
			}
		}
		return teamNeedVO;
	}


	int conflictCourseStrategyCount = 0;
	//在TeamStrategy里新建ConflictCourseStrategy
	int createConflictCourseStrategyOfTeamStrategy(Long courseId){
		List<TeamStrategy> teamStrategyList = strategyDAO.listTeamStrategyByCourseId(courseId);
		List<TeamStrategy> allTeamStrategyList = strategyDAO.listAllTeamStrategy();
		Byte strategySerial = (byte)(teamStrategyList.size()+1);
		conflictCourseStrategyCount = 0;
		for(TeamStrategy teamStrategy:allTeamStrategyList)
			if(teamStrategy.getStrategyName().equals("ConflictCourseStrategy"))
				conflictCourseStrategyCount+=1;
		Integer count = conflictCourseStrategyCount+1;
		Long strategyId = count.longValue();
		return strategyDAO.createTeamStrategy(courseId, strategySerial, "ConflictCourseStrategy", strategyId);
	}


	//在ConflictCourseStrategy里新建项
	int createConflictCourseStrategy(Long strategyId, Long conflictCourseId){
		return strategyDAO.createConflictCourseStrategy(strategyId, conflictCourseId);
	}

	//在ConflictCourseStrategy里修改项
	int updateConflictCourseStrategy(Long strategyId, Long conflictCourseId, Long newConflictCourseId){
		return strategyDAO.updateConflictCourseStrategy(strategyId, conflictCourseId, newConflictCourseId);

	}

	//在ConflictCourseStrategy里删除项
//	int deleteConflictCourseStrategy(Long strategyId){
//		return strategyDAO.deleteConflictCourseStrategy(strategyId);
//	}

	int teamAndStrategyCount = 0;
	//在TeamStrategy里新建TeamAndStrategy
	int createTeamAndStrategyOfTeamStrategy(Long courseId){
		List<TeamStrategy> teamStrategyList = strategyDAO.listTeamStrategyByCourseId(courseId);
		List<TeamStrategy> allTeamStrategyList = strategyDAO.listAllTeamStrategy();
		Byte strategySerial = (byte)(teamStrategyList.size()+1);
		teamAndStrategyCount = 0;
		for(TeamStrategy teamStrategy:allTeamStrategyList)
			if(teamStrategy.getStrategyName().equals("TeamAndStrategy"))
				teamAndStrategyCount+=1;
		Integer count = teamAndStrategyCount+1;
		Long strategyId = count.longValue();
		return strategyDAO.createTeamStrategy(courseId, strategySerial, "TeamAndStrategy", strategyId);
	}

	int memberLimitStrategyCount = 0;
	//在TeamAndStrategy里新建MemberLimitStrategy
	int createMemberLimitStrategyOfTeamAndStrategy(Long teamStrategyId){
		List<TeamAndStrategy> allTeamAndStrategyList = strategyDAO.listAllTeamAndStrategy();
		memberLimitStrategyCount = 0;
		for(TeamAndStrategy teamAndStrategy:allTeamAndStrategyList)
			if(teamAndStrategy.getStrategyName().equals("MemberLimitStrategy"))
				memberLimitStrategyCount+=1;
		Integer count = memberLimitStrategyCount+1;
		Long strategyId = count.longValue();
		return strategyDAO.createTeamAndStrategy(teamStrategyId, "MemberLimitStrategy", strategyId);
	}

	//在MemberLimitStrategy里新建项
	int createMemberLimitStrategy(Byte minMember, Byte maxMember){
		return strategyDAO.createMemberLimitStrategy(minMember, maxMember);
	}

	//在MemberLimitStrategy里修改项
	int updateMemberLimitStrategy(Long strategyId, Byte minMember, Byte maxMember){
		return strategyDAO.updateMemberLimitStrategy(strategyId, minMember, maxMember);
	}

	//在MemberLimitStrategy里删除项
//	int deleteMemberLimitStrategy(Long strategyId){
//		return strategyDAO.deleteMemberLimitStrategy(strategyId);
//	}

	int teamOrStrategyCount = 0;
	//在TeamAndStrategy里新建TeamOrStrategy
	int createTeamOrStrategyOfTeamAndStrategy(Long teamStrategyId){
		List<TeamAndStrategy> allTeamAndStrategyList = strategyDAO.listAllTeamAndStrategy();
		teamOrStrategyCount = 0;
		for(TeamAndStrategy teamAndStrategy:allTeamAndStrategyList)
			if(teamAndStrategy.getStrategyName().equals("TeamOrStrategy"))
				teamOrStrategyCount+=1;
		Integer count = teamOrStrategyCount+1;
		Long strategyId = count.longValue();
		return strategyDAO.createTeamAndStrategy(teamStrategyId, "TeamOrStrategy", strategyId);
	}


	//在TeamOrStrategy里新建CourseMemberLimitStrategy
	int createCourseMemberLimitStrategyOfTeamOrStrategy(Long teamAndstrategyId){
		List<TeamOrStrategy> teamOrStrategyList = strategyDAO.listAllTeamOrStrategy();
		Integer count = teamOrStrategyList.size()+1;
		Long strategyId = count.longValue();
		return strategyDAO.createTeamOrStrategy(teamAndstrategyId, "CourseMemberLimitStrategy", strategyId);
	}

	//在CourseMemberLimitStrategy里新建项
	int createCourseMemberLimitStrategy(Long courseId, Byte minMember, Byte maxMember){
		return strategyDAO.createCourseMemberLimitStrategy(courseId, minMember, maxMember);
	}
	//在CourseMemberLimitStrategy里修改项
	int updateCourseMemberLimitStrategy(Long courseId, Byte minMember, Byte maxMember){
		return strategyDAO.updateCourseMemberLimitStrategy(courseId, minMember, maxMember);
	}

	//在CourseMemberLimitStrategy里删除项
//	int deleteCourseMemberLimitStrategy(Long courseId, Byte minMember, Byte maxMember){
//		return strategyDAO.deleteCourseMemberLimitStrategy(courseId, minMember, maxMember);
//	}
}
