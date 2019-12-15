package client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import domain.User;
import exception.RegisterException;
import service.UserService;
import utils.ActiveCodeUtils;
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����ύ�����ݷ�װ��javaBean
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			// ���b������
			user.setActiveCode(ActiveCodeUtils.createActiveCode());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		// ����service���ע�������
		UserService service = new UserService();
		try {
			service.register(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		// ע��ɹ�����ת��registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
		return;
	}
}

