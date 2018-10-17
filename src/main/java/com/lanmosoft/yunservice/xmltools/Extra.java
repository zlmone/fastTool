package com.lanmosoft.yunservice.xmltools;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Extra {
    private String type;
    private String content;
    
    public Extra(){}

    @XmlElement
    public String getType() {
        return type;
    }

    @XmlElement
    public String getContent() {
        return content;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
