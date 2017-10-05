package com.sillaps.intern.planner.problems.tsp

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.maps.DistanceMatrixApi
import com.sillaps.intern.planner.model.Location
import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.PlanningVariable
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType


@PlanningEntity
class Place(name: String? = null, latitude: Double? = null, longitude: Double? = null , var duration: Long = 0L) : Location(name, latitude, longitude) {

    @PlanningVariable(valueRangeProviderRefs = arrayOf("places"), graphType = PlanningVariableGraphType.CHAINED) //"originRange"
    var previousPoint: Place? = null
    var origin:Boolean? = false
        set(value) {
            field=value
            if (value == true) {
                previousPoint = this
                duration = 0
            }
        }

    /*init {
        if (origin == true) previousPoint = this
    }*/

    fun getDistanceFrom(location: Location): Long {
        return location.getDistanceTo(this)
    }

    fun getTimeFromPreviousPoint(): Long{
        if (previousPoint == null) {
            return 0L
        }
        return previousPoint!!.getTimeTo(this)

    }

    fun getDistanceFromPreviousPoint(): Long {
        if (previousPoint == null) {
            return 0L
        }
        return getDistanceFrom(previousPoint!!)
    }

}