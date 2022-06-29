package rolaman.vacuumlabsmarks.repository

import com.fasterxml.jackson.databind.json.JsonMapper
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.text
import com.mongodb.client.model.TextSearchOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component
import rolaman.vacuumlabsmarks.model.Trademark

@Component
class TrademarkRepository(
    mongoTemplate: MongoTemplate,
    mapper: JsonMapper,
) : MongoRepository<Trademark>(mongoTemplate, mapper) {

    fun findByName(name: String): Trademark? {
        val documents = mongoTemplate.getCollection(collectionName)
            .find(eq("name", name))
        return documents.asSequence()
            .map { mapper.readValue(it.toJson(), Trademark::class.java) }
            .firstOrNull()
    }

    fun findManyByText(text: String): Sequence<Trademark> {
        val searchOptions = TextSearchOptions()
        searchOptions.caseSensitive(false)
        searchOptions.diacriticSensitive(false)

        return mongoTemplate.getCollection(collectionName)
            .find(text(text, searchOptions))
            .asSequence()
            .map { mapper.readValue(it.toJson(), Trademark::class.java) }
    }

    override val collectionName: String = "trademark"
}
