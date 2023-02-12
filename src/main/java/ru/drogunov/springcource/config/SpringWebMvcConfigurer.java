package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Аналог applicationContextMVC.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.drogunov.springcource")
@PropertySources({
        @PropertySource("classpath:springWebMvcConfigurer.properties"),
        @PropertySource("classpath:db.properties")
})
public class SpringWebMvcConfigurer implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    @Value("${springWebMvcConfigurer.prefix}")
    private String prefix;
    @Value("${springWebMvcConfigurer.suffix}")
    private String suffix;
    @Value("${springWebMvcConfigurer.enableSpringELCompiler}")
    private boolean enableSpringELCompiler;
    @Value("${db.URL}")
    private String URL;
    @Value("${db.userName}")
    private String userName;
    @Value("${db.password}")
    private String password;
    
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
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
    }
    
    @Bean
    public Connection connection() {
        try {
            return DriverManager.getConnection(URL, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
