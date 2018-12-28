package com.abc.rumango.median.iso.entity2;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the rights database table.
 * 
 */
@Entity
@Table(name="rights")
@NamedQuery(name="Right.findAll", query="SELECT r FROM Right r")
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	private Boolean auth;

	private Boolean close;

	private Boolean copy;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="new")
	private Boolean new_;

	private Boolean print;

	private Boolean remove;

	private Boolean reopen;

	@Column(name="role_id")
	private Long roleId;

	private Integer screen;

	private Boolean unlock;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	private Boolean view;

	public Right() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAuth() {
		return this.auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public Boolean getClose() {
		return this.close;
	}

	public void setClose(Boolean close) {
		this.close = close;
	}

	public Boolean getCopy() {
		return this.copy;
	}

	public void setCopy(Boolean copy) {
		this.copy = copy;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getNew_() {
		return this.new_;
	}

	public void setNew_(Boolean new_) {
		this.new_ = new_;
	}

	public Boolean getPrint() {
		return this.print;
	}

	public void setPrint(Boolean print) {
		this.print = print;
	}

	public Boolean getRemove() {
		return this.remove;
	}

	public void setRemove(Boolean remove) {
		this.remove = remove;
	}

	public Boolean getReopen() {
		return this.reopen;
	}

	public void setReopen(Boolean reopen) {
		this.reopen = reopen;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getScreen() {
		return this.screen;
	}

	public void setScreen(Integer screen) {
		this.screen = screen;
	}

	public Boolean getUnlock() {
		return this.unlock;
	}

	public void setUnlock(Boolean unlock) {
		this.unlock = unlock;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getView() {
		return this.view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

}