package com.chafan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chafan.entity.Student;
import com.chafan.entity.Student1;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: 茶凡
 * @ClassName StudentMapper
 * @date 2023/11/9 21:58
 * @Description TODO
 */

@Mapper
public interface StudentMySQL_Mapper extends BaseMapper<Student1> {


}
