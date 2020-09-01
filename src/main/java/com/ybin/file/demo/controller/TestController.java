package com.ybin.file.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * @Author: bingy
 * @Date: 2020.9.1 23:32
 * @Version: 1.0
 * @Description:
 **/

@Api(tags = "test-Excel操作", description = "test-处理Excel")
@Controller
@RequestMapping("/file")
public class TestController {

    @ApiOperation("test-上传Excel")
    @GetMapping("/excel/import")
    @ResponseBody
    public String importExcel() {
        return "Hello world";
    }

    @ApiOperation("test-Excel转换跳转")
    @GetMapping("/excel/goexcel")
    public String goExcel() {
        return "goExcel";
    }

    @ApiOperation("test-Excel转换")
    @PostMapping("/excel/exchange")
    public String exchange(Model model) {
        //File file = model.getAttribute("file");
        return null;
    }
}
