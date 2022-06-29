package rolaman.vacuumlabsmarks.model

sealed interface XmlTrademarkItem {
    val xmlId: String
}

data class XmlWordTrademarkItem(
    override val xmlId: String,
    val text: String,
) : XmlTrademarkItem

data class XmlVisualTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class XmlAudibleTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class XmlFigurativeTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class XmlMotionTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class Xml3dShapeTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class XmlPositionTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

data class XmlPatternTrademarkItem(
    override val xmlId: String,
) : XmlTrademarkItem

