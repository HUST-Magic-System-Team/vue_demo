package com.manli.manli_java.advice;

import com.manli.manli_java.util.ResultBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
//
//    @Override
//    public Object beforeBodyWrite(Object result, MethodParameter returnType, MediaType selectedContentType,
//                                          Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
////        //获取在ApiInterceptor对指定请求参数放如到线程局部变量的对象
////        RequestModel model = RequestModel.getRequestModel();
////        //移除线程局部变量,释放内存
////        RequestModel.removeRequestModel();
////        //保存日志
////        operateLogService.saveLog(model, result);
////        Map<String, Object> map = new HashMap<>();
////        map.put("code",result.code);
////        map.put("msg",result.msg);
////        map.put("data",result.data);
////        return map;
//        return result;
//    }


    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter methodParameter,
                                  MediaType mediaType, Class clas, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //通过 ServerHttpRequest的实现类ServletServerHttpRequest 获得HttpServletRequest
        //ServletServerHttpRequest sshr=(ServletServerHttpRequest) serverHttpRequest;
        //此处获取到request 是为了取到在拦截器里面设置的一个对象 是我项目需要,可以忽略
        //HttpServletRequest request=   sshr.getServletRequest();

        //将返回值returnValue转成我需要的类型Message<?>  方便统一修改其中的某个属性
        // Messages是我自定义的一个类
        ResultBean result = (ResultBean) returnValue;
        //统一修改返回值/响应体

        //返回修改后的值
        return result;
    }


//    @Override
//    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
//        //Do Nothing.
//        return true;
//    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clas) {
        //获取当前处理请求的controller的方法
        String methodName = methodParameter.getMethod().getName();
        String clazz = methodParameter.getContainingClass().getName();
        // 不拦截/不需要处理返回值 的方法
        if (clazz.equals("TaskController") && methodName.equals("result")) {
            return true;
        }
        return false;
    }

}
