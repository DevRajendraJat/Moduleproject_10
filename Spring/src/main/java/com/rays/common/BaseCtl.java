package com.rays.common;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rays.attachment.AttachmentDto;
import com.rays.attachment.AttachmentServiceInt;
import com.rays.dto.CarDto;
import com.rays.form.CarForm;
import com.rays.service.CarServiceInt;

public class BaseCtl<F extends BaseForm, T extends BaseDto, S extends BaseServiceInt<T>> {

	@Autowired
	public CarServiceInt service;
	
	@Autowired
	public AttachmentServiceInt  attachmentService;

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(true);

		if (bindingResult.hasErrors()) {
			res.setSuccess(false);

			Map<String, String> errors = new HashMap<String, String>();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});

			res.addInputError(errors);

		}
		return res;

	}

	@PostMapping("preload")
	public ORSResponse preload(@RequestBody CarForm form) {

		ORSResponse res = new ORSResponse(true);

		CarDto dto = new CarDto();

		dto.setPurchaseDate(form.getPurchaseDate());

		List<DropDownList> roleList = service.search(dto, 0, 0);

		res.addResult("rolelist", roleList);

		return res;

	}

	@PostMapping("add")
	public ORSResponse add(@RequestBody @Valid CarForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;

		}

		CarDto dto = new CarDto();

		dto.setId(form.getId());
		dto.setCarName(form.getCarName());
		dto.setOwnerName(form.getOwnerName());
		dto.setModel(form.getModel());
		dto.setPrice(form.getPrice());
		dto.setPurchaseDate(form.getPurchaseDate());
		service.add(dto);
		res.addMessage("DATA ADDEDD SUCCESSFULLY");

		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody  @Valid CarForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;

		}

		CarDto dto = new CarDto();
		dto.setId(form.getId());
		dto.setCarName(form.getCarName());
		dto.setOwnerName(form.getOwnerName());
		dto.setModel(form.getModel());
		dto.setPrice(form.getPrice());
		dto.setPurchaseDate(form.getPurchaseDate());
		service.update(dto);
		res.addMessage("DATA UPDATED SUCCESSFULLY");
		return res;
	}

	@GetMapping("delete/{id}")
	public ORSResponse delete(@PathVariable long id) {

		ORSResponse res = new ORSResponse(true);
		service.delete(id);
		res.addMessage("DATA DELETED");
		return res;

	}
	
	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody CarForm form , @PathVariable int pageNo) {
		
		ORSResponse res = new ORSResponse(true);
		
		CarDto dto = new CarDto();
		
		dto.setId(form.getId());
		dto.setPurchaseDate(form.getPurchaseDate());
		dto.setCarName(form.getCarName());
		
		List list = service.search(dto, pageNo, 5);
		
		if (list.size() == 0) {
			res.addMessage("NOT RECORD FOUND");
			
		}else {
			res.addData(list);
		}
		return res;
		
	}
	@GetMapping("get/{id}")
	public ORSResponse get(@RequestBody @PathVariable long id) {
		ORSResponse res = new ORSResponse(true);

		CarDto dto = service.findbyPk(id);

		if (dto != null) {
			res.addData(dto);
		}
		return res;
	}
	
	@PostMapping("profilePic/{carId}")
	public ORSResponse uploadPic(@PathVariable Long carId, @RequestParam("file") MultipartFile file,
			HttpServletResponse response) {

		ORSResponse res = new ORSResponse(true);

		AttachmentDto attachmentDto = new AttachmentDto(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(carId);

		CarDto carDto = service.findbyPk(carId);

		if (carDto.getImageId() != null && carDto.getImageId() > 0) {

			attachmentDto.setId(carDto.getImageId());
		}

		Long imageId = attachmentService.add(attachmentDto);

		if (carDto.getImageId() == null) {

			carDto.setImageId(imageId);

			service.update(carDto);
		}

		res.addResult("imageId", imageId);

		return res;
	}

	@GetMapping("profilePic/{carId}")
	public @ResponseBody void downloadPic(@PathVariable Long carId, HttpServletResponse response) {

		try {

			CarDto carDto = service.findbyPk(carId);
			
			AttachmentDto attachmentDto = null;

			if (carDto != null) {
				attachmentDto = attachmentService.findbyid(carDto.getImageId());
			}

			if (attachmentDto != null) {
				response.setContentType(attachmentDto.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDto.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found>>>>>>>>>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
