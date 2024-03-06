package com.itheima.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

/**
 * @author TrueReality
 * @date 2023/4/8
 * @apiNotes
 */
@RestController
@RequestMapping("/common")
class CommonController(@Value("\${reggie.basepath}") private val baseFilename: String) {
    @PostMapping("/upload")
    fun imageUpload(file: MultipartFile): Result<String> {
        //处理一下filename 使其唯一
        val originalFilename = file.originalFilename
        val destFileName = baseFilename + UUID.randomUUID().toString() + originalFilename?.run {
            substring(lastIndexOf("."))
        }
        File(baseFilename).takeUnless {
            it.exists()
        }?.apply {
            mkdirs()
        }
        file.transferTo(File(destFileName))
        return Result.success("图片上传成功")
    }
}