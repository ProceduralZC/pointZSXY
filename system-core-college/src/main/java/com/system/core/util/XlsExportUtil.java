package com.system.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class XlsExportUtil {
	
	/**  
	 * Excel 2003  
	 */  
	private final static String XLS = "xls";   
	
	/**  
	 * Excel 2007  
	 */  
	private final static String XLSX = "xlsx";   
	
	// excel最大行数,必须小于65536,并且为1000整数倍
//   private static final int MAXROW=20000;
   // 每次查询写入excel个数
//   private static final int PAGESIZE=1000;
   
   private static Logger logger = LoggerFactory.getLogger(XlsExportUtil.class);
   /**
    * 
    * 此方法描述的是：<p> Description:根据查询结果导出xls<p> 
    * @param downloadfileName 生成的文件名
    * @param list 查询结果数组
    * @param exportColumns 定义输出到xls的列
    * @param caption 如果有标题填值,否则置null
    * @param header 如果有头,填与输出列顺序对应的字符串数组,否则置null
    * @throws Exception 执行写excel可能发生的异常
    * @author:    
    * @version: 1.0
    * @createTime：2015-01-05 下午02:54:34
    */
   public static void exportXlsByList(String downloadfileName,List<? extends Object> list,
		   String[] exportColumns,String caption,String[] header,HttpServletResponse response)throws Exception{
	   
	     if(list.size() <0){
	    	 throw new IllegalArgumentException("输入数据为空！");
	     }
	     if(exportColumns == null || exportColumns.length<0){
	    	 throw new IllegalArgumentException("属性域数组不能为空;要不我怎么从你list<model>中取值啊！");
	     }
	   //如果存在标题，则写标题
	     int rows = 0;//记录些xls的行号
	     
	     //开始写xls
	     HSSFWorkbook workbook = new HSSFWorkbook();
	     //写sheet名称
	     HSSFSheet sheet = caption==null||caption.trim().length()==0?workbook.createSheet():workbook.createSheet(caption);
	     HSSFCellStyle columnTopStyle = XlsExportUtil.getColumnTopStyle(workbook);//获取列头样式对象 
	     HSSFCellStyle style = XlsExportUtil.getStyle(workbook);                  //单元格样式对象   
	     //如果存在列表，则按顺序写入列名
	     if(header !=null &&header.length>0){
	    	 HSSFRow columnNameRow = sheet.createRow(rows);
	    	 for(int i=0;i<header.length;i++){
	    		 HSSFCell columnNameCell = columnNameRow.createCell(i);
	    		 columnNameCell.setCellValue(header[i]);
	    		 columnNameCell.setCellStyle(columnTopStyle);
	    		 sheet.setColumnWidth(i, 100*80);
	    	 }
	    	 rows++;
	     }
	     writeListData(workbook,sheet,list,exportColumns,rows,style);
	     outputWorkbook(workbook, downloadfileName,response);
	   
   }
   private static final String CONTENTTYPE = "application/x-download";
  /**
   * 
   * 此方法描述的是： 向xls写入文件  
   * @author:    
   * @version: 1.0
   * @createTime：2015-01-05 下午04:01:32
   */
   public static void outputWorkbook(HSSFWorkbook workbook,
		String downloadfileName,HttpServletResponse response) throws Exception{
	   OutputStream out = null;
	   String yearMothDate = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
	 try{
		 String fileName = new String((downloadfileName+yearMothDate+".xls").getBytes("gb2312"),"ISO-8859-1");
		 //HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);;
		 response.reset();
		 logger.info("向response输出文件流开始！");
		 response.setContentType(CONTENTTYPE);
		 response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		 out = response.getOutputStream();
		 workbook.write(out);
		 out.flush();
		 logger.info("向response输出文件流完毕！");
	 }finally{
		 try{
			 if(out!=null){
				 out.close();
			 }
		 }catch(Exception ex){
			 logger.error("文件流关闭异常！");
		 }
	 }
}
/**
    * 
    * 此方法描述的是：向一个sheet中写入数据 ,如果还有next则创建1个新的sheet 返回此sheet已经写入的行数   
    * @author:    
    * @version: 1.0
    * @createTime：2015-01-05 下午03:17:05
    */
   private static int writeListData(HSSFWorkbook workbook,HSSFSheet sheet,List<?> list,String[] exportColumns,int rows,HSSFCellStyle style)throws Exception{
	   logger.info("向sheet{}写入{}行数据！",sheet,list.size());
	   //循环结果集list，取出model,按照定义的属性名输出
	   HSSFCellStyle dateStyle = workbook.createCellStyle();
	   dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd"));
	   
	   HSSFCellStyle timeStyle = workbook.createCellStyle();
	   timeStyle.setDataFormat(workbook.createDataFormat().getFormat("hh:MM:ss"));
	   
	   for(Object model:list){
		   HSSFRow columnNameRow = sheet.createRow(rows);
		   writeRow(columnNameRow,model,exportColumns,dateStyle,timeStyle,style);
		   rows++;
	   }
	   /*Map map = getTotal(list,"yjk");
	   BigDecimal totalMoney = (BigDecimal)map.get("totalMoney");
	   BigDecimal czMoney = (BigDecimal)map.get("czMoney");
	   BigDecimal tfMoney = (BigDecimal)map.get("tfMoney");
	   HSSFRow columnRow = sheet.createRow(rows);
	   HSSFCell cell = columnRow.createCell(0);
	   cell.setCellValue("总计");
	   cell.setCellStyle(style);
	   HSSFCell cell1 = columnRow.createCell(1);
	   cell1.setCellValue("交易金额：");
	   cell1.setCellStyle(style);
	   HSSFCell cell2 = columnRow.createCell(2);
	   cell2.setCellValue(totalMoney.toString());
	   cell2.setCellStyle(style);
	   HSSFCell cell3 = columnRow.createCell(3);
	   cell3.setCellValue("账户充值金额：");
	   cell3.setCellStyle(style);
	   HSSFCell cell4 = columnRow.createCell(4);
	   cell4.setCellValue(czMoney.toString());
	   cell4.setCellStyle(style);
	   HSSFCell cell5 = columnRow.createCell(5);
	   cell5.setCellValue("账户退费金额：");
	   cell5.setCellStyle(style);
	   HSSFCell cell6 = columnRow.createCell(6);
	   cell6.setCellValue(tfMoney.toString());
	   cell6.setCellStyle(style);*/
//	   sheet.addMergedRegion(new CellRangeAddress((short)rows, (short)rows, (short)0, (short)0));
	return rows;
   }
   /**
    * 
    * 此方法描述的是：数据类型的转换
    * @author:    
    * @version: 1.0
    * @createTime：2015-01-05 下午03:26:37
    */
private static void writeRow(HSSFRow columnNameRow, Object data,
		String[] exportColumns, HSSFCellStyle dateStyle, HSSFCellStyle timeStyle,HSSFCellStyle style) throws Exception{
   //如果声明域包含导出指定的columns,则将值写入xls
	for(int i=0;i<exportColumns.length;i++){
		String columnName = exportColumns[i];
		HSSFCell columnNameCell = columnNameRow.createCell(i);
		Object o = PropertyUtils.getProperty(data,columnName);
		if(o!=null){
			if(o instanceof Integer){
				columnNameCell.setCellValue((Integer)o);
			}else if(o instanceof Double){
				columnNameCell.setCellValue((Double)o);
			}else if(o instanceof java.util.Calendar){
				columnNameCell.setCellStyle(dateStyle);
				columnNameCell.setCellValue((java.util.Calendar) o);
			}else {
				// 其他情况全部为string
				String value = convertValue(o, columnName);
				columnNameCell.setCellValue(value);
			}
		} else {
			// 属性值为空,填空值
			//columnNameCell.setCellValue("");
		}
		columnNameCell.setCellStyle(style);
		}
}

/**
 * 所有的特殊值判断都放到这里,可以对不同数据类型的判断,以及具体值的判断
 * 
 * @param o
 *            此列的查询结果
 * @param columnName
 *            此列的model属性名
 * @return
 */
private static String convertValue(Object o, String columnName) {
	if (columnName.equals("is_Used")) {
		if ("0".equals(o.toString())) {
			return "否";
		} else if ("1".equals(o.toString())) {
			return "是";
		}
	}
	if(columnName.equals("owner_Sex")){
		if ("0".equals(o.toString())) {
			return "女";
		} else if ("1".equals(o.toString())) {
			return "男";
		}
	}
	if(columnName.equals("isMarry")){
		if ("0".equals(o.toString())) {
			return "未婚";
		} else if ("1".equals(o.toString())) {
			return "已婚";
		}
	}
	if(columnName.equals("unit_Name")){
		return o.toString().replace("单元", "");
	}
	
	// 其他条件一律返回字符串
	return o.toString();
}
/*  
 * 列头单元格样式 
 */      
public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {  
      
      // 设置字体   
      HSSFFont font = workbook.createFont();  
      //设置字体大小   
      font.setFontHeightInPoints((short)11);  
      //字体加粗   
      //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
      //设置字体名字    
      font.setFontName("华文仿宋");  
      //设置样式;    
      HSSFCellStyle style = workbook.createCellStyle();  
      //style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
      style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

      //设置底边框;    
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
      //设置底边框颜色;     
      style.setBottomBorderColor(HSSFColor.GREY_40_PERCENT.index);  
      //设置左边框;      
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
      //设置左边框颜色;    
      style.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);  
      //设置右边框;    
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
      //设置右边框颜色;    
      style.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);  
      //设置顶边框;    
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
      //设置顶边框颜色;     
      style.setTopBorderColor(HSSFColor.GREY_40_PERCENT.index);  
      //在样式用应用设置的字体;     
      style.setFont(font);  
      //设置自动换行;    
      style.setWrapText(false);  
      //设置水平对齐的样式为居中对齐;     
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
      //设置垂直对齐的样式为居中对齐;    
      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        
      return style;  
        
}  
/*   
 * 列数据信息单元格样式 
 */    
