package com.itheima.entity

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonFormat

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 套餐
 */
@TableName("reggie.setmeal")
data class Combo(
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val id: Long? = null,
@JsonFormat(shape = JsonFormat.Shape.STRING)
    val categoryId: Long? = null,
    //套餐名称
    val name: String? = null,

    //套餐价格
    val price: BigDecimal? = null,

    //状态 null:停用 1:启用
    val status: Int? = null,

    //编码
    val code: String? = null,

    //描述信息
    val description: String? = null,

    //图片
    val image: String? = null,

    @TableField(fill = FieldFill.INSERT)
    val createTime: LocalDateTime? = null,

    @TableField(fill = FieldFill.INSERT_UPDATE)
    val updateTime: LocalDateTime? = null,

    @TableField(fill = FieldFill.INSERT)
    val createUser: Long? = null,

    @TableField(fill = FieldFill.INSERT_UPDATE)
    val updateUser: Long? = null,
    @TableField(exist = false)
    //是否删除
    val isDeleted: Int? = null
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}