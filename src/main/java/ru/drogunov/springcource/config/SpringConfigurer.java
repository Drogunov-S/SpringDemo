package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Аналог applicationContextMVC.xml
 */
@Configuration
@EnableWebMvc
@ComponentScan("ru.drogunov.springcource")
@PropertySources({
        @PropertySource("classpath:springConfigurer.properties"),
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:hibernate.properties")
})
@EnableTransactionManagement(proxyTargetClass = true, mode = AdviceMode.PROXY)
@EnableJpaRepositories("ru.drogunov.springcource.repositories")
public class SpringConfigurer implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    private final Environment env;
    @Value("${springConfigurer.characterEncoding}")
    private String characterEncoding;
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
    public SpringConfigurer(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
    }
    
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(suffix);
        templateResolver.setCharacterEncoding(characterEncoding);
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
        resolver.setCharacterEncoding(characterEncoding);
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(env.getRequiredProperty("hibernate.connection.password"));
        return dataSource;
    }
    
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("ru.drogunov.springcource.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl", env.getProperty("hibernate.hbm2ddl"));
        return properties;
    }
}
























