package com.rays.attachment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AttachmentDAOImpl implements AttachmentDAOInt {
	

	@PersistenceContext
	EntityManager entitymanager;

	@Override
	public long add(AttachmentDto dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(AttachmentDto dto) {
		entitymanager.merge(dto);

	}

	@Override
	public void delete(AttachmentDto dto) {
		entitymanager.remove(dto);

	}

	@Override
	public AttachmentDto findbyPk(long pk) {
		AttachmentDto dto = entitymanager.find(AttachmentDto.class, pk);
		return dto;
	}
	
	

}
