package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
/**
 * Аналог applicationContextMVC.xml
 * */
@Configuration
@EnableWebMvc
@ComponentScan("ru.drogunov.springcource")
@PropertySource("classpath:springWebMvcConfigurer.properties")
public class SpringWebMvcConfigurer implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    @Value("${springWebMvcConfigurer.prefix}")
    private String prefix;
    @Value("${springWebMvcConfigurer.suffix}")
    private String suffix;
    @Value("${springWebMvcConfigurer.enableSpringELCompiler}")
    private boolean enableSpringELCompiler;
//    @Value("${springWebMvcConfigurer.order}")
//    private int order;
//    @Value("${springWebMvcConfigurer.viewNames}")
//    private String[] viewNames;
    
    @Autowired
    public SpringWebMvcConfigurer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(suffix);
        return templateResolver;
    }
    
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(enableSpringELCompiler);
        return templateEngine;
    }
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
        //TODO В примере эти два параметра не внедряются, Вопрос почему?
//        resolver.setOrder(order);
//        resolver.setViewNames(viewNames);
    }
}
