package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // local mockup collection for testing purposes
//    private List<EmployeeRest> employees = new ArrayList<>(Arrays.asList(
//            new EmployeeRest(1, "date1", "Max", "Garipov", 'M', "date2"),
//            new EmployeeRest(2, "date1", "Alina", "Garipova", 'F', "date2")
//    ));

    public List<EmployeeRest> readAllEmployees() {
        // temp mockup
//        return employees;
        // read all EmployeeRest records from DB
//        List<EmployeeRest> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
        // shorter version of the same as above
        return (List<EmployeeRest>) employeeRepository.findAll();
    }

    // does not work atm
//    public List<EmployeeRest> findPaginated(int pageNo, int pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<EmployeeRest> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    public EmployeeRest readEmployee(Integer emp_no) {
        // temp mockup
//        return employees.stream().filter(employeeRest -> employeeRest.getEmp_no().equals(emp_no)).findFirst().get();
        // read a single EmployeeRest record
        EmployeeRest employeeRest = employeeRepository.findById(emp_no).orElse(null);
        return employeeRest;
    }

    public EmployeeRest createEmployee(EmployeeRequest employeeRequest) {
        // temp mockup
//        employees.add(employeeRest);
        // create new EmployeeRest record
        System.out.println("Gender char before = " + employeeRequest.getGender());
        employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
        System.out.println("Gender char after = " + employeeRequest.getGender());
        //System.out.println("Id of the new JSON payload employeeRest = " + employeeRest.getEmp_no());
        //EmployeeRest savedEmployeeRest = employeeRepository.save(employeeRest);
        //System.out.println("Id of the saved EmployeeRest = " + savedEmployeeRest.getEmp_no());
        EmployeeRest newEmployeeRest = new EmployeeRest();
        newEmployeeRest.setBirth_date(employeeRequest.getBirth_date());
        newEmployeeRest.setFirst_name(employeeRequest.getFirst_name());
        newEmployeeRest.setLast_name(employeeRequest.getLast_name());
        newEmployeeRest.setGender(employeeRequest.getGender());
        newEmployeeRest.setHire_date(employeeRequest.getHire_date());
        return employeeRepository.save(newEmployeeRest);
    }

    public EmployeeRest updateEmployee(Integer emp_no, EmployeeRequest employeeRequest) {
        // temp mockup
//        for (int i = 0; i < employees.size(); i++) {
//            EmployeeRest e = employees.get(i);
//            if (e.getEmp_no().equals(emp_no)) {
//                employees.set(i, employeeRest);
//                return;
//            }
//        }
        // update an EmployeeRest record
        EmployeeRest existingEmployeeRest = employeeRepository.findById(emp_no).orElse(null);
        if (existingEmployeeRest != null) {
            if (employeeRequest.getBirth_date() != null && !(employeeRequest.getBirth_date().equalsIgnoreCase(existingEmployeeRest.getBirth_date()))) {
                existingEmployeeRest.setBirth_date(employeeRequest.getBirth_date());
            }
            if (employeeRequest.getFirst_name() != null && !(employeeRequest.getFirst_name().equalsIgnoreCase(existingEmployeeRest.getFirst_name()))) {
                existingEmployeeRest.setFirst_name(employeeRequest.getFirst_name());
            }
            if (employeeRequest.getLast_name() != null && !(employeeRequest.getLast_name().equalsIgnoreCase(existingEmployeeRest.getLast_name()))) {
                existingEmployeeRest.setLast_name(employeeRequest.getLast_name());
            }
            if (employeeRequest.getGender() != null) {
                employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
                if (!(employeeRequest.getGender().equals(existingEmployeeRest.getGender()))) {
                    existingEmployeeRest.setGender(employeeRequest.getGender());
                }
            }
            if (employeeRequest.getHire_date() != null && !(employeeRequest.getHire_date().equalsIgnoreCase(existingEmployeeRest.getHire_date()))) {
                existingEmployeeRest.setHire_date(employeeRequest.getHire_date());
            }
        }
        return employeeRepository.save(existingEmployeeRest);
    }

    public void deleteEmployee(Integer emp_no) {
        // temp mockup
//        employees.removeIf(e -> e.getEmp_no().equals(emp_no));
        // delete an EmployeeRest record
        employeeRepository.deleteById(emp_no);
    }
}
