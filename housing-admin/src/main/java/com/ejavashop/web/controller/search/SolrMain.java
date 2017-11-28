package com.ejavashop.web.controller.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.ejavashop.vo.search.SearchProductVO;

public class SolrMain {

    //solr url
    public static final String URL    = "http://101.200.83.4:8080/solr";
    //solr应用
    public static final String SERVER = "ejavashopcore";
    //    //待索引、查询字段
    public static String[]     docs   = { "Solr是一个独立的企业级搜索应用服务器", "它对外提供类似于Web-service的API接口",
            "用户可以通过http请求", "向搜索引擎服务器提交一定格式的XML文件生成索引", "也可以通过Http Get操作提出查找请求", "并得到XML格式的返回结果" };

    //    public static String[]     docs   = { "我爱你中国" };

    public static SolrClient getSolrClient() {
        return new HttpSolrClient(URL + "/" + SERVER);
    }

    /**
     * 新建索引
     */
    public static void createIndex() {
        SolrClient client = getSolrClient();
        int i = 0;
        List<SolrInputDocument> docList = new ArrayList<SolrInputDocument>();
        for (String str : docs) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", i++);
            doc.addField("brand", i);
            doc.addField("seller", i);
            doc.addField("cate", i);
            doc.addField("sortIndex", i);
            doc.addField("content", str);
            docList.add(doc);
        }
        try {
            client.add(docList);
            client.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索
     */
    public static void search() {
        String keyword = "(测试 笔记本)";
        SolrClient client = getSolrClient();
        SolrQuery query = new SolrQuery();
        //        query.setQuery("content:(测试)");
        //        query.setQuery("name1:(测试)");
        StringBuilder sb = new StringBuilder();
        sb.append(SearchProductVO.CONTENT_);
        sb.append(":");
        sb.append(keyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.NAME1_);
        sb.append(":");
        sb.append(keyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(keyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.BRAND_);
        sb.append(":");
        sb.append(keyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(keyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.CATE_);
        sb.append(":");
        sb.append(keyword);

        query.setQuery(sb.toString());
        //        query.setQuery(SearchProductVO.CONTENT_ + ":" + keyword);
        //        query.setQuery(SearchProductVO.NAME1_ + ":" + keyword);
        //        query.setQuery(SearchProductVO.SELLER_ + ":" + keyword);
        //        query.setQuery(SearchProductVO.BRAND_ + ":" + keyword);
        //        query.setQuery(SearchProductVO.SELLER_ + ":" + keyword);
        //        query.setQuery(SearchProductVO.CATE_ + ":" + keyword);

        // 分页，start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        query.set("start", 0);
        query.set("rows", 5);
        query.setHighlight(true);
        query.setHighlightSimplePre("<font color=\"red\">");
        query.setHighlightSimplePost("</font>");
        query.setParam("hl.fl", "name1");

        QueryResponse response = null;
        try {
            response = client.query(query);
            System.out.println(response.toString());
            System.out.println();
            SolrDocumentList docs = response.getResults();
            System.out.println("文档个数：" + docs.getNumFound());

            System.out.println("查询时间：" + response.getQTime());
            for (SolrDocument doc : docs) {
                //                System.out.println("id: " + doc.getFieldValue("id") + "      content: "
                //                                   + doc.getFieldValue("content"));

                String id = (String) doc.getFieldValue("id");
                if (response.getHighlighting().get(id) != null) {
                    System.out.println(response.getHighlighting().get(id).get("name1").toString()
                        .replace("[", "").replace("]", ""));
                    System.out.println(doc.getFieldValue("mallPcPrice"));
                    System.out.println(doc.getFieldValue("malMobilePrice"));
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 删除索引  
    // 据查询结果删除：  
    public static void deleteByQuery() {
        SolrClient client = getSolrClient();
        try {
            for (int i = 0; i < 200; i++) {
                // 删除所有的索引  
                client.deleteById(i + "");
                client.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //        createIndex();
        //        search();
        deleteByQuery();
    }

}
