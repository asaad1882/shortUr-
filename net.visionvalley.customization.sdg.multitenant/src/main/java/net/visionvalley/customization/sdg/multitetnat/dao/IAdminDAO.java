package net.visionvalley.customization.sdg.multitetnat.dao;

import java.util.List;

import net.visionvalley.customization.sdg.multitetnat.entity.AdminEntity;

public interface IAdminDAO {
	List<AdminEntity> getAllAdmins();
	List<AdminEntity> getAllAdminsByParaentId(int id);
}
