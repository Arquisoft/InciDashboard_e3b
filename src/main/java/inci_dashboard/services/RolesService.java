package inci_dashboard.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
	private String[] roles = {"ROLE_OPERADOR","ROLE_ADMIN"};
	
	public String[] getRoles() {
		return roles;
	}
}
