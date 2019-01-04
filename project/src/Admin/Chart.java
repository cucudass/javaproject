package Admin;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

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

/**
 * A simple demonstration application showing how to create a bar chart overlaid
 * with a line chart.
 */

public class Chart {   
	static Timer texit;
	static TimerTask ttask;
       // Run As > Java Application 으로 실행하면 바로 확인할 수 있음.
	static int cur;
	StockDB sd;
	DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1
	public JFreeChart getChart() {
        // 데이터 생성
		sd = new StockDB();
        

    	texit = new Timer();
		ttask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
		        ChartAdd();        // 데이터 입력 반복 ( 값, 범례, 카테고리 )     
			}
		};
		texit.schedule(ttask, 4500,2000);
		ChartAdd(); // 데이터 입력 ( 값, 범례, 카테고리 )     
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();

        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 10);

        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);

        renderer.setSeriesPaint(0, new Color(0,162,255));

        // plot 생성
        final CategoryPlot plot = new CategoryPlot();

        // plot 에 데이터 적재

        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);             // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                       // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                      // Y축 가이드 라인 표시여부

        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());              // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // 카테고리 라벨 위치 조정

        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());                 // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정

        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        return chart;
    }
	void ChartAdd() {
		dataset1.addValue(sd.SelectCur("불고기"), "수량(단위 : %)", "불고기");
        dataset1.addValue(sd.SelectCur("새우버거"),"수량(단위 : %)", "새우버거");
        dataset1.addValue(sd.SelectCur("치즈버거"), "수량(단위 : %)", "치즈버거");
        dataset1.addValue(sd.SelectCur("치킨버거"), "수량(단위 : %)", "치킨버거");
        dataset1.addValue(sd.SelectCur("치즈스틱"), "수량(단위 : %)", "치즈스틱");
        dataset1.addValue(sd.SelectCur("치킨윙"), "수량(단위 : %)", "치킨윙");
        dataset1.addValue(sd.SelectCur("샐러드"), "수량(단위 : %)", "샐러드");
        dataset1.addValue(sd.SelectCur("감자튀김"), "수량(단위 : %)", "감자튀김");
        dataset1.addValue(sd.SelectCur("콜라"), "수량(단위 : %)", "콜라");
        dataset1.addValue(sd.SelectCur("사이다"), "수량(단위 : %)", "사이다");
        dataset1.addValue(sd.SelectCur("환타"), "수량(단위 : %)", "환타");
        dataset1.addValue(sd.SelectCur("커피"), "수량(단위 : %)", "커피");
        dataset1.addValue(sd.SelectCur("베이컨"), "수량(단위 : %)", "베이컨");
        dataset1.addValue(sd.SelectCur("치즈"), "수량(단위 : %)", "치즈");
        dataset1.addValue(sd.SelectCur("햄"), "수량(단위 : %)", "햄");
        dataset1.addValue(sd.SelectCur("계란"), "수량(단위 : %)", "계란");
	}

    
}
