import java.net.Socket;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String myPasswod="farid";

        System.out.print("Type your password :");
        Scanner sc = new Scanner(System.in);
        String typedPassword = sc.nextLine();

       if (typedPassword.equalsIgnoreCase(myPasswod)){

           while (true) {
               System.out.println("Choose the Operation");
               System.out.println("1. List of Information");
               System.out.println("2. Add Information");
               System.out.println("3. Edit Information");
               System.out.println("4. Delete Information");
               System.out.println("5. Search");
               System.out.println("6. Exit");
               System.out.print("Your Choice :");

               Scanner choice = new Scanner(System.in);
               String myChoice = choice.nextLine();

               switch (myChoice) {
                   case "1", "1.", "1)":
                       viewInformation();
                       break;
                   case "2", "2.", "2)":
                       addInformation(choice);
                       break;
                   case "3", "3.", "3)":
                       editInformation(choice);
                       break;
               case "4","4.","4)":
                   deleteInformation(choice);
                   break;
               case "5","5.","5)":
                   searchInformation(choice);
                   break;
                   case "6", "6.", "6)":
                       System.exit(0);
               }
           }


       }
       else System.exit(0);

    }
    public static void addInformation(Scanner scanner){
        System.out.print("Name :");
        String name = scanner.nextLine();
        System.out.print("Login :");
        String login= scanner.nextLine();
        System.out.print("Password :");
        String password = scanner.nextLine();

        String sql = "INSERT INTO myinformation (name, login, pass) VALUES (?,?,?)";

        try (Connection connection = DriverManager.getConnection(DB.getURL(),DB.getUSER(),DB.getPASS() );
             PreparedStatement preparedStatement = connection.prepareStatement(sql) )
             {
                 preparedStatement.setString(1,name);
                 preparedStatement.setString(2,login);
                 preparedStatement.setString(3,password);
                 preparedStatement.executeUpdate();

                 System.out.println("Information added successfully");


        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public static void  viewInformation(){
        String sql ="SELECT * FROM myinformation";

        try (Connection connection = DriverManager.getConnection(DB.getURL(),DB.getUSER(),DB.getPASS());
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()){
                System.out.println("ID :" + resultSet.getInt("id"));
                System.out.println("Name :" +resultSet.getString("name"));
                System.out.println("Login :" +resultSet.getString("login"));
                System.out.println("Password :" +resultSet.getString("pass"));
                System.out.println("_____________________________________________________");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void editInformation(Scanner scanner){
        System.out.print("ID number of Information for edit : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New name :");
        String name = scanner.nextLine();
        System.out.print("New login:");
        String login = scanner.nextLine();
        System.out.print("New password :");
        String password = scanner.nextLine();

        String sql = "UPDATE myinformation SET login=?, name=?, pass=? WHERE id=?";

        try (Connection connection = DriverManager.getConnection(DB.getURL(),DB.getUSER(),DB.getPASS());
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

                preparedStatement.setString(1, login);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
                System.out.println("Information Updated");


        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void deleteInformation(Scanner scanner){
        System.out.print("ID of Information that you want to delete :");
        int id = scanner.nextInt();
        String sql = "DELETE FROM myinformation WHERE id=?";
        try (Connection connection= DriverManager.getConnection(DB.getURL(),DB.getUSER(),DB.getPASS());
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            System.out.println("Information Deleted");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void searchInformation(Scanner scanner){
        System.out.print("Search by name :");
        String name = scanner.nextLine();

        try {
            Connection connection = DriverManager.getConnection(DB.getURL(),DB.getUSER(),DB.getPASS());
            String sql = "SELECT * FROM myinformation WHERE name ILIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                System.out.println("__________________________________________________");
                System.out.println("ID : " + resultSet.getInt("id"));
                System.out.println("Name :"+ resultSet.getString("name") );
                System.out.println("Name :"+ resultSet.getString("login") );
                System.out.println("Name :"+ resultSet.getString("pass") );
                System.out.println("__________________________________________________");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }





    }

    }



