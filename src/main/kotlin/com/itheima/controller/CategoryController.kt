package com.itheima.controller

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.itheima.entity.Category
import com.itheima.service.CategoryService
import com.itheima.service.impl.CategoryServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.truereality.reggiedelivery.common.Result
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping

/**
 * @author TrueReality
 * @date 2023/4/4
 * @apiNotes
 */
@RestController
@RequestMapping("/category")
class CategoryController(@Autowired private val categoryService: CategoryServiceImpl) {
    @PostMapping
    fun save(@RequestBody category: Category):Result<String>{
        categoryService.save(category)
        return Result.success("保存分类成功~~")
    }
    @GetMapping("/page")
    fun pageQuery(page: Long, pageSize: Long):Result<Page<Category>>{
        val pageInfo=Page<Category>(page,pageSize)
        val ktQueryWrapper=KtQueryWrapper(Category::class.java)
        ktQueryWrapper.orderByAsc(Category::sort)
        categoryService.page(pageInfo,ktQueryWrapper)
        return Result.success(pageInfo)
    }
    @DeleteMapping
    fun delete(ids:Long):Result<String>{
        categoryService.removeById(ids)
        return Result.success("删除成功~~")
    }
    @PutMapping
    fun update(@RequestBody category: Category):Result<String>{
        categoryService.updateById(category)
        return Result.success("修改成功!~~~")
    }
}