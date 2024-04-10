package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDto;
import com.rays.common.DropDownList;

@Entity
@Table(name = "ST_CAR")
public class CarDto extends BaseDto  implements DropDownList{
	
	@Column(name = "OWNERNAME", length = 50)
	public String ownerName;

	@Column(name = "CARNAME", length = 50)
	public String carName;

	@Column(name = "MODEL", length = 50)
	public String model;

	@Column(name = "PURCHASEDATE", length = 50)
	public Date purchaseDate;

	@Column(name = "PRICE", length = 50)
	public Long price;
	
	@Column(name = "IMAGEID", length = 50)
	public Long imageId;


	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	@Override
	public String getvalue() {
		// TODO Auto-generated method stub
		return purchaseDate +" ";
	}

}
