package com.my.blog.website;

import com.my.blog.website.modal.Vo.UserVo;
import com.my.blog.website.utils.TaleUtils;

/**
 * ************************************
 *
 * @ClassName: Pwdtest
 * @Auther: dangjinhu
 * @Date: 2018/4/2
 * @Description: 接口测试方法
 * @Copyright: All rights reserver.
 * ************************************
 */
public class Pwdtest {
    public static void main(String args[]){
        UserVo user = new UserVo();
        user.setUsername("111");
        user.setPassword("111");
        String encodePwd = TaleUtils.MD5encode(user.getUsername() + user.getPassword());
        System.out.println(encodePwd);
    }
}
