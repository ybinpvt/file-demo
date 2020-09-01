package com.ybin.file.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: bingy
 * @Date: 2020.9.1 23:32
 * @Version: 1.0
 * @Description:
 **/

@Api(tags = "Excel操作", description = "处理Excel")
@Controller
@RequestMapping("/file")
public class TestController {

    @ApiOperation("上传Excel")
    @GetMapping("/excel/import")
    @ResponseBody
    public String importExcel() {
        return null;
    }
}
