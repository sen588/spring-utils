package sen.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExcelRead {

    @Test
    public void ExcelRead() throws IOException {
        InputStream is = new FileInputStream("E:/audition/课程分类列表模板.xls");
        Workbook workbook = new HSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        // 读取标题所有内容
        Row rowTitle = sheet.getRow(0);
        if (rowTitle != null) {// 行不为空
            // 读取cell
            int cellCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                Cell cell = rowTitle.getCell(cellNum);
                if (cell != null) {

                    int cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + "|");
                }
            }
            System.out.println();
        }

        // 读取商品列表数据
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {

            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空

                // 读取cell
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {

                    System.out.print("【" + (rowNum + 1) + "-" + (cellNum + 1) + "】");

                    Cell cell = rowData.getCell(cellNum);
                    if (cell != null) {

                        int cellType = cell.getCellType();

                        //判断单元格数据类型
                        String cellValue = "";
                        switch (cellType) {
                            case HSSFCell.CELL_TYPE_STRING://字符串
                                System.out.print("【STRING】");
                                cellValue = cell.getStringCellValue();
                                break;

                            case HSSFCell.CELL_TYPE_BOOLEAN://布尔
                                System.out.print("【BOOLEAN】");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;

                            case HSSFCell.CELL_TYPE_BLANK://空
                                System.out.print("【BLANK】");
                                break;

                            case HSSFCell.CELL_TYPE_NUMERIC:
                                System.out.print("【NUMERIC】");
                                //cellValue = String.valueOf(cell.getNumericCellValue());

                                if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期
                                    System.out.print("【日期】");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                } else {
                                    // 不是日期格式，则防止当数字过长时以科学计数法显示
                                    System.out.print("【转换成字符串】");
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cellValue = cell.toString();
                                }
                                break;

                            case Cell.CELL_TYPE_ERROR:
                                System.out.print("【数据类型错误】");
                                break;
                        }

                        System.out.println(cellValue);
                    }
                }
            }
        }
        is.close();
    }
    
    
    //获取excel表中的数据存放到一个二维数组中
    private String[][] gainExcelContent() {
        //加载文件目录下Excel的内容
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(EXCEL_PATH);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        //读取数据
        Sheet sheet = workbook.getSheetAt(0);
        //获取行的总数
        int rowCount = sheet.getPhysicalNumberOfRows();
        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
        String[][] array = null;
        if(rowCount > 0) {
            array = new String[rowCount][cellCount];
        }
        int conut = 0;
        for (int rowNum = 0; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    Cell cell = rowData.getCell(cellNum);
                    if (cell != null) {
                        int cellType = cell.getCellType();
                        switch (cellType) {
                            //字符串类型数据
                            case HSSFCell.CELL_TYPE_STRING :
                                conut++;
                                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                array[rowNum][cellNum] = cell.getStringCellValue();
                                break;
                            //日期类型数据
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                Date date = cell.getDateCellValue();
                                array[rowNum][cellNum] = new DateTime(date).toString("yyyy-MM-dd HH:mm:ss");
                                break;
                        }
                    }
                }
            }
        }
        //将array数组中的有效数据copy到array_copy数组中
        String[][] array_copy = new String[conut][cellCount];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] != null){
                    array_copy[i][j] = array[i][j];
                }
            }
        }
        return array_copy;
    }
}
