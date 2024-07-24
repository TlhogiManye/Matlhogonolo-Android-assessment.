package com.glucode.about_you.engineers

import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.engineers.models.Engineer
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EngineersFragmentTest {

    private val engineersList = MockData.engineers

    @Test
    fun testFilterByYears() {
        val sortedList = engineersList.sortedBy { it.quickStats.years }
        val expectedList = listOf(
            engineersList[0], // Reenen (6 years)
            engineersList[3], // Stefan (7 years)
            engineersList[4], // Brandon (9 years)
            engineersList[5], // Henri (10 years)
            engineersList[2],  // Eben (14 years)
            engineersList[1], // Wilmar (15 years)





        )
        assertEquals(expectedList, sortedList)
    }

    @Test
    fun testFilterByCoffees() {
        val sortedList = engineersList.sortedBy { it.quickStats.coffees }
        val expectedList = listOf(
            engineersList[2],  // Eben (1000 coffees)
            engineersList[5], // Henri (1800 coffees)
            engineersList[1], // Wilmar (4000 coffees)
            engineersList[0], // Reenen (5400 coffees)
            engineersList[3], // Stefan (9000 coffees)
            engineersList[4], // Brandon (99999 coffees)
        )
        assertEquals(expectedList, sortedList)
    }

    @Test
    fun testFilterByBugs() {
        val sortedList = engineersList.sortedBy { it.quickStats.bugs }
        val expectedList = listOf(
            engineersList[2], // Eben (100 bugs)
            engineersList[3], // Stefan (700 bugs)
            engineersList[5], // Henri (1000 bugs)
            engineersList[0], // Reenen (1800 bugs)
            engineersList[1], // Wilmar (4000 bugs)
            engineersList[4], // Brandon (99999 bugs)
        )
        assertEquals(expectedList, sortedList)
    }
}
