package Yat.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Yat.com.wordcount.WordCount;

/**
 * Servlet implementation class WordCountServlet
 */
@WebServlet("/WordCountServlet")
public class WordCountServlet extends HttpServlet {
	public static String fileName;
	private static final long serialVersionUID = 1L;
	public static TreeMap<String,Integer> map;
	public static String[] str;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//System.out.println("-----");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//����ҳ������
		String id=null;
		//Servlet����һ������ǰ̨�ͺ�̨�Ľ���
		fileName = request.getParameter("fileName");//�ļ���
		String  words = request.getParameter("word");//ָ������
		System.out.println(words);
		String wordnum = request.getParameter("wordnum");//��Ƶ��
		String wordlines = request.getParameter("wordlines");//����
		String result = request.getParameter("result");//���
		id=request.getParameter("id");//���ܱ�ŵĻ�ȡ
		
		if(id.equals("0"))//�ļ����ϴ�
			{
			WordCount wordCount = new WordCount();//����һ������
			map = wordCount.upload(fileName);
			out.print("<script>alert('�ļ��ϴ��ɹ�����');location.href='html/Login.jsp'</script>");	
			
		}else if(id.equals("1")){
			//ָ�����ʵĲ���
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			TreeMap<String,Integer> map1 = new TreeMap<String, Integer>(); 
			WordCount wordCount = new WordCount();
		    map1= wordCount.bijiao(map, words);
		    //����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime=(float)((endTime-startTime)/1000)*1000;
			//��jspҳ�洫����
			request.setAttribute("map1", map1);
			request.setAttribute("excTime", excTime);
			System.out.println("ִ��ʱ�䣺"+excTime+"ms"); 
            request.getRequestDispatcher("html/Login.jsp").forward(request, response);//�ض���
		}else if(id.equals("2")){
			//��Ƶ�ʵ�ͳ��
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			int k=Integer.parseInt(wordnum);
			ArrayList gaopin=wordCount.gaopin(map, k);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime2=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("gaopin", gaopin);
			request.setAttribute("excTime2", excTime2);
	        request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
			
		}else if(id.equals("3")){
			//����ͳ��
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			List list=wordCount.statistics(fileName);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime3=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime3", excTime3);
			request.setAttribute("list", list);
			request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
		}else if(id.equals("4")){
			//д���ļ�
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount cunfang = new WordCount();
			cunfang.cunfang(map);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime1=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime1", excTime1);
			out.print("<script>alert('��ųɹ�����');location.href='html/WordCount.jsp'</script>");	
			   
			request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
