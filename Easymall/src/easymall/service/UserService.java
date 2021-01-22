package easymall.service;

import easymall.po.User;

public interface UserService {

	public User login(User user);
	// 检查用户是否已被注册
	public Boolean checkUsername(String username);
	// 注册
	public int regist(User user);
	
}
