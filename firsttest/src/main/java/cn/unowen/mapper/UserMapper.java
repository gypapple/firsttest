package cn.unowen.mapper;

import org.apache.ibatis.annotations.Param;

import cn.unowen.pojo.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByNameAndPwd(User record);

	int lockedTime(String name);
	
	User selectByName(String name);
	
	boolean lockOrNo(String name, int lock);
	
	int errorCount(@Param("name")String name,@Param("flag")int flag);
	
	int lockNow(String name);
}