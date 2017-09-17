package endpoint;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import net.endpoint.filesystem.model.FsProfile;
import net.endpoint.filesystem.repository.FsProfileDocumentRepsitory;
import net.endpoint.filesystem.repository.FsProfileRepository;
import net.endpoint.filesystem.service.FsService;

public class MongoDbTest extends ApplicationTest {


	private MongoClient mongoClient;
	private MongoDatabase database ;
	
	@Autowired
	FsService fsService;
	
	@Before
	public void setUp() throws Exception {
		// connectDb();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	
	@Test
	public void testREpo(){
	
		
		FsProfile pro = new FsProfile();	
		pro.setName("test-doc");
		pro.setTimestamp(new Date());
		pro.setUserId("profileUserId");
		fsService.addNewProfile(pro);
		
		
		
		fsService.getAll().forEach(t->System.out.println(t.toString()));
		
		System.out.println(pro.toString());
		
	}
	
	
	
	Block<Document> printBlock = new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		           System.out.println(document.toJson());
		       }
		};
//		@Test
		public void testDb(){
			createDateBase();
			MongoCollection<Document> collection = getTable("testDocument");
			 MongoIterable<String> list = database.listCollectionNames();
			for (String name : list) {
			    System.out.println("T"+name);
			}

		 collection.find(new Document("name","228-555-0149")).forEach(printBlock);
			
//			Document document = new Document("name", "Café Con Leche")
//		               .append("contact", new Document("phone", "228-555-0149")
//		                                       .append("email", "cafeconleche@example.com")
//		                                       .append("location",Arrays.asList(-73.92502, 40.8279556)))
//		               .append("stars", 3)
//		               .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));
	//
//		collection.insertOne(document);
			
			collection.getDocumentClass().isArray();
			
//		   MongoCollection<Document> coll = this.getTable("testTable");																																																																																																																															
//		   System.out.println("Count:"+coll.count());	
			assertNotNull(database);
			this.closeDb();
		}
		
		
		
		private MongoCollection<Document>  getTable(String tableName){
			return database.getCollection(tableName);
		}
		
		private void createDateBase(){
	        MongoIterable<String> list = this.mongoClient.listDatabaseNames();
	        list.iterator().forEachRemaining(t->System.out.println("DBNAME:"+t.toString()));
			this.database = mongoClient.getDatabase("test");
		}
		
		
		private void closeDb(){
			this.mongoClient.close();
		}
		
		
		private void connectDb(){	
//		this.mongoClient = this.mongoClient != null ? this.mongoClient : new MongoClient();		
//		if(mongoClient != null){
//			System.out.println("ConnectionDetail"+	mongoClient.getConnectPoint());
//			System.out.println("------------------------------------------------------------");
//		   }
//
	}
		
	}




