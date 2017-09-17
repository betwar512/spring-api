package net.endpoint.institute.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ins_body_part")
public class InsBodyPart  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ins_body_part_id")
	private long id;
	@ManyToOne
	@JoinColumn(name="ins_anatomy_id")
	private InsAnatomy anatomy;
	@Column(name="created_at")
	private Date timestamp;
	
	
	
	public long getId() {
		return id;
	}
	public InsAnatomy getAnatomy() {
		return anatomy;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setAnatomy(InsAnatomy anatomy) {
		this.anatomy = anatomy;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
	
}
