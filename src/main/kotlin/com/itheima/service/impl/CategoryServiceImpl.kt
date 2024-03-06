package com.itheima.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.itheima.common.CustomedException
import com.itheima.dao.CategoryDao
import com.itheima.entity.Category
import com.itheima.entity.Combo
import com.itheima.entity.Dish
import com.itheima.service.CategoryService
import com.itheima.service.ComboService
import com.itheima.service.DishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.Serializable

/**
 * @author TrueReality
 * @date 2023/3/14
 * @apiNotes
 */
@Service
class CategoryServiceImpl(
    @Autowired private val dishService: DishService,
    @Autowired private val comboService: ComboService
) : ServiceImpl<CategoryDao, Category>(), CategoryService {

    override fun removeById(id: Long) {
        val dishQueryWrapper = KtQueryWrapper(Dish::class.java)
        val comboQueryWrapper = KtQueryWrapper(Combo::class.java)
        //先看传进来的category的id是菜品类型还是套餐类型

       val dishCount= dishService.count(dishQueryWrapper.eq(Dish::categoryId, id))
        if (dishCount!=0){
            throw CustomedException("还有关联的菜品未删除嗷~~")
        }else{
            val comboCount= comboService.count(comboQueryWrapper.eq(Combo::categoryId, id))
            if (comboCount!=0){
                throw CustomedException("还有关联的套餐未删除嗷~~")
            }
        }
        this.removeById(id)
    }

}