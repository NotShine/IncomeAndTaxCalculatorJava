import java.util.Scanner;

public class TaxCalc {

    // function to validate gender input.
    public static boolean isValidgender(char gender) {
        return gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int NORMALHOURS = 40;
        final double OVERTIMEFACTORONE = 1.5;
        final double OVERTIMEFACTORTWO = 2.0;
        final double HOURLYRATE = 20;
        final int TOTAL_WEEKS = 52;
        final int OVERTIMELIMITONEHOURS = 5;

        double GrossIncome = 0;
        int TOTAL_OVERTIME_LIMIT_ONE_HOURS = 45;

        // income tier
        final double INCOME_TIER1 = 10000;
        final double INCOME_TIER2 = 12000;
        final double INCOME_TIER3 = 30000;
        final double INCOME_TIER4 = 35000;
        final double INCOME_TIER5 = 70000;
        final double INCOME_TIER6 = 100000;
        final double INCOME_TIER7 = 150000;

        // tax rate constant declarations
        final double TAXRATE0 = 0;
        final double TAXRATE1 = 0.1;
        final double TAXRATE2 = 0.2;
        final double TAXRATE3 = 0.3;

        // age constant declarations

        final int AGE_TIER_1 = 55;
        final int AGE_TIER_2 = 80;

        // tax amount
        double taxAmount = 0;

        // employee input
        int YearBirth = 0;
        double Hours = 0;
        String FirstName = "";
        String LastName = " ";
        String EmployeeID = " ";
        char Gender = '-'; // initialise

        // firstname
        while (true) {
            System.out.println("Please enter your first name: ");
            FirstName = scanner.next();
            if (FirstName.matches("[a-zA-z]{3,}")) {
                break;
            } else {
                System.out.println("Invalid input.");

            }
        }

        // lastName
        while (true) {
            System.out.println("Please enter your last name: ");
            LastName = scanner.next();
            if (LastName.matches("[a-zA-Z]{3,}")) {
                break;
            } else {
                System.out.println("Invalid input.");

            }
        }

        while (true) {
            System.out.println("Please enter your employee ID: ");
            EmployeeID = scanner.next();
            if (EmployeeID.matches("[sS]\\d[0-9]{7}")) {
                break;
            } else {
                System.out.println("Invalid input.");

            }
        }

        // gender

        while (true) {
            System.out.println("Please enter your gender: ");
            Gender = scanner.next().charAt(0);
            if (isValidgender(Gender)) {
                break;
            } else {
                System.out.println("Invalid input.");
                scanner.next().charAt(0);
            }
        }

        // YOB input
        while (true) {
            System.out.println("Enter your year of birth: ");
            if (scanner.hasNextInt() || YearBirth >= 2006) {
                YearBirth = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. ");

            }
        }

        // hours worked
        while (true) {
            System.out.println("Please input the hours you worked: ");
            if (scanner.hasNextDouble() || Hours < 0 || Hours > 168) {
                Hours = scanner.nextDouble();
                break;
            } else {
                System.out.println("Invalid input. ");

            }
        }

        // calculate age
        int Age = 2023 - YearBirth;

        // calculate gross pay
        if (Hours == 40) {
            GrossIncome = Hours * HOURLYRATE;
        }

        else if (Hours > 40 && Hours <= 45) {
            // 40 * 20 + ((45 - 40) * 1.5 * 20)
            GrossIncome = (NORMALHOURS * HOURLYRATE) + ((Hours - NORMALHOURS) * (OVERTIMEFACTORONE * HOURLYRATE));
        } else if (Hours > 45) {
            GrossIncome = (NORMALHOURS * HOURLYRATE) + (OVERTIMELIMITONEHOURS * OVERTIMEFACTORONE * HOURLYRATE)
                    + ((Hours - TOTAL_OVERTIME_LIMIT_ONE_HOURS) * OVERTIMEFACTORTWO * HOURLYRATE);
        }

        // annual income calculation
        double AnnualIncome = GrossIncome * TOTAL_WEEKS;

        // tax calculation
        if (Age < AGE_TIER_1) {
            if (Gender == 'M' || Gender == 'm') {
                if (AnnualIncome < INCOME_TIER1) { // > 10,000
                    taxAmount = AnnualIncome * TAXRATE0;
                } else if (AnnualIncome >= INCOME_TIER1 && AnnualIncome <= INCOME_TIER3) { // > 10,000 & <= 30,000
                    taxAmount = AnnualIncome * TAXRATE1;
                } else if (AnnualIncome > INCOME_TIER3 && AnnualIncome <= INCOME_TIER6) { // < 30,000 & <= 100,000
                    taxAmount = AnnualIncome * TAXRATE2;
                }

                else { // income > 100,000
                    taxAmount = AnnualIncome * TAXRATE3;
                }

            }

            else if (Gender == 'F' || Gender == 'f') {
                if (AnnualIncome < INCOME_TIER2) {
                    taxAmount = AnnualIncome * TAXRATE0;
                } else if (AnnualIncome >= INCOME_TIER2 && AnnualIncome <= INCOME_TIER4) {
                    taxAmount = AnnualIncome * TAXRATE1;
                } else if (AnnualIncome > INCOME_TIER4 && AnnualIncome <= INCOME_TIER6) {
                    taxAmount = AnnualIncome * TAXRATE2;
                } else {
                    taxAmount = AnnualIncome * TAXRATE3;
                }

            }
        }

        System.out.println("***********************************************************");
        System.out.println("Income and Tax Calculator");
        System.out.println("*************************");
        System.out.println("Your name: " + FirstName + " " + LastName);
        System.out.println("Your Employee ID: " + EmployeeID);
        System.out.println("Your Age: " + Age);
        System.out.println("Your Gross Pay WeeK: " + GrossIncome);
        System.out.println("Your Annual Salary: " + AnnualIncome);
        System.out.println("Your Tax Amount: " + taxAmount);

        scanner.close();
    }

}
