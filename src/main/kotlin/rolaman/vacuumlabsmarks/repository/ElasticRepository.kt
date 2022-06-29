package rolaman.vacuumlabsmarks.repository

import co.elastic.clients.elasticsearch.ElasticsearchClient
import rolaman.vacuumlabsmarks.model.Entity

abstract class ElasticRepository<E : Entity>(
    protected val client: ElasticsearchClient,
) {

    protected abstract val index: String

    fun add(entity: E) {
        client.index<E> {
            it.index(index)
                .id(entity.id)
                .document(entity)
        }
    }
}