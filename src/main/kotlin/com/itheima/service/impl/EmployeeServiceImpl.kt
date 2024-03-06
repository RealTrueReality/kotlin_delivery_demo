package com.itheima.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.itheima.dao.EmployeeDao
import com.itheima.entity.Employee
import com.itheima.service.EmployeeService
import org.springframework.stereotype.Service

/**
 * @author TrueReality
 * @date 2023/3/14
 * @apiNotes
 */
@Service
class EmployeeServiceImpl : ServiceImpl<EmployeeDao, Employee>(), EmployeeService