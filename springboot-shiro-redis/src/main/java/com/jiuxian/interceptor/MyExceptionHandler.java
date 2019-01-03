package com.jiuxian.interceptor;

import com.jiuxian.common.utils.JSONUtil;
import com.jiuxian.exception.BusinessException;
import com.jiuxian.common.utils.ActionUtils;
import com.jiuxian.web.model.ActionResult;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        ActionResult result = new ActionResult();
        result.setSuccess(false);
        result.setAuthorize(true);
        if (e instanceof BusinessException) {
            result.setMessage("账号已禁用!");
        } else if (e instanceof UnknownAccountException) {
            result.setMessage("账号有误，请检查");
        } else if (e instanceof DisabledAccountException) {
            result.setMessage("账号已禁用!");
        } else if (e instanceof LockedAccountException) {
            result.setMessage("账号已冻结!");
        } else if (e instanceof IncorrectCredentialsException) {
            result.setMessage("密码错误!");
        } else {
            result.setMessage(e.getMessage());
        }
        //ActionUtils.handleResult(result);
        ActionUtils.handleResult(response, JSONUtil.toJSON(result));
        return null;
    }
}
