package client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.OrderService;

/*import domain.Order;
import utils.MailUtils;*/

public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ö���������
		String orderid = request.getParameter("orderid");
		String paySuccess = "��ϲ��֧���ɹ���";
		if (null != orderid) {
				OrderService service = new OrderService();
				// ���ݶ������޸Ķ���״̬
				try {
					service.updateState(orderid);
					request.setAttribute("paySuccess", paySuccess);
					
				    /*Order order=new Order();
					String emailMsg = "��ϲ��������ɹ���";
					MailUtils.sendMail(order.receiverEmail, emailMsg);*/
					
					request.getRequestDispatcher("/findPaidOrderByUser").forward(request, response);
					return;
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("�޸Ķ���״̬ʧ��");
				}
			}
	}
}
