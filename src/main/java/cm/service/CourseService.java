package cm.service;

import cm.dao.CourseDao;
import cm.entity.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	private CourseDao courseDao;

	public Course findCourseById(long id){
		return courseDao.selectCourseById(id);
	}
}
