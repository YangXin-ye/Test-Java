package com.iweb.service.impl;

import cn.hutool.db.PageResult;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.WildcardQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iweb.entity.District;
import com.iweb.entity.Products;
import com.iweb.service.DistrictService;
import com.iweb.service.EsService;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.service.impl
 * @Project：Test-Java
 * @name：EsServiceImpl
 * @Date：2024/7/2 17:16
 * @Description:
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private DistrictService districtService;

    private final ElasticsearchClient client;

    public EsServiceImpl() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        this.client = new ElasticsearchClient(transport);
    }


    private static final String INDEX_NAME = "district";
    private static final String INDEX_MAPPING = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": { \"type\": \"keyword\" },\n" +
            "      \"parent_id\": { \"type\": \"keyword\" },\n" +
            "      \"city_code\": { \"type\": \"keyword\" },\n" +
            "      \"adcode\": { \"type\": \"keyword\" },\n" +
            "      \"name\": { \"type\": \"text\", \"analyzer\": \"standard\" },\n" +
            "      \"polyline\": { \"type\": \"text\" },\n" +
            "      \"center\": { \"type\": \"text\" },\n" +
            "      \"level\": { \"type\": \"keyword\" },\n" +
            "      \"remark\": { \"type\": \"text\" },\n" +
            "      \"create_by\": { \"type\": \"keyword\" },\n" +
            "      \"create_time\": { \"type\": \"date\", \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\" },\n" +
            "      \"update_by\": { \"type\": \"keyword\" },\n" +
            "      \"update_time\": { \"type\": \"date\", \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\" },\n" +
            "      \"deleted\": { \"type\": \"boolean\" }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    @SneakyThrows
    @Override
    public void syncData() {

        // 检查索引是否存在
        boolean indexExists = client.indices().exists(new ExistsRequest.Builder().index(INDEX_NAME).build()).value();

        if (!indexExists) {
            // 创建索引
            client.indices().create(c -> c
                    .index(INDEX_NAME)
                    .mappings(m -> m
                            .properties("id", p -> p.keyword(k -> k))
                            .properties("parent_id", p -> p.keyword(k -> k))
                            .properties("city_code", p -> p.keyword(k -> k))
                            .properties("adcode", p -> p.keyword(k -> k))
                            .properties("name", p -> p.text(t -> t.analyzer("standard")))
                            .properties("polyline", p -> p.text(t -> t))
                            .properties("center", p -> p.text(t -> t))
                            .properties("level", p -> p.keyword(k -> k))
                            .properties("remark", p -> p.text(t -> t))
                            .properties("create_by", p -> p.keyword(k -> k))
                            .properties("create_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
                            .properties("update_by", p -> p.keyword(k -> k))
                            .properties("update_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
                            .properties("deleted", p -> p.boolean_(b -> b))
                    )
            );
        } else {
            // 删除现有索引
            client.indices().delete(new DeleteIndexRequest.Builder().index(INDEX_NAME).build());
            // 重新创建索引
            client.indices().create(c -> c
                    .index(INDEX_NAME)
                    .mappings(m -> m
                            .properties("id", p -> p.keyword(k -> k))
                            .properties("parent_id", p -> p.keyword(k -> k))
                            .properties("city_code", p -> p.keyword(k -> k))
                            .properties("adcode", p -> p.keyword(k -> k))
                            .properties("name", p -> p.text(t -> t.analyzer("standard")))
                            .properties("polyline", p -> p.text(t -> t))
                            .properties("center", p -> p.text(t -> t))
                            .properties("level", p -> p.keyword(k -> k))
                            .properties("remark", p -> p.text(t -> t))
                            .properties("create_by", p -> p.keyword(k -> k))
                            .properties("create_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
                            .properties("update_by", p -> p.keyword(k -> k))
                            .properties("update_time", p -> p.date(d -> d.format("yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")))
                            .properties("deleted", p -> p.boolean_(b -> b))
                    )
            );
        }
        //同步数据
        List<District> list = districtService.list();
        for (District district : list) {
            client.index(i -> i
                    .index(INDEX_NAME)
                    .id(district.getId())
                    .document(district));
        }


    }

    @SneakyThrows
    @Override
    public Page<District> list(String name, Integer pageNum, Integer pageSize){
        SearchRequest searchRequest = new SearchRequest.Builder()
                .index(INDEX_NAME)
                .query(q -> q
                        .bool(b -> b
                                .must(m -> m
                                        .wildcard(wc -> wc
                                                .field("name")
                                                .value("*" + name + "*")  // 模糊匹配的值
                                        )
                                )
                        )
                )
                .from(pageNum * pageSize)  // 设置分页起始位置
                .size(pageSize)         // 设置每页的大小
                .build();
        if(StringUtils.isEmpty(name)){
            searchRequest = new SearchRequest.Builder()
                    .index(INDEX_NAME)
                    .from(pageNum * pageSize)  // 设置分页起始位置
                    .size(pageSize)
                    .trackTotalHits(t -> t.enabled(true))
                    .build();
        }

        // 执行查询请求
        SearchResponse<District> searchResponse = client.search(searchRequest, District.class);

        // 处理查询结果
        List<District> districts = new ArrayList<>();
        for (Hit<District> hit : searchResponse.hits().hits()) {
            District district = hit.source();
            district.setId(hit.id());
            districts.add(district);
        }

        Page<District> returnPage = new Page<>(pageNum, pageSize);
        returnPage.setTotal(searchResponse.hits().total().value());
        returnPage.setRecords(districts);
        return returnPage;
    }
}
