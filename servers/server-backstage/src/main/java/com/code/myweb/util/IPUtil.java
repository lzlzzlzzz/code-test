package com.code.myweb.util;

import org.apache.commons.lang3.StringUtils;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取IP
 *
 */
public class IPUtil {

    /**
     * 获取客户端IP
     *
     * @return IP地址
     */
    public static String getIpAddr() {
        return getIpAddr(ServletUtils.getRequest());
    }

    /**
     * 获得客户端IP
     *
     * @param request request
     * @return 客户端IP
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("CLIENTIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getIpAddrForIps(String ips){
        String ipResult = ips;
        if(StringUtils.isNotEmpty(ips)){
            int idx = ips.indexOf(",");
            if( idx !=-1 ){
                String[] ipsArr = ips.split(",");
                for (int i = 0; i < ipsArr.length; i++) {
                    if( !StringUtils.isEmpty(StringUtils.trim(ipsArr[i])) && !isInternalIp(StringUtils.trim(ipsArr[i]))){
                        return  ipsArr[i];
                    }
                }
                return ipsArr[0];
            }
            // 全部都是内网ip，返回本地ip
			if(ipResult.indexOf(",")>=1) {
                ipResult = "127.0.0.1";
            }
        }
        return ipResult;
    }

    /**
     * 是否是内网ip
     * @param ip ip
     * @return
     */
    public static boolean isInternalIp(String ip) {
        if(StringUtils.isEmpty(ip)){
            return false;
        }
        byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        //10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        //172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        //192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                switch (b1) {
                    case SECTION_6:
                        return true;
                }
            default:
                return false;
        }
    }
}