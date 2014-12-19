package com.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.mybatis.model.User;

public interface UserMapper {
	
	public User selectUserById(Integer id2); 
	public void insertUser(User user);
	
	public Integer countByExample(Map<String,Object> param);
	public List<Map<String,Object>> selectByExample(Map<String,Object> param);
	/**
	 * ע�ͷ�ʽҲ��ʹ�ã�
	 * �ٶȣ�MyBatis-Spring-1.2.2 ָ���ֲ�
	 * @param userId
	 * @return
	 */
	/*@Select("SELECT * FROM users WHERE id = #{userId}") 
	User getUser(@Param("userId") String userId);*/
}
