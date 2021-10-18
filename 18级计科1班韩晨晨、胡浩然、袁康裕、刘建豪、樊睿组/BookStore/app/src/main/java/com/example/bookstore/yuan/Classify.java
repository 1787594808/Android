package com.example.bookstore.yuan;

import java.util.List;

public class Classify {
    private String classifyName;//名称
    private List<Content> content;

    public Classify(String classifyName, List<Content> content) {
        this.classifyName=classifyName;
        this.content=content;
    }

    public String getclassifyName() {
        return classifyName;
    }

    public void setclassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
