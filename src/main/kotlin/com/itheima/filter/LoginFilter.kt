package com.itheima.filter

import com.alibaba.fastjson.JSON
import com.itheima.utils.LocalContent
import com.truereality.reggiedelivery.common.Result
import org.springframework.util.AntPathMatcher
import java.io.IOException
import java.util.logging.Logger
import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 检查用户是否已经完成登录
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = ["/*"])

class LoginCheckFilter : Filter {
    //定义不需要处理的请求路径
    private val urls = arrayOf(
        "/employee/login",
        "/employee/logout",
        "/backend/**",
        "/front/**",
        "/common/**"
    )

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse


        //1、获取本次请求的URI
        val requestURI = request.requestURI // /backend/index.html
        Logger.getAnonymousLogger().info("拦截到请求:$requestURI")

        //2、判断本次请求是否需要处理
        val check = check(requestURI)

        //3、如果不需要处理，则直接放行
        if (check) {
            Logger.getAnonymousLogger().info("本次请求${requestURI}不需要处理")
            filterChain.doFilter(request, response)
        } else {
            val SessionUserId = request.session.getAttribute("employee")
            //4、判断登录状态，如果已登录，则直接放行
            if (SessionUserId != null) {
                LocalContent.setContent(SessionUserId as Long)
                Logger.getAnonymousLogger().info("用户已登录，用户id为:" + SessionUserId)
                filterChain.doFilter(request, response)
            } else {
                Logger.getAnonymousLogger().info("用户未登录")
                //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
                response.writer.write(JSON.toJSONString(Result.error<String>("NOTLOGIN")))
            }
        }
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param requestURI
     * @return
     */
    fun check(requestURI: String?): Boolean {
        for (url in urls) {
            val match = PATH_MATCHER.match(
                url,
                requestURI!!
            )
            if (match) return true
        }
        return false
    }

    companion object {
        //路径匹配器，支持通配符
        val PATH_MATCHER = AntPathMatcher()
    }
}