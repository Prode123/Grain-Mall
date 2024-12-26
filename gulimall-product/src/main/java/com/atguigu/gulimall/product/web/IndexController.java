package com.atguigu.gulimall.product.web;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Category2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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

    @Autowired
    RedissonClient redisson;


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

    @GetMapping("/hello")
    public String hello() {
//        获取一把锁，只要锁的名字一样，就是同一把锁
        RLock myLock = redisson.getLock("myLock");
//        加锁
        myLock.lock();// 阻塞式等待 默认加的锁都是30s时间
//        1、锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务时间长，锁自动过期被删掉
//        2、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除
        try {
            System.out.println("加锁成功，执行业务。。。。。"+Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            解锁
//            假设线程执行出现异常,redis会不会释放锁
            System.out.println("释放锁。。。。。"+Thread.currentThread().getId());
            myLock.unlock();
        }

        return "hello";
    }

}
