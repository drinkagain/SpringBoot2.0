package com.jiuxian.http;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.jiuxian.json.JSONUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * IP工具类
 * @author geekcattle
 */
public class IpUtil {
    /**
     * 获取登录用户的IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 通过IP获取地址(需要联网，调用淘宝的IP库)
     * @param ip
     * @return
     */
    public static IpInfo getIpInfo(String ip) {
        try {
            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            HttpURLConnection htpcon = (HttpURLConnection) url.openConnection();
            htpcon.setRequestMethod("GET");
            htpcon.setDoOutput(true);
            htpcon.setDoInput(true);
            htpcon.setUseCaches(false);
            InputStream in = htpcon.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder temp = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return JSONUtil.toBean(temp.toString(), IpInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        IpInfo ipInfo = getIpInfo("115.239.210.27");
        System.out.println(ipInfo);
    }

    @Data
    public static class IpInfo {
        private Integer code;
        private IpData data;
    }

    @Data
    public static class IpData {
        private String ip;

        private String country;

        private String area;

        private String region;

        private String city;

        private String county;

        private String isp;

        @JsonAlias("country_id")
        private String countryId;

        @JsonAlias("area_id")
        private String areaId;

        @JsonAlias("region_id")
        private String regionId;

        @JsonAlias("city_id")
        private String cityId;

        @JsonAlias("county_id")
        private String countyId;

        @JsonAlias("isp_id")
        private String ispId;
    }
}