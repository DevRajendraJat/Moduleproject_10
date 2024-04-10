package com.rays.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rays.dto.CarDto;

public class BaseDAOImpl<T extends BaseDto> implements BaseDAOInt<T> {

	@PersistenceContext
	public EntityManager entitymanager;

	@Override
	public long add(CarDto dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(CarDto dto) {
		entitymanager.merge(dto);

	}

	@Override
	public void delete(CarDto dto) {
		entitymanager.remove(dto);

	}

	@Override
	public CarDto findbyPk(long pk) {
		CarDto dto = entitymanager.find(CarDto.class, pk);
		return dto;
	}

	@Override
	public List search(CarDto dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<CarDto> cq = builder.createQuery(CarDto.class);
		Root<CarDto> qRoot = cq.from(CarDto.class);

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getCarName() != null && dto.getCarName().length() > 0) {
				predicateList.add(builder.like(qRoot.get("carName"), dto.getCarName() + "%"));

			}
			
			if (dto.getPurchaseDate() != null) {
				predicateList.add(builder.equal(qRoot.get("purchaseDate"), dto.getPurchaseDate()));

			}

			if (dto.getId() != null && dto.getId() > 0) {
				predicateList.add(builder.equal(qRoot.get("id"), dto.getId()));
			}

		}
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<CarDto> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);

		}

		List<CarDto> list = tq.getResultList();
		return list;

	}

}
