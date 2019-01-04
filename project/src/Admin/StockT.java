package Admin;

import java.awt.*;
import javax.swing.*;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StockT extends JFrame implements ActionListener {
	static final int SCREEN_WIDTH = 1310;
	static final int SCREEN_HEGIHT = 500;
	StockDB sd = new StockDB();
	StockData sdata = new StockData();
	static JFrame stock;
	static JButton addorder;
	static ChartPanel cp1;
	static JPanel p1;
	static Chart demo;
	static JCheckBox jcb1, jcb2, jcb3, jcb4, jcb5, jcb6, jcb7, jcb8, jcb9, jcb10, jcb11, jcb12, jcb13, jcb14, jcb15,
			jcb16;
	static int str,total,min;
	static String[] m = new String[16];
	static ArrayList arr = new ArrayList<>(),arr1 = new ArrayList<>();
	Font lstfn = new Font("고딕", Font.BOLD, 11);

	public StockT() {
		stock = new JFrame("재고 수량");
		stock.setSize(SCREEN_WIDTH, SCREEN_HEGIHT);
		stock.setLayout(null);
		stock.setResizable(false);
		stock.setBackground(Color.white);
		stock.setLocation(610, 300);
		stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChartPanel1();
		Panel1();
		stock.setVisible(true);
	}

	public void ChartPanel1() {
		cp1 = new ChartPanel(null);
		cp1.setLayout(null);
		cp1.setBounds(0, 0, 1310, 400);
		cp1.setBackground(Color.white);

		demo = new Chart();
		JFreeChart chart = demo.getChart();
		ChartFrame frame1 = new ChartFrame("Bar Chart", chart);
		cp1.setChart(demo.getChart());
		stock.add(cp1);
	}

	public void Panel1() {
		p1 = new JPanel();
		p1.setBounds(0, 410, 1310, 90);
		p1.setLayout(null);
		p1.setBackground(new Color(255,0,0,0));
		addorder = new JButton("발주");
		addorder.setBounds(1240, 0, 60,55);
		addorder.addActionListener(this);
		p1.add(addorder);

		jcb1 = new JCheckBox("불고기");
		jcb1.setBounds(80, 0, 70, 55);
		jcb1.addItemListener(new MyItemListeners());
		jcb1.setFont(lstfn);
		p1.add(jcb1);

		jcb2 = new JCheckBox("새우버거");
		jcb2.setBounds(150, 0, 80, 55);
		jcb2.addItemListener(new MyItemListeners());
		jcb2.setFont(lstfn);
		p1.add(jcb2);

		jcb3 = new JCheckBox("치즈버거");
		jcb3.setBounds(230,0, 80, 55);
		jcb3.addItemListener(new MyItemListeners());
		jcb3.setFont(lstfn);
		p1.add(jcb3);

		jcb4 = new JCheckBox("치킨버거");
		jcb4.setBounds(310, 0, 80, 55);
		jcb4.addItemListener(new MyItemListeners());
		jcb4.setFont(lstfn);
		p1.add(jcb4);

		jcb5 = new JCheckBox("치즈스틱");
		jcb5.setBounds(390,0,80,55);
		jcb5.addItemListener(new MyItemListeners());
		jcb5.setFont(lstfn);
		p1.add(jcb5);

		jcb6 = new JCheckBox("치킨윙");
		jcb6.setBounds(470, 0, 70, 55);
		jcb6.addItemListener(new MyItemListeners());
		jcb6.setFont(lstfn);
		p1.add(jcb6);

		jcb7 = new JCheckBox("샐러드");
		jcb7.setBounds(540,0, 70, 55);
		jcb7.addItemListener(new MyItemListeners());
		jcb7.setFont(lstfn);
		p1.add(jcb7);

		jcb8 = new JCheckBox("감자튀김");
		jcb8.setBounds(610, 0, 80, 55);
		jcb8.addItemListener(new MyItemListeners());
		jcb8.setFont(lstfn);
		p1.add(jcb8);

		jcb9 = new JCheckBox(" 콜라");
		jcb9.setBounds(690,0, 60, 55);
		jcb9.addItemListener(new MyItemListeners());
		jcb9.setFont(lstfn);
		p1.add(jcb9);

		jcb10 = new JCheckBox("사이다");
		jcb10.setBounds(750,0, 70, 55);
		jcb10.addItemListener(new MyItemListeners());
		jcb10.setFont(lstfn);
		p1.add(jcb10);

		jcb11 = new JCheckBox(" 환타");
		jcb11.setBounds(820,0, 60, 55);
		jcb11.addItemListener(new MyItemListeners());
		jcb11.setFont(lstfn);
		p1.add(jcb11);

		jcb12 = new JCheckBox(" 커피");
		jcb12.setBounds(890,0, 60, 55);
		jcb12.addItemListener(new MyItemListeners());
		jcb12.setFont(lstfn);
		p1.add(jcb12);

		jcb13 = new JCheckBox("베이컨");
		jcb13.setBounds(960,0, 70, 55);
		jcb13.addItemListener(new MyItemListeners());
		jcb13.setFont(lstfn);
		p1.add(jcb13);

		jcb14 = new JCheckBox("치즈");
		jcb14.setBounds(1040,0, 60, 55);
		jcb14.addItemListener(new MyItemListeners());
		jcb14.setFont(lstfn);
		p1.add(jcb14);

		jcb15 = new JCheckBox("  햄");
		jcb15.setBounds(1110,0, 60, 55);
		jcb15.addItemListener(new MyItemListeners());
		jcb15.setFont(lstfn);
		p1.add(jcb15);

		jcb16 = new JCheckBox("계란");
		jcb16.setBounds(1180,0, 60, 55);
		jcb16.addItemListener(new MyItemListeners());
		jcb16.setFont(lstfn);
		p1.add(jcb16);

		stock.add(p1);
	}

	class MyItemListeners implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (jcb1.isSelected() == true) {
				m[0] = "불고기";
			} else if (jcb1.isSelected() == false) {
				m[0] = null;
			}
			if (jcb2.isSelected() == true) {
				m[1] = "새우버거";
			} else if (jcb2.isSelected() == false) {
				m[1] = null;
			}
			if (jcb3.isSelected() == true) {
				m[2] = "치즈버거";
			} else if (jcb3.isSelected() == false) {
				m[2] = null;
			}
			if (jcb4.isSelected() == true) {
				m[3] = "치킨버거";
			} else if (jcb4.isSelected() == false) {
				m[3] = null;
			}
			if (jcb5.isSelected() == true) {
				m[4] = "치즈스틱";
			} else if (jcb5.isSelected() == false) {
				m[4] = null;
			}
			if (jcb6.isSelected() == true) {
				m[5] = "치킨윙";
			} else if (jcb6.isSelected() == false) {
				m[5] = null;
			}
			if (jcb7.isSelected() == true) {
				m[6] = "샐러드";
			} else if (jcb7.isSelected() == false) {
				m[6] = null;
			}
			if (jcb8.isSelected() == true) {
				m[7] = "감자튀김";
			} else if (jcb8.isSelected() == false) {
				m[7] = null;
			}
			if (jcb9.isSelected() == true) {
				m[8] = "콜라";
			} else if (jcb9.isSelected() == false) {
				m[8] = null;
			}
			if (jcb10.isSelected() == true) {
				m[9] = "사이다";
			} else if (jcb10.isSelected() == false) {
				m[9] = null;
			}
			if (jcb11.isSelected() == true) {
				m[10] = "환타";
			} else if (jcb11.isSelected() == false) {
				m[10] = null;
			}
			if (jcb12.isSelected() == true) {
				m[11] = "커피";
			} else if (jcb12.isSelected() == false) {
				m[11] = null;
			}
			if (jcb13.isSelected() == true) {
				m[12] = "베이컨";
			} else if (jcb13.isSelected() == false) {
				m[12] = null;
			}
			if (jcb14.isSelected() == true) {
				m[13] = "치즈";
			} else if (jcb14.isSelected() == false) {
				m[13] = null;
			}
			if (jcb15.isSelected() == true) {
				m[14] = "햄";
			} else if (jcb15.isSelected() == false) {
				m[14] = null;
			}
			if (jcb16.isSelected() == true) {
				m[15] = "계란";
			} else if (jcb16.isSelected() == false) {
				m[15] = null;
				
			}
		}
	}
	void Test() {
		sd.SelTotal();
		sd.SelMinimum();
		ArrayList a = sd.SelCurrent(); int b = 0;
		
		for(int j =0;j<a.size();j++) {
			StockData d = (StockData) a.get(j);
			str = d.getCurrent();
			int dif = total - str;
		if(j == m.length-1) { // j가 m.length-1 일때
			if(arr.isEmpty() == false) {
				if(arr1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "발주할 수 있는 제품이 없습니다. \n 발주는 재고가 30%이하인 제품만 가능합니다.", "발주 불가!", JOptionPane.ERROR_MESSAGE);
				}else {
				JOptionPane.showMessageDialog(null, arr.toString()+"을(를) 제외한"+arr1.toString()+"이(가) 발주가 완료되었습니다. \n 최대 3일 정도 소요 됩니다.", "발주 완료!", JOptionPane.INFORMATION_MESSAGE);
				}
			}else {
				if(arr1.isEmpty() == false) {
					JOptionPane.showMessageDialog(null,arr1.toString()+"이(가) 발주가 완료되었습니다. \n 최대 3일 정도 소요 됩니다.", "발주 완료!", JOptionPane.INFORMATION_MESSAGE);
				}else {
				JOptionPane.showMessageDialog(null, "발주할 제품을 선택해 주세요.", "발주 불가!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else {
			if(m[j] != null) { 
				if(dif + min >= total) {
					sd.UpdateCur(m[j]);
					arr1.add(m[j]);
				}else{
					arr.add(m[j]);
				}
			} // m[j] 가 null 이 아닐때
			else if(m[j] == null) {} //아무런 동작 없음.
		} // j 가 m.length -1 일떄의 else 끝		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addorder) {
			Test();
			arr.clear();
			arr1.clear();
			jcb1.setSelected(false);jcb2.setSelected(false);jcb3.setSelected(false);jcb4.setSelected(false);jcb5.setSelected(false);
			jcb6.setSelected(false);jcb7.setSelected(false);jcb8.setSelected(false);jcb9.setSelected(false);jcb10.setSelected(false);
			jcb11.setSelected(false);jcb12.setSelected(false);jcb13.setSelected(false);jcb14.setSelected(false);jcb15.setSelected(false);
			jcb16.setSelected(false);
		}
	}
}
