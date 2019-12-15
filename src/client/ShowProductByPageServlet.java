package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PageBean;
import service.ProductService;
import org.apache.log4j.Logger;
//��ҳ��ʾ����
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger("onelogger");
		try{
			logger.info("�û��������Ʒ");
			// 1.���嵱ǰҳ�룬Ĭ��Ϊ1
			int currentPage = 1;
			String _currentPage = request.getParameter("currentPage");
			if (_currentPage != null) {
				currentPage = Integer.parseInt(_currentPage);
			}
			// 2.����ÿҳ��ʾ����,Ĭ��Ϊ4
			int currentCount = 4;
			String _currentCount = request.getParameter("currentCount");
			if (_currentCount != null) {
				currentCount = Integer.parseInt(_currentCount);
			}
			// 3.��ȡ���ҵķ���
			String category = "mobilephone";
			String _category =request.getParameter("category");
			if (_category != null) {
				category = _category;
			}
			// 4.����service����ɻ�ȡ��ǰҳ��ҳBean����.
			ProductService service = new ProductService();
			PageBean bean = service.findProductByPage(currentPage, currentCount,
					category);
			// �����ݴ洢��request��Χ����ת��index.jspҳ��չʾ
			request.setAttribute("bean", bean);
			request.getRequestDispatcher("/client/index.jsp").forward(request, response); 
			return;
		}catch(Exception e){
			logger.info("������Ϣ"+e.toString());
		}
	}
}
