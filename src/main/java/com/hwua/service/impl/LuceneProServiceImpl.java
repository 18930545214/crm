package com.hwua.service.impl;

import com.hwua.pojo.Product;
import com.hwua.service.LuceneProService;
import com.hwua.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class LuceneProServiceImpl implements LuceneProService {

    @Override
    public void createIndex(List<Product> products) throws Exception {
        System.out.println("lucene初始化");
        IndexWriter indexWriter = LuceneUtil.indexWriter();
        indexWriter.deleteAll();//删除所有
        for (Product product : products) {
            Document document = LuceneUtil.addDocument(product);
            //5. 把文档放到索引库中
            indexWriter.addDocument(document);//写到索引库
        }
        indexWriter.commit();
        indexWriter.close();
    }

    @Override
    public List<Product> searchProsByTerm(String fieldName, String term,  Integer count) throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        Directory directory = FSDirectory.open(new File("c:/crm_lucene").toPath());
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建一个QueryParser对象
        //第一个参数: 当查询的字符串进行分词后到指定的域中查询文档
        //第二个参数: 指定使用分词器来对搜索的字符串进行分词
        QueryParser queryParser = new QueryParser(fieldName,new IKAnalyzer());
        Query query = queryParser.parse(term);
        TopDocs topDocs = indexSearcher.search(query, count);
        for(ScoreDoc scoreDoc:topDocs.scoreDocs){
            int docId = scoreDoc.doc;//获取文档的id
            Document document = indexSearcher.doc(docId);//得到文档对象
            Product product = new Product();
            product.setId(document.get("productId"));
            product.setProductName(document.get("productName"));
            product.setProductNum(document.get("productNum"));
            product.setCityName(document.get("productCityName"));
            product.setDepartureTime(document.get("departureTime"));
            product.setProductDesc(document.get("productDesc"));
            product.setProductPrice(Double.valueOf(document.get("productPrice")));
            product.setProductStatus(Integer.parseInt(document.get("productStatus")));
            products.add(product);
        }
        System.out.println(products);
        indexReader.close();
        return products;
    }
    /**
     * 更新索引库中的文档
     * @throws Exception
     */
    @Override
    public void updateDocument(Product product) throws Exception{
        IndexWriter indexWriter = LuceneUtil.indexWriter();
        //创建一个Document对象
        Document document = LuceneUtil.addDocument(product);
        indexWriter.updateDocument(new Term("productId",product.getId()),document);
        indexWriter.close();
    }

    @Override
    public void addDocument(Product product) throws Exception {
        IndexWriter indexWriter = LuceneUtil.indexWriter();
        Document document = LuceneUtil.addDocument(product);
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Override
    public void deleteDocument(String id) throws Exception {
        IndexWriter indexWriter = LuceneUtil.indexWriter();
        Query query = new TermQuery(new Term("productId",id));
        long res = indexWriter.deleteDocuments(query);//删除指定的文档
        System.out.println(res);
        indexWriter.close();
    }
}
