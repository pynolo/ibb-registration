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
@Table(name = "hubs")
public class Participants extends BaseEntity {
	private static final long serialVersionUID = -298878866265848096L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "email", nullable = false, length = 64)
	private String email;
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "arrival_time", length = 128)
	private String arrivalTime;
	@Column(name = "arrival_transportation", length = 128)
	private String arrivalTransportation;
	@Column(name = "country_code", length = 4)
	private String countryCode;
	@Column(name = "country_name", length = 256)
	private String countryName;
	@Column(name = "food_restrictions", length = 2048)
	private String foodRestrictions;

	public Participants() {
	}
	
	public Participants(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Participants)) {
			return false;
		}
		Participants other = (Participants) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Participants[id=" + id + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getArrivalTransportation() {
		return arrivalTransportation;
	}

	public void setArrivalTransportation(String arrivalTransportation) {
		this.arrivalTransportation = arrivalTransportation;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getFoodRestrictions() {
		return foodRestrictions;
	}

	public void setFoodRestrictions(String foodRestrictions) {
		this.foodRestrictions = foodRestrictions;
	}

}
