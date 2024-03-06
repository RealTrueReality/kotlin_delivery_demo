package com.itheima.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 菜品
 */

data class Dish(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val id: Long?=null,

    //菜品名称
    val name: String?=null,

    //菜品分类id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val categoryId: Long?=null,

    //菜品价格
    val price: BigDecimal?=null,

    //商品码
    val code: String?=null,

    //图片
    val image: String?=null,

    //描述信息
    val description: String?=null,

    //null 停售 1 起售
    val status: Int?=null,

    //顺序
    val sort: Int?=null,

    @TableField(fill = FieldFill.INSERT)
    val createTime: LocalDateTime?=null,

    @TableField(fill = FieldFill.INSERT_UPDATE)
    val updateTime: LocalDateTime?=null,

    @TableField(fill = FieldFill.INSERT)
    val createUser: Long?=null,

    @TableField(fill = FieldFill.INSERT_UPDATE)
    val updateUser: Long?=null,

    //是否删除
    val isDeleted: Int?=null
) : Serializable {


    companion object {
        private const val serialVersionUID = 1L
    }
}