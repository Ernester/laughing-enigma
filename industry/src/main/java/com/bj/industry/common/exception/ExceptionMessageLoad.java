package com.bj.industry.common.exception;

import com.bj.industry.common.message.MessageHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@Component
public class ExceptionMessageLoad implements InitializingBean {

    private static final String MESSAGE_PROPER = "/config/message.properties";

    private MessageHolder messageHolder = MessageHolder.getInstance();

    @Override
    public void afterPropertiesSet() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(MESSAGE_PROPER);
        Properties properties = new Properties();
        try (Reader reader = new InputStreamReader(resourceAsStream, "UTF-8")) {
            properties.load(reader);
        }
        for (Object object : properties.keySet()){
            String key = object.toString();
            messageHolder.addMessage(Integer.valueOf(key),properties.getProperty(key));
        }
    }
}
