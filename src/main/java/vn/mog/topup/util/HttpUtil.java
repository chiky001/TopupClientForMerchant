package vn.mog.topup.util;

import org.springframework.web.client.RestTemplate;

public class HttpUtil {
	public static <T> T postForObject(final String url, final Object request, final Class<T> response) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate.postForObject(url, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KIEM TRA LAI DUONG TRUYEN...");
		}
		return null;
	}
}
