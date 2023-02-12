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
    @Value("${db.password}")
    private String URL;
    @Value("${db.userName}")
    private String userName;
    @Value("${db.URL}")
    private String password;
    
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
    }
    
    @Bean
    public Connection connection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
//           TODO Не понимаю почему не принимает переменные которые подгружает Spring из property
//            return DriverManager.getConnection(URL, userName,password);
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/first_db", "postgres", "postgres");

        } catch (SQLException e) {
            System.out.println("Error ///////////////////////////////////////////////////////");
            throw new RuntimeException(e);
        }
    }
    
}
