package rolaman.vacuumlabsmarks.model

data class Trademark(
    override val id: String,
    val name: String,
    val externalId: String,
    val normalizedName: String,
): Entity
