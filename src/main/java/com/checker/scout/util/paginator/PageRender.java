package com.checker.scout.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int actualPage;
    private int totalPages;
    private int numElementPage;
    private static final int intervalo=5;
    private List<PageItem> listPageItem;
    private int rangeIni;
    private int rangeEnd;

    public PageRender(String url,Page<T> page){
        this.url=url;
        this.page=page;
        this.numElementPage = page.getSize();
        this.totalPages= page.getTotalPages();
        //Non dimenticare in previous thymeleaf restare 2 , per che se non se no si anulla.
        this.actualPage=page.getNumber()+1;
        this.listPageItem=new ArrayList<>();
        
    }

    public void rangeOfPage(){
        
        rangeIni=((((actualPage-1)/intervalo)+1)*(intervalo))-intervalo+1;
        rangeEnd=rangeIni+intervalo-1;
        if(rangeEnd>totalPages){
            rangeEnd=totalPages;
        }

        for(int i=rangeIni ;i<=rangeEnd;i++){
            listPageItem.add(new PageItem(i,(actualPage)==i));
        }
    }

    public String getUrl() {
        return url;
    }

    public Page<T> getPage() {
        return page;
    }

    public int getActualPage() {
        return actualPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<PageItem> getListPageItem() {
        return listPageItem;
    }
    public boolean isFirst(){
        return page.isFirst();
    }
    public boolean isLast(){
        return page.isLast();
    }
    public boolean isHasNext(){
        return page.hasNext();
    }
    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
    
}
