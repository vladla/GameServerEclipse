package deadlyspace.jelastic.servint.net;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import deadlyspace.jelastic.servint.net.json.JSONException;
import deadlyspace.jelastic.servint.net.json.JSONObject;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends ServletBased {

    @Override
    protected ApiObject parse(String s) {
        AuthApiObject ao = new AuthApiObject();
        try {
            JSONObject jo = new JSONObject(s);
            ao.setAppkey(jo.getString("appkey"));
            ao.setImei(jo.getString("imei"));
            ao.setIp(jo.getString("ip"));
            ao.setLogin(jo.getString("login"));
            ao.setPassword(jo.getString("password"));
            ao.setEmail(jo.getString("email"));
        } catch (JSONException ex) {
            ao.setMessage("parse error" + ex.getMessage());
            ao.setStatus("fail");
            ex.printStackTrace(System.err);
        }
        return ao;
    }

    private class AuthApiObject extends ApiObject {

        private String imei = "", login = "", ip = "", password ="", email = "";

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }
        
        public String getIp() {
        	return ip==null?"":ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getPassword() {
			return password==null?"":password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email==null?"":email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLogin() {
            return login==null?"":login;		
        }

		public void setLogin(String login) {
			this.login = login;
		}

        @Override
        public String toJson() {
            Map m = new HashMap();
//            m.put("session", this.getSession());
//            m.put("message", this.getMessage());
//            m.put("status", this.getStatus());
//            m.put("appkey", this.getAppkey());
            m.put("session", "11111111");
            m.put("message", "all ok. AuthServlet work good");
            m.put("status", "ok");
            m.put("appkey", "!ja34nasjal2@#$nhfsjdk@");

            return new JSONObject(m).toString();
        }
    }
}