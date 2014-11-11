package org.dtelaroli.cms.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@UniqueConstraint(columnNames = "email")
public class User extends Model {

	private static final long serialVersionUID = 44587093069375546L;

	@NotNull
	@Email
	@Column(length = 120)
	private String email;
	
	@NotNull
	@Column(length = 80)
	@Size(min = 6)
	private String password;
	
	@Transient
	private String confirm;
	
	@NotNull
	private boolean active = true;
	
	@ManyToMany
	private List<Role> roles = new ArrayList<>();
	
	public User() {
	}
	
	@AssertTrue(message = "{password.matches}")
	public boolean isValid() {
		if(password == null) {
			return password == confirm;
		}
		return password.equals(confirm);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
