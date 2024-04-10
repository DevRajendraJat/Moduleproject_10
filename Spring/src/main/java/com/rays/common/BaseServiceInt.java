package com.rays.common;

import java.util.List;

import com.rays.dto.CarDto;

public interface BaseServiceInt<T extends BaseDto> {
	
public long add(CarDto dto);
	
	public void update(CarDto dto);
	
	public void delete(long pk);
	
	public CarDto findbyPk(long pk);
	
	public List search(CarDto dto, int pageNo,int pageSize);

}
