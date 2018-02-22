package net.visionvalley.customization.sdg.multitetnat.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminRowMapper implements RowMapper<AdminEntity>{
	
	public AdminEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setAdminName(row.getString("commonname") != null && !row.getString("commonname").equals("")
				? row.getString("commonname") : row.getString("username"));
		adminEntity.setTenantName(row.getString("name"));
		adminEntity.setEmail(row.getString("email"));
		adminEntity.setCellNum(row.getString("cellnumber"));
		return adminEntity;
	}

}
