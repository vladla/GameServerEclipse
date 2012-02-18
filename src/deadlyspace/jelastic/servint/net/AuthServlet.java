package deadlyspace.jelastic.servint.net;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import deadlyspace.jelastic.servint.net.core.dao.DAOConnector;
import deadlyspace.jelastic.servint.net.json.JSONException;
import deadlyspace.jelastic.servint.net.json.JSONObject;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends ServletBased {
	@Override
    protected ApiObject parse(String s) {
        RegApiObject ao = new RegApiObject();
        try {
        	//�� ����� ����� ���, ��� ����
            JSONObject jo = new JSONObject(s);
            String in_imei = jo.getString("imei");
            String in_appkey = jo.getString("appkey");
            String in_ip = jo.getString("ip");
            String in_login = jo.getString("login");
            String in_password = jo.getString("password");
            String in_email = jo.getString("email");

            String v = DAOConnector.runQuery("CALL isUserExists(" +
            		"'"+in_login+"'," +
            		"'"+in_password+"')");
            if (!"ok".equals(v)) {
            	throw new Exception(v);
            }
            ao.setStatus("ok");
            ao.setAppkey(in_appkey);
            ao.setMessage(v);
            System.out.println(v);
        } catch (Exception ex) {
            ao.setMessage(ex.getMessage());
            ao.setStatus("fail");
            ex.printStackTrace(System.err);
        }
        return ao;
    }

    private class RegApiObject extends ApiObject {
        @Override
        public String toJson() {
            Map<String, String> m = new HashMap<String, String>();
            m.put("session", genereteNewSess());
            m.put("message", this.getMessage());
            m.put("status", this.getStatus());
            m.put("appkey", this.getAppkey());
            return new JSONObject(m).toString();
        }
        
        private String genereteNewSess (){
        	java.util.Date today = new java.util.Date();
            java.sql.Timestamp ts1 = new java.sql.Timestamp(today.getTime());
            long tsTime1 = ts1.getTime();
        	return Long.toString(tsTime1);
        }
    }
}