package com.atguigu.gulimall.product.web;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Category2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author LiTong(Prode)
 * @Data 2024/04/16 19:18
 */
@RestController
public class IndexController {

    @Autowired
    CategoryService categoryService;



//    @GetMapping({"/","index.html"})
//    public String indexPage(Model model){
//        //查出所有的一级分类
//        List<CategoryEntity> categoryEntities =  categoryService.getLevel1Categorys();
//        model.addAttribute("categorys",categoryEntities);
//        //视图解析器：classpath:/templates/ + 返回值 + .html
////        return "classpath:/templates/index.html";
//        return "index";
//    }

    @ResponseBody
    @GetMapping("/index/catelog.json")
    public Map<String, List<Category2Vo> > getCatalogJson(){
        Map<String, List<Category2Vo> > catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }


}
