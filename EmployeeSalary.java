public class EmployeeSalary {

//    double hoursPerWeek = 80;
//    double HourlyPay = 10;
//    double yearlySalary = hoursPerWeek * HourlyPay * 52;

    //whats a better way to implement code from top

    public static double salaryCalculator(double hoursPerWeek,
                                          double amountPerHour,
                                          int vacationDays){

        // add an if incase a negative gets added
        if (hoursPerWeek < 0 )
           return -1;
        if (amountPerHour <0 )
        return -1;

        double weeklyPayCheck = hoursPerWeek * amountPerHour;
        double unpaidTime = vacationDays *amountPerHour * 8;
        return (weeklyPayCheck *52) - unpaidTime;

    }

    public static void main(String[] args) {
        double salary = salaryCalculator(-1,25, 8);
            System.out.println(salary);

    }
}
