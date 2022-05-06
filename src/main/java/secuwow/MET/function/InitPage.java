package secuwow.MET.function;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


@Slf4j
public class InitPage {
    public static String HTTP_URL;

    public void servAddr(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        String scheme       = httpRequest.getScheme();
        String serverName   = httpRequest.getServerName();
        int portNumber      = httpRequest.getServerPort();
        String contextPath  = httpRequest.getContextPath();

        String httpContextUrl = scheme + "://" + serverName + ":" + portNumber;
        HTTP_URL = httpContextUrl;
    }
}
