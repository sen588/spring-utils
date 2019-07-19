package sen.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWrite {

    /*==================================2003.xls===================================*/
    /*
    使用HSSF
        缺点：最多只能处理65536行，否则会抛出异常
        java.lang.IllegalArgumentException: Invalid row number (65536) outside allowable range (0..65535)
        优点：过程中写入缓存，不操作磁盘，最后一次性写入磁盘，速度快
     */
    @Test
    public void ExcelWriteHSSF() throws IOException {
        //记录开始时间
        long begin = System.currentTimeMillis();
        //创建一个SXSSFWorkbook
        Workbook workbook = new SXSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet();
        //xls文件最大支持65536行
        for(int rowNum = 1; rowNum <= 65536; rowNum++)
        {
            //创建一个行
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 1; cellNum <= 10; cellNum++)
            {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream("E:/audition/test-write03-bigdata.xls");
        workbook.write(fos);
        // 操作结束，关闭文件
        fos.close();
        //记录结束时间
        long finish = System.currentTimeMillis();
        System.out.println("花费时间：" + (double)(finish - begin)/1000);
    }

    /*==================================2007.xls===================================*/
    /*
    使用XSSF
        缺点：写数据时速度非常慢，非常耗内存，也会发生内存溢出，如100万条
        优点：可以写较大的数据量，如20万条
     */
    @Test
    public void ExcelWriteXSSF() throws IOException {
        //记录开始时间
        long begin = System.currentTimeMillis();
        //创建一个SXSSFWorkbook
        Workbook workbook = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet();
        for(int rowNum = 1; rowNum <= 100000; rowNum++)
        {
            //创建一个行
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 1; cellNum <= 10; cellNum++)
            {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream("E:/audition/test-write07-bigdata.xlsx");
        workbook.write(fos);
        // 操作结束，关闭文件
        fos.close();
        //记录结束时间
        long finish = System.currentTimeMillis();
        System.out.println("花费时间：" + (double)(finish - begin)/1000);
    }

    /*==================================2007.xls===================================*/
    /*
    使用SXSSF
        优点：可以写非常大的数据量，如100万条甚至更多条，写数据速度快，占用更少的内存
        注意：
            过程中会产生临时文件，需要清理临时文件
            默认由100条记录被保存在内存中，如果查过这数量，则最前面的数据被写入临时文件
            如果想自定义内存中数据的数量，可以使用new SXSSFWorkbook(数量)
     */
    @Test
    public void ExcelWriteSXSSF() throws IOException {
        //记录开始时间
        long begin = System.currentTimeMillis();
        //创建一个SXSSFWorkbook
        Workbook workbook = new SXSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet();
        //xlsx文件最大支持1048576行
        for(int rowNum = 1; rowNum <= 1048575; rowNum++)
        {
            //创建一个行
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 1; cellNum <= 10; cellNum++)
            {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream("E:/audition/test-write07-sxssf-bigdata.xlsx");
        workbook.write(fos);
        // 操作结束，关闭文件
        fos.close();
        //记录结束时间
        long finish = System.currentTimeMillis();
        System.out.println("花费时间：" + (double)(finish - begin)/1000);
    }
}
