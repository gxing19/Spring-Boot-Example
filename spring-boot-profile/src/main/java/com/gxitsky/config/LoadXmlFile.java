package com.gxitsky.config;

import org.springframework.context.annotation.ImportResource;

//加载XML配置
@ImportResource(locations = {"classpath:app1-context.xml", "classpath:app2-context.xml"})
public class LoadXmlFile {

}
