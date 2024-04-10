package com.rays.common;

public class BaseForm {
	

	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BaseDto getDto() {
		return null;
		
	}
	
	public <T extends BaseDto> T initDto(T dto){
		
		if (id != null && id > 0){
			dto.setId(id);
			
		}else {
			dto.setId(null);
		}
		return dto;
		
	}

}
