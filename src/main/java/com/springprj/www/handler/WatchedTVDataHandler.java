package com.springprj.www.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.map.HashedMap;

import com.springprj.www.domain.movietv.TVVO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class WatchedTVDataHandler {
	private int actionAdventure = 0;
	private int animation = 0;
	private int comedy = 0;
	private int crime = 0;
	private int documentary = 0;
	private int drama = 0;
	private int family = 0;
	private int kids = 0;
	private int mistery = 0;
	private int news = 0;
	private int reality = 0;
	private int sf = 0;
	private int soap = 0;
	private int talk = 0;
	private int warPolitics = 0;
	private int western = 0;
	
	public List<Entry<String, Integer>> getGenres() {
		Map<String, Integer> map = new HashedMap<String, Integer>(){{
			put("actionAdventure" , actionAdventure);
			put("animation", animation);
			put("comedy" , comedy);
			put("crime", crime);
			put("documentary", documentary);
			put("drama", drama);
			put("family", family);
			put("kids", kids);
			put("mistery", mistery);
			put("news", news);
			put("reality", reality);
			put("sf", sf);
			put("soap", soap);
			put("talk", talk);
			put("warPolitics", warPolitics);
			put("western", western);
		}};
		
		List<Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			public int compare (Entry<String, Integer> obj1, Entry<String, Integer> obj2) {
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		return entries;
	}

	public WatchedTVDataHandler(List<TVVO> list) {
		for (TVVO tvvo : list) {
			String[] strArr = tvvo.getGenres().split(",");
			for (String gen : strArr) {

				switch (gen) {
				case "10759":
					actionAdventure++;
					break;
				case "16":
					animation++;
					break;
				case "35":
					comedy++;
					break;
				case "80":
					crime++;
					break;
				case "99":
					documentary++;
					break;
				case "18":
					drama++;
					break;
				case "10762":
					kids++;
					break;
				case "9648":
					mistery++;
					break;
				case "10763":
					news++;
					break;
				case "10764":
					reality++;
					break;
				case "10765":
					sf++;
					break;
				case "10766":
					soap++;
					break;
				case "10767":
					talk++;
					break;
				case "10768":
					warPolitics++;
					break;
				case "37":
					western++;
					break;

				default:
					break;
				}
			}
		}
	}

}
