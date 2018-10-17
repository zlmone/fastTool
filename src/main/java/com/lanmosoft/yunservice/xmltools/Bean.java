package com.lanmosoft.yunservice.xmltools;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="bean" )
public class Bean {
    private Integer id;
    private String code;
    private String name;
    private List<Level> levels = new ArrayList<Level>();
    private List<Extra> extras = new ArrayList<Extra>();
     
    public Bean(){}
 
    @XmlAttribute
    public Integer getId() {
        return id;
    }
 
    @XmlAttribute
    public String getCode() {
        return code;
    }
 
    @XmlElement
    public String getName() {
        return name;
    }
 
    @XmlElementWrapper(name="levels")
    @XmlElement(name="level")
    public List<Level> getLevels() {
        return levels;
    }
 
    @XmlElementWrapper(name="extras")
    @XmlElement(name="extra")
    public List<Extra> getExtras() {
        return extras;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public void setCode(String code) {
        this.code = code;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
 
    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }
     
}