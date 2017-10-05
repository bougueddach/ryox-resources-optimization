package com.sillaps.intern.planner

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.maps.DistanceMatrixApi
import com.google.maps.GeoApiContext
import com.sillaps.intern.planner.model.LocationData
import com.sillaps.intern.planner.model.LocationElement
import com.sillaps.intern.planner.problems.tsp.Place
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import java.io.File
import java.io.FileWriter

object PlacesData {

    var data = mutableListOf<LocationData>()
    var context = GeoApiContext.Builder().apiKey("AIzaSyAmMhlpK-H6YPJEaq7a14HAiznzJyaA9-o").build()

    /*fun createJsonData(visits:List<Place>){
        var coordinatesArray = arrayOfNulls<String>(5)
        var i: Int = 0
        for (place in visits) {
            coordinatesArray[i] = place.latitude.toString() + ", " + place.longitude.toString()
            i++
        }
        val map = DistanceMatrixApi.getDistanceMatrix(context, coordinatesArray, coordinatesArray).await()
        var originRow: JSONArray = JSONArray()
        var rows: JSONObject? = JSONObject()
        var destinationRow: JSONArray? = JSONArray()
        for (i in 0 until map.rows.size) {
            for (j in 0 until map.rows[0].elements.size) {
                var element: JSONObject = JSONObject()
                element.put("destinationCoordinates", coordinatesArray[j])
                element.put("distance", map.rows[i].elements[j].distance.inMeters)
                element.put("duration", map.rows[i].elements[j].duration.inSeconds)
                destinationRow!!.add(element)
            }
            rows!!.put("originCoordinates", coordinatesArray[i])
            rows!!.put("elements", destinationRow)
            originRow.add(rows)
            rows = null
            rows = JSONObject()
            destinationRow = null
            destinationRow = JSONArray()
        }

        val file = FileWriter("./src/main/resources/data/mapData.json")
        file.write(originRow.toString())
        file.flush()
        data = ObjectMapper().readValue(File("./src/main/resources/data/mapData.json"), object : TypeReference<List<LocationData>>() {})

    }*/

    fun createData(visits:List<Place>){
        var coordinatesArray = arrayOfNulls<String>(visits.size)
        for(i in 0 until visits.size){
            coordinatesArray[i] = visits[i].latitude.toString() + ", " + visits[i].longitude.toString()
        }
        val map = DistanceMatrixApi.getDistanceMatrix(context, coordinatesArray, coordinatesArray).await()
        for (i in 0 until map.rows.size) {
            var locationData=LocationData()
            locationData.originCoordinates=coordinatesArray[i]
            for (j in 0 until map.rows[0].elements.size) {
                var elementObject=LocationElement()
                elementObject.destinationCoordinates=coordinatesArray[j]
                elementObject.distance=map.rows[i].elements[j].distance.inMeters
                elementObject.duration=map.rows[i].elements[j].duration.inSeconds
                locationData.elements!!.add(elementObject)
            }
            data!!.add(locationData)
        }
    }

}