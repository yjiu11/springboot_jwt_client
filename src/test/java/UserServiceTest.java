import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjiu.Client8004_APP;
import com.yjiu.pojo.AccessToken;
import com.yjiu.pojo.Audience;
import com.yjiu.tools.HttpClientUtil;
import com.yjiu.tools.LoginPara;
import com.yjiu.tools.ResultMsg;

@RunWith(SpringJUnit4ClassRunner.class) // 让junit与spring环境进行整合
@SpringBootTest(classes = { Client8004_APP.class }) // 自动加载spring相关的配置文件
public class UserServiceTest {
	@Autowired
	private Audience audience;

	@Test
	public void testInterface() {
		String url = "http://localhost:8080/api/test";
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiMSIsInVuaXF1ZV9uYW1lIjoidGVzdCIsInVzZXJpZCI6InRlc3QiLCJpc3MiOiJyZXN0YXBpdXNlciIsImF1ZCI6IjA5OGY2YmNkNDYyMWQzNzNjYWRlNGU4MzI2MjdiNGY2IiwiZXhwIjoxNTQ0MjM2Njk3LCJuYmYiOjE1NDQwNjM4OTd9.FzhvwgNTAqA70nK2zIIoJ7E-9Ti1T_vCEiAJTim6ufk");
		Map<String, String> params = new HashMap<>();
		String result = HttpClientUtil.sendPost(url, headers, params);
		System.out.println(result);
	}

	@Test
	public void getToken() {
		String url = "http://localhost:8080/oauth/token";
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json;charset=UTF-8");
		LoginPara para = new LoginPara(audience.getClientId(),"test","test");
		JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(para).toString());
		String result = HttpClientUtil.sendPost(url, headers, obj);
		System.out.println(result);
		ResultMsg token = JSON.parseObject(result, ResultMsg.class);
		AccessToken tt = JSONObject.parseObject(JSONObject.toJSON(token.getP2pdata()).toString(), AccessToken.class);
		System.out.println(tt.getAccess_token());
	}
}