public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
      // 设置字体   
      HSSFFont font = workbook.createFont();  
      //设置字体大小   
      font.setFontHeightInPoints((short)15);   
      //字体加粗   
      //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);   
      //设置字体名字    
      font.setFontName("华文仿宋");  
      //设置样式;    
      HSSFCellStyle style = workbook.createCellStyle();  
      //设置底边框;    
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
      //设置底边框颜色;     
      style.setBottomBorderColor(HSSFColor.BLACK.index);  
      //设置左边框;      
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
      //设置左边框颜色;    
      style.setLeftBorderColor(HSSFColor.BLACK.index);  
      //设置右边框;    
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
      //设置右边框颜色;    
      style.setRightBorderColor(HSSFColor.BLACK.index);  
      //设置顶边框;    
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
      //设置顶边框颜色;     
      style.setTopBorderColor(HSSFColor.BLACK.index);  
      //在样式用应用设置的字体;     
      style.setFont(font);  
      //设置自动换行;    
      style.setWrapText(false);  
      //设置水平对齐的样式为居中对齐;     
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
      //设置垂直对齐的样式为居中对齐;    
      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
       
      return style;  
  
}  

/**  
 * 由Excel流的Sheet导出至List  
 *   
 * @param is  
 * @param extensionName  
 * @param sheetNum  
 * @return  
 * @throws IOException  
 */  
