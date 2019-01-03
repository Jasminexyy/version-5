package cm.utils;

import cm.entity.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yunfeng Huang
 * @Description:
 * @Date: Created in 2018/12/30
 */
public class FileReadUtil {

    public static List<Student> listStudentByExcel(MultipartFile multipartFile) {
        List<Student> result=new ArrayList<>();
        XSSFWorkbook workbook=null;
        try {
            workbook=new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e) {
            return null;
        }
        XSSFSheet sheet=workbook.getSheetAt(0);
        int rowNum=sheet.getLastRowNum();
        for(int i=2;i<rowNum;i++){
            XSSFRow row=sheet.getRow(i);
            Student student=new Student();
            student.setAccount(row.getCell(0).getStringCellValue());
            student.setStudentName(row.getCell(1).getStringCellValue());
            student.setPassword("123456");
            result.add(student);
        }
        return result;
    }

}
