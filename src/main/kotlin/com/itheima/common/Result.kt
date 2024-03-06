package com.truereality.reggiedelivery.common

import lombok.Data
import java.util.HashMap

@Data
class Result<T> {
    var code: Int? = null //编码：1成功，0和其它数字为失败
    var msg: String? = null //错误信息
    var data: T? = null //数据
    val map: MutableMap<String?, Any?> = HashMap() //动态数据
    fun add(key: String?, value: Any?): Result<T> {
        map[key] = value
        return this
    }


    companion object {
        fun <T> success(`object`: T): Result<T> {
            val result = Result<T>()
            result.data = `object`
            result.code = 1
            return result
        }

        fun <T> error(msg: String?): Result<T> {
            val result = Result<T>()
            result.msg = msg
            result.code = 0
            return result
        }
    }
}