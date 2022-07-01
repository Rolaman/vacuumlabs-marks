package rolaman.vacuumlabsmarks.reader

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.springframework.stereotype.Component
import rolaman.vacuumlabsmarks.model.*
import java.io.File

@Component
class XmlTrademarkReader(
    private val xmlMapper: XmlMapper,
) {

    fun read(file: File): XmlTrademarkItem {
        val jsonNode = xmlMapper.readTree(file)
        val data = jsonNode.required("TradeMarkTransactionBody")
            .required("TransactionContentDetails")
            .required("TransactionData")
            .required("TradeMarkDetails")
            .required("TradeMark")
        val xmlId = data.required("ApplicationNumber").asText()
        return when (val type = data.required("MarkFeature").asText().uppercase()) {
            "WORD" -> {
                XmlWordTrademarkItem(
                    xmlId = xmlId,
                    text = data.required("WordMarkSpecification")
                        .required("MarkVerbalElementText").asText(),
                )
            }
            "AUDIBLE" -> {
                XmlAudibleTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "VISUAL" -> {
                XmlVisualTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "FIGURATIVE" -> {
                XmlFigurativeTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "MOTION" -> {
                XmlMotionTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "3D SHAPE" -> {
                Xml3dShapeTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "POSITION" -> {
                XmlPositionTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "PATTERN" -> {
                XmlPatternTrademarkItem(
                    xmlId = xmlId,
                )
            }
            "SOUND" -> {
                XmlSoundTrademarkItem(
                    xmlId = xmlId,
                )
            }
            else -> throw Exception("Unknown trademark type $type")
        }
    }
}
