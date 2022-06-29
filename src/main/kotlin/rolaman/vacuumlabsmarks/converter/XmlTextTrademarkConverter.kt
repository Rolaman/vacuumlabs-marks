package rolaman.vacuumlabsmarks.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import rolaman.vacuumlabsmarks.model.Trademark
import rolaman.vacuumlabsmarks.model.XmlWordTrademarkItem
import java.util.UUID

@Component
class XmlTextTrademarkConverter : Converter<XmlWordTrademarkItem, Trademark> {

    override fun convert(source: XmlWordTrademarkItem): Trademark {
        return Trademark(
            id = UUID.randomUUID().toString(),
            name = source.text,
            externalId = source.xmlId,
            normalizedName = normalize(source.text),
        )
    }

    private fun normalize(text: String): String {
        return text
            .lowercase()
            .replace("&", " and ")
            .replace(NON_ALPHANUMERIC_REGEX, " ")
            .replace("_", " ")
            .replace(DUPLICATED_WHITESPACES_REGEX, " ")
            .trim()
    }

    companion object {
        val NON_ALPHANUMERIC_REGEX = "[^\\w\\d]".toRegex()
        val DUPLICATED_WHITESPACES_REGEX = "\\s+".toRegex()
    }
}
