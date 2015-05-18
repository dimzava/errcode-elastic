package errcode.elastic

import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap
import ratpack.exec.Promise
import ratpack.http.client.HttpClient
import ratpack.http.client.ReceivedResponse

import javax.inject.Inject

class ElasticSearchService implements SearchService {

    private final ElasticSearchConfig config
    private final HttpClient httpClient

    @Inject
    ElasticSearchService(ElasticSearchConfig config, HttpClient httpClient) {
        this.config = config
        this.httpClient = httpClient
    }

    @Override
    Promise<LogEvent> findByErrcode(String errcode) {
        URI uri = "${config.baseUrl}/${config.indexName}/_search?q=${config.queryParam}:${errcode}".toURI()
        httpClient.get(uri).map { ReceivedResponse response ->
            LazyMap json = new JsonSlurper().parseText(response.body.text)
            LazyMap hits = json.hits
            if(hits.total == 0) {
                return null
            }
            return new LogEvent(hits.hits[0]._source)
        }
    }
}
