package com.hwua.service;


import com.github.pagehelper.PageInfo;
import com.hwua.pojo.Product;
import org.apache.lucene.index.IndexWriter;

import java.util.List;

public interface LuceneProService {
    void createIndex(List<Product> products) throws Exception;//创建产品索引库
    List<Product> searchProsByTerm(String fieldName, String term,  Integer count) throws Exception;//根据输入关键词全局查询
    void updateDocument(Product product)throws Exception;
    void addDocument(Product product)throws Exception;
    void deleteDocument(String id)throws Exception;
}
