package rolaman.vacuumlabsmarks.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.ElasticsearchTransport
import co.elastic.clients.transport.rest_client.RestClientTransport
import com.fasterxml.jackson.databind.json.JsonMapper
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticConfig(
    private val jsonMapper: JsonMapper,
) {

    @Value("\${elastic.host:localhost}")
    lateinit var elasticHost: String

    @Bean
    fun elasticsearchClient(): ElasticsearchClient {
        val restClient: RestClient = RestClient.builder(
            HttpHost(elasticHost, 9200)
        ).build()
        val transport: ElasticsearchTransport = RestClientTransport(
            restClient, JacksonJsonpMapper(jsonMapper)
        )
        return ElasticsearchClient(transport)
    }
}