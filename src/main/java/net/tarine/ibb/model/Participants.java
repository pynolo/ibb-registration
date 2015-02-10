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
@Table(name = "participants")
public class Participants extends BaseEntity {
	private static final long serialVersionUID = -298878866265848096L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@Column(name = "code", nullable = false, length = 128)
	private String code;
	@Basic(optional = false)
	@Column(name = "email", nullable = false, length = 64)
	private String email;
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 128)
	private String name;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Column(name = "arrival_time", length = 128)
	private String arrivalTime;
	@Column(name = "country_name", length = 256)
	private String countryName;
	@Column(name = "food_restrictions", length = 2048)
	private String foodRestrictions;
	@Column(name = "volunteering", length = 2048)
	private String volunteering;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "payment")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payment;
	
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


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getVolunteering() {
		return volunteering;
	}

	public void setVolunteering(String volunteering) {
		this.volunteering = volunteering;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPayment() {
		return payment;
	}

	public void setPayment(Date payment) {
		this.payment = payment;
	}

}
