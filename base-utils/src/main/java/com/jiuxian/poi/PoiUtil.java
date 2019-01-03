package com.jiuxian.poi;

import com.jiuxian.date.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class PoiUtil {

    public static Row getRow(Sheet sheet, int index) {
        if (sheet.getRow(index) != null) {
            return sheet.getRow(index);
        } else {
            return sheet.createRow(index);
        }
    }

    public static Cell getCell(Sheet sheet, int rowIndex, int colIdx) {
        Row row = getRow(sheet, rowIndex);
        if (row.getCell(colIdx) != null) {
            return row.getCell(colIdx);
        } else {
            return row.createCell(colIdx);
        }
    }

    public static void setContent(HttpServletRequest request, HttpServletResponse response, Workbook workbook, String name) throws IOException {
        if (workbook != null) {

            String fileName = name + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + ".xlsx";
            // 针对IE或者以IE为内核的浏览器：
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = urlEncoder(fileName);
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }
            response.setContentType("application/ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("UTF-8");
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(outputStream);
        }
    }

    public static String urlEncoder(String str) {
        try {
            if (!StringUtils.isEmpty(str)) {
                return URLEncoder.encode(str, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
