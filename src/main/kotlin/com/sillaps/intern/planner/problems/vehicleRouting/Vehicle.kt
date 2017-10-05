package com.sillaps.intern.planner.problems.vehicleRouting

import com.sillaps.intern.planner.model.Location


class Vehicle(name: String?= null, var capacity: Int?= null,  var depot: Location?= null) {
    var nextCustomer: Customer?= null

}