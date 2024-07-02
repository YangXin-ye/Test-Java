//package com.iweb.utils;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.BulkRequest;
//import co.elastic.clients.elasticsearch.core.BulkResponse;
//import co.elastic.clients.elasticsearch.core.IndexRequest;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import co.elastic.clients.elasticsearch.indices.ExistsRequest;
//import co.elastic.clients.elasticsearch.indices.GetIndexRequest;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import co.elastic.clients.util.ObjectBuilder;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//
//import java.io.ByteArrayInputStream;
//import java.util.List;
//import java.util.function.Function;
//
///**
// * @Author：
// * @Package：com.iweb.utils
// * @Project：Test-Java
// * @name：ElasticsearchUtil
// * @Date：2024/7/2 17:19
// * @Description:
// */
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import co.elastic.clients.elasticsearch.indices.ExistsRequest;
//import co.elastic.clients.elasticsearch.indices.IndexSettings;
//import co.elastic.clients.elasticsearch.indices.IndexState;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.ElasticsearchTransport;
//import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
//import co.elastic.clients.elasticsearch.indices.ExistsRequest;
//import co.elastic.clients.elasticsearch.indices.IndexSettings;
//import co.elastic.clients.elasticsearch.indices.IndexState;
//import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.json.JsonData;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Map;
//
//public class ElasticSearchSync {
//
//    private static final String INDEX_NAME = "district";
//    private static final String INDEX_MAPPING = "{\n" +
//            "  \"mappings\": {\n" +
//            "    \"properties\": {\n" +
//            "      \"id\": { \"type\": \"keyword\" },\n" +
//            "      \"parent_id\": { \"type\": \"keyword\" },\n" +
//            "      \"city_code\": { \"type\": \"keyword\" },\n" +
//            "      \"adcode\": { \"type\": \"keyword\" },\n" +
//            "      \"name\": { \"type\": \"text\", \"analyzer\": \"standard\" },\n" +
//            "      \"polyline\": { \"type\": \"text\" },\n" +
//            "      \"center\": { \"type\": \"text\" },\n" +
//            "      \"level\": { \"type\": \"keyword\" },\n" +
//            "      \"remark\": { \"type\": \"text\" },\n" +
//            "      \"create_by\": { \"type\": \"keyword\" },\n" +
//            "      \"create_time\": { \"type\": \"date\", \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\" },\n" +
//            "      \"update_by\": { \"type\": \"keyword\" },\n" +
//            "      \"update_time\": { \"type\": \"date\", \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\" },\n" +
//            "      \"deleted\": { \"type\": \"boolean\" }\n" +
//            "    }\n" +
//            "  }\n" +
//            "}";
//
//
//    public static void main(String[] args) throws Exception {
//        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//
//        // 检查索引是否存在
//        boolean indexExists = client.indices().exists(new ExistsRequest.Builder().index(INDEX_NAME).build()).value();
//
//        if (!indexExists) {
//            // 创建索引
//            client.indices().create(c -> c
//                    .index(INDEX_NAME)
//                    .mappings(m -> m
//                            .properties("id", p -> p.keyword(k -> k))
//                            .properties("parent_id", p -> p.keyword(k -> k))
//                            .properties("city_code", p -> p.keyword(k -> k))
//                            .properties("adcode", p -> p.keyword(k -> k))
//                            .properties("name", p -> p.text(t -> t.analyzer("standard")))
//                            .properties("polyline", p -> p.text(t -> t))
//                            .properties("center", p -> p.text(t -> t))
//                            .properties("level", p -> p.keyword(k -> k))
//                            .properties("remark", p -> p.text(t -> t))
//                            .properties("create_by", p -> p.keyword(k -> k))
//                            .properties("create_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
//                            .properties("update_by", p -> p.keyword(k -> k))
//                            .properties("update_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
//                            .properties("deleted", p -> p.boolean_(b -> b))
//                    )
//            );
//        } else {
//            // 删除现有索引
//            client.indices().delete(new DeleteIndexRequest.Builder().index(INDEX_NAME).build());
//            // 重新创建索引
//            client.indices().create(c -> c
//                    .index(INDEX_NAME)
//                    .mappings(m -> m
//                            .properties("id", p -> p.keyword(k -> k))
//                            .properties("parent_id", p -> p.keyword(k -> k))
//                            .properties("city_code", p -> p.keyword(k -> k))
//                            .properties("adcode", p -> p.keyword(k -> k))
//                            .properties("name", p -> p.text(t -> t.analyzer("standard")))
//                            .properties("polyline", p -> p.text(t -> t))
//                            .properties("center", p -> p.text(t -> t))
//                            .properties("level", p -> p.keyword(k -> k))
//                            .properties("remark", p -> p.text(t -> t))
//                            .properties("create_by", p -> p.keyword(k -> k))
//                            .properties("create_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
//                            .properties("update_by", p -> p.keyword(k -> k))
//                            .properties("update_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
//                            .properties("deleted", p -> p.boolean_(b -> b))
//                    )
//            );
//        }
//
//        // 从数据库中同步数据
//        syncDataFromDatabase(client);
//
//        // 关闭客户端
//        restClient.close();
//    }
//
//    private static void syncDataFromDatabase(ElasticsearchClient client) throws Exception {
//        // 数据库连接信息
//        String url = "jdbc:mysql://localhost:3306/your_database";
//        String user = "your_username";
//        String password = "your_password";
//
//        Connection conn = DriverManager.getConnection(url, user, password);
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM district");
//
//        while (rs.next()) {
//            client.index(i -> i
//                    .index(INDEX_NAME)
//                    .id(rs.getString("id"))
//                    .document(new District(
//                            rs.getString("id"),
//                            rs.getString("parent_id"),
//                            rs.getString("city_code"),
//                            rs.getString("adcode"),
//                            rs.getString("name"),
//                            rs.getString("polyline"),
//                            rs.getString("center"),
//                            rs.getString("level"),
//                            rs.getString("remark"),
//                            rs.getString("create_by"),
//                            rs.getTimestamp("create_time"),
//                            rs.getString("update_by"),
//                            rs.getTimestamp("update_time"),
//                            rs.getBoolean("deleted")
//                    ))
//            );
//        }
//
//        rs.close();
//        stmt.close();
//        conn.close();
//    }
//}
//
//class District {
//    private String id;
//    private String parentId;
//    private String cityCode;
//    private String adcode;
//    private String name;
//    private String polyline;
//    private String center;
//    private String level;
//    private String remark;
//    private String createBy;
//    private java.util.Date createTime;
//    private String updateBy;
//    private java.util.Date updateTime;
//    private boolean deleted;
//
//    // Constructor, getters, and setters omitted for brevity
//}
