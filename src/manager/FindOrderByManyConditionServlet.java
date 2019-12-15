package manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Order;
import service.OrderService;
/**
 * ��������ѯ����
 * @author admin
 *
 */
public class FindOrderByManyConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //��ȡ������ź��ռ�������
		String id = request.getParameter("id");
		String receiverName = request.getParameter("receiverName");
        //����Service�����
		OrderService service = new OrderService();
		//����Service��OrderService���findOrderByManyCondition()������ѯ����
		List<Order> orders = service.findOrderByManyCondition(id, receiverName);
        //����ѯ�����ӵ�request��������
		request.setAttribute("orders", orders);
        //����ת����list.jspҳ�棬����request�����response��ӦҲת������ҳ����
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request,
				response);
	}
}
