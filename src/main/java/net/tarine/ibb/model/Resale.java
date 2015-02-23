package net.tarine.ibb.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author paolo
 */
@Entity
@Table(name = "resale")
public class Resale extends BaseEntity {
	private static final long serialVersionUID = 8062714332994455761L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "expiration")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiration;
	@Basic(optional = false)
	@Column(name = "resale_type", nullable = false, length = 4)
	private String resaleType;
	@Basic(optional = false)
	@Column(name = "message", nullable = false, length = 256)
	private String message;
	@Basic(optional = false)
	@Column(name = "email", nullable = false, length = 64)
	private String email;
	@Basic(optional = false)
	@Column(name = "days", nullable = false)
	private Integer days;
		
	public Resale() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getResaleType() {
		return resaleType;
	}

	public void setResaleType(String resaleType) {
		this.resaleType = resaleType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Resale)) {
			return false;
		}
		Resale other = (Resale) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Resale[id=" + id + "]";
	}
}
