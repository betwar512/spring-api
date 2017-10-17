package net.endpoint.config.db;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "net.endpoint.filesystem")
public class FsConfig  {

	
//	@Bean
//	public GridFsTemplate gridFsTemplate() throws Exception {
//		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
//	}
	
//	@Override
//	protected String getDatabaseName() {
//		
//		return "test";
//	}

//	@Override
//	public Mongo mongo() throws Exception {
//
//		return new MongoClient("10.0.0.84");
//	}

}
