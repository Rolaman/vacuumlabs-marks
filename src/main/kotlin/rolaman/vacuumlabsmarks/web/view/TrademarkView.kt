package rolaman.vacuumlabsmarks.web.view

import rolaman.vacuumlabsmarks.model.Trademark

data class TrademarkView(
    val id: String,
    val name: String,
)

fun trademarkView(trademark: Trademark): TrademarkView {
    return TrademarkView(
        id = trademark.id,
        name = trademark.name,
    )
}
