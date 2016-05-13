//Yo baby
package org.courseregistration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {


	@Column(name = "street_name", nullable = false)
	private String streetName;

	@Column(name = "apt_no", nullable = false)
	private Integer aptNo;

	@Column(name = "zip_code", nullable = false)
	private Integer zipcode;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;


	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public Integer getAptNo() {
		return aptNo;
	}

	public void setAptNo(Integer aptNo) {
		this.aptNo = aptNo;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Address))
			return false;

		Address address = (Address) o;

		if (!getId().equals(address.getId()))
			return false;
		if (!streetName.equals(address.streetName))
			return false;
		if (aptNo != null ? !aptNo.equals(address.aptNo)
				: address.aptNo != null)
			return false;
		if (!zipcode.equals(address.zipcode))
			return false;
		if (!city.equals(address.city))
			return false;
		return state.equals(address.state);

	}
//Override
	//Override
	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + streetName.hashCode();
		result = 31 * result + (aptNo != null ? aptNo.hashCode() : 0);
		result = 31 * result + zipcode.hashCode();
		result = 31 * result + city.hashCode();
		result = 31 * result + state.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		StringBuilder builder = new StringBuilder();
		builder.append("\n\t[ Address: " + streetName);
		builder.append(" #" + aptNo);
		builder.append(", " + city);
		builder.append(" " + zipcode);
		builder.append(", " + state + " ]");
		return builder.toString();

	}
}
