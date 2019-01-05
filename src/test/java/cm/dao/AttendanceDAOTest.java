package cm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)

public class AttendanceDAOTest {
    @Autowired
    private AttendanceDAO attendanceDAO;

    @Test
    public void getByKlassSeminarIdAndTeamId() {
        System.out.println(attendanceDAO.getByKlassSeminarIdAndTeamId(21L,18L));
    }
}