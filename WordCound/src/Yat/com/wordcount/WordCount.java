package Yat.com.wordcount;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.swing.JFrame;

public class WordCount extends JFrame{
	

		//ͳ�����е���
		public TreeMap  upload(String fileName) throws ServletException, IOException{
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			String line = fileName;
			File file = new File(line);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			//���ı��е�Ӣ�Ĵ�����ڼ�������
	        while((line=reader.readLine())!=null){
	        	line = line.toLowerCase();//���Դ�Сд
	        	String[] str = line.split("[^a-zA-Z]");//���˳�ֻ������ĸ��  
	        	for(int i = 0; i<str.length; i++){
	        		String word = str[i].trim();
	        		if(map.containsKey(word) &&  word.length() != 0){
	        			map.put(word, map.get(word)+1);
	        		}else{
	        			map.put(word, 1);
	        		}
	        	}
	        }	
			return map;
		}
		
		//���ҵ���֮��������Ⱥ������ҵ��ʵĸ���
		public TreeMap  bijiao(TreeMap<String, Integer> tm,String words) {//����������У���Ҫ�������֮ǰ���뵥�ʵļ��ϣ��û�������ַ���
			TreeMap<String,Integer> map1 = new TreeMap<String, Integer>();  
			String[] word= words.split(";");//ͨ���ֺ�����ȡ�û�������ַ���
	        int i;
	        for(i=0; i<word.length; i++) {
	        	for(Entry<String,Integer> entry : tm.entrySet()) { 
	        		if(word[i].equals(entry.getKey()))//�뼯���еĵ��ʱȽ�
	        		{
	        			map1.put(entry.getKey(), entry.getValue());
	        			//System.out.println(entry.getKey() + "�ĸ�����" + entry.getValue()); 
	        			break;
	        		}
	            } 
	        }
			return map1;
	        
	    }
		
		//��״ͼ����ʾ
			public WordCount(){
				super();
				setTitle("��������ͼ");
				setBounds(3, 200, 450, 450);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			public void paint(Graphics g,TreeMap<String, Integer> tm,String [] ss){
				int Width = getWidth();
				int Height = getHeight();
				int leftMargin = 50;//����ͼ��߽�
				int topMargin = 50;//����ͼ�ϱ߽�
				Graphics2D g2 = (Graphics2D) g;
				int ruler = Height-topMargin;
				int rulerStep = ruler/20;//����ǰ�ĸ߶�����Ϊ20����λ
				g2.setColor(Color.WHITE);//���ư�ɫ����
				g2.fillRect(0, 0, Width, Height);//���ƾ���ͼ
				g2.setColor(Color.LIGHT_GRAY);
				for(int i=0;i<rulerStep;i++){
					g2.drawString((400-20*i)+"��", 8, topMargin+rulerStep*i);//����Y���ϵ�����
				}
				g2.setColor(Color.RED);
				int m=0;
				for(int i = 0;i<ss.length;i++){
					int value = tm.get(ss[i]);
					int step = (m+1)*40;//����ÿ������ͼ��ˮƽ���Ϊ40
					g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//����ÿ����״��
					g2.drawString(ss[i], leftMargin+step*2, Height-value-5);	//��ʶÿ����״��		
					m++;
				}
			}
		
		
		//���ʵĴ��
		public void cunfang(TreeMap<String, Integer> tm){

			//ͳ�Ƹ��ı����е�����������Ƶ�������ܽ����ʼ���Ƶ�����ֵ�˳��������ļ�result.txt
			BufferedWriter bw = null;
			try {
				File file1 = new File("result.txt");
				
				
				if (!file1.exists()) {
					file1.createNewFile();
				}
				FileWriter fw = new FileWriter(file1.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	        Iterator<String> it1 = tm.keySet().iterator();
	        while(it1.hasNext())
	        {
	        	String key = (String) it1.next();
	        	Integer value = tm.get(key);
	        	
	        	try {
					bw.write(key+"="+value+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
		
		
		
		 //��Ƶ�ʵ�ͳ�� ����k
		 public ArrayList gaopin(TreeMap<String,Integer> tm,int k){
			
			 ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(tm.entrySet());
			 
			 Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
			  public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
			   return o2.getValue() - o1.getValue(); 
			    }  
			   }); 
			     //���ǰk����
			    for(int i = 0; i<k; i++){  
			     System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
			        }     
			   return list;
		 }
		 
		 //ͳ���ı��������ַ�
		 public List  statistics(String fileName) throws IOException{
		 
			String line = fileName;
			System.out.println(fileName);
			File file = new File(line);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader br = new BufferedReader(isr);
		  int charNum= 0 ;//�ַ���
		  int wordsNum= 0;//������
		  int lineNum = 0;//����
		  //��������ʽ�����ļ�
		  
		  while( br.read()!= -1){
		  String s = br.readLine();
		  charNum+=s.length();
		  wordsNum +=s.split(" ").length;
		  lineNum ++;
		  }
		  isr.close();//�ر�
		  List list=new ArrayList();
		  list.add(charNum);
		  list.add(wordsNum);
		  list.add(lineNum);
		  
		 // System.out.println("�ַ���:"+charNum+"\n������:"+wordsNum+"\n����:"+lineNum);
		  return list;
		 }
	}

