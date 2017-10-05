package com.sillaps.intern.planner.problems.vehicleRouting

import com.sillaps.intern.planner.model.Location
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScore

//@PlanningSolution
class VehicleRoutingProblem {
    var depot: Location?= null

    //@PlanningEntityCollectionProperty
    //@ValueRangeProvider(id = "customers")
    var customers: List<Customer>?= null

    //@PlanningEntityCollectionProperty
    //@ValueRangeProvider(id = "vehicles")
    var vehicles: List<Vehicle>?= null

    @Suppress("unused")
    //@PlanningScore
    lateinit var score: SimpleLongScore

    constructor(customers: List<Customer> , vehicles: List<Vehicle>) {
        this.customers = customers
        this.vehicles = vehicles
    }
}