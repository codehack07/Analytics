package com.analytics.elastic.learn;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticSearchClient {

	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws UnknownHostException {
		
		
		System.out.println("hello");
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		
		String json = "{" +
		        "\"user\":\"kimchy\"," +
		        "\"postDate\":\"2013-01-30\"," +
		        "\"message\":\"trying out Elasticsearch\"" +
		    "}";
		
		client.prepareIndex("story_card_view", "news","1")
		.setSource(json).get();
		
		GetResponse getResponse = client.prepareGet("story_card_view","news","1").get();
		
        System.out.println(getResponse);
		System.out.println(client.toString());
		
        client.close();
		
	}

	public static Map<String, Object> putJsonDocument(String title, String content, Date postDate,

			String[] tags, String author) {

		Map<String, Object> jsonDocument = new HashMap<String, Object>();

		jsonDocument.put("title", title);

		jsonDocument.put("conten", content);

		jsonDocument.put("postDate", postDate);

		jsonDocument.put("tags", tags);

		jsonDocument.put("author", author);

		return jsonDocument;

	}
}
