package com.axiv548.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @date 2021/3/21 19:44
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${img.dir}")
    private String realPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将虚拟路径/img/，映射到绝对路径

        String firstImg= realPath + "/firstImg/";
        String contentImg= realPath + "/contentImg/";
        String albumImg= realPath + "/albumImg/";
        String photoImg= realPath + "/photoImg/";


        registry.addResourceHandler("/firstImg/**").addResourceLocations("file:"+firstImg);
        registry.addResourceHandler("/contentImg/**").addResourceLocations("file:"+contentImg);
        registry.addResourceHandler("/albumImg/**").addResourceLocations("file:"+albumImg);
        registry.addResourceHandler("/photoImg/**").addResourceLocations("file:"+photoImg);

    }

}
