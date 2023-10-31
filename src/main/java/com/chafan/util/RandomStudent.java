package com.chafan.util;

import com.chafan.entity.Course;
import com.chafan.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: 茶凡
 * @ClassName RandomStudent
 * @date 2023/10/31 10:04
 * @Description 随机生成 学生文档数据
 */
@Component
public class RandomStudent {


    /**
     * 计算机课程
     */
    private static final String[] computerCourses = {
            "计算机编程基础",
            "数据结构与算法",
            "计算机网络原理",
            "数据库系统",
            "操作系统原理",
            "软件工程导论",
            "人工智能基础",
            "机器学习与数据挖掘",
            "Web开发技术",
            "分布式系统设计",
            "网络安全与加密",
            "云计算与大数据",
            "计算机图形学",
            "计算机体系结构",
            "嵌入式系统设计",
            "经济学原理",
            "心理学导论",
            "西方文学史",
            "政治学概论",
            "生物化学基础",
            "艺术史与鉴赏",
            "社会学理论",
            "地球科学导论",
            "法律基础",
            "历史文化考古",
            "哲学思想史",
            "传媒与传播学",
            "医学伦理学",
            "语言学概论",
            "数学分析基础",
            "计算理论与自动机"
    };
    /**
     * 成绩等级
     */
    private static final String[] gradeLevels = {"A", "B", "C", "D", "E"};

    /**
     * 姓
     */
    private static final String[] firstNames = {
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨",
            "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
            "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "鲁",
            "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费",
            "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
            "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "欧阳", "慕容"
    };

    /**
     * 名
     */
    private static final String[] secondNames = {
            "伟", "强", "磊", "亮", "勇", "军", "杰", "涛", "明", "超", "霞", "红", "芳", "辉", "静", "秀",
            "洋", "丽", "燕", "欣", "敏", "涵", "倩", "婷", "娟", "建华", "建明", "建国", "小平", "国强", "子明",
            "子轩", "宇轩", "佳琪", "欣怡", "欣妍", "璇玑", "正豪", "文杰", "文轩", "鑫鑫", "欢欢", "美美", "梦婷",
            "璐璐", "兰兰", "嘉茂", "兴国", "世平", "荣国", "星辰", "星翰", "洪涛", "洪伟", "锦涛", "健", "婧"
    };

    /**
     * 随机生成姓名
     * @return
     */
    public static String generateName() {
        Random random = new Random();
        return firstNames[random.nextInt(firstNames.length)] + secondNames[random.nextInt(secondNames.length)];
    }

    /**
     * 性别随机
     * @return
     */
    public static String RandomGender() {
        Random random = new Random();
        int randomNumber = random.nextInt(2); // 生成 0 或 1
        return randomNumber == 0 ? "男":"女";
    }

    /**
     * 年龄随机
     * @param minAge
     * @param maxAge
     * @return
     */
    public static int generateAge(int minAge, int maxAge) {
        Random random = new Random();
        return random.nextInt(maxAge - minAge + 1) + minAge;
    }

    /**
     * 随机生成电话号码
     * @return
     */
    public static String generatePhoneNumber() {
        Random random = new Random();

        // 生成手机号段 (11位数)
        String[] mobileSegments = {"13", "14", "15", "16", "17", "18", "19"};
        String segment = mobileSegments[random.nextInt(mobileSegments.length)];

        // 生成剩下的 9 位数字
        StringBuilder sb = new StringBuilder(segment);
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10)); // 0 - 9 的随机数
        }
        return sb.toString();
    }


    /**
     * 随机生成学号
     * @return
     */
    public static String generateStudentID() {
        Random random = new Random();

        StringBuilder studentID = new StringBuilder("33"); // 前两位是固定的

        // 生成接下来的 11 位数字
        for (int i = 0; i < 11; i++) {
            studentID.append(random.nextInt(10)); // 0 - 9 的随机数
        }

        return studentID.toString();
    }

    /**
     * 随机生成学生信息
     * @return
     */
    public static Student generateStudent(){

        Random random = new Random();
        Student student = new Student();
        student.setName(generateName());
        student.setAge(generateAge(18, 30));
        student.setGender(RandomGender());
        student.setStudent_id(generateStudentID());
        student.setPhone_number(generatePhoneNumber());

        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            Course course = new Course();
            course.setSubject(computerCourses[random.nextInt(computerCourses.length-1)]);
            course.setScore(gradeLevels[random.nextInt(gradeLevels.length-1)]);
            courseList.add(course);
        }
        student.setGrades(courseList);
        return student;
    }
}
