package com.sillaps.intern.planner.problems.vehicleRouting

import com.sillaps.intern.planner.model.Location
import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.variable.AnchorShadowVariable
import org.optaplanner.core.api.domain.variable.PlanningVariable
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType

//@PlanningEntity
class Customer(name: String?= null, latitude: Double?= null, longitude: Double?=null, var  demand: Int?= null ) : Location(name, latitude, longitude) {

    //@PlanningVariable(valueRangeProviderRefs = arrayOf("customers" , "vehicles"), graphType = PlanningVariableGraphType.CHAINED)
    var previousCustomer: Customer?= null

    var nextCustomer: Customer?= null
    //@AnchorShadowVariable
    var vehicle: Vehicle?= null


}