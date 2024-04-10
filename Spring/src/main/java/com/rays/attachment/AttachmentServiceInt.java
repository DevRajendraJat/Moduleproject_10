package com.rays.attachment;

public interface AttachmentServiceInt {
	
	public long add(AttachmentDto dto);

	public void update(AttachmentDto dto);

	public void delete(long pk);

	public AttachmentDto findbyid(long pk);

}
