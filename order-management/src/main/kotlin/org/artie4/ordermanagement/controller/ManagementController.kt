package org.artie4.ordermanagement.controller

import org.artie4.ordermanagement.client.DataTierClient
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/v1")
class ManagementController(
    private val dataTierClient: DataTierClient
) {

    @RequestMapping("planets")
    suspend fun getPlanets(model: Model): String {
        model.addAttribute("planets", dataTierClient.getPlanets())
        return "planets"
    }
}