package com.itheima.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Data
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 分类
 */

data class Category(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val id: Long?=null,
    val type: Int?=null,
    val name: String?=null,
    val sort: Int?=null,
    @TableField(fill = FieldFill.INSERT) val createTime: LocalDateTime?=null,
    @TableField(fill = FieldFill.INSERT_UPDATE) val updateTime: LocalDateTime?=null,
    @TableField(fill = FieldFill.INSERT) val createUser: Long?=null,
    @TableField(fill = FieldFill.INSERT_UPDATE) val updateUser: Long?=null,
   @TableField(exist = false) val isDeleted: Int?=null
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}