package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Repository("userDao")
@Mapper
public interface UserDao {
	
	// 登录
	public User login(User user);
	// 检查用户是否已被注册
	public User checkUsername(String username);
	// 注册
	public int regist(User user);
	
}
