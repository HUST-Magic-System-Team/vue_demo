package com.manli.manli_java.config;

import com.manli.manli_java.interceptor.CheckLoginInterceptor;
import com.manli.manli_java.util.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public CheckLoginInterceptor checkLoginInterceptor() {
        return new CheckLoginInterceptor();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
    }

    //view templates解析
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/test2/**").setViewName("test");  //支持正则的
        registry.addRedirectViewController("/test2/**", "/test");  //支持正则的
    }


    //拦截器相关
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration reg1 = registry.addInterceptor(checkLoginInterceptor());                     //拦截的对象会进入这个类中进行判断
        reg1.addPathPatterns("/**").excludePathPatterns("/js/**", "/css/**", "/img/**", "/api/login/**", "/api/demo/**");       //所有路径都被拦截
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        List<MediaType> fastMediaTypes = new ArrayList<>();
////        解决json输出中文乱码的问题
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//    }


//    @Bean
//    public HttpMessageConverters converter() {
//        // 1.定义一个converters转换消息的对象
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
////        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        // 3.在converter中添加配置信息
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 4.将converter赋值给HttpMessageConverter
//        HttpMessageConverter<?> converter = fastConverter;
//        // 5.返回HttpMessageConverters对象
//        return new HttpMessageConverters(converter);
//    }

//    添加jsonp支持
//    @Bean
//    public JSONPResponseBodyAdvice jsonpResponseBodyAdvice() {
//        return new JSONPResponseBodyAdvice();
//    }


    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }


}
