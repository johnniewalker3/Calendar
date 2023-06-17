import java.util.Scanner;
import java.util.HashMap;
import java.lang.Math;
public class Calendar {
    public static boolean isLeapYear(int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

    }
    public static void initializeKeyValues(HashMap<String, Integer> keyValue){
        keyValue.put("Jan", 1);
        keyValue.put("Feb", 4);
        keyValue.put("Mar", 4);
        keyValue.put("Apr", 0);
        keyValue.put("May", 2);
        keyValue.put("June", 5);
        keyValue.put("July", 0);
        keyValue.put("Aug", 3);
        keyValue.put("Sept", 6);
        keyValue.put("Oct", 1);
        keyValue.put("Nov", 4);
        keyValue.put("Dec", 6);
    }
    public static Month[] initializeMonths(){
        Month[] obMonth = new Month[12];
        obMonth[0] = Month.January;
        obMonth[1] = Month.February;
        obMonth[2] = Month.March;
        obMonth[3] = Month.April;
        obMonth[4] = Month.May;
        obMonth[5] = Month.June;
        obMonth[6] = Month.July;
        obMonth[7] = Month.August;
        obMonth[8] = Month.September;
        obMonth[9] = Month.October;
        obMonth[10] = Month.November;
        obMonth[11] = Month.December;
        return obMonth;
    }
    public static Day[] initializeDays(){
        Day[] obDays = new Day[7];
        obDays[0] = Day.Saturday;
        obDays[1] = Day.Sunday;
        obDays[2] = Day.Monday;
        obDays[3] = Day.Tuesday;
        obDays[4] = Day.Wednesday;
        obDays[5] = Day.Thursday;
        obDays[6] = Day.Friday;
        return obDays;
    }
    public static int calculateFirstDayOfMonth(int year, HashMap<String, Integer> keyValue,Month curMonth,int day){
        int[] digits = new int[2];//at pos 0 is the least significant digit,at pos 1 the next more significant digit etc...
        digits[0] = year%10;
        int newYear = year/10;
        digits[1] = newYear%10;
        int dayNum = (digits[1]*10 + digits[0])/4;
        dayNum += day;
        switch(curMonth){
            case January:
                dayNum += keyValue.get("Jan");
                if (isLeapYear(year))
                    dayNum -=1;
                break;
            case February:
                dayNum += keyValue.get("Feb");
                if (isLeapYear(year))
                    dayNum -= 1;
                break;
            case March:
                dayNum += keyValue.get("Mar");
                break;
            case April:
                dayNum += keyValue.get("Apr");
                break;
            case May:
                dayNum += keyValue.get("May");
                break;
            case June:
                dayNum += keyValue.get("June");
                break;
            case July:
                dayNum += keyValue.get("July");
                break;
            case August:
                dayNum += keyValue.get("Aug");
                break;
            case September:
                dayNum += keyValue.get("Sept");
                break;
            case October:
                dayNum += keyValue.get("Oct");
                break;
            case November:
                dayNum += keyValue.get("Nov");
                break;
            case December:
                dayNum += keyValue.get("Dec");
                break;
        }
        while (year<1700)
            year += 400;
        while (year>=2100)
            year-=400;
        if (year>=1700 && year<=1799)
            dayNum += 4;
        else if (year>=1800 && year<1900)
            dayNum += 2;
        else if (year>=1900 && year<2000)
            dayNum += 0;
        else
            dayNum += 6;
        dayNum += (digits[1]*10 + digits[0]);
        return dayNum%7;
    }
    public static void printCalendar(int year, HashMap<String, Integer> keyValue, Month[] obMonth){
        for (int i=0;i<obMonth.length;i++){
            if (obMonth[i].compareTo(Month.September) == 0)
                System.out.println(" ".repeat(15) + Month.September + " ".repeat(18));
            else
                System.out.println(" ".repeat(15) + obMonth[i] + " ".repeat(18));
            System.out.println("Mon   " + "Tue   " + "Wed   " + "Thu   " + "Fri   " + "Sat   " + "Sun");
            int daysForLeapYear = 28;
            daysForLeapYear = isLeapYear(year)?29:28;
            switch(calculateFirstDayOfMonth(year, keyValue, obMonth[i], 1)){
                case 0:
                    System.out.print(" ".repeat(30) + " 1    ");
                    break;
                case 1:
                    System.out.println(" ".repeat(36) + " 1    ");
                    break;
                case 2:
                    System.out.print(" 1    ");
                    break;
                case 3:
                    System.out.print(" ".repeat(6) + " 1    ");
                    break;
                case 4:
                    System.out.print(" ".repeat(12) + " 1    ");
                    break;
                case 5:
                    System.out.print(" ".repeat(18) + " 1    ");
                    break;
                case 6:
                    System.out.print(" ".repeat(24) + " 1    ");
                    break;
            }
            for (int day=2;day<=Math.max(obMonth[i].getDays(),daysForLeapYear);day++){
                  if (day<=9){
                      if (calculateFirstDayOfMonth(year, keyValue, obMonth[i],day) == 1)
                          System.out.println(" " + day + " ".repeat(4));
                      else
                         System.out.print(" " + day + " ".repeat(4));
                  }
                  else{
                      if (calculateFirstDayOfMonth(year, keyValue, obMonth[i],day) == 1)
                          System.out.println(day + " ".repeat(4));
                      else
                          System.out.print(day + " ".repeat(4));
                  }
            }
           System.out.println("\n");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Year:");
        int year = sc.nextInt();
        sc.nextLine();
        HashMap<String, Integer> keyValue = new HashMap<>();
        initializeKeyValues(keyValue);
        Month[] obMonth = initializeMonths();
        Day[] obDays = initializeDays();
        int dayNum = calculateFirstDayOfMonth(year, keyValue, Month.April, 5);
        //System.out.println(dayNum);
        printCalendar(year, keyValue, obMonth);

    }
}
