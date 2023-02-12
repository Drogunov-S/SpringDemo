package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;

/**
 * Аналог applicationContextMVC.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.drogunov.springcource")
@PropertySources({
        @PropertySource("classpath:springConfigurer.properties"),
        @PropertySource("classpath:db.properties")
})
public class SpringConfigurer implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    @Value("${springConfigurer.prefix}")
    private String prefix;
    @Value("${springConfigurer.suffix}")
    private String suffix;
    @Value("${springConfigurer.enableSpringELCompiler}")
    private boolean enableSpringELCompiler;
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.URL}")
    private String URL;
    @Value("${db.userName}")
    private String userName;
    @Value("${db.password}")
    private String password;
    
    
    @Autowired
    public SpringConfigurer(ApplicationContext applicationContext) {
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
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(URL);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
    
}
