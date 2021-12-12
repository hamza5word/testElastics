package com.elastics.testelastics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppPropertiesConfig {

    @Value("${dev.mode}")
    public String DEV_MODE;

    @Value("${item.img.path}")
    public String ITEM_IMG_PATH;

}
