package cm.dao;

import cm.entity.Admin;
import cm.entity.LoginUser;
import cm.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/19
 */
@Component
public class AdminDAO {

    @Autowired
    private AdminMapper adminMapper;

    public Admin getAdminById(String adminId){
        return  adminMapper.getByAdminId(adminId);
    }

    public LoginUser getAdminByAdminAccount(String userAccount){
        LoginUser result=adminMapper.getAdminByAdminAccount(userAccount);
        if(result==null) {
            return null;
        }
        result.setUserRole("admin");
        return result;
    }

}
