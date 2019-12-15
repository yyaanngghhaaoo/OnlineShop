package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * ���ģ��֧����
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.��Ҫ�ύ�����ݵõ�
		// ��� ֧�������������
		//��ȡLogger����	
		Logger logger = Logger.getLogger("onelogger");
		try{			
			String orderid = request.getParameter("orderid");
			String money = request.getParameter("money");
			// ����
			String bank = request.getParameter("yh");		
			request.setAttribute("bank", bank);
			request.setAttribute("orderid", orderid);	
			request.setAttribute("money", money);
			request.getRequestDispatcher("/orderstate").forward(request, response);		
			logger.info("֧���ɹ�");
			
		}catch(Exception e){
			logger.info("������Ϣ"+e.toString());
		}
	}
}
