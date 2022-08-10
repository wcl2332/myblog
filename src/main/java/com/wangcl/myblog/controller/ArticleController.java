package com.wangcl.myblog.controller;

import com.wangcl.myblog.constant.enums.ErrorCode;
import com.wangcl.myblog.model.vo.Result;
import com.wangcl.myblog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
@Api(tags = "博客文章相关")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("文章首页列表")
    @PostMapping("/list")
    public Result getArticleList(@RequestParam("page")Integer page,@RequestParam("size") Integer size,@RequestParam("authorid")String authorid) {
        if(page == null || size == null  || authorid.isEmpty()){
            return Result.fail(ErrorCode.Parms_ERROR.getCode(),ErrorCode.Parms_ERROR.getMsg());
        }
        return articleService.findArticleAll(page,size,authorid);
    }

    @ApiOperation("文章详细信息")
    @GetMapping("/{id}")
    public Result getArticleById(@PathVariable("id")Long id){
        return articleService.findArticleById(id);
    }

    @ApiOperation("文章归档")
    @PostMapping("/file")
    public Result getArticleFile(@RequestParam("authorid")String authorid){
        return articleService.findArticleFile(authorid);
    }

}
