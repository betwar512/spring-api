package net.endpoint.config;

import java.util.Collections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import net.endpoint.utils.enums.EmailTemplates.EmailContentProperties;


@Configuration
@PropertySource("classpath:emailconfig.properties")
public class SpringMailConfig  implements ApplicationContextAware, EnvironmentAware {

	    private ApplicationContext applicationContext;
	    private Environment environment;



	    @Override
	    public void setApplicationContext(final ApplicationContext applicationContext)  {
	        this.applicationContext = applicationContext;
	    }

	    @Override
	    public void setEnvironment(final Environment environment) {
	        this.environment = environment;
	    }


	    /*
	     *  Message externalization/internationalization for emails.
	     *
	     *  NOTE we are avoiding the use of the name 'messageSource' for this bean because that
	     *       would make the MessageSource defined in SpringWebConfig (and made available for the
	     *       web-side template engine) delegate to this one, and thus effectively merge email
	     *       messages into web messages and make both types available at the web side, which could
	     *       bring undesired collisions.
	     *
	     *  NOTE also that given we want this specific message source to be used by our
	     *       SpringTemplateEngines for emails (and not by the web one), we will set it explicitly
	     *       into each of the TemplateEngine objects with 'setTemplateEngineMessageSource(...)'
	     */
	    @Bean
	    public ResourceBundleMessageSource emailMessageSource() {
	        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	        messageSource.setBasename("mail/MailMessages");
	        return messageSource;
	    }


	    /* ******************************************************************** */
	    /*  THYMELEAF-SPECIFIC ARTIFACTS FOR EMAIL                              */
	    /*  TemplateResolver(3) <- TemplateEngine                               */
	    /* ******************************************************************** */
	    @Primary
	    @Bean
	    public TemplateEngine emailTemplateEngine() {
	        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	        // Resolver for TEXT emails
	        templateEngine.addTemplateResolver(textTemplateResolver());
	        // Resolver for HTML emails (except the editable one)
	        templateEngine.addTemplateResolver(htmlTemplateResolver());
	        // Resolver for HTML editable emails (which will be treated as a String)
	        templateEngine.addTemplateResolver(stringTemplateResolver());
	        // Message source, internationalization specific to emails
	        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
	        return templateEngine;
	    }

	    private ITemplateResolver textTemplateResolver() {
	        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(1));
	        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
	        templateResolver.setPrefix("/mail/");
	        templateResolver.setSuffix(".txt");
	        templateResolver.setTemplateMode(TemplateMode.TEXT);
	        templateResolver.setCharacterEncoding(EmailContentProperties.ENCODE_UTF_8.getValue());
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }

	    private ITemplateResolver htmlTemplateResolver() {
	        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(2));
	        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
	        templateResolver.setPrefix("/mail/");
	        templateResolver.setSuffix(".html");
	        templateResolver.setTemplateMode(TemplateMode.HTML);
	        templateResolver.setCharacterEncoding(EmailContentProperties.ENCODE_UTF_8.getValue());
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }

	    private ITemplateResolver stringTemplateResolver() {
	        final StringTemplateResolver templateResolver = new StringTemplateResolver();
	        templateResolver.setOrder(Integer.valueOf(3));
	        // No resolvable pattern, will simply process as a String template everything not previously matched
	        templateResolver.setTemplateMode(EmailContentProperties.HTML5.getValue());
	        templateResolver.setCacheable(false);
	        return templateResolver;
	    }
	
	

}
