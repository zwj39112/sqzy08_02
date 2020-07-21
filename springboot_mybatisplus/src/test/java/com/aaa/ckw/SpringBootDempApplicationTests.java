package com.aaa.ckw;

import com.aaa.ckw.entity.User;
import com.aaa.ckw.entity.UserVo;
import com.aaa.ckw.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringBootDempApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> users = userService.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    void contextLoads2() {
        User user = new User();
       for(int i=6;i<100;i++){
           user.setDeptId(i);
           user.setLoginName("kk"+i);
           user.setUserName("张安"+i);
           userService.insert(user);
       }
    }


    @Test
    void contextLoads3() {

    }
}
