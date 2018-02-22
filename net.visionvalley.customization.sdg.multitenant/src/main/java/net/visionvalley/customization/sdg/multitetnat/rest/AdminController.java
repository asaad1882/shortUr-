package net.visionvalley.customization.sdg.multitetnat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.visionvalley.customization.sdg.multitetnat.entity.AdminEntity;
import net.visionvalley.customization.sdg.multitetnat.service.IAdminService;

@Controller
@RequestMapping("sdg")
public class AdminController {
	@Autowired
	private IAdminService adminService;
	@GetMapping("admin/{id}")
	public ResponseEntity<List<AdminEntity>> getAllAdminsByParaentId(@PathVariable("id") Integer id) {
		List<AdminEntity> adminEntities = adminService.getAllAdminsByParaentId(id);
		return new ResponseEntity<List<AdminEntity>>(adminEntities, HttpStatus.OK);
	}

}
