import com.google.common.collect.ImmutableSet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTask {
    public static void main(String[] args) {

        Optional<Employee> _1 = Optional.of(new Employee(1000));
        Optional<Employee> _2 = Optional.of(new Employee(2000));
        Optional<Employee> _3 = Optional.of(new Employee(null));
        Optional<Employee> _4 = Optional.ofNullable(null);

        Set<Optional<Employee>> employees_1234 = ImmutableSet.of(_1, _2, _3, _4);
        Set<Optional<Employee>> employees_34 = ImmutableSet.of(_3, _4);
        Set<Optional<Employee>> employees_4 = ImmutableSet.of(_4);

        //expected output 2000
        System.out.println(getMaxSalaryOrElse(employees_1234, 0));

        //expected output 0
        System.out.println(getMaxSalaryOrElse(employees_34, 0));

        //expected output 0
        System.out.println(getMaxSalaryOrElse(employees_4, 0));
    }

    /**
     * @param employees - set of Employees which we have to parse
     * @param defaultValue - value uses in case when object is null or does not exist
     * @return max salary among given employees or defaultValue if all employees are not present
     */
    private static Integer getMaxSalaryOrElse(Set<Optional<Employee>> employees, int defaultValue) {

        List<Integer> salaries = employees
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList())
                .stream()
                .map(o -> o.getSalary().isPresent()? o.getSalary().get(): defaultValue)
                .collect(Collectors.toList());
        return salaries.isEmpty()? defaultValue: Collections.max(salaries);
    }
}
