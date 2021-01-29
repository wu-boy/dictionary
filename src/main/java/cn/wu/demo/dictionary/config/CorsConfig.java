package cn.wu.demo.dictionary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * @author wusq
 * @date 2021/1/18
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // 允许跨越访问的路径
                //.allowedOrigins("*") // 允许跨域访问的源
                .allowedOriginPatterns("*") // SpringBoot2.4开始allowedOriginPatterns代替allowedOrigins
                .allowedMethods("*") // 允许请求方法
                .maxAge(16800) // 预检间隔时间，单位是秒
                .allowedHeaders("*") // 允许头部设置
                .allowCredentials(Boolean.TRUE); // 是否发送cookie
    }

}
