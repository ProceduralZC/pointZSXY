package com.system.manager.util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
 
public class BarCodePrintUtil {
    
	/** 
     * 打印条形码 
     * @param fileName 
     * @param count 
     */  
    public static void drawImageWithParam(String fileName, int count) {  
        try {  
            DocFlavor dof = null;  
            //根据用户选择不同的图片格式获得不同的打印设备
            if (fileName.endsWith(".gif")) {  
                dof = DocFlavor.INPUT_STREAM.GIF;  
            } else if (fileName.endsWith(".jpg")) {  
                dof = DocFlavor.INPUT_STREAM.JPEG;  
            } else if (fileName.endsWith(".png")) {  
                dof = DocFlavor.INPUT_STREAM.PNG;  
            }  
          
            //获得打印属性 
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();  
            // 获取默认打印机  
            PrintService[] ps = PrintServiceLookup.lookupPrintServices(dof,pras);  
            //如果没有获取打印机 
            if (ps.length == 0){ 
               //终止程序 
               return; 
            } 
            //获得打印设备 ，字节流方式，图片格式 
            pras.add(OrientationRequested.PORTRAIT);  
          	pras.add(PrintQuality.HIGH);  
            //每一次默认打印count页 
            //pras.add(new Copies(count));  
            //pras.add(MediaSizeName.ISO_A10); // 设置打印的纸张  
  
            DocAttributeSet das = new HashDocAttributeSet();  
            das.add(new MediaPrintableArea(0, 0, 1, 1, MediaPrintableArea.INCH));  
            //字节流获取图片信息 
            FileInputStream fin = new FileInputStream(fileName);  
            //设置打印内容
            Doc doc = new SimpleDoc(fin, dof, das);  
            DocPrintJob job = ps[0].createPrintJob();  
  
            job.print(doc, pras);  
            fin.close();  
        } catch (IOException ie) {  
            ie.printStackTrace();  
        } catch (PrintException pe) {  
            pe.printStackTrace();  
        }  
    }
    
    public static void drawImage(String fileName, int count) {  
        try {  
            DocFlavor dof = null;  
            //根据用户选择不同的图片格式获得不同的打印设备
            if (fileName.endsWith(".gif")) {  
                dof = DocFlavor.INPUT_STREAM.GIF;  
            } else if (fileName.endsWith(".jpg")) {  
                dof = DocFlavor.INPUT_STREAM.JPEG;  
            } else if (fileName.endsWith(".png")) {  
                dof = DocFlavor.INPUT_STREAM.PNG;  
            }  
          
            // 获取默认打印机  
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();  
            //字节流获取图片信息 
            FileInputStream fin = new FileInputStream(fileName);  
            //设置打印内容
            Doc doc = new SimpleDoc(fin, dof, null);  
            DocPrintJob job = ps.createPrintJob();  
            job.print(doc, null);  
            fin.close();  
        } catch (IOException ie) {  
            ie.printStackTrace();  
        } catch (PrintException pe) {  
            pe.printStackTrace();  
        }  
    }
    
    public static void main(String[] args) {
		drawImage("d:/NXH71563.png", 1);
	}
}