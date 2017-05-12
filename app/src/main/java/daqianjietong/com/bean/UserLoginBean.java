package daqianjietong.com.bean;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class UserLoginBean {


        private String txt_phone;
        private String txt_password;

        public String getTxt_phone() {
            return txt_phone;
        }

        public void setTxt_phone(String txt_phone) {
            this.txt_phone = txt_phone;
        }

        public String getTxt_password() {
            return txt_password;
        }

        public void setTxt_password(String txt_password) {
            this.txt_password = txt_password;
        }

        @Override
        public String toString() {
            return "{" +
                    "txt_phone='" + txt_phone + '\'' +
                    ", txt_password='" + txt_password + '\'' +
                    '}';
        }

}
