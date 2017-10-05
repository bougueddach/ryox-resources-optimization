package com.sillaps.intern.planner

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sillaps.intern.planner.problems.tsp.Deliverer
import com.sillaps.intern.planner.problems.tsp.Place
import com.sillaps.intern.planner.services.ORService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(SpringRunner::class)
@SpringBootTest
class ORTests {

    @Autowired
    lateinit var planner: ORService



    // TSP
    @Test
    fun tspSolverTest() {

        val visits = listOf<Place>(
                Place("Brussel", 50.8427501, 4.3515499),
                Place("Bonheiden", 51.0185788, 4.5336048),
                Place("Antwerp", 51.2605159, 4.2301104),
                Place("Leuven", 50.8841972, 4.6352191),
                Place("Zaventem", 50.8683589, 4.4433749)
        )
                //Brussel|Bonheiden|Antwerp|Leuven|Zaventem
        assert(visits.isNotEmpty())
        visits[0].origin = true
        assertEquals(visits[0].previousPoint, visits[0])
        val solution = planner.tsp(visits)
        assertNotNull(solution)
        /*assertEquals(solution.places!![4].previousPoint, solution.places!![0]) // Brussel <- Zaventem
        assertEquals(solution.places!![3].previousPoint, solution.places!![4]) // Zaventem <- Leuven
        assertEquals(solution.places!![1].previousPoint, solution.places!![3]) // Leuven <- Bonheiden
        assertEquals(solution.places!![2].previousPoint, solution.places!![1]) // Bonheiden <- Antwerp
        assertEquals(solution.places!![0].previousPoint, solution.places!![2]) // Antwerp <- brussel
        */
    }

    // Test Driver Application (TDA)
    @Test
    fun solveFromJsonFile() {

        // TODO: load cities_be.json ( Librairie: jsoniter , Jackson )
        val mapper: ObjectMapper = ObjectMapper()
        val cities: List<Place> = mapper.readValue(File("./src/test/resources/data/cities_be.json"), object : TypeReference<List<Place>>() {})
        assert(cities.isNotEmpty(), { "Chargement de fichier invalide" })
        val solution = planner.tsp(cities)

        assertNotNull(solution)
        /*assertEquals(solution.places!![4].previousPoint, solution.places!![0]) // Brussel <- Zaventem
        assertEquals(solution.places!![3].previousPoint, solution.places!![4]) // Zaventem <- Leuven
        assertEquals(solution.places!![1].previousPoint, solution.places!![3]) // Leuven <- Bonheiden
        assertEquals(solution.places!![2].previousPoint, solution.places!![1]) // Bonheiden <- Antwerp
        assertEquals(solution.places!![0].previousPoint, solution.places!![2]) // Antwerp <- brussel
        */
    }

    //Road Distance
    @Test
    fun getdistance() {
        var B = Place("Brussel", 50.8427501, 4.3515499)
        var C = Place("Bonheiden", 51.0185788, 4.5336048)
        PlacesData.createData(listOf<Place>(B,C))
        var res=B.getRoadDistanceTo(C)
        print("")
    }

    //many TSP
    @Test
    fun vehicleTspTest() {
        val visits = listOf<Place>(
                Place("Brussel", 50.8427501, 4.3515499),
                Place("Bonheiden", 51.0185788, 4.5336048, 7200),
                Place("Antwerp", 51.2605159, 4.2301104, 5400),
                Place("Leuven", 50.8841972, 4.6352191, 3600),
                Place("Zaventem", 50.8683589, 4.4433749, 900)
        )

        visits[0].origin = true
        assertEquals(visits[0].previousPoint, visits[0])
        PlacesData.createData(visits)
        val deliverers = listOf<Deliverer>(
                Deliverer("D1", 18000),
                Deliverer("D2", 18000),
                Deliverer("D3", 18000)
        )

        //val solution = planner.getVisitsListByDeliverer(visits.toMutableList(), deliverers)
        //assertNotNull(solution)

    }

/*
    //Vehicle Routing
    @Test
    fun vehicleRoutingTest(){

        val depot1 = Location("Depot:1",50.8683589, 4.4433749)
        var vehicles = listOf<Vehicle>(
          Vehicle("V1",100 , depot1),
          Vehicle("V2",100 , depot1)
        )

       var customers = listOf<Customer>(
                Customer("C1", , 28),
                Customer("C2", , 10),
                Customer("C3", , 45),
                Customer("C4", , 5),
                Customer("C5", , 78)
        )



    }*/


}
