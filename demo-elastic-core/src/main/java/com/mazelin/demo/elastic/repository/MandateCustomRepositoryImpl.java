package com.mazelin.demo.elastic.repository;

import com.mazelin.demo.elastic.model.Mandate;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MandateCustomRepositoryImpl implements MandateCustomRepository {

    @Override
    public List<Mandate> findAll(Set<String> offers, String query) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200)).build());

        SearchRequest searchRequest = new SearchRequest("gp");
        searchRequest.types("mandate");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(offers.size() >0 )
            searchSourceBuilder.query(QueryBuilders.termsQuery("offer", offers));


        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "id", "client.fistname", "client.lastname"));


        searchSourceBuilder.size(1000);

        searchRequest.source(searchSourceBuilder);

        final SearchResponse searchResponse = client.search(searchRequest);

        Arrays.asList(searchResponse.getHits().getHits()).stream();


        return null;
    }
}
