package net.endpoint.main.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 * Authority implementation for oath 
 * @author A.H.Safaie 
 *
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	private String authority;
	
	@Override
	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static List<GrantedAuthority> getAuthoritize(String[] strs){
		List<GrantedAuthority> list = new ArrayList<>();
		for(String str:strs){
			GrantedAuthorityImpl gtr = new GrantedAuthorityImpl();
			gtr.setAuthority(str);
			list.add(gtr);
			}
		return list;
		
	}
	
}
