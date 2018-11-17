package com.jiuxian.web;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ActionUtils {
    private static void setHeader(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
    }

    public static void handleResult(HttpServletResponse response, String result) {
        setHeader(response);
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
            out.close();
        } catch (Exception ignore) {
        }
    }

}
