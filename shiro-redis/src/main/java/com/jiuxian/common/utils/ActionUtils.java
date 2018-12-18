package com.jiuxian.common.utils;

import com.jiuxian.web.model.ActionResult;

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
        } catch (Exception e) {
        }
    }

    public static ActionResult handleResult() {
        ActionResult result = new ActionResult();
        result.setSuccess(true);
        result.setSessionExpire(false);
        result.setAuthorize(true);
        result.setMessage("操作执行成功!");
        return result;
    }

    public static ActionResult handleResult(Object obj) {
        ActionResult result = new ActionResult();
        result.setSuccess(true);
        result.setData(obj);
        result.setSessionExpire(false);
        result.setAuthorize(true);
        result.setMessage("操作执行成功!");
        return result;
    }

    public static ActionResult handleResult(String message, Object obj) {
        ActionResult result = new ActionResult();
        result.setSuccess(true);
        result.setData(obj);
        result.setMessage(message);
        result.setSessionExpire(false);
        result.setAuthorize(true);
        return result;
    }

    public static ActionResult handleResult(boolean success, String message, Object obj) {
        ActionResult result = new ActionResult();
        result.setSuccess(success);
        result.setData(obj);
        result.setMessage(message);
        result.setSessionExpire(false);
        result.setAuthorize(true);
        return result;
    }

    public static ActionResult handleResult(boolean success, String message) {
        ActionResult result = new ActionResult();
        result.setSuccess(success);
        result.setMessage(message);
        result.setSessionExpire(false);
        result.setAuthorize(true);
        return result;
    }

    public static ActionResult unAuthorize() {
        ActionResult result = new ActionResult();
        result.setSuccess(false);
        result.setMessage("未认证请登录");
        result.setSessionExpire(false);
        result.setAuthorize(false);
        return result;
    }

    public static ActionResult sessionExpire() {
        ActionResult result = new ActionResult();
        result.setSuccess(false);
        result.setMessage("登录过期,请重新登录!");
        result.setSessionExpire(true);
        result.setAuthorize(false);
        return result;
    }

    public static ActionResult logOut() {
        ActionResult result = new ActionResult();
        result.setSuccess(true);
        result.setMessage("退出登录!");
        result.setSessionExpire(false);
        result.setAuthorize(false);
        return result;
    }
}
