import java.io.*;
import java.util.Scanner;

public class EmployeeBookMain {
    public static void main(String[] args) {
        EmployeeBookMain employeeMain = new EmployeeBookMain();
        EmployeeBook employeeBook1 = null;
        String fileName = "employees.db";
        int choice;
        Scanner scan = new Scanner(System.in);
        while (true){
            if(employeeBook1 == null){
                System.out.println("\033[4mСписок сотрудников не создан.\033[0m");
                System.out.println("[1] Вы можете загрузить его из файла");
                System.out.println("[2] Создать список вручную");
                System.out.println("[3] Или выйти из программы");
                choice = employeeMain.readChoice(scan,1,3);
                if(choice == 1){
                    System.out.println("[1] Использовать текущее имя файла: \""+fileName+"\"");
                    System.out.println("[2] Задать новое имя");
                    choice = employeeMain.readChoice(scan,1,2);
                    if(choice==2){
                        System.out.println("Введите имя файла: ");
                        fileName = employeeMain.readString();
                    }
                    employeeBook1 = employeeMain.readEmployeeBookFromFile(fileName);
                } else if (choice == 2) {
                    employeeBook1 = new EmployeeBook();
                    System.out.println("Создан \033[4mпустой\033[0m список.");

                }
                else {
                    break;
                }
            }
            else {
                System.out.println("[1] Добавление сотрудников.");
                System.out.println("[2] Удаление сотрудников.");
                System.out.println("[3] Изменение сотрудников.");
                System.out.println("[4] Задачи со списком сотрудников.");
                System.out.println("[5] Задачи с конкретными отделами.");
                System.out.println("[6] Сохранение списка сотрудников в файл.");
                System.out.println("[7] Выход.");
                choice = employeeMain.readChoice(scan,1,7);
                if(choice == 1){
                    while(true){
                        System.out.println("[1] Добавить нового сотрудника.");
                        System.out.println("[2] Возврат.");
                        choice = employeeMain.readChoice(scan,1,2);
                        if (choice == 1){
                            System.out.println("Введите фамилию.");
                            String lastName = employeeMain.readString();
                            System.out.println("Введите имя.");
                            String firstName = employeeMain.readString();
                            System.out.println("Введите отчество.");
                            String middleName = employeeMain.readString();
                            System.out.println("Введите номер отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            System.out.println("Введите зарплату.");
                            double salary = employeeMain.readPositiveDouble(scan);
                            employeeBook1.addEmployee(new Employee(lastName,firstName,middleName,departmentID,salary));
                        }else{
                            break;
                        }
                    }
                } else if (choice == 2) {
                    while(true){
                        System.out.println("[1] Удаляем сотрудника по его ID.");
                        System.out.println("[2] Удаляем сотрудника по его ФИО.");
                        System.out.println("[3] Возврат.");
                        choice = employeeMain.readChoice(scan,1,3);
                        if(choice == 1){
                            System.out.println("Введите ID сотрудника");
                            int ID = employeeMain.readChoice(scan,0,employeeBook1.getNumberOfEmployees()-1);
                            employeeBook1.deleteEmployeeByID(ID);
                        } else if (choice == 2) {
                            System.out.println("Введите фамилию.");
                            String lastName = employeeMain.readString();
                            System.out.println("Введите имя.");
                            String firstName = employeeMain.readString();
                            System.out.println("Введите отчество.");
                            String middleName = employeeMain.readString();
                            employeeBook1.deleteEmployeeByName(lastName,firstName,middleName);
                        }else {
                            break;
                        }
                    }
                } else if (choice == 3) {
                    while(true){
                        System.out.println("[1] Изметь отдел сотрудника по его ID.");
                        System.out.println("[2] Изметь отдел сотрудника по его ФИО.");
                        System.out.println("[3] Изменить зарплату сотруднику по его ID.");
                        System.out.println("[4] Изменить зарплату сотруднику по его ФИО.");
                        System.out.println("[5] Возврат.");
                        choice = employeeMain.readChoice(scan,1,5);
                        if (choice == 1){
                            System.out.println("Введите ID сотрудника");
                            int ID = employeeMain.readChoice(scan,0,employeeBook1.getNumberOfEmployees()-1);
                            System.out.println("Введите новый номер отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            employeeBook1.getEmployeeByID(ID).setDepartmentID(departmentID);
                        } else if (choice == 2) {
                            System.out.println("Введите фамилию. ");
                            String lastName = employeeMain.readString();
                            System.out.println("Введите имя. ");
                            String firstName = employeeMain.readString();
                            System.out.println("Введите отчество. ");
                            String middleName = employeeMain.readString();
                            int ID = employeeBook1.findEmployeeByName(lastName,firstName,middleName);
                            if(ID != -1){
                                System.out.println("Введите новый номер отдела.");
                                int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                                employeeBook1.getEmployeeByID(ID).setDepartmentID(departmentID);
                            }
                        } else if (choice == 3) {
                            System.out.println("Введите ID сотрудника.");
                            int ID = employeeMain.readChoice(scan,0,employeeBook1.getNumberOfEmployees()-1);
                            System.out.println("Введите новую зарплату.");
                            double salary = employeeMain.readPositiveDouble(scan);
                            employeeBook1.getEmployeeByID(ID).setSalary(salary);
                        } else if (choice == 4) {
                            System.out.println("Введите фамилию.");
                            String lastName = employeeMain.readString();
                            System.out.println("Введите имя.");
                            String firstName = employeeMain.readString();
                            System.out.println("Введите отчество.");
                            String middleName = employeeMain.readString();
                            int ID = employeeBook1.findEmployeeByName(lastName,firstName,middleName);
                            if(ID != -1){
                                System.out.println("Введите новую зарплату.");
                                double salary = employeeMain.readPositiveDouble(scan);
                                employeeBook1.getEmployeeByID(ID).setSalary(salary);
                            }
                        }else {
                            break;
                        }
                    }
                } else if (choice == 4) {
                    while (true){
                        System.out.println("\n[1] Список всех сотрудников со всеми данными.");
                        System.out.println("[2] Поиск сотрудника с минимальной зарплатой.");
                        System.out.println("[3] Поиск сотрудника с максимальной зарплатой.");
                        System.out.println("[4] Подсчет среднего значения зарплат.");
                        System.out.println("[5] Список Ф. И. О. всех сотрудников.");
                        System.out.println("[6] Проиндексировать зарплату.");
                        System.out.println("[7] Сотрудники с зарплатой меньше числа.");
                        System.out.println("[8] Сотрудники с зарплатой больше (или равно) числа.");
                        System.out.println("[9] Получить Ф. И. О. всех сотрудников по отделам.");
                        System.out.println("[10] Возврат.");
                        choice = employeeMain.readChoice(scan,1,10);
                        if(choice == 1){
                            employeeBook1.printAllData();
                        } else if (choice == 2) {
                            System.out.println("Сотрудник с минимальной зарплатой:");
                            employeeBook1.printEmployeeDataByID(employeeBook1.getIDOFEmployeeWithMinSalary());
                        } else if (choice == 3) {
                            System.out.println("Сотрудник с максимальной зарплатой:");
                            employeeBook1.printEmployeeDataByID(employeeBook1.getIDOFEmployeeWithMaxSalary());
                        } else if (choice == 4) {
                            System.out.printf("%s %-15.2f","Среднее значение зарплат: ",employeeBook1.getAverageSalary());
                            System.out.println();
                        } else if (choice == 5) {
                            employeeBook1.printAllNames();
                        } else if (choice == 6) {
                            System.out.println("Введите % для индексирования зарплаты");
                            double percent = employeeMain.readPositiveDouble(scan);
                            employeeBook1.salaryIndexingForAll(percent);
                        } else if (choice == 7) {
                            System.out.println("Введите зарплату для сравнения");
                            double salary = employeeMain.readPositiveDouble(scan);
                            employeeBook1.printEmployeesWithSalaryLessThen(salary);
                        } else if (choice == 8) {
                            System.out.println("Введите зарплату для сравнения ");
                            double salary = employeeMain.readPositiveDouble(scan);
                            employeeBook1.printEmployeesWithSalaryMoreThen(salary);
                        } else if (choice == 9) {
                            employeeBook1.printAllNamesSortedByDepartment();
                        } else {
                            break;
                        }
                    }
                } else if (choice == 5) {
                    while (true){
                        System.out.println("[1] Сотрудник с минимальной зарплатой в отделе.");
                        System.out.println("[2] Сотрудник с максимальной зарплатой в отделе.");
                        System.out.println("[3] Сумма затрат на зарплату по отделу.");
                        System.out.println("[4] Средняя зарплату по отделу.");
                        System.out.println("[5] Проиндексировать зарплату всех сотрудников отдела на процент.");
                        System.out.println("[6] Напечатать всех сотрудников отдела.");
                        System.out.println("[7] Возврат.");
                        choice = employeeMain.readChoice(scan,1,7);
                        if(choice == 1){
                            System.out.println("Введите ID отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            employeeBook1.printEmployeeData(employeeBook1.getIDOfEmployeeWithMinSalaryByDepartment(departmentID));
                        } else if (choice == 2) {
                            System.out.println("Введите ID отдела. ");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            employeeBook1.printEmployeeData(employeeBook1.getIDOfEmployeeWithMaxSalaryByDepartment(departmentID));
                        } else if (choice == 3) {
                            System.out.println("Введите ID отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            System.out.println("Сумма затрат на зарплату по отделу ("+departmentID+"): "+employeeBook1.getAllSalary(departmentID));
                        } else if (choice == 4) {
                            System.out.println("Введите ID отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            System.out.println("Средняя зарплату по отделу ("+departmentID+"): "+(employeeBook1.getAllSalary(departmentID)/employeeBook1.getNumberOfEmployeesInDepartment(departmentID)));
                        } else if (choice == 5) {
                            System.out.println("Введите ID отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            System.out.println("Введите % для индексирования зарплаты для выбранного отдела.");
                            double percent = employeeMain.readPositiveDouble(scan);
                            employeeBook1.salaryIndexingForDepartment(departmentID,percent);
                        } else if (choice == 6) {
                            System.out.println("Введите ID отдела.");
                            int departmentID = employeeMain.readChoice(scan,employeeBook1.getStartDepartmentID(),employeeBook1.getEndDepartmentID());
                            employeeBook1.printAllEmployeesInDepartment(departmentID);
                        } else {
                            break;
                        }
                    }
                } else if (choice == 6) {
                    System.out.println("[1] Использовать текущее имя файла: \""+fileName+"\"");
                    System.out.println("[2] Задать новое имя ");
                    choice = employeeMain.readChoice(scan,1,2);
                    if(choice==2){
                        System.out.println("Введите имя файла: ");
                        fileName = employeeMain.readString();
                    }
                    employeeMain.writeEmployeeBookToFile(employeeBook1,fileName);
                } else if (choice == 7){
                    break;
                }
            }

        }
        scan.close();
    }
    public String readString(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            while(true){
                str = reader.readLine();
                if(!str.contains("?") && !str.contains(":") && !str.contains("*")){
                    break;
                }else{
                    System.out.println("Нельзя использовать символы: \"?\", \":\", \"*\"");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
    public int readChoice(Scanner scan, int minValue, int maxValue){
        int choice;
        System.out.println("Введите значение от "+minValue+" до "+maxValue+" включительно.");
        while(true){
            while(!scan.hasNextInt()){
                System.out.println("Введено недопустимое значение, попробуйте снова!");
                scan.nextLine();
            }
            choice=scan.nextInt();
            if(choice >= minValue && choice <= maxValue){
                break;
            }
            else {
                System.out.println("Значение должно быть от "+minValue+" до "+maxValue+" включительно.");
                scan.nextLine();
            }
        }
        return choice;
    }
    public double readPositiveDouble(Scanner scan){
        double positiveDouble; //зарплата с копейками - нецелое число
        while(true){
            while(!scan.hasNextDouble()){
                System.out.println("Введено недопустимое значение, попробуйте снова!");
                scan.nextLine();
            }
            positiveDouble=scan.nextDouble();
            if(positiveDouble > 0){
                break;
            }
            else {
                System.out.println("Значение зарплаты должно быть больше 0!");
                scan.nextLine();
            }
        }
        return positiveDouble;
    }
    public void writeEmployeeBookToFile(EmployeeBook employeeBook, String fileName){
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(employeeBook);
            objectOut.close();
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public EmployeeBook readEmployeeBookFromFile(String fileName){
        EmployeeBook readBook = null;
        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            readBook = (EmployeeBook) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch (FileNotFoundException e){
            System.out.println("Файл "+fileName+" не существует!\n");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return readBook;
    }
}