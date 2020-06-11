package com.animationdemo.bean;

/**
 * Copyright 2018 bejson.com
 */
import java.util.List;

/**
 * Auto-generated: 2018-10-29 10:15:52
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class HeWeatherBean {

    private List<HeWeather6> HeWeather6;
    public void setHeWeather6(List<HeWeather6> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }
    public List<HeWeather6> getHeWeather6() {
        return HeWeather6;
    }

    /**
     * Auto-generated: 2018-10-29 10:15:52
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class HeWeather6 {

        private Basic basic;
        private Update update;
        private String status;
        private List<Daily_forecast> daily_forecast;
        public void setBasic(Basic basic) {
            this.basic = basic;
        }
        public Basic getBasic() {
            return basic;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }
        public Update getUpdate() {
            return update;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setDaily_forecast(List<Daily_forecast> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }
        public List<Daily_forecast> getDaily_forecast() {
            return daily_forecast;
        }

        /**
         * Auto-generated: 2018-10-29 10:15:52
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Basic {

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;
            public void setCid(String cid) {
                this.cid = cid;
            }
            public String getCid() {
                return cid;
            }

            public void setLocation(String location) {
                this.location = location;
            }
            public String getLocation() {
                return location;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }
            public String getParent_city() {
                return parent_city;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }
            public String getAdmin_area() {
                return admin_area;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }
            public String getCnty() {
                return cnty;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
            public String getLat() {
                return lat;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }
            public String getLon() {
                return lon;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
            public String getTz() {
                return tz;
            }

        }

        /**
         * Auto-generated: 2018-10-29 10:15:52
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Update {

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        /**
         * Auto-generated: 2018-10-29 10:15:52
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Daily_forecast {

            private String cond_code_d;
            private String cond_code_n;
            private String cond_txt_d;
            private String cond_txt_n;
            private String date;
            private String hum;
            private String mr;
            private String ms;
            private String pcpn;
            private String pop;
            private String pres;
            private String sr;
            private String ss;
            private String tmp_max;
            private String tmp_min;
            private String uv_index;
            private String vis;
            private String wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;
            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
            }
            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
            }
            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }
            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }
            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setDate(String date) {
                this.date = date;
            }
            public String getDate() {
                return date;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }
            public String getHum() {
                return hum;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }
            public String getMr() {
                return mr;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }
            public String getMs() {
                return ms;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }
            public String getPcpn() {
                return pcpn;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }
            public String getPop() {
                return pop;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }
            public String getPres() {
                return pres;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }
            public String getSr() {
                return sr;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
            public String getSs() {
                return ss;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }
            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }
            public String getTmp_min() {
                return tmp_min;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }
            public String getUv_index() {
                return uv_index;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }
            public String getVis() {
                return vis;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
            }
            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }
            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }
            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
            public String getWind_spd() {
                return wind_spd;
            }

        }

    }

    public String toString() {
        return "location:" + getHeWeather6().get(0).getBasic().location + "\n" +
                "cond_txt_d:" + getHeWeather6().get(0).getDaily_forecast().get(0).cond_code_d + "\n" +
                "wind_dir:" + getHeWeather6().get(0).getDaily_forecast().get(0).wind_dir + "\n" +
                "tmp:" + getHeWeather6().get(0).getDaily_forecast().get(0).tmp_min + "--" +
                getHeWeather6().get(0).getDaily_forecast().get(0).tmp_max + "℃";
    }

}
