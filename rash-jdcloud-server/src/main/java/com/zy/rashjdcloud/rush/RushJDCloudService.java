package com.zy.rashjdcloud.rash;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author zy
 * @date 2019-10-3 20:13
 */
public class RashJDCloudService {


    public static String rash() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 6, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        long period = 100;
        String rashDate = "2019-10-03 22:12:59";
        String requestParams = "{\"activityCode\":\"ada8afd783dd4772b8b9e356b8e018e0\",\"activityProductCode\":\"8ddc3c47f4404732aaebe20127f6aeeb\",\"dataCenter\":\"cn-east-2\",\"count\":1,\"chargeMode\":\"3\",\"chargeDuration\":\"1\",\"chargeUnit\":\"4\",\"serviceCode\":\"vm|ip\",\"activityPriceCode\":\"b0a41f1e463d4f7b99c92cda0873101f\",\"imageId\":\"img-kxs3xhhwy6\",\"instanceType\":\"h.g2.large\",\"lAz\":\"cn-east-2a\",\"bootVolumeType\":\"hdd.std1\",\"vpcId\":\"vpc-74kckopxzv\",\"subnetId\":\"subnet-zr0kial21r\",\"floatingIP\":{\"rate\":1,\"provider\":\"BGP\"}}";
        String cookie = "3AB9D23F7A4B3C9B=O3RQWDVIQGQEDCLKVIZBA5JBRMTII2EFSVEPNOUP44G45Q4W3IWZ3XUZ7RYBCGJJ26GU3OKSV4BRHT3LNPAITJZX5I; _ga=GA1.2.2048379531.1570067776; _gid=GA1.2.1257476233.1570067776; jdcloud_sitelang=cn; jdcloud_sitelang_server=cn; pin=15228163685_p; unick=%E8%96%9B%E5%AE%9A%E8%B0%94%E7%9A%84%E7%8C%ABsssss; thor=949AE864260345ADFBA950EAFF1EA7DE00BE768E1457E7C1C0866180696D9A233061B99E3570B08E2999503EED13B873F3E53ED3F52B85C37DF5110E3BDC7D3C860AC612B930E982E1D9AAB17F93F0B25861C6D586F813BD988B67461BEA2C17F67AB642F6AA463F192D82A9994329FE42AB5A91FC82E3C7CC40F60F65994B6954BF6186A1B51AD2BA3BFD8074A2DD45; __jdv=269124599|DMT_baidubrand|2019baidubrand|title|NA|1570097848982; Hm_lvt_38f625421267eb5065e400d79fc42c74=1570067842,1570097593,1570097839,1570097849; __jdc=231099870; __jda=231099870.15700335516621480019288.1570033552.1570097593.1570103973.4; __jdb=231099870.1.15700335516621480019288|4.1570103973; Hm_lpvt_38f625421267eb5065e400d79fc42c74=1570103973";
        long initDelay = getTimeMillis(rashDate);

        executor.scheduleAtFixedRate(
                new RashJDCloudTask(requestParams, cookie, pool),
                initDelay,
                period,
                TimeUnit.MILLISECONDS);
        return "";
    }

    static class RashJDCloudTask implements Runnable {
        private String param;
        private String cookieStr;
        private ThreadPoolExecutor pool;

        public RashJDCloudTask(String param, String cookieStr, ThreadPoolExecutor pool) {
            this.param = param;
            this.cookieStr = cookieStr;
            this.pool = pool;
        }

        public RashJDCloudTask() {
        }


        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    try {
                        DefaultHttpClient client = new DefaultHttpClient();
                        HttpPost post = new HttpPost("https://promotion.jdcloud.com/u/activity/createPackageResource");
                        StringEntity entity = new StringEntity(param, "utf-8");
                        post.setEntity(entity);
                        post.setHeader("Cookie", cookieStr);
                        post.setHeader("Referer", "https://promotion.jdcloud.com/productTrialforPay?uuid=ada8afd783dd4772b8b9e356b8e018e0");
                        post.setHeader("Content-Type", "application/json;charset=UTF-8");
                        System.out.println("任务执行时刻>>>" + LocalDateTime.now());
                        HttpResponse response = client.execute(post);
                        System.out.println("任务执行完成时刻>>>" + LocalDateTime.now());
                        String result = EntityUtils.toString(response.getEntity(), "utf-8");
                        JSONObject jsonObject = JSON.parseObject(result);
                        String code = jsonObject.getString("code");
                        String msg = jsonObject.getString("code");
                        if ("20000".equals(code)) {
                            System.err.print("抢到咯~~~~~~~");
                            System.exit(0);
                        } else {

                        }
                        System.out.println(jsonObject.toJSONString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    /**
     * 获取指定时间对应的毫秒数
     *
     * @return
     */
    private static long getTimeMillis(String rashDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            Date date = dateFormat.parse(rashDate);
            return (date.getTime() - System.currentTimeMillis()) + 950;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        rash();
    }

}
