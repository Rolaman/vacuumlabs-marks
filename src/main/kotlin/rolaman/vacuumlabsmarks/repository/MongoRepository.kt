package rolaman.vacuumlabsmarks.repository

import com.fasterxml.jackson.databind.json.JsonMapper
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import rolaman.vacuumlabsmarks.model.Entity

abstract class MongoRepository<E: Entity>(
    protected val mongoTemplate: MongoTemplate,
    protected val mapper: JsonMapper,
) {

    protected abstract val collectionName: String

    fun add(entity: E) {
        val json = mapper.writeValueAsString(entity)
        mongoTemplate.getCollection(collectionName).insertOne(Document.parse(json))
    }
}