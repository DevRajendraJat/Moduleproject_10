package com.rays.dao;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CarDto;

@Repository
public class CarDAOImpl extends BaseDAOImpl<CarDto> implements CarDAOInt {

}
