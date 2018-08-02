package com.linn.frame.interceptor;

import com.alibaba.fastjson.JSON;
import com.linn.frame.controller.BaseController;
import com.linn.frame.entity.ResultBean;
import com.linn.frame.util.SysContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * Created by Administrator on 2018\1\16 0016.
 */
public class MyMappingExceptionResolver extends BaseController implements HandlerExceptionResolver {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {

        logger.error(ex.getMessage(),ex);
        //是否异步请求
        if (!(req.getHeader("accept").contains("application/json") ||
                (req.getHeader("X-Requested-With") != null &&
                        req.getHeader("X-Requested-With").contains("XMLHttpRequest")))) {
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("ex",ex);
            //根据不同错误转向不同页面
            return new ModelAndView("common/error",model);
        } else{
            ResultBean rb = new ResultBean(SysContent.ERROR, "系统错误");
            String content = JSON.toJSONString(rb);
            writer(resp,content,ContentType.JSON);
        }
        return  null;
    }

}
