import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
class WrongStudentName extends Exception {
}

class WrongAge extends Exception {
}

class WrongDateOfBirth extends Exception {
}

class WrongMenu extends Exception {
}

class WrongParse extends Exception{
    public WrongParse(String errorMessage) {
        super(errorMessage);
    }
}
//Napisz metodę, która zwraca listę studentów posortowaną według nazwiska. Możesz wykorzystać wbudowane mechanizmy sortowania lub napisać własny komparator.

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            try {
                int ex = menu();
                switch (ex) {
                    case 1:
                        exercise1();
                        break;
                    case 2:
                        exercise2();
                        break;
                    case 3:
                        exercise3();
                        break;
                    case 4:
                        exercise4();
                        break;
                    default:
                        return;
                }
            } catch (IOException e) {
            } catch (WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            } catch (WrongAge e) {
                System.out.println("Błędny wiek studenta!");
            } catch (WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia studenta!");
            } catch (WrongMenu e) {
                System.out.println("Błędny wybór!");
            } catch(WrongParse e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static int menu() throws WrongMenu {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("4 - aby wypisać wszystkich studentów posortowanych po imieniu");
        System.out.println("0 - aby wyjść z programu");
        String input = scan.next();
        if(input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")){
            return Integer.parseInt(input);
        }else throw new WrongMenu();
        
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if (name.contains(" - _"))
            throw new WrongStudentName();
        return name;
    }

    public static int ReadAge() throws WrongAge {
        System.out.println("Podaj wiek: ");
        var age = scan.nextInt();
        scan.nextLine();
        if (age < 0 || age > 100)
            throw new WrongAge();
        return age;
    }

    public static String ReadDate() throws WrongDateOfBirth {
        System.out.println("Podaj datę urodzenia DD-MM-YYY");
        var date = scan.nextLine();
        String[] parts = date.split("-");
        if (parts.length != 3) {
            throw new WrongDateOfBirth();
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > 2024) {
            throw new WrongDateOfBirth();
        }
        return date;
    }

    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDate();
        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException, WrongParse{
        var students = (new Service()).getStudents();
        for (Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException, WrongParse{
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if (wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
    public static void exercise4() throws IOException, WrongParse{
        var students = (new Service()).getStudents();
        Collections.sort(students, );
        for (Student current : students) {
            System.out.println(current.ToString());
            //również można spróbować Arrays.sort()
        }
    }
      class Sortbyname() implements Comparator<Student> {

          public int compare(Student a, Student b)
          {

              return a.GetName().compareTo(b.GetName());
          }
      }
}