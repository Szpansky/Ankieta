package Server.Database;


import Data.Answers;
import Data.Question;
import Data.Questions;
import Data.Statistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class JDBC {
    /**
     * Metoda ładuje sterownik jdbc
     *
     * @return true/false
     */
    public static boolean checkDriver(String driver) {
        // LADOWANIE STEROWNIKA
        System.out.print("Sprawdzanie sterownika:");
        try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!");
            return false;
        }
    }

    /**
     * Metoda służy do połączenia z MySQL bez wybierania konkretnej bazy
     *
     * @return referencja do uchwytu bazy danych
     */
    public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        System.out.println("Połączenie z bazą danych: ... OK");
        return conn;
    }

    /**
     * tworzenie obiektu Statement przesyłającego zapytania do bazy connection
     *
     * @param connection - połączenie z bazą
     * @return obiekt Statement przesyłający zapytania do bazy
     */
    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }


    public static void closeConnection() {
        System.out.print("\nZamykanie polaczenia z bazą:");
        try {
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out
                    .println("Bląd przy zamykaniu polączenia z bazą! " + e.getMessage() + ": " + e.getErrorCode());
            ;
            System.exit(4);
        }
        System.out.print(" zamknięcie OK");
    }

    /**
     * Wykonanie kwerendy i przesłanie wyników do obiektu ResultSet
     *
     * @param s   - Statement
     * @param sql - zapytanie
     * @return wynik
     */
    private static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }

    private static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return -1;
    }


    private static Statement st;
    private static Connection con;

    public static void connectToDB() {
        if (checkDriver("com.mysql.jdbc.Driver"))
            System.out.println(" ... OK");
        else
            System.exit(1);

        con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
        st = createStatement(con);

        assert st != null;

        if (executeUpdate(st, "USE questionnaire;") == 0)
            System.out.println("Baza wybrana");
        else {
            System.out.println("Baza nie istnieje! Tworzymy bazę: ");
            if (executeUpdate(st, "create Database questionnaire;") == 1)
                System.out.println("Baza utworzona");
            else
                System.out.println("Baza nieutworzona!");
            if (executeUpdate(st, "USE questionnaire;") == 0)
                System.out.println("Baza wybrana");
            else
                System.out.println("Baza niewybrana!");
        }

    }


    public static Questions getQuestions() {
        System.out.println("DOWNLOADING QUESTIONS FROM DB");

        Question question = null;

        Questions questions = new Questions();

        String sql = "Select * from questions order by rand() limit 10";

        ResultSet r = executeQuery(st, sql);
        try {

            if (r != null) {
                while (r.next()) {
                    Object id = r.getObject(1);
                    Object text = r.getObject(2);
                    if (id != null && text != null) {

                        question = new Question(id.toString(), text.toString());
                        questions.add(question);

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Bląd odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return questions;
    }

    public static Statistics getStatistics(){

        return new Statistics();
    }

    public static void sendAnswers(Answers answers) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date = (dateFormat.format(cal.getTime()));

        System.out.println("SENDING ANSWER TO DB");
        String sql;
        sql = "INSERT INTO `questions_answers_count` (`id`, `question_id`, `answer`, `date`) VALUES";

        //-1 bo ostatni element odpowiada za "exit"
        for (int i = 0; i < answers.size() - 1; i++) {
            sql = sql + " (NULL,'" + answers.get(i).id + "' ,'" + answers.get(i).answer + "','" + date + "' ), ";
        }
        sql = sql.substring(0, sql.length() - 2);
        executeUpdate(st, sql);
    }

	
	/*public ArrayList<String[]> getQuestions(Path filePath) {
		
		 String [] question;
		 
		 ArrayList<String[]> questionList = new ArrayList<String[]>();
		 
		Charset charset = Charset.forName("UTF-8");
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		        question = line.split("@");
		        questionList.add(question);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return questionList;
	}
	
	public void addQuestions(ArrayList<String[]> questions, Statement st) {
		String sql;
		sql = "INSERT INTO `questions_` (`id`, `text`, `a`, `b`, `c`, `d`) VALUES";
		for (int i=0 ; i< 10000; i++) {
		sql = sql + " (NULL,"+questions.get(i)[0]+" ,"+questions.get(i)[0]+","+questions.get(i)[1]+","+questions.get(i)[2]+","+questions.get(i)[3]+","+questions.get(i)[4]+","+questions.get(i)[5]+" ), ";
		}
		sql = sql.substring(0, sql.length()-2);
		executeUpdate(st, sql);
	}
	*/


}