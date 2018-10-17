package com.lanmosoft.yunservice.xmltools;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;



@XmlRootElement
public class Level {
    
    private Integer Key;
    private String value;
    
    public Level(){}
    
    @XmlAttribute(name="key")
    public Integer getKey() {
        return Key;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setKey(Integer key) {
        Key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}