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
		
		Employee emp = selectEmployee();
		
		ExpenseType et = selectExpType();
		
		System.out.print("Enter expense quantinty or value to continue (or 0 to return to main menu): ");
		do {
			int quant = input.nextInt();
			input.nextInt(); // skip newline
			if (value = 0){
				mainMenu();
			}
			else if (value < 0) {
				System.out.print("Invalid number - value/quantinty must be positive!\nPlease enter a positive number (or 0 to return to main menu): ");
                flag = false;
			else {
				flag = true;
			}
		} while (!flag);
		
		System.out.print("Enter expense description: ");
		int reason = input.nextLine();
		input.nextLine(); // skip newline
		
		Expense e = new Expense(emp, et, quant, reason);   
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    Employee selectEmployee() {
        int i = 1;
        boolean flag;
       
        for (Employee employee : employees) {
            System.out.println(i+". "+employee.toString());
            //changed employee to employee.tostring
            i++;
        }
       
        do {
            System.out.print("Enter number to select employee: ");
            int index = input.nextInt();
            input.nextLine(); // skip newline
            if (index-1 <= employees.size() && index >= 1)  {
                employee = employees.get(index-1);
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
			
        return employee;
    }
	
	ExpenseType selectExpType ()
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
	
	int selectExpenseType() {
		boolean flag;
	
		System.out.println("Expense Type"
							+"\n1: expense defined by quantinty"
							+"\n2: expense defined by value"
							+"\n0: return to main menu");
		do {
			System.out.print("Enter selection to continue [0-2]: ");
			int menu = input.nextInt();
			input.nextLine(); // skip newline
			if (menu == 0) {
				mainMenu();
			}
			else if (menu == 1 || menu == 2) {
				flag = true;
			}
			else {
				System.out.print("Invalid number!");
				flag = false;
			}
		} while (!flag);
		return menu;
    }
