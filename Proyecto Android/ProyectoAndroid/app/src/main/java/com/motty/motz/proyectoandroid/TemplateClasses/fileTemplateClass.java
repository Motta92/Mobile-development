package com.motty.motz.proyectoandroid.TemplateClasses;

/**
 * Created by Carlos on 4/12/2016.
 */
public class fileTemplateClass {
    public Integer id;
    public String name;
    public String contentType;
    public Integer from;
    public Integer to;
    public String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }
}
