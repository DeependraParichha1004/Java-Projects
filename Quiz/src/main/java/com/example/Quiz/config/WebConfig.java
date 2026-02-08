package com.example.Quiz.config;

// import com.example.Quiz.AuthenticationInterceptor;
import com.example.Quiz.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor authInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptors)
                .addPathPatterns("/payment/v1/**", "/question/**")
                .excludePathPatterns("/api/login");
    }
}