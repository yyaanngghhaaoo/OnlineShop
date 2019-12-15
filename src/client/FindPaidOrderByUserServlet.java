package client;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Order;
import domain.User;
import service.OrderService;
import utils.MailUtils;
public class FindPaidOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��Ϊ��user����session
		User user = (User) request.getSession().getAttribute("user");		
		// ����service�еķ���,�����û���Ϣ���Ҷ���
		OrderService service = new OrderService();
		List<Order> orders = service.findOrderByUser(user);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/client/orderlist.jsp").forward(request, response);
		try{
			String emailMsg = "��ϲ�����������֧��������ɹ���";
		    MailUtils.sendMail(user.getEmail(), emailMsg);
		}catch (Exception e) {
			e.printStackTrace();
			}
		return;
	}
}