package com.cn.cooxin.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.alibaba.fastjson.JSONObject;

/**
 * ClassName: Youtu(鑵捐浼樺浘宸ュ叿绫�
 * @Version 1.0
 * @Author: airufei
 * date: 2016骞�鏈�鏃�涓婂崍9:34:37
 */
public class YoutuUtil {
	
	private static class TrustAnyTrustManager implements X509TrustManager {
		
		public void checkClientTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		}
		
		public void checkServerTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		}
		
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}
	
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * http
	 */
	public final static  String API_YOUTU_END_POINT = "http://api.youtu.qq.com/youtu/";
	
	/**
	 * https
	 */
	public final static String API_TENCENTYUN_END_POINT = "https://youtu.api.qcloud.com/youtu/";
	// 30 days
	private static int EXPIRED_SECONDS = 2592000;
	private String m_appid;
	private String m_secret_id;
	private String m_secret_key;
	private String m_end_point;
	private boolean m_use_youtu;
	
	/**
	 * PicCloud 鏋勯�鏂规硶
	 * 
	 * @param appid
	 *            鎺堟潈appid
	 * @param secret_id
	 *            鎺堟潈secret_id
	 * @param secret_key
	 *            鎺堟潈secret_key
	 */
	public YoutuUtil(String appid, String secret_id, String secret_key,String end_point) {
		m_appid = appid;
		m_secret_id = secret_id;
		m_secret_key = secret_key;
		m_end_point=end_point;
		m_use_youtu=!end_point.startsWith("https");
	}
	
	
	
	/**
	 * StatusText:(鎺ュ彛鐘舵�鐮�
	 * @Author airufei
	 * @param status
	 * @return
	 */
	public String StatusText(int status) {
		
		String statusText = "UNKOWN";

        switch (status)
        {
        	case 0:
                statusText = "CONNECT_FAIL";
                break;
            case 200:
                statusText = "HTTP OK";
                break;
            case 400:
                statusText = "BAD_REQUEST";
                break;
            case 401:
                statusText = "UNAUTHORIZED";
                break;
            case 403:
                statusText = "FORBIDDEN";
                break;
            case 404:
                statusText = "NOTFOUND";
                break;
            case 411:
                statusText = "REQ_NOLENGTH";
                break;
            case 423:
                statusText = "SERVER_NOTFOUND";
                break;
            case 424:
                statusText = "METHOD_NOTFOUND";
                break;
            case 425:
                statusText = "REQUEST_OVERFLOW";
                break;
            case 500:
                statusText = "INTERNAL_SERVER_ERROR";
                break;
            case 503:
                statusText = "SERVICE_UNAVAILABLE";
                break;
            case 504:
                statusText = "GATEWAY_TIME_OUT";
                break;
        }
        return statusText;		
	}
	

	private void GetBase64FromFile(String filePath, StringBuffer base64)
	throws IOException {
		File imageFile = new File(filePath);
		if (imageFile.exists()) {
			InputStream in = new FileInputStream(imageFile);
			byte data[] = new byte[(int) imageFile.length()]; // 鍒涘缓鍚堥�鏂囦欢澶у皬鐨勬暟缁�			in.read(data); // 璇诲彇鏂囦欢涓殑鍐呭鍒癰[]鏁扮粍
			in.close();
			base64.append(Base64Util.encode(data));

		} else {
			throw new FileNotFoundException(filePath + " not exist");
		}

	}
	
	private JSONObject SendHttpRequest(JSONObject postData, String mothod)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		StringBuffer mySign = new StringBuffer("");
		long exptime=System.currentTimeMillis() / 1000 + EXPIRED_SECONDS;
		YoutuSign.Sign(m_appid, m_secret_id, m_secret_key,exptime,"", mySign);
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		URL url = new URL(m_end_point + mothod);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("Host", "api.youtu.qq.com");
		connection.setRequestProperty("user-agent", "youtu-java-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();

		// POST璇锋眰
		DataOutputStream out = new DataOutputStream(
			connection.getOutputStream());

		postData.put("app_id", m_appid);
		out.write(postData.toString().getBytes("utf-8"));
		//out.writeBytes(postData.toString());
		out.flush();
		out.close();
		// 璇诲彇鍝嶅簲
		BufferedReader reader = new BufferedReader(new InputStreamReader(
			connection.getInputStream()));
		String lines;
		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
		
		reader.close();
		// 鏂紑杩炴帴
		connection.disconnect();

		JSONObject respose =  JSONObject.parseObject(resposeBuffer.toString());

		return respose;

	}
	

	private  JSONObject SendHttpsRequest(JSONObject postData,String mothod)
	throws NoSuchAlgorithmException, KeyManagementException,
	IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
			new java.security.SecureRandom());
		
		StringBuffer mySign = new StringBuffer("");
		YoutuSign.Sign(m_appid, m_secret_id, m_secret_key,
			System.currentTimeMillis() / 1000 + EXPIRED_SECONDS,
			"", mySign);

		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		
		URL url = new URL(m_end_point + mothod);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setSSLSocketFactory(sc.getSocketFactory());
		connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
     // set header
		connection.setRequestMethod("POST");
		connection.setRequestProperty("accept", "*/*");
		connection.setRequestProperty("Host", "youtu.api.qcloud.com");
		connection.setRequestProperty("user-agent", "youtu-java-sdk");
		connection.setRequestProperty("Authorization", mySign.toString());

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "text/json");
		connection.connect();
		
    	// POST璇锋眰
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());

		postData.put("app_id", m_appid);
		out.write(postData.toString().getBytes("utf-8"));
		// 鍒锋柊銆佸叧闂�		out.flush();
		out.close();
		
		// 璇诲彇鍝嶅簲
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String lines;
		StringBuffer resposeBuffer = new StringBuffer("");
		while ((lines = reader.readLine()) != null) {
			lines = new String(lines.getBytes(), "utf-8");
			resposeBuffer.append(lines);
		}
     	
		reader.close();
     	// 鏂紑杩炴帴
		connection.disconnect();

		JSONObject respose = JSONObject.parseObject(resposeBuffer.toString());

		return respose;
	}

	
	/**
	 * DetectFace:(浜鸿劯妫�祴)
	 * @Author airufei
	 * @param image_path
	 * @param mode
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public JSONObject DetectFace(String image_path,int mode) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		data.put("mode", mode);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/detectface"):SendHttpsRequest(data, "api/detectface");

		return respose;
	}
	
	
	public JSONObject DetectFaceUrl(String url, int mode)
	throws IOException, KeyManagementException,
	NoSuchAlgorithmException {
		JSONObject data = new JSONObject();
		data.put("url", url);
		data.put("mode", mode);
		JSONObject respose = m_use_youtu ? SendHttpRequest(data, "api/detectface")
		: SendHttpsRequest(data, "api/detectface");

		return respose;
	}

	
	
	public JSONObject FaceShape(String image_path,int mode) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException  {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		data.put("mode", mode);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceshape"):SendHttpsRequest(data, "api/faceshape");

		return respose;
	}
	
	public JSONObject FaceShapeUrl(String url,int mode) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException  {

		JSONObject data = new JSONObject();
		data.put("url", url);
		data.put("mode", mode);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceshape"):SendHttpsRequest(data, "api/faceshape");

		return respose;
	}
	
	public JSONObject FaceCompare(String image_path_a, String image_path_b)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path_a, image_data);
		data.put("imageA", image_data.toString());
		image_data.setLength(0);

		GetBase64FromFile(image_path_b, image_data);
		data.put("imageB", image_data.toString());
		
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/facecompare"):SendHttpsRequest(data, "api/facecompare");

		return respose;
	}
	
	public JSONObject FaceCompareUrl(String urlA, String urlB)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {

		JSONObject data = new JSONObject();

		data.put("urlA", urlA);
		data.put("urlB", urlB);
		
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/facecompare"):SendHttpsRequest(data, "api/facecompare");

		return respose;
	}

	public JSONObject FaceVerify(String image_path, String person_id)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {

		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("person_id", person_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceverify"):SendHttpsRequest(data, "api/faceverify");

		return respose;
	}

	public JSONObject FaceVerifyUrl(String url, String person_id)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {

		JSONObject data = new JSONObject();

		data.put("url", url);

		data.put("person_id", person_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceverify"):SendHttpsRequest(data, "api/faceverify");

		return respose;
	}

	public JSONObject FaceIdentify(String image_path, String group_id)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("group_id", group_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceidentify"):SendHttpsRequest(data, "api/faceidentify");

		return respose;
	}

	public JSONObject FaceIdentifyUrl(String url, String group_id)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();
		data.put("url", url);
		data.put("group_id", group_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/faceidentify"):SendHttpsRequest(data, "api/faceidentify");

		return respose;
	}

	public JSONObject NewPerson(String image_path, String person_id,
		List<String> group_ids) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		image_data.setLength(0);

		data.put("person_id", person_id);
		data.put("group_ids", group_ids);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/newperson"):SendHttpsRequest(data, "api/newperson");

		return respose;
	}

	public JSONObject NewPersonUrl(String url, String person_id,
		List<String> group_ids) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();
		data.put("url", url);

		data.put("person_id", person_id);
		data.put("group_ids", group_ids);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/newperson"):SendHttpsRequest(data, "api/newperson");

		return respose;
	}

	public JSONObject DelPerson(String person_id) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {

		JSONObject data = new JSONObject();

		data.put("person_id", person_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/delperson"):SendHttpsRequest(data, "api/delperson");

		return respose;
	}

	public JSONObject AddFace(String person_id, List<String> image_path_arr)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();
		List<String> images = new ArrayList<String>();
		for (String image_path : image_path_arr) {
			image_data.setLength(0);
			GetBase64FromFile(image_path, image_data);
			images.add(image_data.toString());
		}

		data.put("images", images);
		image_data.setLength(0);

		data.put("person_id", person_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/addface"):SendHttpsRequest(data, "api/addface");

		return respose;
	}

	public JSONObject AddFaceUrl(String person_id, List<String> url_arr)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();
		
		data.put("urls", url_arr);
		data.put("person_id", person_id);

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/addface"):SendHttpsRequest(data, "api/addface");

		return respose;
	}

	public JSONObject DelFace(String person_id, List<String> face_id_arr)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {

		JSONObject data = new JSONObject();

		data.put("face_ids", face_id_arr);
		data.put("person_id", person_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/delface"):SendHttpsRequest(data, "api/delface");

		return respose;

	}

	public JSONObject SetInfo(String person_name, String person_id)
	throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("person_name", person_name);
		data.put("person_id", person_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/setinfo"):SendHttpsRequest(data, "api/setinfo");

		return respose;

	}

	public JSONObject GetInfo(String person_id) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("person_id", person_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/getinfo"):SendHttpsRequest(data, "api/getinfo");

		return respose;
	}

	public JSONObject GetGroupIds() throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/getgroupids"):SendHttpsRequest(data, "api/getgroupids");

		return respose;
	}

	public JSONObject GetPersonIds(String group_id) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("group_id", group_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/getpersonids"):SendHttpsRequest(data, "api/getpersonids");

		return respose;
	}

	public JSONObject GetFaceIds(String person_id) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("person_id", person_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/getfaceids"):SendHttpsRequest(data, "api/getfaceids");

		return respose;
	}

	public JSONObject GetFaceInfo(String face_id) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("face_id", face_id);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "api/getfaceinfo"):SendHttpsRequest(data, "api/getfaceinfo");

		return respose;
	}


	public JSONObject FuzzyDetect(String image_path) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());

		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/fuzzydetect"):SendHttpsRequest(data, "imageapi/fuzzydetect");

		return respose;
	}

	public JSONObject FuzzyDetectUrl(String url) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("url", url);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/fuzzydetect"):SendHttpsRequest(data, "imageapi/fuzzydetect");
		return respose;
	}

	public JSONObject FoodDetect(String image_path) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/fooddetect"):SendHttpsRequest(data, "imageapi/fooddetect");
		return respose;
	}

	public JSONObject FoodDetectUrl(String url) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();

		data.put("url", url);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/fooddetect"):SendHttpsRequest(data, "imageapi/fooddetect");
		return respose;
	}


	public JSONObject ImageTag(String image_path) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		StringBuffer image_data = new StringBuffer("");
		JSONObject data = new JSONObject();

		GetBase64FromFile(image_path, image_data);
		data.put("image", image_data.toString());
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/imagetag"):SendHttpsRequest(data, "imageapi/imagetag");
		return respose;
	}

	public JSONObject ImageTagUrl(String url) throws IOException,
	 KeyManagementException, NoSuchAlgorithmException {
		JSONObject data = new JSONObject();
		data.put("url", url);
		JSONObject respose =m_use_youtu?SendHttpRequest(data, "imageapi/imagetag"):SendHttpsRequest(data, "imageapi/imagetag");
		return respose;
	}


}