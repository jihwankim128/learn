package org.delivery.api.domain.temp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/temp")
@RestController
class TempApiController {

    @GetMapping("")
    fun temp(): String {
        return "hello kopring";
    }

}