package cm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeminarScoreDAOTest {
    @Autowired
    SeminarScoreDAO seminarScoreDAO;

    @Test
    public void getByKlassSeminarIdAndTeamId() {
        System.out.println(seminarScoreDAO.getByKlassSeminarIdAndTeamId(9L,21L));
    }
}