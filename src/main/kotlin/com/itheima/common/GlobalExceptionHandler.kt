package com.itheima.common


import com.truereality.reggiedelivery.common.Result
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLIntegrityConstraintViolationException
import java.util.logging.Level
import java.util.logging.Logger


/**
 * @author TrueReality
 * @date 2023/3/26
 * @apiNotes
 */
@RestControllerAdvice
@ResponseBody
@Slf4j
class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun ExceptionHandler(sqlIntegrityConstraintViolationException: SQLIntegrityConstraintViolationException): Result<String>? {
        val message = sqlIntegrityConstraintViolationException.message
        Logger.getAnonymousLogger().log(Level.WARNING, message)
        val result = message?.let {
            if (message.contains("Duplicate entry")) {
                val split = message.split(" ")
                val duplicatedName = split[2]
                Result.error<String>("${duplicatedName}用户名重复")
            } else {
                Result.error("发生未知错误")
            }
        }
        return result
    }

    @ExceptionHandler(CustomedException::class)
    fun ExceptionHandler(customedException: CustomedException): Result<String>? {
        Logger.getAnonymousLogger().log(Level.WARNING, customedException.message)
        return Result.error(customedException.message)
    }
}