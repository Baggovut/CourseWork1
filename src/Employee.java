import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String firstName, lastName, middleName;
    private int departmentID;
    private double salary;
    // всего у нас может быть 5 отделов
    private final int startDepartmentID = 1, endDepartmentID = 5;

    /**
     * @param lastName фамилия сотрудника
     * @param firstName имя сотрудника
     * @param middleName отчество сотрудника
     * @param departmentID номер депертамента сотрудника
     * @param salary зарплата сотрудника
     */
    public Employee(String lastName, String firstName, String middleName, int departmentID, double salary){
        if (salary <= 0){
            throw new IllegalArgumentException("Зарплата должна быть больше 0.");
        }
        if(departmentID < startDepartmentID || departmentID > endDepartmentID){
            throw new IllegalArgumentException("Номер отдела может быть от "+startDepartmentID+" до "+endDepartmentID+" включительно.");
        }
        this.departmentID = departmentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.salary = salary;
    }

    /**
     * @return получаем имя сотрудника
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return получаем фамилию сотрудника
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @return получаем отчество сотрудника
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * @return узнаём зарплату сотрудника
     */
    public double getSalary() {
        return this.salary;
    }

    /**
     * @return получаем номер отдела сотрудника
     */
    public int getDepartmentID() {
        return this.departmentID;
    }

    /**
     * @param salary новая зарплата сотрудника
     */
    public void setSalary(double salary) {
        if (salary <= 0){
            throw new IllegalArgumentException("Зарплата должна быть больше 0.");
        }
        this.salary = salary;
    }

    public void setDepartmentID(int departmentID){
        if(departmentID < startDepartmentID || departmentID > endDepartmentID){
            throw new IllegalArgumentException("Номер отдела может быть от 1 до 5 включительно.");
        }
        this.departmentID = departmentID;

    }

    /**
     * @return ID ервого отдела
     */
    public int getStartDepartmentID() {
        return this.startDepartmentID;
    }

    /**
     * @return ID последнего отдела
     */
    public int getEndDepartmentID() {
        return this.endDepartmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Фамилия='" + lastName + '\'' +
                ", имя='" + firstName + '\'' +
                ", отчество='" + middleName + '\'' +
                ", зарплата=" + salary +
                ", номер отдела=" + departmentID +
                "}\n";
    }
}
