import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeBook implements Serializable {
    private ArrayList<Employee> employeeBook;
    private static final long serialVersionUID = 1L;

    public EmployeeBook(){
        employeeBook = new ArrayList<>();
    }

    /**
     * Добавляем нового сотрудника
     * @param employee сотрудник
     */
    public void addEmployee(Employee employee){
        this.employeeBook.add(employee);
    }

    /**
     * @param ID идентификатор сотрудника
     * @return объект Employee
     */
    public Employee getEmployeeByID(int ID){
        return this.employeeBook.get(ID);
    }

    /**
     * Удаляем сотрудника по его ID
     * @param ID идентификатор удаляемого сотрудника
     */
    public void deleteEmployeeByID(int ID){
        employeeBook.remove(ID);
    }

    /**
     * Находим ID сотрудника по его Ф.И.О.
     * @param lastName фамилия сотрудника
     * @param firstName имя сотрудника
     * @param middleName отчество сотрудника
     * @return ID сотрудника
     */
    public int findEmployeeByName(String lastName, String firstName, String middleName){
        // сотрудник ищестя до первого совпадения ФИО
        // в условие задачи не было ничего сказано про совпадение ФИО, поэтому реализовать метод с массивом идентефикаторов вместо одной переменной, не стал
        // если данный метод использовать для удаления сотрудников, то он будет работать корректно, только нужно будет запустить несколько раз
        // если использовать для изменения сотрудников, то будет меняться самый первый сотрудник с совпадающем ФИО
        for(int i = 0; i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getFirstName().equals(firstName) && getEmployeeByID(i).getLastName().equals(lastName) && getEmployeeByID(i).getMiddleName().equals(middleName)){
                return i;
            }
        }
        System.out.println("\033[4mНе найден сотрудник по заданным ФИО\033[0m");
        return -1;
    }

    /**
     * Удаляем сотрудника по его Ф.И.О.
     * @param lastName фамилия сотрудника
     * @param firstName имя сотрудника
     * @param middleName отчество сотрудника
     */
    public void deleteEmployeeByName(String lastName, String firstName, String middleName){
        int ID = findEmployeeByName(lastName, firstName, middleName);
        if(ID != -1){
            deleteEmployeeByID(ID);
        }
    }

    /**
     * @return Узнаём количество всех сотрудников
     */
    public int getNumberOfEmployees(){
        return this.employeeBook.size();
    }

    /**
     * @return ID работника с максимальной зарплатой
     */
    public int getIDOFEmployeeWithMaxSalary(){
        double maxSalary = getEmployeeByID(0).getSalary();
        int ID=-1;
        for(int i = 0; i < getNumberOfEmployees(); i++){
            if(getEmployeeByID(i).getSalary() > maxSalary){
                maxSalary = getEmployeeByID(i).getSalary();
                ID = i;
            }
        }
        return ID;
    }
    //поиск сотрудника с максимальной зарплатой
    /**
     * @return объект класса Employee у которого максимальная зарплата
     */
    public Employee getEmployeeWithMaxSalary(){
        return getEmployeeByID(getIDOFEmployeeWithMaxSalary());
    }

    /**
     * @return ID работника с минимальной зарплатой
     */
    public int getIDOFEmployeeWithMinSalary(){
        double minSalary = getEmployeeByID(0).getSalary();
        int ID = -1;
        for(int i = 0; i < getNumberOfEmployees(); i++){
            if(getEmployeeByID(i).getSalary() < minSalary){
                minSalary = getEmployeeByID(i).getSalary();
                ID = i;
            }
        }
        return ID;
    }

    //поиск сотрудника с минимальной зарплатой
    /**
     * @return объект класса Employee у которого минимальная зарплата
     */
    public Employee getEmployeeWithMinSalary(){
        return getEmployeeByID(getIDOFEmployeeWithMinSalary());
    }

    /**
     * @return средняя зарплата
     */
    public double getAverageSalary(){
        double allSalary=0;
        for (int i = 0;i < getNumberOfEmployees();i++){
            allSalary+= getEmployeeByID(i).getSalary();
        }
        return allSalary/getNumberOfEmployees();
    }

    /**
     * @param departmentID ID отдела
     * @return количество сотрудников в конкретном отделе
     */
    public int getNumberOfEmployeesInDepartment(int departmentID){
        int counter = 0;
        for (int i = 0;i < getNumberOfEmployees();i++){
            if (getEmployeeByID(i).getDepartmentID() == departmentID){
                counter++;
            }
        }
        return counter;
    }

    /**
     * @param departmentID ID отдела
     * @return ID сотрудника с минимальной зарплатов в отделе
     */
    public int getIDOfEmployeeWithMinSalaryByDepartment(int departmentID){
        boolean firstEmployee = true;
        double minSalary = 0;
        int ID = -1;
        for (int i = 0;i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getDepartmentID() == departmentID){
                if(firstEmployee){
                    minSalary = getEmployeeByID(i).getSalary();
                    firstEmployee = false;
                    ID = i;
                }else{
                    if(minSalary > getEmployeeByID(i).getSalary()){
                        minSalary = getEmployeeByID(i).getSalary();
                        ID = i;
                    }
                }
            }
        }
        return ID;
    }

    /**
     * @param departmentID ID отдела
     * @return ID сотрудника с максимальной зарплатов в отделе
     */
    public int getIDOfEmployeeWithMaxSalaryByDepartment(int departmentID){
        double maxSalary = 0;
        int ID = -1;
        for (int i = 0;i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getDepartmentID() == departmentID){
                if(maxSalary < getEmployeeByID(i).getSalary()){
                    maxSalary = getEmployeeByID(i).getSalary();
                    ID = i;
                }
            }
        }
        return ID;
    }

    /**
     * @param departmentID ID отдела
     * @return сумму затрат на зарплату по отделу
     */
    public double getAllSalary(int departmentID){
        double salary = 0;
        for (int i = 0;i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getDepartmentID() == departmentID){
                salary += getEmployeeByID(i).getSalary();
            }
        }
        return salary;
    }
    /**
     * Список всех сотрудников со всеми данными
     */
    public void printAllData(){
        String fullName;
        System.out.println("Список всех сотрудников со всеми данными:");
        System.out.printf("%3s %-30s %-13s %-10s", "ID", "Ф.И.О.", "номер отдела", "зарплата");
        System.out.println();
        for (int i = 0;i < getNumberOfEmployees();i++){
            fullName = getEmployeeByID(i).getLastName()+" "+ getEmployeeByID(i).getFirstName()+" "+ getEmployeeByID(i).getMiddleName();
            System.out.printf("%3d %-30s %-13d %10.2f",i,fullName, getEmployeeByID(i).getDepartmentID(), getEmployeeByID(i).getSalary());
            System.out.println();
        }
    }

    /**
     * Выводит данные по сотруднику
     * @param ID идентификатор сотрудника
     */
    public void printEmployeeData(int ID){
        System.out.printf("%3s %-30s %-10s", "ID", "Ф.И.О.", "зарплата");
        System.out.println();
        String fullName = getEmployeeByID(ID).getLastName()+" "+ getEmployeeByID(ID).getFirstName()+" "+ getEmployeeByID(ID).getMiddleName();
        System.out.printf("%3d %-30s %10.2f",ID,fullName,getEmployeeByID(ID).getSalary());
        System.out.println();
    }

    /**
     * Выводит всех сотрудников отдела (все данные, кроме ID отдела).
     * @param departmentID ID отдела
     */
    public void printAllEmployeesInDepartment(int departmentID){
        System.out.printf("%3s %-30s %-10s", "ID", "Ф.И.О.", "зарплата");
        System.out.println();
        for (int i = 0;i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getDepartmentID() == departmentID){
                String fullName = getEmployeeByID(i).getLastName()+" "+ getEmployeeByID(i).getFirstName()+" "+ getEmployeeByID(i).getMiddleName();
                System.out.printf("%3d %-30s %10.2f",i,fullName,getEmployeeByID(i).getSalary());
                System.out.println();
            }
        }
    }

    /**
     * Список Ф. И. О. всех сотрудников
     */
    public void printAllNames(){
        System.out.println("Список Ф. И. О. всех сотрудников:");
        for (int i = 0;i < getNumberOfEmployees();i++){
            System.out.println(
                    getEmployeeByID(i).getLastName()+" "+
                            getEmployeeByID(i).getFirstName()+ " "+
                            getEmployeeByID(i).getMiddleName()
            );
        }
    }

    /**
     * Выводит сотрудников с зарплатой меньше передаваемого числа
     * @param salary зарплата
     */
    public void printEmployeesWithSalaryLessThen(double salary){
        System.out.printf("%3s %-30s %-10s", "ID", "Ф.И.О.", "зарплата");
        System.out.println();
        int counter = 0;
        for (int i = 0; i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getSalary() < salary){
                String fullName = getEmployeeByID(i).getLastName()+" "+ getEmployeeByID(i).getFirstName()+" "+ getEmployeeByID(i).getMiddleName();
                System.out.printf("%3d %-30s %10.2f",i,fullName, getEmployeeByID(i).getSalary());
                System.out.println();
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("Не найдено сотрудников по заданному критерию!");
        }
    }

    /**
     * Выводит сотрудников с зарплатой больше или равно передаваемого числа
     * @param salary зарплата
     */
    public void printEmployeesWithSalaryMoreThen(double salary){
        System.out.printf("%3s %-30s %-10s", "ID", "Ф.И.О.", "зарплата");
        System.out.println();
        int counter = 0;
        for (int i = 0; i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getSalary() >= salary){
                String fullName = getEmployeeByID(i).getLastName()+" "+ getEmployeeByID(i).getFirstName()+" "+ getEmployeeByID(i).getMiddleName();
                System.out.printf("%3d %-30s %10.2f",i,fullName, getEmployeeByID(i).getSalary());
                System.out.println();
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("Не найдено сотрудников по заданному критерию!");
        }
    }

    /**
     * @return начальный ID отдела
     */
    public int getStartDepartmentID(){
        try {
            return getEmployeeByID(0).getStartDepartmentID();
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @return конечный ID отдела
     */
    public int getEndDepartmentID(){
        try {
            return getEmployeeByID(0).getEndDepartmentID();
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Выводит имена сотрудников отсортированных по отделу
     */
    public void printAllNamesSortedByDepartment(){
        System.out.println("Список Ф. И. О. всех сотрудников по отдеам:");
        for(int i = getStartDepartmentID();i <= getEndDepartmentID();i++){
            System.out.println("Отдел: "+i);
            for (int j = 0; j < getNumberOfEmployees();j++){
                if(getEmployeeByID(j).getDepartmentID() == i){
                    System.out.println(
                            getEmployeeByID(j).getLastName()+" "+
                                    getEmployeeByID(j).getFirstName()+ " "+
                                    getEmployeeByID(j).getMiddleName()
                    );
                }
            }
        }
    }

    /**
     * Выводит данные сотрудника по его идентификатору
     * @param ID идентификатор сотрудника
     */
    public void printEmployeeDataByID(int ID){
        System.out.printf("%3s %-30s %-13s %-10s", "ID", "Ф.И.О.", "номер отдела", "зарплата");
        System.out.println();
        String fullName = getEmployeeByID(ID).getLastName()+" "+ getEmployeeByID(ID).getFirstName()+" "+ getEmployeeByID(ID).getMiddleName();
        System.out.printf("%3d %-30s %-13d %10.2f",ID,fullName, getEmployeeByID(ID).getDepartmentID(), getEmployeeByID(ID).getSalary());
        System.out.println();
    }

    /**
     * Вызвать изменение зарплат у всех сотрудников на величину аргумента в %
     * @param percent процент индексирования зарплаты
     */
    public void salaryIndexingForAll(double percent){
        for(int i = 0; i < getNumberOfEmployees();i++){
            double newSalary = getEmployeeByID(i).getSalary() + getEmployeeByID(i).getSalary()/100*percent;
            getEmployeeByID(i).setSalary(newSalary);
        }
    }

    /**
     * Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра
     * @param departmentID ID департамента
     * @param percent процент для индексации
     */
    public void salaryIndexingForDepartment(int departmentID, double percent){
        for(int i = 0; i < getNumberOfEmployees();i++){
            if(getEmployeeByID(i).getDepartmentID() == departmentID){
                double newSalary = getEmployeeByID(i).getSalary() + getEmployeeByID(i).getSalary()/100*percent;
                getEmployeeByID(i).setSalary(newSalary);
            }
        }
    }

    @Override
    public String toString() {
        return "EmployeeBook{" +
                "eBook=" + employeeBook +
                '}';
    }
}
