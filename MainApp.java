/* Omada: 117
Suggrafeis:
1100130 - Theodoros Balas
3200234 - Peter Frangatzis
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Employee class
class Employee {
	private String name, surname;
    	private double maxMonthlyTotal;

    	public Employee(String n, String s, double maxMonthlyTotal) {
		this.name = n;
		this.surname = s;
		this.maxMonthlyTotal = maxMonthlyTotal;
    	}//do we need "this."?

    	public double getMaxMonthlyTotal() {
		return this.maxMonthlyTotal;
    	}

    	public String toString() {
		return name + " " + surname;
    	}
}

//ExpenseType class
abstract class ExpenseType {
	private int ID;
	private String desc;
	private double maxMonthlyExpense;

	public ExpenseType (int ID, String desc, double maxMonthlyExpense) {
		this.ID = ID;
    	this.desc = desc;
        this.maxMonthlyExpense = maxMonthlyExpense;
	}

	public String getDescription () {
		return this.desc;
	}

	public double getMaxMonthlyExpense () {
		return this.maxMonthlyExpense;
	}
}

class ExpenseType1 extends ExpenseType {
	private double price;
	private String unitOfMeasurement;
	public ExpenseType1(int ID, String desc, double maxMonthlyExpense, double p, String unit) {
		super(ID, desc, maxMonthlyExpense);
		price = p;
		unitOfMeasurement = unit;
	}

	public double getPrice () {
		return this.price;
	}

	public String getUnitMeasurement () {
		return this.unitOfMeasurement;
	}

	public double calcValue (double quantity) {
		return  quantity * this.price;
	}

	public String toString() {
		return "Price per unit: " + this.price + "/" + this.unitOfMeasurement;
	}
}

//ExpenseType2 class
class ExpenseType2 extends ExpenseType {
	private double rate;
	public ExpenseType2(int ID, String desc, double maxMonthlyExpense, double rate) {
		super(ID, desc, maxMonthlyExpense);
		this.rate = rate;
	}

	public double getRate() {
		return this.rate;
	}

	public double calcValue (double n) {
		return (n * this.rate) / 100;
	}

	public String toString() {
		return "Rate in percentage: " + this.rate + "%";
	}
}

//Expense class
class Expense {
    private Employee employee;
    private ExpenseType expenseType;
    private double amount;
	private String reason;

	public Expense (Employee emp, ExpenseType type, double amount, String reason) {
		this.employee = emp;
		this.expenseType = type;
		this.amount = amount;
		this.reason = reason;
	}

    public Employee getEmployee() {
		return employee;
    }

	public ExpenseType getExpenseType () {
		return this.expenseType; 
	}

    public String toString() {
		return employee + " spent " + amount + "$, the reason being: " + reason + " " + expenseType;
    }
}
	
//Transaction class
abstract class Transaction {
	private Employee employee;
	private double value;

	public Transaction(Employee employee, double value) {
		this.employee = employee;
		this.value = value;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public double getValue() {
		return this.value;
	}

	public String toString() {
		return "Transaction of " + this.getValue() + " made by " + this.getEmployee();
	}
}

//Downpayment class
class Downpayment extends Transaction {
	public Downpayment(Employee employee, double value) {
		super(employee, value);
	}

	@Override
	public String toString() {
		return this.getEmployee() + " made a down payment of " + this.getValue();
	}
}

//Difference class
class Difference extends Transaction {
	public Difference(Employee employee, double value) {
		super(employee, value);
	}

	@Override
	public String toString() {
		return this.getEmployee() + " spent " + this.getValue() + " over his maximum monthly expense limit";
	}
}

//Compensation class
class Compensation extends Transaction {
	private ExpenseType expenseType;
	public Compensation(Employee employee, double value, ExpenseType type) {
		super(employee, value);
		this.expenseType = type;
	}

	public ExpenseType getExpenseType() {
		return this.expenseType;
	}

	@Override
	public String toString() {
		return this.getValue() + " Compensation of " + this.getExpenseType() + " by " + this.getEmployee();
	}


}

//Finalised class
class Finalised extends Transaction {
	public Finalised(Employee employee, double value) {
		super(employee, value);
	}

	public String toString() {
		return "Final compensation for " + this.getEmployee() + " => " + this.getValue();
	}
}

//MainApp class
public class MainApp {
    Scanner input;
    ArrayList<Employee> employees;
    ArrayList<Expense> expenses;
	ArrayList<ExpenseType> expenseTypes;
    ArrayList<Transaction> transactions;

    //Load data to create database
    void loadData() {
		Employee petros = new Employee("Peter", "Frangatzis", 1000);
		employees.add(petros);
		Employee theodore = new Employee("Theodore", "Balas", 600);
		employees.add(theodore);
		Employee karl = new Employee("Karl", "Marx", 200);
		employees.add(karl);
		Employee john = new Employee("John", "Lenon", 800);
		employees.add(john);
		ExpenseType1 travel = new ExpenseType1(2368, "travel", 1000, 10, "kms");
		expenseTypes.add(travel);
		ExpenseType1 food = new ExpenseType1(1542, "food", 500, 50, "meal");
		expenseTypes.add(food);
		ExpenseType1 rec = new ExpenseType1 (6900, "recreation", 400, 100, "activity");
		expenseTypes.add(rec);
		ExpenseType2 health = new ExpenseType2 (6589, "healthcare", 700, 0.5);
		expenseTypes.add(health);
		ExpenseType2 ins = new ExpenseType2 (1543, "insurance", 350, 0.6);
		expenseTypes.add(ins);
		ExpenseType2 wfh = new ExpenseType2 (5658, "working from home", 1000, 0.8);
		expenseTypes.add(wfh);
		Downpayment d1 = new Downpayment (karl, 100);
		transactions.add(d1);
		Downpayment d2 = new Downpayment (theodore, 500);
		transactions.add(d2);
		Downpayment d3 = new Downpayment (petros, 200);
		transactions.add(d3);
		Downpayment d4 = new Downpayment (john, 800);
		transactions.add(d4);
		Expense e1 = new Expense (theodore, travel, 30, "kms");
		expenses.add(e1);
		Expense e2 = new Expense (theodore, food, 5, "meals");
		expenses.add(e2);
		Expense e3 = new Expense (theodore, ins, 500, "car");
		expenses.add(e3);
		Expense e4 = new Expense (karl, wfh, 500, "screen");
		expenses.add(e4);
		Expense e5 = new Expense (karl, health, 400, "check-up");
		expenses.add(e5);
		Expense e6 = new Expense (karl, rec, 3, "museum");
		expenses.add(e6);
		Expense e7 = new Expense (petros, health, 400, "doctor");
		expenses.add(e7);
		Expense e8 = new Expense (petros, ins, 1000, "liability");
		expenses.add(e8);
		Expense e9 = new Expense (petros, travel, 20, "kms");
		expenses.add(e9);
		Expense e10 = new Expense (john, wfh, 400, "bed");
		expenses.add(e10);
		Expense e11 = new Expense (john, wfh, 600, "guitar");
		expenses.add(e11);
		Expense e12 = new Expense (john, rec, 1, "day tripper");
		expenses.add(e12);
    }
	//MainApp Function
    public MainApp() {
        input = new Scanner(System.in);
        employees = new ArrayList<Employee>();
		expenses = new ArrayList<Expense>();
		expenseTypes = new ArrayList<ExpenseType>();
		transactions = new ArrayList<Transaction>();
		loadData();
    }
	
    void newEmployee() {
        System.out.print("Enter employee's last name: ");
        String a = input.nextLine();//skip new line
        System.out.print("Enter employee's first name: ");
        String b = input.nextLine();//skip new line
        System.out.print("Enter employee's max monthly compensation: ");
        double c = input.nextDouble();
		input.nextLine();//skip new line
        employees.add(new Employee(a, b, c));
    }

	void newExpenseType() {
		System.out.print("Enter ID: ");
		int a = input.nextInt();
		input.nextLine();//skip new line
		System.out.print("Enter description of expense type: ");
		String b = input.nextLine();
		input.nextLine();//skip new line
		System.out.print("Enter maximum monthly expense of expense type: ");
		Double c = input.nextDouble();
		input.nextLine();//skip new line
		int type = selectExpenseType();

		if (type == 1) {
			System.out.print("Enter price: ");
			double p = input.nextDouble();
			input.nextLine();//skip new line
			System.out.print("Enter unit of measurement: ");
			String u = input.nextLine();
			input.nextLine();//skip new line
			expenseTypes.add(new ExpenseType1(a, b, c, p, u));
		} else {
			System.out.print("Enter rate: ");
			double r = input.nextDouble();
			input.nextLine();
			expenseTypes.add(new ExpenseType2(a, b, c, r));
		}
	}

	void printExpenses() {
		for(Expense expense : expenses) {
			System.out.println(expense);
		}
	}
		
	void newDownpayment() {
		boolean flag;
		Employee emp = selectEmployee();
		
		System.out.print("Please enter value of downpayment (or 0 to return to main menu): ");
		do {
			double value = input.nextInt();
			input.nextLine(); // skip newline
			if (value == 0) {
				mainMenu();
			}
			else if (value < 0) {
				System.out.print("Invalid number - value must be positive!\nPlease enter value of downpayment (or 0 to return to main menu): ");
                flag = false;
			}
			else {
				flag = true;
			}
		} while (!flag);
			
		Downpayment d = new Downpayment(value, emp);
	}
		
	void newExpense() {
		boolean flag;
		int value;
		Employee emp = selectEmployee();
		
		ExpenseType et = selectExpType();
		
		System.out.print("Enter expense quantinty or value to continue (or 0 to return to main menu): ");
		do {
			int quant = input.nextInt();
			input.nextInt(); // skip newline
			if (value == 0){
				mainMenu();
			}
			else if (value < 0) {
				System.out.print("Invalid number - value/quantinty must be positive!\nPlease enter a positive number (or 0 to return to main menu): ");
                flag = false;
			} else {
				flag = true;
			}
		} while (!flag);
		
		System.out.print("Enter expense description: ");
		int reason = input.nextInt();
		input.nextLine(); // skip newline
		
		Expense e = new Expense(emp, et, quant, reason);  
	}

    public void mainMenu() {
        int menu;
	
		do {
			System.out.println("Main Menu"
			       +"\n1: Add new expense type"
			       +"\n2: Add new employee"
			       +"\n3: Add new employee expense"
			       +"\n4: Add new downpayment transaction"
			       +"\n5: Print employees' expenses"
			       +"\n6: Clear employee's expenses"
			       +"\n7: Print employees' transactions"
			       +"\n8: Clear all company's employees' expenses"
			       +"\n9: Print final monthly sum of all employees"
			       +"\n0: exit");
			System.out.print("Enter selection to continue [0-9]: ");
			menu = input.nextInt();
			input.nextLine(); //skip new line
			switch (menu) {
				case 1:
					newExpenseType();
					break;
				case 2: 
					newEmployee();
					break;
				case 3:
					newExpense();
					break;
				case 4:
					newDownpayment();
					break;
				case 5: 
					printExpenses();
					break;
				/*case 6: 
					clearExpenses();
					break;
				case 7: 
					printTransactions();
					break;
				case 8:
					clearAll();
					break;
				case 9:
					printAll();
					break;*/
				case 0:
					break;
				default:
					System.out.println("Invalid number!");
			}
		} while (menu != 0);
	}

	List<Expense> getExpensesForEmployee(Employee employee) {
		List<Expense> employeeExpenses = new ArrayList<Expense>();
		for (Expense expense: expenses) {
			if (expense.getEmployee() == employee) {
				employeeExpenses.add(expense);
			}
		}
        return employeeExpenses;
    }

	Employee selectEmployee() {
        int i = 1;
        boolean flag;

        for (Employee employee : employees) {
            System.out.println(i+". "+employee.toString());
            //changed employee to employee.toString()
            i++;
        }
		
		Employee employee;
      
        do {
            System.out.print("Enter number to select employee: ");
            int index = input.nextInt();
            input.nextLine(); // skip newline
            if (index-1 <= employees.size() && index >= 1)  {
                employee = employees.get(index-1);
                flag = true;
            }
            /* elseif (index=0) {
                    mainMenu();
            }
            can that work since function returns nothing? */
            else {
                System.out.print("Invalid number!");
                flag = false;
            }
        } while (!flag);
           
        return employee;
    }

	int selectExpenseType() {
        boolean flag;
		int menu;
        System.out.println("Expense Type"
                            +"\n1: expense defined by quantinty"
                            +"\n2: expense defined by value"
                            +"\n0: return to main menu");
        do {
            System.out.print("Enter selection to continue [0-2]: ");
            menu = input.nextInt();
            input.nextLine(); // skip newline
            if (menu == 0){
                mainMenu();
            }
            else if (menu == 1 || menu == 2) {
                flag = true;
            }
            else {
                flag = false;
            }
        } while (!flag);

		return menu;
    }

	ExpenseType selectExpType () {
		int i = 1;
        boolean flag;
       
        for (ExpenseType expenseType : expenseTypes) {
            System.out.println(i+". "+expenseType.toString());
            i++;
        }
		
		do {
			System.out.print("Enter number to select expense type: ");
			int index = input.nextInt();
			input.nextLine(); // skip newline
			if (index-1 <= expenseTypes.size() && index >= 1)  {
				expenseType = expenseTypes.get(index-1);
				flag = true;
			}
			/* elseif (index==0) {
					mainMenu();
			}
			can that work since function returns nothing? */
			else {
				System.out.print("Invalid number!");
				flag = false;
			}
        } while (!flag);
			
        	return expenseType;
		}
	}


	public static void main(String[] args) {
        System.out.println("Welcome to MainApp!");
		MainApp myapp = new MainApp();
        myapp.mainMenu();
		myapp.loadData();
    }
}
