package client;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PageBean;
import service.ProductService;
/**
 * ǰ̨ҳ�棬���ڲ˵��������������ܵ�servlet
 */
public class UserMenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1.���嵱ǰҳ�룬Ĭ��Ϊ1
		int currentPage = 1;
		String _currentPage = req.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.����ÿҳ��ʾ����,Ĭ��Ϊ4
		int currentCount = 4;	
		//��ȡǰ̨ҳ�������������ֵ
		req.setCharacterEncoding("UTF-8");
		String searchfield = URLDecoder.decode(req.getParameter("textfield"),"utf-8");
		//�����������û������ֵ��������ݵ�ΪĬ��ֵ����ʱĬ�ϲ�ѯȫ����ƷĿ¼
		if("Search".equals(searchfield)){
			req.getRequestDispatcher("/showUserProductByPage").forward(req, resp);
			return;
		}
		//����service��ķ�����ͨ������ģ����ѯ��������Ӧ��ͼ��
		ProductService service = new ProductService();
		PageBean bean = service.findBookByName(currentPage,currentCount,searchfield);
		// �����ݴ洢��request��Χ����ת��product_search_list.jspҳ��չʾ
		req.setAttribute("bean", bean);
		req.getRequestDispatcher("/client/user_product_search_list.jsp").forward(req, resp);
	}
}
