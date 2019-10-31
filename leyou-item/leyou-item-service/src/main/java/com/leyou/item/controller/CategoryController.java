package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询子节点
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid){

          try{
              if (pid == null || pid < 0) {
                  //400 参数不合法
                  //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
              }

              //获取查询
              List<Category> categories = this.categoryService. queryCategoriesByPid(pid);
              if (CollectionUtils.isEmpty(categories)) {
                  //404:资源服务器未找到
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
              }
              //200:查询成功
              return ResponseEntity.ok(categories);
          } catch (Exception e) {
              e.printStackTrace();
          }
          //500:服务器内部错误
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
