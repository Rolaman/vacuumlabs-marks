package rolaman.vacuumlabsmarks.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.ElasticsearchTransport
import co.elastic.clients.transport.rest_client.RestClientTransport
import com.fasterxml.jackson.databind.json.JsonMapper
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.elasticsearch.ElasticsearchContainer
import javax.annotation.PreDestroy

@TestConfiguration
class TestElasticConfig(
    private val jsonMapper: JsonMapper,
) {

    lateinit var container: ElasticsearchContainer

    @Bean
    fun elasticsearchClient(): ElasticsearchClient {
        container = ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.2.3")
        container.addExposedPort(9200)
        container.start()
        val restClient: RestClient = RestClient.builder(
            HttpHost.create("localhost:9200")
        ).build()
        val transport: ElasticsearchTransport = RestClientTransport(
            restClient, JacksonJsonpMapper(jsonMapper)
        )
        return ElasticsearchClient(transport)
    }

    @PreDestroy
    fun destroy() {
        container.stop()
    }
}
