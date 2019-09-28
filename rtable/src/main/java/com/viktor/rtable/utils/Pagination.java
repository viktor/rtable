package com.viktor.rtable.utils;

import java.util.List;

/**
 * Pagination Algorithm.
 *
 * @param <T>
 */
public class Pagination<T> {

    private int itemsPerPage;
    private int indexPage = 0;
    private List<T> collection;

    public Pagination(List<T> items, int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        this.collection = items;
    }

    public List<T> next(){
        if(((indexPage+1)*itemsPerPage) < collection.size())
            indexPage++;
        return pages();
    }

    public List<T> prev(){
        if(indexPage > 0)
            indexPage--;
        return pages();
    }

    public List<T> pages(){
        try {
            return collection.subList(indexPage*itemsPerPage, (indexPage*itemsPerPage + itemsPerPage));
        }catch (IndexOutOfBoundsException e) {
            try{
                return collection.subList(indexPage*itemsPerPage, (collection.size()));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return collection;
        }
    }

    public int getPageIndex(){
        return indexPage;
    }

}