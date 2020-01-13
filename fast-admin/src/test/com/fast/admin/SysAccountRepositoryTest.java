package com.fast.admin;


import cn.fast.admin.entity.SysRolesDept;
import cn.fast.admin.mapper.ISysRolesDeptMapper;
import cn.fast.admin.service.ISysRolesDeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * jpa 使用测试
 * https://blog.csdn.net/long476964/article/details/79677526
 * @author houqj
 * @date 2020-01-06 9:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysAccountRepositoryTest.class)
public class SysAccountRepositoryTest {


//    @Test
//    public void test1(){
//        SysUsers sysUsers = new SysUsers();
//        sysUsers.setAge(3)
//                .setGender("1")
//                .setUserName("张三");
//        sysUsersRepository.save(sysUsers);
//    }
//
//    @Test
//    public void test2(){
//        SysUsers sysUsers = new SysUsers();
//        sysUsers.setUserName("张三");
//        System.out.println(sysUsers.getId()+sysUsers.getIsDel());
//        Example<SysUsers> example= Example.of(sysUsers);
//        Optional<SysUsers> one = sysUsersRepository.findOne(example);
//        if(one.isPresent()){
//            System.out.println(one.get());
//        }else{
//            System.out.println("查询结果为空");
//        }
//    }
//
//    @Test
//    public void exampleMather(){
//
//        SysUsers sysUsers = new SysUsers();
//        sysUsers.setUserName("张三");
//
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("user_name", ExampleMatcher.GenericPropertyMatchers.startsWith())//模糊查询匹配开头，即{username}%
//                .withMatcher("gender" ,ExampleMatcher.GenericPropertyMatchers.contains())//全部模糊查询，即%{address}%
//                .withIgnorePaths("password");//忽略字段，即不管password是什么值都不加入查询条件
//        Example<SysUsers> example = Example.of(sysUsers ,matcher);
//        List<SysUsers> list = sysUsersRepository.findAll(example);
//        System.out.println(list);
//    }
}