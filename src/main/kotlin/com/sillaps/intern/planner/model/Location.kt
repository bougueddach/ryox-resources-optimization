package com.sillaps.intern.planner.model

import com.sillaps.intern.planner.PlacesData
import java.io.Serializable

open class Location(var name: String? = null,
                    var latitude: Double? = null,
                    var longitude: Double? = null) : Serializable {


    fun getAirDistanceDoubleTo(location: Location): Double {
        // Implementation specified by TSPLIB http://www2.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/
        // Euclidean distance (Pythagorean theorem) - not correct when the surface is a sphere
        val latitudeDifference = location.latitude!! - this.latitude!!
        val longitudeDifference = location.longitude!! - this.longitude!!
        return Math.sqrt(
                latitudeDifference * latitudeDifference + longitudeDifference * longitudeDifference)
    }

    fun getRoadDistanceTo(location: Location): Long {

        return PlacesData.data!!.first (
                {it.originCoordinates == "${this.latitude}, ${this.longitude}"}
        ).elements!!.first(
                { it.destinationCoordinates == "${location.latitude}, ${location.longitude}" }
        ).distance!!
    }

    fun getTimeTo(location: Location): Long {
        return PlacesData.data!!.first (
                {it.originCoordinates == "${this.latitude}, ${this.longitude}"}
        ).elements!!.first(
                { it.destinationCoordinates == "${location.latitude}, ${location.longitude}" }
        ).duration!!
    }


    fun getDistanceTo(location: Location): Long = getRoadDistanceTo(location)

}