package com.rays.attachment;

public interface AttachmentDAOInt {
	
	public long add(AttachmentDto dto);

	public void update(AttachmentDto dto);

	public void delete(AttachmentDto dto);

	public AttachmentDto findbyPk(long pk);

}
