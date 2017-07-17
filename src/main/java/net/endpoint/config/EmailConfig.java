package net.endpoint.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mail.transformer.*;



@Configuration
@EnableIntegration
public class EmailConfig {

	 @Autowired
	 DirectChannel outboundMail;

	@Bean
	@Transformer(inputChannel="inboundMail", outputChannel="outboundMail")
	public org.springframework.integration.transformer.Transformer transformer() {
	    return new MailToStringTransformer();
	}
	
}
