package com.mybatis.dao;

import com.mybatis.model.User;

public interface UserMapper {
	
	public User selectUserById(Integer id2); 
	public void insertUser(User user);
	/**
	 * ע�ͷ�ʽҲ��ʹ�ã�
	 * �ٶȣ�MyBatis-Spring-1.2.2 ָ���ֲ�
	 * @param userId
	 * @return
	 */
	/*@Select("SELECT * FROM users WHERE id = #{userId}") 
	User getUser(@Param("userId") String userId);*/
}
