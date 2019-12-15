package service;
import java.sql.SQLException;
import java.util.Date;
import javax.security.auth.login.LoginException;
import dao.UserDao;
import domain.User;
import exception.ActiveUserException;
import exception.RegisterException;
import utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();
	// ע�����
	public void register(User user) throws RegisterException {
		// ����dao���ע�����
		try {
			dao.addUser(user);
			// ���ͼ����ʼ�
			String emailMsg = "��ϲ����ע��OnlineShop�ɹ���";
			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("ע��ʧ��");
		}
	}
	// �����û�
	public void activeUser(String activeCode) throws ActiveUserException {
		try {
			// ���ݼ���������û�
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) {
				throw new ActiveUserException("�����û�ʧ��");
			}
			// �жϼ������Ƿ���� 24Сʱ�ڼ�����Ч.
			// 1.�õ�ע��ʱ��
			Date registTime = user.getRegistTime();
			// 2.�ж��Ƿ�ʱ
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24) {
				throw new ActiveUserException("���������");
			}
			// �����û��������޸��û���state״̬
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("�����û�ʧ��");
		}
	}
	// ��¼����
	public User login(String username, String password) throws LoginException {
		try {
			//���ݵ�¼ʱ��������û��������룬�����û�
			User user = dao.findUserByUsernameAndPassword(username, password);
			//����ҵ�������Ҫȷ���û��Ƿ�Ϊ�����û�
			if (user != null) {
				// ֻ���Ǽ�����ܵ�¼�ɹ���������ʾ���û�δ���
				/*if (user.getState() == 0) {
					return user;
				}
				throw new LoginException("�û�δ����");*/
				return user;
			}
			throw new LoginException("�û������������");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("��¼ʧ��");
		}
	}
}
