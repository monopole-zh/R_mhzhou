import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartHash {

	public ChartHash() {
	}

	ChartPanel chartPanel = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createPort(String title, Map<String, Map<String, Double>> datas, String type, String danwei,
			Font font) {
		try {
			DefaultCategoryDataset ds = new DefaultCategoryDataset();

			Set<Entry<String, Map<String, Double>>> set1 = datas.entrySet();
			Iterator iterator1 = (Iterator) set1.iterator();
			Iterator iterator2 = null;
			HashMap<String, Double> map = null;
			Set<Entry<String, Double>> set2 = null;
			Entry entry1 = null;
			Entry entry2 = null;

			while (iterator1.hasNext()) {
				entry1 = (Entry) iterator1.next();
				map = (HashMap<String, Double>) entry1.getValue();
				set2 = map.entrySet();
				iterator2 = set2.iterator();
				while (iterator2.hasNext()) {
					entry2 = (Entry) iterator2.next();
					ds.setValue(Double.parseDouble(entry2.getValue().toString()), entry2.getKey().toString(),
							entry1.getKey().toString());
				}
			}

			JFreeChart chart = ChartFactory.createLineChart(title, type, danwei, ds, PlotOrientation.VERTICAL, true,
					true, false);

			chart.getTitle().setFont(font);

			font = new Font("宋体", Font.BOLD, 15);
			chart.getLegend().setItemFont(font);

			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			plot.getDomainAxis().setLabelFont(font);

			plot.getDomainAxis().setTickLabelFont(font);

			font = new Font("宋体", Font.BOLD, 18);
			plot.getRangeAxis().setLabelFont(font);

			plot.setForegroundAlpha(1.0f);
			chartPanel = new ChartPanel(chart, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	public ChartPanel makeAChartHash() {
		Font font = new Font("宋体", Font.BOLD, 20);
		createPort("Hash算法性能分析", HashPerformance.getAESDatas(), "文件大小", "运行时间(ms)", font);
		return getChartPanel();
	}

	static class HashPerformance {

		public static void creatFile() {
			try {
				RandomAccessFile creatFile10m = new RandomAccessFile("F://10m.txt", "rw");
				creatFile10m.setLength(10 * 1024 * 1024);
				RandomAccessFile creatFile100m = new RandomAccessFile("F://100m.txt", "rw");
				creatFile100m.setLength(100 * 1024 * 1024);
				RandomAccessFile creatFile500m = new RandomAccessFile("F://500m.txt", "rw");
				creatFile500m.setLength(500 * 1024 * 1024);
				RandomAccessFile creatFile1g = new RandomAccessFile("F://1g.txt", "rw");
				creatFile1g.setLength(1024 * 1024 * 1024);
				creatFile10m.close();
				creatFile100m.close();
				creatFile500m.close();
				creatFile1g.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		public static Double getTimeLength(String fileName, String hashType) {
			long begin = 0;
			long end = 0;
			try {
				begin = 0;
				end = 0;
				FileInputStream fileInputStream = new FileInputStream(fileName);
				MessageDigest messageDigest = MessageDigest.getInstance(hashType);
				DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
				byte[] buffer = new byte[4096];
				begin = System.currentTimeMillis();
				while (dInputStream.read(buffer) != -1)
					;
				dInputStream.close();
				end = System.currentTimeMillis();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			return (double) (end - begin);

		}

		public static Map<String, Map<String, Double>> getAESDatas() {
			Map<String, Map<String, Double>> datas = new HashMap<String, Map<String, Double>>();

			Map<String, Double> map1 = new HashMap<String, Double>();
			Map<String, Double> map2 = new HashMap<String, Double>();
			Map<String, Double> map3 = new HashMap<String, Double>();
			Map<String, Double> map4 = new HashMap<String, Double>();

			map1.put("SHA-1", getTimeLength("F://10m.txt", "SHA-1"));
			map1.put("SHA-256", getTimeLength("F://10m.txt", "SHA-256"));
			map1.put("SHA-384", getTimeLength("F://10m.txt", "SHA-384"));
			map1.put("SHA-512", getTimeLength("F://10m.txt", "SHA-512"));

			map2.put("SHA-1", getTimeLength("F://100m.txt", "SHA-1"));
			map2.put("SHA-256", getTimeLength("F://100m.txt", "SHA-256"));
			map2.put("SHA-384", getTimeLength("F://100m.txt", "SHA-384"));
			map2.put("SHA-512", getTimeLength("F://100m.txt", "SHA-512"));

			map3.put("SHA-1", getTimeLength("F://500m.txt", "SHA-1"));
			map3.put("SHA-256", getTimeLength("F://500m.txt", "SHA-256"));
			map3.put("SHA-384", getTimeLength("F://500m.txt", "SHA-384"));
			map3.put("SHA-512", getTimeLength("F://500m.txt", "SHA-512"));

			map4.put("SHA-1", getTimeLength("F://1g.txt", "SHA-1"));
			map4.put("SHA-256", getTimeLength("F://1g.txt", "SHA-256"));
			map4.put("SHA-384", getTimeLength("F://1g.txt", "SHA-384"));
			map4.put("SHA-512", getTimeLength("F://1g.txt", "SHA-512"));

			datas.put("10M", map1);
			datas.put("100M", map2);
			datas.put("500M", map3);
			datas.put("1G", map4);
			return datas;
		}
	}
}
