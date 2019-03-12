package com.example.itunesapp.data;

import com.example.itunesapp.data.Result;

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
