package net.visionvalley.customization.sdg.multitetnat.service;

import java.util.List;

import net.visionvalley.customization.sdg.multitetnat.entity.AdminEntity;

public interface IAdminService {

	List<AdminEntity> getAllAdminsByParaentId(int id);

}
