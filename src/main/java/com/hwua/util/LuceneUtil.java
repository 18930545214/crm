package com.hwua.util;

import com.hwua.pojo.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class LuceneUtil {
    public static Document addDocument(Product product){
        Document document = new Document();
        document.add(new TextField("productId", product.getId(), Field.Store.YES));
        document.add(new TextField("productName", product.getProductName(), Field.Store.YES));
        document.add(new StoredField("productNum", product.getProductNum()));
        document.add(new StoredField("productCityName", product.getCityName()));
        document.add(new StoredField("departureTime", product.getDepartureTime()));
        document.add(new StoredField("productDesc", product.getProductDesc()));
        document.add(new StoredField("productPrice", product.getProductPrice()));
        document.add(new StoredField("productStatus", product.getProductStatus()));
        return document;
    }
    public static IndexWriter indexWriter() throws Exception{
        //指定索引库的路径
        Directory directory = FSDirectory.open(new File("c:/crm_lucene").toPath());
        //创建索引库写入对象,并告知写到哪里,使用指定的配置(分析器,默认使用的是标准分析器StandardAnalyzer)
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()));
        return indexWriter;
    }
}
