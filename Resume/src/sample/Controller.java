package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.FileChooser;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller extends SqlCon {



    @FXML
    public TextField nameEntry;

    @FXML
    private TextField surnameEntry;

    @FXML
    private TextField emailEntry;

    @FXML
    private TextField company1;

    @FXML
    private TextField workDone1;

    @FXML
    private TextField company2;

    @FXML
    private TextField workDone2;

    @FXML
    private TextField company3;

    @FXML
    private TextField workDone3;

    @FXML
    private TextField skill2;

    @FXML
    private TextField skill4;

    @FXML
    private TextField skill1;

    @FXML
    private TextField skill3;

    @FXML
    private TextField imagePath;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField searhField;


    @FXML
    public void save(ActionEvent event) {
            Alert a = new Alert(Alert.AlertType.NONE);
            Alert b = new Alert(Alert.AlertType.NONE);
            SqlCon mySql = new SqlCon();
            CreateDOCX docx = new CreateDOCX();

                String name = nameEntry.getText();
                String surname =  surnameEntry.getText();
                String email = emailEntry.getText();

                String companyName1 = company1.getText();
                String work1 = workDone1.getText();
                String companyName2 = company2.getText();
                String work2 = workDone2.getText();
                String skill11 = skill1.getText();
                String skill22 = skill2.getText();
                String skill33  = skill3.getText();
                String skill44 = skill4.getText();
                String imageP = imagePath.getText();
                String companyName3 = company3.getText();
                String work3 = workDone3.getText();
         if (validateString(name) && validateString(surname) && !name.equals("") && !surname.equals("")) {
                if (!noRepeat(name, surname)) {
                    makeJDBCConnection();
                    addDataToDB(name, surname, email, companyName1, work1, companyName2, work2, skill11, skill22, skill33, skill44, imageP, companyName3, work3);
                 }
                docx.creatDOCX(name, surname, email, companyName1, work1, companyName2, work2, skill11, skill22, skill33, skill44, imageP, companyName3, work3);
                b.setAlertType(Alert.AlertType.INFORMATION);
                b.setHeaderText("Please check your desktop. Resume for " + name + " " + surname + " was successfully written. ");
                b.show();
         }

         else{
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setTitle("ERROR");
            // set content text
            a.setHeaderText("Invalid input");
            // show the dialog
            a.show();
            nameEntry.setOnMouseClicked(e -> nameEntry.selectAll());
            surnameEntry.setOnMouseClicked(e -> surnameEntry.selectAll());
    }
}



    private boolean validateString (String str) {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();


        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png" ,"*jpeg"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String imageUrl = selectedFile.toURI().toURL().toExternalForm();
                Image image = new Image(imageUrl);
                imageView.setVisible(true);
                imageView.setImage(image);
            } catch (MalformedURLException ex) {
                throw new IllegalStateException(ex);
            }
        }

        if (selectedFile != null) {
            imagePath.setText(String.valueOf(selectedFile.getAbsoluteFile()));
        } else {
            System.out.println("Image file  is valid!");
            //???
            Alert imAlert = new Alert(Alert.AlertType.NONE);
            imAlert.setTitle("warning");
            imAlert.setHeaderText("invalid input");
        }
    }


    @FXML
    public void search(ActionEvent event)  {
        try {
            superClean();
            searhField.setOnMouseClicked(e->searhField.selectAll());
            //separate the input into name and surname
            String input = searhField.getText();

            String name1 = input.substring(0, input.indexOf(" "));
            String surname1 = input.replaceAll(name1 + " ", "");

            name1 = name1.toLowerCase();
            surname1 = surname1.toLowerCase();
            System.out.println(name1 + " " + surname1);// check whether above code works or not



            //connect db
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/resume";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "test", "test");

            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "" +
                    "SELECT * FROM person where name ='" + name1 + "'"+" and surname ='"+ surname1+"'";
            System.out.println(query);



            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            //String name= rs.getString("name");
            while (rs.next()) {
                String name= rs.getString("name");
                name = name.toLowerCase();
                System.out.println("db found  "+ name);
                String surName= rs.getString("surname").toLowerCase();
                System.out.println("db found  "+ surName);
                if (name.equals(name1) && surName.equals(surname1)) {
                    System.out.println("they are equal,trueee");
                    nameEntry.setText(name.substring(0,1).toUpperCase()+name.substring(1));


                    surnameEntry.setText(surName.substring(0,1).toUpperCase()+surName.substring(1));
                    String email = rs.getString("email");
                    emailEntry.setText(email);

                    String companY1 =rs.getString("companyName1");
                    company1.setText(companY1);
                    String  w1 = rs.getString("workDone1");
                    workDone1.setText(w1);

                    String companY2 =rs.getString("companyName2");
                    company2.setText(companY2);
                    String  w2 = rs.getString("workDone2");
                    workDone2.setText(w2);

                    String companY3 =rs.getString("companyName3");
                    company3.setText(companY3);
                    String  w3 = rs.getString("workDone3");
                    workDone3.setText(w3);

                    String perSkills = rs.getString("skill1");
                    skill1.setText(perSkills);

                    String phSkill = rs.getString("skill2");
                    skill2.setText(phSkill);

                    String lan = rs.getString("skill3");
                    skill3.setText(lan);

                    String addSkill = rs.getString("skill4");
                    skill4.setText(addSkill);

                    String pic = rs.getString("image");
                    imagePath.setText(pic);

                    File im = new File(pic);
                    String imageUrl = im.toURI().toURL().toExternalForm();
                    Image image = new Image(imageUrl);
                    imageView.setVisible(true);
                    imageView.setImage(image);
                }

            }
            st.close();
        } catch (Exception e) {

        }


    }

    @FXML
    void clear(ActionEvent event) {
        searhField.clear();
        superClean();

    }

    public void superClean(){
        nameEntry.clear();
        surnameEntry.clear();
        emailEntry.clear();
        company1.clear();
        company2.clear();
        workDone1.clear();
        workDone2.clear();
        skill1.clear();
        skill2.clear();
        skill3.clear();
        skill4.clear();
        imagePath.clear();
        imageView.setVisible(false);
        company3.clear();
        workDone3.clear();
    }
    
    public boolean noRepeat(String name, String surName){

            try {
                name = name.toLowerCase();
                surName = surName.toLowerCase();
                //connect db
                String myDriver = "com.mysql.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost:3306/resume";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "test", "test");
                String query = "" +
                        "SELECT * FROM person where name ='" + name + "'" + " and surname ='" + surName + "'";
                System.out.println(query);
                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String nameDB = rs.getString("name");
                    nameDB = nameDB.toLowerCase();
                    System.out.println("db found  " + name);
                    String surNameDB = rs.getString("surname").toLowerCase();
                    System.out.println("db found  " + surName);
                    if (name.equals(nameDB) && surName.equals(surNameDB)) {
                        System.out.println("same, will be not added");
                       return true;
                    }

                }

                st.close();
            } catch (Exception e) {
                System.out.println("wrong");
            }
            return false;





            }




}
