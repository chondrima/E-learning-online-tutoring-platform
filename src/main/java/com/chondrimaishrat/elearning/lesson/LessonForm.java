package com.chondrimaishrat.elearning.lesson;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class LessonForm { @NotBlank @Size(max=150) private String title; @NotBlank @Size(max=4000) private String content; @Size(max=500) private String videoUrl; @Size(max=500) private String resourceUrl; public String getTitle(){return title;} public void setTitle(String v){title=v;} public String getContent(){return content;} public void setContent(String v){content=v;} public String getVideoUrl(){return videoUrl;} public void setVideoUrl(String v){videoUrl=v;} public String getResourceUrl(){return resourceUrl;} public void setResourceUrl(String v){resourceUrl=v;} }
