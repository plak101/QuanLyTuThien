package charity.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

public class ChartCustom {
    //design line chart

    public static void customizeLineChart(JFreeChart chart) {
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 18));
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setRangeGridlinePaint(Color.gray);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12));
        domainAxis.setLabelFont(new Font("Arial", Font.BOLD, 14));

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 12));
        rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 14));

        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(51, 153, 255));
        renderer.setSeriesStroke(0, new BasicStroke(2.5f));
        renderer.setDefaultShapesVisible(true);   // <-- thay vì setBaseShapesVisible
        renderer.setDefaultShapesFilled(true);

        chart.getLegend().setItemFont(new Font("Arial", Font.PLAIN, 12));
    }

    // design pie chart
    public static void customizePieChart(JFreeChart chart) {
        chart.getTitle().setFont(FontCustom.Arial18B());
        chart.setBackgroundPaint(Color.white);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);//tat bong

        //tuy chinh label hien thi dang: ten (so - phan tram)
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{0} ({2})", new DecimalFormat("#,##0"), new DecimalFormat("0.00%")
        ));
        plot.setLabelFont(FontCustom.Arial12());
        plot.setLabelBackgroundPaint(Color.white);
        plot.setLabelShadowPaint(null);
        plot.setOutlinePaint(null);

        //tuy chon vien cho tung phan
        plot.setSectionOutlinesVisible(true);
        plot.setDefaultSectionOutlinePaint(Color.white);

        //legend (chu thich)
        if (chart.getLegend() != null) {
            chart.getLegend().setItemFont(FontCustom.Arial12());
        }
    }

    public static void customizeBarChart(JFreeChart chart) {
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(FontCustom.Arial18B());
        chart.getLegend().setItemFont(FontCustom.Arial12());

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setOutlineVisible(false);

        // thiet ke truc X (nguoi quyen gop)
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(FontCustom.Arial12());
        domainAxis.setLabelFont(FontCustom.Arial12());
        domainAxis.setCategoryMargin(0.2);//khoang cach giua cac nhom

        //thiet ke truc y (Gia tri Tien)
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelFont(FontCustom.Arial12());
        rangeAxis.setLabelFont(new Font("Arial", Font.BOLD, 14));

        //renderer cho coot
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(0.1);//chieu rong cot toi da


        //hien thi gia tri tren cot
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelFont(FontCustom.Arial12());
        renderer.setDefaultItemLabelPaint(Color.BLACK);
    }
}
