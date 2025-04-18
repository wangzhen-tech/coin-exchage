package com.wang.util;

import org.apache.commons.lang3.StringUtils;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
/**
 * @Author wangzhen
 * @Description csv的导出工具类
 * @Date 2025/4/17 21:14
 * @Version 1.0
 */
public class ReportCsvUtils {
    // 导出为csv文件，与reportListCsv，导出的是一些基本数据
    public static void reportList(
            HttpServletResponse response,
            String[] header,
            String[] properties,
            String fileName,
            List<?> soureList
    ) throws Exception {

        if (header == null || properties == null || soureList == null || header.length <= 0 || properties.length <= 0 || soureList.size() <= 0)
            return;
        if (StringUtils.isBlank(fileName)) {
            fileName = "1.csv";
        }
        response.setContentType("application/csv");
        response.setCharacterEncoding("GBK");
        response.setHeader("Content-FileName", URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(header);
        for (Object obj : soureList) {
            csvWriter.write(obj, properties);
        }
        csvWriter.close();


    }

    // 导出为csv文件，比reportList多一个参数：PROCESSORS
    public static void reportListCsv(
            HttpServletResponse response,
            String[] header,
            String[] properties,
            String fileName,
            List<?> soureList,
            CellProcessor[] PROCESSORS
    ) throws Exception {

        if (header == null || properties == null || soureList == null || header.length <= 0 || properties.length <= 0 || soureList.size() <= 0)
            return;
        if (StringUtils.isBlank(fileName)) {
            fileName = "1.csv";
        }
        response.setContentType("application/csv");
        response.setCharacterEncoding("GBK");
        response.setHeader("Content-FileName", URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");


        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        csvWriter.writeHeader(header);
        for (Object obj : soureList) {
            csvWriter.write(obj, properties, PROCESSORS);
        }
        csvWriter.close();
    }
}
