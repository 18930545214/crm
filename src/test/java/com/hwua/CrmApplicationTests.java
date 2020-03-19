package com.hwua;

import com.hwua.pojo.Orders;
import com.hwua.pojo.RolePermission;
import com.hwua.util.LuceneUtil;
import org.apache.lucene.index.IndexWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmApplicationTests {


    @Test
    void contextLoads() throws Exception{
        IndexWriter indexWriter = LuceneUtil.indexWriter();
        indexWriter.deleteAll();//删除所有
    }

}
