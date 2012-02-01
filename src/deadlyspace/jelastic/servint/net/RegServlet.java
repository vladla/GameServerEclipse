/*
 * 
 */
package deadlyspace.jelastic.servint.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import deadlyspace.jelastic.servint.net.json.JSONException;
import deadlyspace.jelastic.servint.net.json.JSONObject;

/**
 *
 * @author vladla
 */
@WebServlet(name = "RegServlet", urlPatterns = {"/reg"})
public class RegServlet extends ServletBased {

    @Override
    protected ApiObject parse(String s) {
        RegApiObject ao = new RegApiObject();
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

    private class RegApiObject extends ApiObject {

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
            m.put("session", "sadsad");
            m.put("message", "All ok. RegServlet work good");
            m.put("status", "ok");
            m.put("appkey", "!ja34nasjal2@#$nhfsjdk@");

            return new JSONObject(m).toString();
        }
    }
}
