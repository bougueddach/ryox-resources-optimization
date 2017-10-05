package com.sillaps.intern.planner.controllers


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sillaps.intern.planner.problems.tsp.Deliverer
import com.sillaps.intern.planner.problems.tsp.Place
import com.sillaps.intern.planner.services.ORService
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.sillaps.intern.planner.model.Location

@RestController
@RequestMapping("api")
class ORRest {

    @Autowired
    lateinit var service: ORService

    // TODO: Solve with API ( POST /api/tsp )
    @CrossOrigin()
    @PostMapping("tsp")
    fun tsp(@RequestBody places: List<Place>): List<Location> {
        var tspSolution = service.tsp(places)
        return tspSolution
    }

    /*@PostMapping("manytsp")
    fun getVisitsListByDeliverer(@RequestBody places: String, @RequestBody delivrers: String): Any {
        val mapper: ObjectMapper = ObjectMapper()
        var cities: List<Place> = mapper.readValue(places, object : TypeReference<List<Place>>() {})
        var delivererss: List<Deliverer> = mapper.readValue(delivrers, object : TypeReference<List<Deliverer>>() {})
        var tspSolution = service.getVisitsListByDeliverer(cities.toMutableList(), delivererss)
        return mapper.writeValueAsString((tspSolution))
    }*/

}