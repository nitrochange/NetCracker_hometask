import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class StreamsTaskTest {

    public static Collection<List<Employee>> employeesByDepartment;


    @BeforeAll
    public static void init()
    {
        Employee _1 = new Employee(1000, "Moscow", 20);
        Employee _2 = new Employee(2000, "Moscow", 21);
        Employee _3 = new Employee(3000, "Moscow", 22);
        Employee _4 = new Employee(4000, "Minks", 21);
        Employee _5 = new Employee(2000, "Minks", 20);
        Employee _6 = new Employee(1000, "Minks", 22);
        Employee _7 = new Employee(5000, "Kiev", 22);
        Employee _8 = new Employee(2000, "Kiev", 21);
        Employee _9 = new Employee(1000, "Kiev", 20);

        employeesByDepartment = ImmutableSet.of(_1, _2, _3, _4, _5, _6, _7, _8, _9)
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .values();
    }


    private static Integer findTotalSalaryOfEmployeesBelow22(Collection<List<Employee>> employeesByDepartment) {
        int result = 0;
        for (List<Employee> depart: employeesByDepartment)
            result += depart.stream().filter(item -> item.getAge() < 22).mapToInt(Employee::getSalary).sum();
        if (result == 0) throw new RuntimeException();
        return result;
    }


    @Test
    public void simpleTest()
    {
        Assertions.assertEquals(12000, findTotalSalaryOfEmployeesBelow22(employeesByDepartment));
    }

    @Test
    public void exceptionTest()
    {
        employeesByDepartment.clear();
        Assertions.assertThrows(RuntimeException.class, () -> {findTotalSalaryOfEmployeesBelow22(employeesByDepartment);});
    }


}