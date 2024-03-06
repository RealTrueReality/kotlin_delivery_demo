package com.itheima.utils

/**
 * @author TrueReality
 * @date 2023/4/3
 * @apiNotes
 */
class LocalContent() {

    companion object {
       private val threadLocal: ThreadLocal<Long> = ThreadLocal()
        fun getContent(): Long {
            return threadLocal.get()
        }
        fun setContent(id: Long) {
            threadLocal.set(id)
        }
    }

}