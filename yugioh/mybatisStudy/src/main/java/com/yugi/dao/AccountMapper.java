package com.yugi.dao;

import com.yugi.annotation.MyBatisRepository;
import com.yugi.entity.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@MyBatisRepository
@Repository
public interface AccountMapper {
	List<Emp> findAllEmp();

	List<Map<String,Object>> queryEmp();


//	void update(Integer id);


}
