package com.ybin.file.demo.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: bingy
 * @Date: 2020.9.2 00:05
 * @Version: 1.0
 * @Description:
 **/

@Api(tags = "Excel", description = "Excel")
@Controller
@RequestMapping("/import")
public class ExcelController {

    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("上传Excel")
    public String dataImport(@RequestParam("file") MultipartFile file) {
        try {
            importData(file.getInputStream());
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }

    //操作数据
    private void importData(InputStream in) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        in.close();
        //读取第一个sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        //从第2行读取到最后一行
        for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            try {
                // XSSFRow 代表一行数据
                XSSFRow row = sheet.getRow(rowIndex);
                System.out.println(JSONObject.toJSONString(row));
                //获取单元格信息
                XSSFCell dateCell = row.getCell(0);
                System.out.println(JSONObject.toJSONString(dateCell));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
        workbook.close();
    }
}
