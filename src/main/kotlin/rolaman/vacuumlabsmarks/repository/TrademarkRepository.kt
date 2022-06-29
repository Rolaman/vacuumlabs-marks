package rolaman.vacuumlabsmarks.repository

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.core.SearchRequest
import org.springframework.stereotype.Component
import rolaman.vacuumlabsmarks.model.Trademark

@Component
class TrademarkRepository(
    client: ElasticsearchClient,
) : ElasticRepository<Trademark>(client) {

    fun findByName(name: String): Trademark? {
        val request = SearchRequest.of {
            it.index(index)
                .query { q ->
                    q.term { term ->
                        term.field("name.keyword").value(name)
                    }
                }
                .size(1)
        }
        return client.search(request, Trademark::class.java).hits().hits()
            .map { it.source() }
            .firstOrNull()
    }

    fun findManyByText(text: String): List<Trademark> {
        val request = SearchRequest.of {
            it.index(index)
                .query { q ->
                    q.bool { b ->
                        b.should { q1 ->
                            q1.fuzzy { fuzzy ->
                                fuzzy.field("name").value(text)
                            }
                        }.should { q2 ->
                            q2.regexp { r ->
                                r.field("normalizedName.keyword").value(".*$text.*")
                                    .caseInsensitive(true)
                            }
                        }
                    }
                }
                .size(100)

        }
        return client.search(request, Trademark::class.java).hits().hits()
            .mapNotNull { it.source() }
    }

    override val index: String = "trademark"
}

