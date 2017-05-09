package com.blog.beans;

public class Place {
	private Integer placeId;
	private String province;
	private String city;
	private String area;
	public Place(){
	}
	public Place(String province,String city,String area){
		this.province=province;
		this.city=city;
		this.area=area;
	}
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getPlaceId() {
		return placeId;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getArea() {
		return area;
	}
}
