package cm.dao;

import cm.entity.ShareSeminarApplication;
import cm.mapper.ShareSeminarApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/26
 */
@Component
public class ShareSeminarDAO {
    @Autowired
    private ShareSeminarApplicationMapper shareSeminarApplicationMapper;

    public List<ShareSeminarApplication> listByMainCourseId(Long mainCourseId){
        return shareSeminarApplicationMapper.listByMainCourseId(mainCourseId);
    }

    public List<ShareSeminarApplication> listBySubCourseTeacherId(Long subCourseTeacherId){
        return shareSeminarApplicationMapper.listBySubCourseTeacherId(subCourseTeacherId);
    }
}
