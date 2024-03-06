package com.itheima.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data

import java.io.Serializable
import java.time.LocalDateTime


class Employee: Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val id: Long? = null
    val username: String? = null
    val name: String? = null
    var password: String? = null

    var phone: String? = null
    var sex: String? = null
    val idNumber: String? = null
    var status: Int? = null

    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    @TableField(fill = FieldFill.INSERT)
    var createUser: Long? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateUser: Long? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}