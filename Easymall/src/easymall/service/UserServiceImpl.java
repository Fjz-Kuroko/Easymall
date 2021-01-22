package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.UserDao;
import easymall.po.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
//		User u = userDao.login(user);
//		if (u != null) {
//			return true;
//		}
//		return false;
		return userDao.login(user);
	}

	@Override
	public Boolean checkUsername(String username) {
		User u = userDao.checkUsername(username);
		if (u != null) {
			return true;
		}
		return false;
	}

	@Override
	public int regist(User user) {
		return userDao.regist(user);
	}

}
