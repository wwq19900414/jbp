package com.thestore.eam.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {
	public static List<String> getImgsFromHtml(String pageUrl) {
		List<String> imgUrllist = new ArrayList<String>();

		try {
			URL url = new URL(pageUrl);
			Document doc2 = Jsoup.parse(url, 100000);
			Elements links = doc2.select("img[src]");

			Set<String> imgSet = new HashSet<String>();
			for (Element link : links) {
				String l = link.attr("src");
				if (!imgSet.contains(l)) {
					imgUrllist.add(l);
					imgSet.add(l);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imgUrllist;

	}

	public static void main(String[] wwq) throws Exception {
		List<String> list = getImgsFromHtml("http://www.yhd.com");
		for (String img : list) {
			System.out.println(img);
		}

	}

}
