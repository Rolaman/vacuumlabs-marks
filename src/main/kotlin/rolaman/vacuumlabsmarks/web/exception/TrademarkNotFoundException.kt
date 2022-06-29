package rolaman.vacuumlabsmarks.web.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class TrademarkNotFoundException(
    name: String
) : Exception("Trademark with name $name not found")