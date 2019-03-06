package com.example.syau_web.controller;

import com.example.syau_web.domain.PortData;
import com.example.syau_web.domain.ProductInfo;
import com.example.syau_web.enums.MyExceptionEnum;
import com.example.syau_web.exception.MyException;
import com.example.syau_web.service.Impl.PortServiceImpl;
import com.example.syau_web.service.Impl.ProductInfoServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * create by wangyu
 * 2018/10/23 0023 18:01
 * <p>
 * 把列表中的信息导入到excel中
 */
@Controller
public class ExcelController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Autowired
    private PortServiceImpl portService;

    @RequestMapping("/productExcel.action")
    public void productExcel(HttpServletResponse response) {
//        poi主包
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("商品信息表");

//        商品列表
        List<ProductInfo> productInfoList = productInfoService.ProductList();

//        设置要导出文件的名字
        String fileName = "product" + ".xls";

//        设置数据行，并设置单元格式数据
        int rowNum = 1;
//        headers表示第一行的表头
        String[] headers = {"商品编号", "商品名称", "商品库存", "商品类型"};

//        在excel表中添加表头
        HSSFRow row = sheet.createRow(0);//在0行创建表头吧

//        遍历表头并设置表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            /**
             * ????
             */
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

//        把表中查到的数据放入到对应的列
        for (ProductInfo productInfo : productInfoList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(productInfo.getProductId());
            row1.createCell(1).setCellValue(productInfo.getProductName());
            row1.createCell(2).setCellValue(productInfo.getProductNum());
            row1.createCell(3).setCellValue(productInfo.getProductType());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }
//        return "redirect:/admin/list.action";
    }


    @RequestMapping("/portExcel.action")
    public void portExcel(HttpServletResponse response) {
//        poi主包
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("大棚实时数据信息表");

//        商品列表
//        List<ProductInfo> productInfoList = productInfoService.ProductList();
        List<PortData> portData = portService.selectAll();
//        设置要导出文件的名字
        String fileName = "portData" + ".xls";

//        设置数据行，并设置单元格式数据
        int rowNum = 1;
//        headers表示第一行的表头
        String[] headers = {"温度", "湿度", "光照强度", "检测时间"};

//        在excel表中添加表头
        HSSFRow row = sheet.createRow(0);//在0行创建表头吧

//        遍历表头并设置表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            /**
             * ????
             */
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

//        把表中查到的数据放入到对应的列
        for (PortData productInfo : portData) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(productInfo.getTemperature());
            row1.createCell(1).setCellValue(productInfo.getHumidity());
            row1.createCell(2).setCellValue(productInfo.getLightIntensity());
            row1.createCell(3).setCellValue(String.valueOf(productInfo.getCreateTime()));
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new MyException(MyExceptionEnum.IOEXCEPTION);
        }
//        return "redirect:/admin/list.action";
    }
}
