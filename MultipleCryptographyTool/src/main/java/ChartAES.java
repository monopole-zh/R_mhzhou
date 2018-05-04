import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartAES {
	public ChartAES() {
	}
	public ChartPanel chartPanel = null;
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

	public ChartPanel makeAChartAES() {
		Font font = new Font("宋体", Font.BOLD, 20);

		createPort("AES算法性能分析", AESPerformance.getAESDatas(), "文件大小", "运行时间(ms)", font);
		return getChartPanel();
	}

	static class AESPerformance {
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

		public static Double getTimeLength(String fileName, int encryptedLength) {

			long begin = 0;
			long end = 0;
			try {
				FileInputStream fileInputStream = new FileInputStream(fileName);
				FileOutputStream fileOutputStream = new FileOutputStream("f://enc.tex");

				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

				keyGenerator.init(encryptedLength);

				Key key = keyGenerator.generateKey();

				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, key);

				CipherInputStream cInputStream = new CipherInputStream(fileInputStream, cipher);

				byte[] buffer = new byte[4096];
				int n = 0;
				begin = System.currentTimeMillis();
				while ((n = cInputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, n);
				}
				end = System.currentTimeMillis();

				fileOutputStream.close();
				cInputStream.close();
			} catch (InvalidKeyException e) {

				e.printStackTrace();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			} catch (NoSuchPaddingException e) {

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

			map1.put("AES-128", getTimeLength("F://10m.txt", 128));
			map1.put("AES-192", getTimeLength("F://10m.txt", 192));
			map1.put("AES-256", getTimeLength("F://10m.txt", 256));

			map2.put("AES-128", getTimeLength("F://100m.txt", 128));
			map2.put("AES-192", getTimeLength("F://100m.txt", 192));
			map2.put("AES-256", getTimeLength("F://100m.txt", 256));

			map3.put("AES-128", getTimeLength("F://500m.txt", 128));
			map3.put("AES-192", getTimeLength("F://500m.txt", 192));
			map3.put("AES-256", getTimeLength("F://500m.txt", 256));

			map4.put("AES-128", getTimeLength("F://1g.txt", 128));
			map4.put("AES-192", getTimeLength("F://1g.txt", 192));
			map4.put("AES-256", getTimeLength("F://1g.txt", 256));

			datas.put("10M", map1);
			datas.put("100M", map2);
			datas.put("500M", map3);
			datas.put("1G", map4);
			return datas;
		}
	}
}
