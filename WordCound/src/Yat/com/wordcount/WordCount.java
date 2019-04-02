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
	

		//统计所有单词
		public TreeMap  upload(String fileName) throws ServletException, IOException{
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			String line = fileName;
			File file = new File(line);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader reader = new BufferedReader(isr);
			//将文本中的英文词语放在集合里面
	        while((line=reader.readLine())!=null){
	        	line = line.toLowerCase();//忽略大小写
	        	String[] str = line.split("[^a-zA-Z]");//过滤出只含有字母的  
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
		
		//查找单词之后进行与词群里面查找单词的个数
		public TreeMap  bijiao(TreeMap<String, Integer> tm,String words) {//在这个方法中，需要传入的是之前存入单词的集合，用户输入的字符串
			TreeMap<String,Integer> map1 = new TreeMap<String, Integer>();  
			String[] word= words.split(";");//通过分号来截取用户传入的字符串
	        int i;
	        for(i=0; i<word.length; i++) {
	        	for(Entry<String,Integer> entry : tm.entrySet()) { 
	        		if(word[i].equals(entry.getKey()))//与集合中的单词比较
	        		{
	        			map1.put(entry.getKey(), entry.getValue());
	        			//System.out.println(entry.getKey() + "的个数是" + entry.getValue()); 
	        			break;
	        		}
	            } 
	        }
			return map1;
	        
	    }
		
		//柱状图的显示
			public WordCount(){
				super();
				setTitle("绘制柱形图");
				setBounds(3, 200, 450, 450);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			public void paint(Graphics g,TreeMap<String, Integer> tm,String [] ss){
				int Width = getWidth();
				int Height = getHeight();
				int leftMargin = 50;//柱形图左边界
				int topMargin = 50;//柱形图上边界
				Graphics2D g2 = (Graphics2D) g;
				int ruler = Height-topMargin;
				int rulerStep = ruler/20;//将当前的高度评分为20个单位
				g2.setColor(Color.WHITE);//绘制白色背景
				g2.fillRect(0, 0, Width, Height);//绘制矩形图
				g2.setColor(Color.LIGHT_GRAY);
				for(int i=0;i<rulerStep;i++){
					g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
				}
				g2.setColor(Color.RED);
				int m=0;
				for(int i = 0;i<ss.length;i++){
					int value = tm.get(ss[i]);
					int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
					g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
					g2.drawString(ss[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
					m++;
				}
			}
		
		
		//单词的存放
		public void cunfang(TreeMap<String, Integer> tm){

			//统计该文本所有单词数量及词频数，并能将单词及词频数按字典顺序输出到文件result.txt
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
		
		
		
		 //高频词的统计 整数k
		 public ArrayList gaopin(TreeMap<String,Integer> tm,int k){
			
			 ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(tm.entrySet());
			 
			 Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){  
			  public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
			   return o2.getValue() - o1.getValue(); 
			    }  
			   }); 
			     //输出前k个数
			    for(int i = 0; i<k; i++){  
			     System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
			        }     
			   return list;
		 }
		 
		 //统计文本行数与字符
		 public List  statistics(String fileName) throws IOException{
		 
			String line = fileName;
			System.out.println(fileName);
			File file = new File(line);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader br = new BufferedReader(isr);
		  int charNum= 0 ;//字符数
		  int wordsNum= 0;//数字数
		  int lineNum = 0;//行数
		  //以流的形式读入文件
		  
		  while( br.read()!= -1){
		  String s = br.readLine();
		  charNum+=s.length();
		  wordsNum +=s.split(" ").length;
		  lineNum ++;
		  }
		  isr.close();//关闭
		  List list=new ArrayList();
		  list.add(charNum);
		  list.add(wordsNum);
		  list.add(lineNum);
		  
		 // System.out.println("字符数:"+charNum+"\n单词数:"+wordsNum+"\n行数:"+lineNum);
		  return list;
		 }
	}

