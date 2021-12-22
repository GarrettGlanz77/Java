package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name="ideas")
public class Idea {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Size(min=2, message="Content must not be empty")
	private String content;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
	

	
    @ManyToMany
	@JoinTable(
			name = "users_ideas",
	        joinColumns = @JoinColumn(name="idea_id"),
	        inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> liked_byusers;
    
    public Idea() {}
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}



	
}