package com.itheima.common

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.itheima.utils.LocalContent
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.sql.SQLOutput
import java.time.LocalDateTime
import kotlin.concurrent.thread

/**
 * @author TrueReality
 * @date 2023/4/3
 * @apiNotes
 */
@Component
class MpMetaObjHandler : MetaObjectHandler {
    //        employee.createTime= LocalDateTime.now()
//        employee.updateTime= LocalDateTime.now()
//        val sessionEmployeeId = httpServletRequest.getSession().getAttribute("employee")
//        sessionEmployeeId?.let {
//            employee.createUser=it as Long
//            employee.updateUser=it
//        }
    override fun insertFill(metaObject: MetaObject?) {
        metaObject?.apply {
            setValue("createTime", LocalDateTime.now())
            setValue("updateTime", LocalDateTime.now())
            setValue("createUser", LocalContent.getContent())
            setValue("updateUser", LocalContent.getContent())
        }


    }

    override fun updateFill(metaObject: MetaObject?) {
        metaObject?.apply {
            setValue("updateTime", LocalDateTime.now())
            setValue("updateUser", LocalContent.getContent())
        }

    }
}