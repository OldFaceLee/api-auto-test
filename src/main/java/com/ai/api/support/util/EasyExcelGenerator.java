package com.ai.api.support.util;

import com.ai.api.configration.CaseGenerateConfig;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author: lixuejun
 * @date: Create in 2020/4/14 下午1:43
 * @description:
 */
@Service
public class EasyExcelGenerator {

    @Setter
    @Getter
    public static class ExcelHeaderEntity extends BaseRowModel {

        @ExcelProperty(value = "测试编号",index = 0)
        private Integer orderNum;

        @ExcelProperty(value = "测试用例名称",index = 1)
        private String testcaseName;

        @ExcelProperty(value = "测试用例描述",index = 2)
        private String testcaseDescription;

        @ExcelProperty(value = "测试组名",index = 3)
        private String testcaseGroups;

        @ExcelProperty(value = "是否执行(是或否)\n仅填写E2单元格即可",index = 4)
        private String isRunGroup;

        @ExcelProperty(value = "测试组名(需手填，每组用英文逗号隔开，仅填写F2单元格即可)",index = 5)
        private String groupsExecute;

        @ExcelProperty(value = "是否开启(是或否)parallelByMethod,仅填写G2单元格即可",index = 6)
        private String isParallelByMethod;

        @ExcelProperty(value = "如果开启parallelByMethod，则配置线程数，仅填写H2单元格即可",index = 7)
        private String testNGThreadCount;

    }

    public static void writeExcel(String filePath,String testsuiteLocation) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,true);
        Sheet sheet1 = new Sheet(1,1,ExcelHeaderEntity.class);
        sheet1.setSheetName(CaseGenerateConfig.sheetName);
        List<ExcelHeaderEntity> data = new ArrayList<>();
        //用例名称
        List<String> caseName = ReflectUtil.getInstance().getTestAnnotationMethod(testsuiteLocation);
        //用例对应的用例描述
        Map<String,String> caseDesc = AnnotationUtil.getInstance().getTestAnnDescription(testsuiteLocation);
        //用例对应的测试组
        Map<String,List<String>> caseGroups= AnnotationUtil.getInstance().getAnnotations(testsuiteLocation);
        for (int i = 0; i < caseName.size(); i++) {
            ExcelHeaderEntity items = new ExcelHeaderEntity();
            items.isRunGroup = "";
            items.groupsExecute = "";
            items.isParallelByMethod="";
            items.testNGThreadCount="";
            items.orderNum = i+1;
            items.testcaseName = caseName.get(i);
            List<String> caseGroupList = caseGroups.get(caseName.get(i));
            String caseDesci = caseDesc.get(caseName.get(i));
            items.testcaseGroups = Arrays.toString(caseGroupList.toArray());
            items.testcaseDescription = caseDesci;
            data.add(items);
        }

        TableStyle tableStyle = new TableStyle();
        tableStyle.setTableContentBackGroundColor(IndexedColors.WHITE); //定义正文背景颜色

        Font font = new Font();
        font.setFontHeightInPoints((short)16);  //定义正文字体大小
        tableStyle.setTableContentFont(font);
        Table table = new Table(0);
        table.setTableStyle(tableStyle);


        writer.write(data,sheet1,table);
        writer.finish();
    }
}
