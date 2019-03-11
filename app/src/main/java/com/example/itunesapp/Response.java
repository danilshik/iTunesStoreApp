package com.example.itunesapp;

import com.example.itunesapp.result.Result;

import java.util.List;

public class Response {
    private int resultCount;

    private List<Result> results;

    public void setResultCount(int resultCount){
        this.resultCount = resultCount;
    }
    public int getResultCount(){
        return this.resultCount;
    }
    public void setResults(List<Result> results){
        this.results = results;
    }
    public List<Result> getResults(){
        return results;
    }
}
