package com.ai.api.support.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/13 下午5:41
 * @description:
 */
public class ExcelPoiUtil {
    private ExcelPoiUtil(){}

    private static class Singleton{
        private static final ExcelPoiUtil instance = new ExcelPoiUtil();
    }
    public static ExcelPoiUtil getInstance(){
        return Singleton.instance;
    }

    public static Workbook getWorkbook(String filePath) {
        Workbook wb = null;
        try {
            if (filePath.endsWith(".xls")) {
                File file = new File(filePath);
                InputStream is = new FileInputStream(file);
                wb = new HSSFWorkbook(is);
            } else if (filePath.endsWith(".xlsx") || filePath.endsWith(".xlsm")) {
                wb = new XSSFWorkbook(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     *获取excel存入map 返回二维数组
     */
    public Object[][] getTestData(String file,String sheetName){
        ArrayList<String> arrkey = new ArrayList<>();
        Map<String, String>[][] map = new HashMap[0][];
        try {
            InputStream inputStream = null;
            inputStream = new FileInputStream(new File(file));
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            if (StringUtils.isBlank(sheetName)) {
                System.out.println("sheetName为空");
                return null;
            }
            XSSFSheet sheet = book.getSheet(sheetName);
//        获取总行数
            int rowTotalNum = sheet.getLastRowNum()+1;
//        总列数
            int columns = sheet.getRow(0).getPhysicalNumberOfCells();

            map = new HashMap[rowTotalNum - 1][1];
            // 对数组中所有元素hashmap进行初始化
            if (rowTotalNum > 1) {
                for (int i = 0; i < rowTotalNum - 1; i++) {
                    map[i][0] = new HashMap();
                }
            } else {
                System.out.println("测试的Excel" + file + "中没有数据");
            }
            // 获得首行的列名，作为hashmap的key值
            for (int c = 0; c < columns; c++) {
                String cellvalue = getCellValue(sheet, 0, c);
                arrkey.add(cellvalue);
            }
            // 遍历所有的单元格的值添加到hashmap中
            for (int r = 1; r < rowTotalNum; r++) {
                for (int c = 0; c < columns; c++) {
                    String cellvalue = getCellValue(sheet, r, c);
                    map[r - 1][0].put(arrkey.get(c), cellvalue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *获取指定单元格内容
     */
    public static String getCellValue(String file,String sheetName,int rowNum,int colNum){
        InputStream inputStream = null;
        String value = null;
        try {
            inputStream = new FileInputStream(new File(file));
            XSSFWorkbook book = new XSSFWorkbook(inputStream);
            if (StringUtils.isBlank(sheetName)) {
                System.out.println("sheetName为空");
                return null;
            }
            XSSFSheet sheet = book.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            value = getCellValue(cell);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    private static String getCellValue(Sheet sheet, int rowNum, int cellNum) {
        Cell cell = sheet.getRow(rowNum).getCell(cellNum);
        String value = getCellValue(cell);
        return value;
    }

    private static String getCellValue(Cell cell) {
        String value = "";
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = String.valueOf(cell.getRichStringCellValue());
                return value;
            case NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                return value;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                return value;

            case FORMULA:
                value = String.valueOf(cell.getCellFormula());
                return value;

            case ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                return value;
            case BLANK:
                return value;
            default:
                System.out.println("未知该单元格类型");
                return value;

        }

    }


}
