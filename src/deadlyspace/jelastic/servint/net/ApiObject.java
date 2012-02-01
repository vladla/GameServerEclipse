package deadlyspace.jelastic.servint.net;

/**
*
* @author vladla
*/
public abstract class ApiObject {
   
   private String session = "", message = "", appkey = "";
   private String status = "fail";
   
   public abstract String toJson();

   public String getAppkey() {
       return appkey;
   }

   public void setAppkey(String appkey) {
       this.appkey = appkey;
   }

   public String getSession() {
       return session;
   }

   public void setSession(String session) {
       this.session = session;
   }

   public String getStatus() {
       return status;
   }

   public void setStatus(String status) {
       this.status = status;
   }

   public String getMessage() {
       return message;
   }

   public void setMessage(String message) {
       this.message = message;
   }
   
}
