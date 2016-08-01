package ua.lutsenko.banking.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Denis Lutsenko.
 */

/**
 * This is a wrapper class for HttpServletRequest
 */
public class RequestWrapper {
    private HttpServletRequest request;
    private HttpSession session;
    private Map<String, String> requestParametersMap;
    private Map<String, Object> requestAttributesMap;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
        session = request.getSession(true);
        requestParametersMap = new HashMap<>();
        requestAttributesMap = new HashMap<>();
    }

    public void addParameter(String key, String value) {
        requestParametersMap.put(key, value);

    }

    public void addAttrToSession(String key, Object value) {
        session.setAttribute(key, value);
    }

    /**
     * This method extracts all parameters from request.
     */
    public void extractParamValues() {
        Enumeration parametersList = request.getParameterNames();
        while (parametersList.hasMoreElements()) {
            String paramName = (String) parametersList.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {

                requestParametersMap.put(paramName, paramValues[i]);
                System.out.println(paramName +"  " + paramValues[i]);
            }
        }
    }

    /**
     * This method inserts new attributes to the wrapper class.
     */
    public void insertAttributes() {
        for (Map.Entry<String, Object> mm : requestAttributesMap.entrySet()) {
            request.setAttribute(mm.getKey(), mm.getValue());
        }
    }


    /**
     * This method finds parameter by attribute name.
     *
     * @param name attribute name.
     * @return value of parameter.
     */
    public String findParameterByName(String name) {
        return requestParametersMap.get(name);
    }

    /**
     * This method finds value by attribute name.
     *
     * @param name attribute name.
     * @return value of parameter.
     */
    public Object findSessionAttrByName(String name) {
        return session.getAttribute(name);
    }

    /**
     * This method add attribute to request
     *
     * @param key   attribute name.
     * @param value ettribute value.
     */
    public void addNewAttributes(String key, Object value) {
        requestAttributesMap.put(key, value);
    }

    public HttpSession getSession() {
        return session;
    }

}
