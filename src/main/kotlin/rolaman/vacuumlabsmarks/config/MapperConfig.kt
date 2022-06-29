package rolaman.vacuumlabsmarks.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class MapperConfig {

    @Bean
    fun xmlMapper(): XmlMapper {
        val mapper = XmlMapper()
        mapper.registerKotlinModule()
        return mapper
    }

    @Bean
    @Primary
    fun jsonMapper(): JsonMapper {
        val mapper = JsonMapper()
        mapper.registerKotlinModule()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return mapper
    }
}
