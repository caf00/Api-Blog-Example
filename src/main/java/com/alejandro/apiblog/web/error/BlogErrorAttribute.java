package com.alejandro.apiblog.web.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class BlogErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        final Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        BlogApiError blogApiError = BlogApiError.create(
                (Integer) errorAttributes.get("status"),
                (String) errorAttributes.getOrDefault("message", "No message available"),
                (String) errorAttributes.getOrDefault("error", "No message available")
        );
        return blogApiError.toMap();
    }
}
