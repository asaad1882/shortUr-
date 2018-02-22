package net.visionvalley.customization.sdg.multitetnat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.visionvalley.customization.sdg.multitetnat.dao.IAdminDAO;
import net.visionvalley.customization.sdg.multitetnat.entity.AdminEntity;
@Service
public class AdminService implements IAdminService{
	@Autowired
	private IAdminDAO adminDAO;
	
	public List<AdminEntity> getAllAdminsByParaentId(int id){
		return adminDAO.getAllAdminsByParaentId(id);
	}
   
}
