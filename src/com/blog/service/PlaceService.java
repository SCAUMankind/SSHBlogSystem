package com.blog.service;
import com.blog.beans.Place;
public interface PlaceService extends BaseService<Place> {
	public Place loadPlace(String province,String city,String area);
	public Integer getPlaceId(String province,String city,String area);
}
