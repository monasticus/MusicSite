package com.musicsite.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.musicsite")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.musicsite.repository")
public class AppConfig implements WebMvcConfigurer {

    // === HIBERNATE CONFIGURATIONS
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("musicsitePersistenceUnit");
        return emfb;
    }

    // === HIBERNATE CONFIGURATIONS
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager(emf);
        return tm;
    }

    // === VIEWRESOLVER
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // === VIEWRESOLVER: ENABLE OTHER FILES
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // === CONVERTERS
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getPerformerConverter());
        registry.addConverter(getCategoryConverter());
    }

    @Bean
    public PerformerConverter getPerformerConverter() {
        return new PerformerConverter();
    }

    @Bean
    public CategoryConverter getCategoryConverter() {
        return new CategoryConverter();
    }


    // === RESOURCE HANDLERS
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    // === SPRING MVC EMAIL
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("musicsite.mailservice@gmail.com");
        mailSender.setPassword("peterock1993!");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    // === SPRING MVC EMAIL - TEMPLATE
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "<h1>musicsite.</h1><a href=\"http://localhost:8080/user-confirm/%d?x=%s\">Confirm your account</a><p>Thank You!</p>");
        return message;
    }


}
