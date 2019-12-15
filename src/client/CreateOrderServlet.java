package client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import service.OrderService;
import utils.IdUtils;
@SuppressWarnings("unchecked")
//���ɶ���
public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.�õ���ǰ�û�
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// 2.�ӹ��ﳵ�л�ȡ��Ʒ��Ϣ
		Map<Product, Integer> cart = (Map<Product, Integer>)session.getAttribute("cart");
		// 3.�����ݷ�װ������������
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		order.setId(IdUtils.getUUID());// ��װ����id
		order.setUser(user);// ��װ�û���Ϣ������.
		for (Product p : cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setBuynum(cart.get(p));
			item.setP(p);
			order.getOrderItems().add(item);
		}
		System.out.println(order);
		// 4.����service�����Ӷ�������.
		OrderService service = new OrderService();
		service.addOrder(order);
		response.sendRedirect(request.getContextPath() + "/findOrderByUser");
	}

}