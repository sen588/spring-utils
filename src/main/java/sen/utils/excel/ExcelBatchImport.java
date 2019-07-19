package sen.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ExcelBatchImport
{
    public static void main(String[] args)
    {
        /*
        需要导入的pom文件
        <!--xls(03)-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.9</version>
        </dependency>

        <!--xlsx(07)-->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>3.9</version>
        </dependency>
         */
        System.out.println("excel数据导入到数据库springBoot演示。");
    }
}
/*
excel数据导入到数据库的Controller层
 */
class excelController
{
    public void batchImport(@RequestParam("file") MultipartFile file)
    {
        /*try{
            List<String> errorMsg = subjectService.batchImport(file);
            if(errorMsg.size() == 0){
                return R.ok().message("批量导入成功");
            }else{
                return R.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }

        }catch (Exception e){
            //无论哪种异常，只要是在excel导入时发生的，一律用转成GuliException抛出
            throw new GuliException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }*/
    }
}

/*
excel数据导入到数据库的Service 层
 */
class excelService
{
    //开启事务
    //@Transactional
    public List<String> batchImport(MultipartFile file) throws Exception {
        //错误消息列表
        List<String> errorMsg = new ArrayList<>();
        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());
        //获取工作表
        Sheet sheet = excelHSSFUtil.getSheet();

        int rowCount = sheet.getPhysicalNumberOfRows();
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {

            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空
                //获取一级分类
                String levelOneValue = "";
                Cell levelOneCell = rowData.getCell(0);
                if(levelOneCell != null){
                    levelOneValue = excelHSSFUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }
                //判断一级分类是否重复 TODO
                //将一级分类存入数据库
                /*
                将数据保存到数据库中
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    baseMapper.insert(subjectLevelOne);
                */
                //获取二级分类 TODO
                //判断二级分类是否重复 TODO
                //将二级分类存入数据库 TODO
            }
        }
        return errorMsg;
    }
}