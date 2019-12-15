package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.PageBean;
import service.ProductService;
import org.apache.log4j.Logger;
//分页显示数据
public class ShowUserProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger("onelogger");
		try{
			logger.info("用户正浏览商品");
			// 1.定义当前页码，默认为1
			int currentPage = 1;
			String _currentPage = request.getParameter("currentPage");
			if (_currentPage != null) {
				currentPage = Integer.parseInt(_currentPage);
			}
			// 2.定义每页显示条数,默认为4
			int currentCount = 4;
			String _currentCount = request.getParameter("currentCount");
			if (_currentCount != null) {
				currentCount = Integer.parseInt(_currentCount);
			}
			// 3.获取查找的分类
			String category = "mobilephone";
			String _category =request.getParameter("category");
			if (_category != null) {
				category = _category;
			}
			// 4.调用service，完成获取当前页分页Bean数据.
			ProductService service = new ProductService();
			PageBean bean = service.findProductByPage(currentPage, currentCount,
					category);
			// 将数据存储到request范围，跳转到login_index.jsp页面展示
			request.setAttribute("bean", bean);
			request.getRequestDispatcher("/client/login_index.jsp").forward(request, response); 
			return;
		}catch(Exception e){
			logger.info("错误信息"+e.toString());
		}
	}
}
