package rolaman.vacuumlabsmarks.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Trademark(
    @JsonProperty("_id")
    override val id: String,
    val name: String,
    val externalId: String,
    val normalizedName: String,
): Entity
