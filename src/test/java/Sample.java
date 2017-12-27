import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.util.MimeMessageParser;
import org.junit.Test;

import net.endpoint.emailtemplate.dto.RecivedEmailDto;
import net.endpoint.utils.AESCipherHelper;

public class Sample {

	
	@Test
	public void testCypher() {
		String pass = "mypassword";
		String encripted =  AESCipherHelper.encrypt(pass);
		assertEquals(pass, AESCipherHelper.decrypt(encripted));
		
	
		Set<String> uids = new HashSet<>();
		for(int i = 0 , m = 100; i < m; i++) {
			String uuid = AESCipherHelper.generateUUID();
			System.out.println(uuid);
			uids.add(uuid);
			
		}
		assertEquals(uids.size(), 100);
	}
	
	
	@Test
	public void test() throws MessagingException, IOException {
		   List<RecivedEmailDto> emails = new ArrayList<>();
		 
		  Properties  props  = new Properties();
    	  String       host  = "mail.skinqualitycare.com.au";
    	  String   username  = "admin@skinqualitycare.com.au";
    	  String   password  =  "4650080";
    	  String       port  = "993";
    	  String   provider  = "imap";
    	    props.put("mail.imap.host", host);
    	    props.put("mail.imap.port", port);
    	    props.put("mail.imap.ssl.enable", "true");
    	      //Connect to the server
    	  Session session = Session.getDefaultInstance(props);
    	  Store      store = session.getStore(provider);     
    	  store.connect(host, username, password);
    	  String string = "";
    	             

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();

    //  System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];

         RecivedEmailDto emailDto = new RecivedEmailDto(message);

         emails.add(emailDto);
         System.out.println(emailDto.toString());
         
      }

        //close the store and folder objects
        emailFolder.close(false);
        store.close();
  
	            
              }


	}

