package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CarDAOInt;
import com.rays.dto.CarDto;

@Service
@Transactional
public class CarServiceImpl extends BaseServiceImpl<CarDto, CarDAOInt> implements CarServiceInt{

}
