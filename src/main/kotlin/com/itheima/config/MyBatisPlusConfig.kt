package com.itheima.config

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author TrueReality
 * @date 2023/3/28
 * @apiNotes
 */
@Configuration
class MyBatisPlusConfig {
@Bean
    fun mybatisPlusInterceptor():MybatisPlusInterceptor {
        val mybatisPlusInterceptor=MybatisPlusInterceptor()
        mybatisPlusInterceptor.addInnerInterceptor(PaginationInnerInterceptor())
        return mybatisPlusInterceptor
    }
}