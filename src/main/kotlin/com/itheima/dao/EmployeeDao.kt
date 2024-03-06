package com.itheima.dao

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.itheima.entity.Employee
import org.apache.ibatis.annotations.Mapper

/**
 * @author TrueReality
 * @date 2023/3/14
 * @apiNotes
 */
@Mapper
interface EmployeeDao : BaseMapper<Employee>