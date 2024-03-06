package com.itheima.controller

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.itheima.entity.Employee
import com.itheima.service.EmployeeService
import com.truereality.reggiedelivery.common.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.DigestUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/employee")
class EmployeeController(@Autowired private val employeeService: EmployeeService) {

    @PostMapping("/login")
    fun login(httpServletRequest: HttpServletRequest, @RequestBody employee: Employee): Result<Employee> {
        /*
        处理逻辑如下：
1、将页面提交的密码password进行md5加密处理
2、根据页面提交的用户 名username查询数据库了、如果没有查询到则返回登录失败结果
4、密码比对，如果不一致则返回登录失败结果
5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
6、登录成功，将员工id存入session井返回登录成功结果
         */
        val encriptedPassword = employee.password?.toByteArray()?.let { DigestUtils.md5DigestAsHex(it) }
        employee.password = encriptedPassword
        val ktQueryWrapper = KtQueryWrapper(Employee::class.java)
        ktQueryWrapper.eq(Employee::username, employee.username)
        val userGot = employeeService.getOne(ktQueryWrapper)
        val result = if (userGot == null) {
            Result.error("用户名错了~")
        } else if (userGot.password != employee.password) {
            Result.error("密码错了~")
        } else if (userGot.status == 0) {
            Result.error("您被禁用咯~")
        } else {
            Result.success(userGot)
        }
        httpServletRequest.session.setAttribute("employee", userGot?.id)
        return result
    }

    @PostMapping("/logout")
    fun logout(httpServletRequest: HttpServletRequest): Result<String> {
        httpServletRequest.getSession().removeAttribute("employee")
        return Result.success("退出成功")
    }


    @PostMapping
    fun saveEmployee(httpServletRequest: HttpServletRequest, @RequestBody employee: Employee): Result<String> {
        val md5DigestAsHex: String = DigestUtils.md5DigestAsHex("123456".toByteArray())
        employee.password = md5DigestAsHex
//        employee.createTime= LocalDateTime.now()
//        employee.updateTime= LocalDateTime.now()
//        val sessionEmployeeId = httpServletRequest.getSession().getAttribute("employee")
//        sessionEmployeeId?.let {
//            employee.createUser=it as Long
//            employee.updateUser=it
//        }
        employeeService.save(employee)
        return Result.success("添加成功~~")
    }

    @GetMapping("/page")
    fun pageQuery(page: Long, pageSize: Long, name: String?): Result<Page<Employee>> {
        val pageInfo = Page<Employee>(page, pageSize)
        val ktQueryWrapper = KtQueryWrapper(Employee::class.java)
        ktQueryWrapper.apply {
            like(name?.isBlank() ?: false, Employee::name, name)
            orderByDesc(Employee::createTime)
        }
//        ktQueryWrapper.like(name.isNotEmpty(),Employee::name,name)
//                      .orderByDesc(Employee::createTime)
        //调用service
        employeeService.page(pageInfo, ktQueryWrapper)
        return Result.success(pageInfo)
    }

    @PutMapping
    fun update(httpServletRequest: HttpServletRequest, @RequestBody employee: Employee): Result<String> {
//        val employeeIdInSession = httpServletRequest.session.getAttribute("employee")
//        employee.updateUser=employeeIdInSession as Long
//        employee.updateTime=LocalDateTime.now()
        employeeService.updateById(employee)
        return Result.success("修改成功~~")

    }

    @GetMapping("/{id}")//使用pathvariable
    fun getById(@PathVariable id: Long): Result<Employee> {
        val employeeGot = employeeService.getById(id)
        val result = employeeGot?.let {
            Result.success(employeeGot)
        } ?: Result.error("没有查询到员工信息嗷")
        return result
    }
}