package easymall.service;

import easymall.po.User;

public interface UserService {

	public User login(User user);
	// ����û��Ƿ��ѱ�ע��
	public Boolean checkUsername(String username);
	// ע��
	public int regist(User user);
	
}
