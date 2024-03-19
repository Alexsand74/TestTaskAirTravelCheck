package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.FlightBuilder.createFlight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightCheckTest {
    LocalDateTime threeDaysFromNow = null;
    List<Flight> result = null;
    List<Flight> expected = null;

    @BeforeEach
    void creatingTestData() {
        threeDaysFromNow = LocalDateTime.now().plusDays(3);
        result = FlightBuilder.createFlights();
        expected = Arrays.asList(
                //A normal flight with two hour duration
                //Обычный полет продолжительностью два часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                //Обычный многосегментный рейс
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight with more than two hours ground time
                //Рейс продолжительностью более двух часов наземного времени
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                //Еще один рейс с наземным временем более двух часов.
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }


    @Test
    void testFlightValidation() {
        var actual = FlightCheck.flightValidation(result);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.toString(),actual.toString());
//        for (int i = 0; i < expected.size(); i++) {
//            Flight flightExpected = expected.get(i);
//            Flight flightActual = actual.get(i);
//            List listExpected = flightExpected.getSegments();
//            List listActual = flightActual.getSegments();
//             assertEquals(listExpected.size(),listActual.size());
//            for (int j = 0; j < expected.size(); j++) {
//                assertEquals(listExpected.get(j).,listActual.get(j));
//            }
//        }
    }


//    @AfterEach
//    void clearingTestData() {
//        result = null;
//        threeDaysFromNow = null;
//        expected = null;
//    }
}

//    @Test
//    void testAddEmployee() {
//        employeeService.addEmployee("test", "test2", 15_000, 15);
//
//        Collection<Employee> allEmployees = employeeService.getAll();
//        assertEquals(1, allEmployees.size());
//        var employee = allEmployees.iterator().next();
//        assertEquals("Test", employee.getFirstName());
//        assertEquals("Test2", employee.getLastName());
//        assertEquals(15_000, employee.getSalary());
//        assertEquals(15, employee.getDepartment());
//        Assertions.assertThat();
//        assertTrue();
//        assertThrows();
//        assertNotNull();
//        Assertions.assertThrows();
//
//    }


//    @Test
//    void testAddEmployeesWhenStorageIsFull() {
//        for (int i = 0; i < LIMIT; i++) {
//            employeeService.addEmployee("test_" + i, "test_test_" + i, 0d, 0);
//        }
//        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(
//                "test_"+ LIMIT, "test_test_" + LIMIT, 0, 0));
//    }
//
//    @Test
//    void testAddEmployeeWhenAlreadyExists() {
//        employeeService.addEmployee("test", "test", 0, 0);
//        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(
//                "Test", "Test", 0, 0));
//
//    }
//
//    @Test
//    void testFindEmployee() {
//        employeeService.addEmployee("test", "testtest", 15_000, 1);
//        var actual = employeeService.findEmployee("test", "testtest");
//        assertEquals("Test", actual.getFirstName());
//        assertEquals("Testtest", actual.getLastName());
//        assertEquals(15_000, actual.getSalary());
//        assertEquals(1, actual.getDepartment());
//    }
//
//    @Test
//    void testFindEmployeeWhenNotExist() {
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("test", "testtest"));
//    }
//
//    @Test
//    void testRemoveEmployee() {
//        employeeService.addEmployee("test", "testtest", 10, 1);
//        assertTrue(employeeService.removeEmployee("test", "testtest"));
//        assertEquals(0, employeeService.getAll().size());
//        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("test0", "testtest0"));
//    }
//
//    @Test
//    void testGetAllEmployees() {
//        employeeService.addEmployee("test_1", "test_test_1", 100, 1);
//        employeeService.addEmployee("test_2", "test_test_2", -100, 1);
//        employeeService.addEmployee("test_3", "test_test_3", 100, -1);
//
//        var all = employeeService.getAll();
//        assertEquals(3,all.size());
//
//        var allTest = List.of(new Employee("Test_1", "Test_test_1", 100, 1),
//                new Employee("Test_2", "Test_test_2", -100, 1),
//                new Employee("Test_3", "Test_test_3", 100, -1));
//
//        for (Employee e:all) {
//            assertTrue(allTest.contains(e));
//        }
//        for (Employee e:allTest) {
//            assertTrue(all.contains(e));
//        }
//
//        Assertions.assertTrue(all.size()).isEqualTo(3);
//        assertTrue(all).containsExactlyInAnyOrderElementsOf(allTest);
//
//

