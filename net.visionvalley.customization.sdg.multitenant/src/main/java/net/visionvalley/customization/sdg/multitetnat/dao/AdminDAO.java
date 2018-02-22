package net.visionvalley.customization.sdg.multitetnat.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.visionvalley.customization.sdg.multitetnat.entity.AdminEntity;
import net.visionvalley.customization.sdg.multitetnat.entity.AdminRowMapper;

@Transactional
@Repository
public class AdminDAO implements IAdminDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	public List<AdminEntity> getAllAdmins(){
		return null;
	}

	public List<AdminEntity> getAllAdminsByParaentId(int id) {
		String sql = "select DISTINCT  cellnumber,email,commonname,username , name from platuser pu, appgarden ag, platsecpolicy pp where pp.appgardenid= ag.id and pu.id=pp.userid and   pu.id<>? and pp.level=10 and ag.id in (select psp.appgardenid from platuser u,appgarden t,platsecpolicy psp where u.id=? and psp.appgardenid= t.id and u.id=psp.userid and pp.level=10);";
		RowMapper<AdminEntity> rowMapper = new AdminRowMapper();
		return jdbcTemplate.query(sql, rowMapper,new Object[] {id,id});
	}

}
