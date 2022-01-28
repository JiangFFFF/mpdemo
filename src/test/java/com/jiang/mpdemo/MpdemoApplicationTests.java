package com.jiang.mpdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiang.mpdemo.entity.User;
import com.jiang.mpdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MpdemoApplicationTests {
    @Resource
    private UserMapper userMapper;

    //查询user表中的所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        for(User u:users){
            System.out.println(u);
        }
    }

    //添加测试
    @Test
    public void addUser(){ //mp会自动生成id值
        User user = new User(null, "岳不群",30,"hanmeimei@qq.com",
                null,null,null,null);
        int insert = userMapper.insert(user);
        System.out.println("添加成功："+insert);
    }

    //修改操作
    @Test
    public void updateUser(){
        User user = new User(1487004489493901313L,null,40,null,
                null,null,null,null);
        int row = userMapper.updateById(user);
        System.out.println("影响行数："+row);

    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker(){
        //根据id查询数据
        User user = userMapper.selectById(1487084602244767746L);
        //进行修改
        user.setAge(20);
        userMapper.updateById(user);
    }

    //多个id的批量查询
    @Test
    public void testSelectDemo1(){
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<User> user = userMapper.selectBatchIds(list);
//        for(User l:user){
//            System.out.println(l);
//        }
        user.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPage(){
        //1、创建Page对象
        //传入两个参数：当前页 和 每页显示记录数
        Page<User> page=new Page<>(1,3);
        //调用mp分页查询的方法
        //调用mp分页查询过程中，底层封装把分页所有数据封装到page对象中
        userMapper.selectPage(page, null);

        //通过page对象获取分页数据
        System.out.println(page.getCurrent()); //当前页
        System.out.println(page.getRecords()); //每页数据list集合
        System.out.println(page.getSize()); //每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages()); //总页数

        System.out.println(page.hasNext()); //是否有有下一页
        System.out.println(page.hasPrevious()); //是否有上一页
    }

    //测试物理删除
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(1487081354674864129L);
        System.out.println("影响行数："+i);
    }

    //批量删除
    @Test
    public void deleteBatch(){
//        List<Integer> integers = Arrays.asList(112, 32);
        int i = userMapper.deleteBatchIds(Arrays.asList(1487004086073131009L, 1487004489493901313L));
        System.out.println("影响行数:"+i);
    }

    //测试复杂查询操作
    @Test
    public void testSelectQuery(){
        //创建QueryWrapper对象
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge；大于等于 gt：大于 le；小于等于 lt：小于
        //查询age>=30的记录
//        wrapper.ge("age", 30); //第一个参数为数据库中字段名称，第二个参数设置值

        //eq等于 ；ne不等于
//        wrapper.ne("name","lucy");

        //between
        //查询年龄在20到30之间到记录
//        wrapper.between("age",20,30);

        //like 模糊查询
//        wrapper.like("name","岳");

        //orderByDesc 排序查询
//        wrapper.orderByDesc("age");

        //last (用于最后拼接)
//        wrapper.last("limit 1");

        //指定要查询的列
        wrapper.select("id","name");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);


    }


}
