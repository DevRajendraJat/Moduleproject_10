package com.rays.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttachmentServiceImpl implements AttachmentServiceInt {

	@Autowired
	AttachmentDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDto dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDto dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {
		AttachmentDto dto = findbyid(pk);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public AttachmentDto findbyid(long pk) {
		AttachmentDto dto = dao.findbyPk(pk);
		return dto;
	}

}
