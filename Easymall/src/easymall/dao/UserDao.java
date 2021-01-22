package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Repository("userDao")
@Mapper
public interface UserDao {
	
	// ��¼
	public User login(User user);
	// ����û��Ƿ��ѱ�ע��
	public User checkUsername(String username);
	// ע��
	public int regist(User user);
	
}
