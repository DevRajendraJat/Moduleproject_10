package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dto.CarDto;

public class BaseServiceImpl<T extends BaseDto, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	public D basedao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CarDto dto) {
		long pk = basedao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CarDto dto) {
		basedao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {
		CarDto dto = findbyPk(pk);
		basedao.delete(dto);

	}

	@Transactional(readOnly = true)
	public CarDto findbyPk(long pk) {
		CarDto dto = basedao.findbyPk(pk);

		return dto;
	}

	@Transactional(readOnly = true)
	public List search(CarDto dto, int pageNo, int pageSize) {

		List list = basedao.search(dto, pageNo, pageSize);

		return list;
	}

}
