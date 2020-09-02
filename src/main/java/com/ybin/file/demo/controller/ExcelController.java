package com.ybin.file.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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
            InputStream in = file.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            in.close();
            int newRowIndex = 0;
            //读取第一个sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet newSheet = workbook.createSheet("newSheet");
            //从第2行读取到最后一行
            for (int rowIndex=1; rowIndex<sheet.getLastRowNum(); rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                Cell cell0 = row.getCell(0);
                String cellValue0 = cell0.getStringCellValue();
                Cell cell1 = row.getCell(1);
                String cellValue1 = cell1.getStringCellValue();
                String[] go = cellValue1.split(",");
                List<String> list = Arrays.asList(go);
                for (String goValue:list) {
                    Row newRow = newSheet.createRow(newRowIndex);
                    newRowIndex++;
                    Cell newCell0 = newRow.createCell(0);
                    newCell0.setCellValue(cellValue0);
                    Cell newCell1 = newRow.createCell(1);
                    newCell1.setCellValue(goValue);
                    //System.out.println(goValue);
                }
            }
            workbook.setActiveSheet(1);
            FileOutputStream fileOut = new FileOutputStream("d:\\go.xlsx");
            workbook.write(fileOut);
            // 操作完毕后，记得要将打开的 XSSFWorkbook 关闭
            workbook.close();
            return "ok!文件已经保存在\"D:go.xlsx\"";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