public static List<JSONObject> exportListFromExcel(InputStream is, String extensionName, int sheetNum) throws Exception {   

    Workbook workbook = null;   

    if (extensionName.toLowerCase().equals(XLS)) {   
        workbook = new HSSFWorkbook(is);   
    } else if (extensionName.toLowerCase().equals(XLSX)) {   
        workbook = new XSSFWorkbook(is);   
    }   

    return exportListFromExcel(workbook, sheetNum);   
}  

/**  
 * 由指定的Sheet导出至List  
 *   
 * @param workbook  
 * @param sheetNum  
 * @return  
 * @throws IOException  
 */  
private static List<JSONObject> exportListFromExcel(Workbook workbook, int sheetNum) throws Exception{
	Sheet sheet = workbook.getSheetAt(sheetNum);   
    //解析公式结果   
    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();   
    List<JSONObject> list = new ArrayList<JSONObject>();   
    int minRowIx = sheet.getFirstRowNum()+1;   
     int maxRowIx = sheet.getLastRowNum();   
     for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {   
         Row row = sheet.getRow(rowIx);   
         JSONObject obj = new JSONObject();
         
         short minColIx = row.getFirstCellNum();   
         short maxColIx = row.getLastCellNum();   
         for (short colIx = minColIx; colIx <= maxColIx; colIx++) {   
             Cell cell = row.getCell(new Integer(colIx));   
             CellValue cellValue = evaluator.evaluate(cell);   
             //如果没有值，则返回""
             if (cellValue == null) {
            	 obj.put(colIx+"", "");
                 continue;   
             }   
             // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了   
             // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html   
             switch (cellValue.getCellType()) {  
             case Cell.CELL_TYPE_BOOLEAN:   
            	 obj.put(colIx+"", cellValue.getBooleanValue());
                 break;   
             case Cell.CELL_TYPE_NUMERIC:   
                 // 这里的日期类型会被转换为数字类型，需要判别后区分处理   
                 if (DateUtil.isCellDateFormatted(cell)) {   
                	 obj.put(colIx+"", cell.getDateCellValue());
                 } else {
                	 Double num = cellValue.getNumberValue();
                	 if(num != null){
                		 BigDecimal bg = new BigDecimal(num.toString()); 
                		 obj.put(colIx+"", bg.toPlainString());
                	 }else{
                		 obj.put(colIx+"", "");
                	 }
                 }   
                 break;   
             case Cell.CELL_TYPE_STRING:   
            	 obj.put(colIx+"", cellValue.getStringValue());
                 break;   
             case Cell.CELL_TYPE_FORMULA:   
                 break;   
             case Cell.CELL_TYPE_BLANK:   
                 break;   
             case Cell.CELL_TYPE_ERROR:   
                 break;   
             default:   
                 break;   
             }
             
         }   
         list.add(obj);   
     }   
     return list;   
}   
/**
 * 
 * outputTem(文件输出)
 * (这里描述这个方法适用条件 – 可选)
 * @param request
 * @param response
 * @param fileName 文件名
 * @param srcPath	文件路径
 * @throws UnsupportedEncodingException 
 *void
 * @exception 
 * @since  1.0.0
 */
public static void outputTem(HttpServletRequest request, HttpServletResponse response,String fileName,String srcPath) throws UnsupportedEncodingException{
	response.setContentType("text/html;charset=utf-8");   
    request.setCharacterEncoding("UTF-8");   
    java.io.BufferedInputStream bis = null;   
    java.io.BufferedOutputStream bos = null;   
    String downLoadPath = srcPath +"/"+ fileName;   
    //System.out.println(downLoadPath);   
    try {   
        long fileLength = new File(downLoadPath).length();   
        response.setContentType("application/x-msdownload;");   
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));   
        response.setHeader("Content-Length", String.valueOf(fileLength));   
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
        bos = new BufferedOutputStream(response.getOutputStream());   
        byte[] buff = new byte[2048];   
        int bytesRead;   
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
            bos.write(buff, 0, bytesRead);   
        }   
    } catch (Exception e) {   
        e.printStackTrace();   
    } finally {   
		try {
			if (bis != null)
				bis.close();
			if (bos != null)   
				bos.close();   
		} catch (IOException e) {
			e.printStackTrace();
		}   
    }  
}

}
