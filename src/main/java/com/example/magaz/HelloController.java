package com.example.magaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;

public class HelloController implements Initializable{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //region CreateElements

    @FXML
    private Pane product, comapny, CreateElemPane, dobavpostav, tablPostav, paneForCreateElements, paneForCreateVid,change;
    @FXML
    private Button PostavButMenu, salesButMenu, rpoductButMenu, сompanyButMenu;
    @FXML
    private ImageView imageCreatePane, imageEditPane, imgAddFromOrder;
    @FXML
    private TextField TFcreateElemName, TFcreateElemPrise, TFcreateElemKolVo, TFcreateElemKrOpisa,
            TFeditElemName, TFeditElemPrise, TFeditElemKolVo, tfSearchProd, tfKratkOpis, TFeditElemKratOpis,
            tfAddToOrderkolVo, tfIDdelite, tfEmail, tfTelepfone, tfLastName, tfName, tfSurname, tfNameCompany, tfINN, tfKPP, tfBank, tfKorrS;
    @FXML
    private ScrollPane scrolPaneMat;
    @FXML
    private Pane salePane;
    @FXML
    private Label resultSearch, nameFromOrder, priseFromOrder, kolVoFromOrder;
    @FXML
    private ComboBox CBSclad, CBPostav, ComBox, CBProduct, CBcreteElemVid, CBcreteElemPostavsch, CBcreteElemSkla, choiceWhatsCreate,
            CBVidProdFromEdit, CBsalesPaneVid, CBsalesPaneProd, VidDeleteEdit;
    @FXML
    private TableView tbViewOrderStr;
    @FXML
    private TableView tbViewFinish;
    @FXML
    private TableView tbViewPostav;
    @FXML
    private TableColumn<ObjectProd, Integer> tbCollumnKolVo, tbCollumnPrise, tbCollumnID, tbNumOrder, tbGenPrise, tbID;
    @FXML
    private TableColumn<ObjectProd, String> tbCollumnName, tbDateAndTimeOrder, tbLastName, tbName, tbSurName, tbEmail, tbTelephon, tbNameCompany, tbBank, tbINN, tbKPP, tbKorr, tbCollumnKratkoeOp;


    ArrayList<ObjectProd> list = new ArrayList<ObjectProd>();
    ArrayList<ObjectProd> listZak = new ArrayList<ObjectProd>();
    ArrayList<String> listVidNap = new ArrayList<>();
    ArrayList<String> listVidZak = new ArrayList<>();
    ObservableList<ObjectOrder> listOrderStruct = FXCollections.observableArrayList();

    public File selectedFile;
    String url = "jdbc:mysql://192.168.0.179:3306/maga";
    String user = "Moloko";
    String password = "1221";

    Connection connection;



    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLException: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к базе данных успешно установлено!");
//            uploadElements();
//            fiilGridTable();
//            fillCB();
//            fillVidForProductCreatePane();
//            fillTableOrderFinish(listOrderFinish, tbViewFinish, tbCollumnID, tbDateAndTimeOrder, tbMidPriseOrder, tbGenPrise);
            for (int i = 0; i < listVidNap.size(); i++){
                CBcreteElemVid.getItems().add(listVidNap.get(i));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            printSQLException(e);
        }
    }

    @FXML
    protected void openMenuPostavButMenu() {

        tablPostav.setVisible(true);
        product.setVisible(false);
        salePane.setVisible(false);
        comapny.setVisible(false);
        CreateElemPane.setVisible(false);
        change.setVisible(false);
        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenurpoductButMenu() {

        tablPostav.setVisible(false);
        product.setVisible(true);
        salePane.setVisible(false);
        comapny.setVisible(false);
        CreateElemPane.setVisible(false);
        change.setVisible(false);
        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenusalesButMenu() {

        tablPostav.setVisible(false);
        product.setVisible(false);
        salePane.setVisible(true);
        comapny.setVisible(false);
        CreateElemPane.setVisible(false);
        change.setVisible(false);
        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenuсompanyButMenu() {

        tablPostav.setVisible(false);
        product.setVisible(false);
        salePane.setVisible(false);
        comapny.setVisible(true);
        CreateElemPane.setVisible(false);
        change.setVisible(false);
        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenuCreatePane() {

        tablPostav.setVisible(false);
        product.setVisible(false);
        salePane.setVisible(false);
        comapny.setVisible(false);
        CreateElemPane.setVisible(true);
        change.setVisible(false);

        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenuEditPane() {

        tablPostav.setVisible(false);
        product.setVisible(false);
        salePane.setVisible(false);
        comapny.setVisible(false);
        CreateElemPane.setVisible(false);
        change.setVisible(true);
        dobavpostav.setVisible(false);
    }

    @FXML
    protected void openMenuCreatePanePostavschik() {

        tablPostav.setVisible(false);
        product.setVisible(false);
        salePane.setVisible(false);
        comapny.setVisible(false);
        CreateElemPane.setVisible(false);
        change.setVisible(false);
        dobavpostav.setVisible(true);
    }
    //region Не трогать
    public void fillTable(ObservableList obslist,TableView tableView,TableColumn tableColumnId,TableColumn tableColumnName,
                          TableColumn tableColumnKolVo,TableColumn tableColumnPrise,TableColumn tableCollumnKratkoeOp){
        try {
            tableColumnId.setCellValueFactory(new PropertyValueFactory<ObjectOrder, String>("Id"));
            tableColumnName.setCellValueFactory(new PropertyValueFactory<ObjectOrder, String>("Name"));
            tableColumnPrise.setCellValueFactory(new PropertyValueFactory<ObjectOrder, Integer>("Prise"));
            tableColumnKolVo.setCellValueFactory(new PropertyValueFactory<ObjectOrder, Integer>("KolVo"));
            tableCollumnKratkoeOp.setCellValueFactory(new PropertyValueFactory<ObjectOrder, String>("KratkoeOpisanie"));

            tableView.setItems(obslist);
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
    public void fillTableOrderFinish(ObservableList obslist,TableView tableView,TableColumn tableColumnId,TableColumn tableColumnDate,
                                     TableColumn tableColumnPriseGen) {
        try {
            tableColumnId.setCellValueFactory(new PropertyValueFactory<ObjectFinishOrder, Integer>("Id"));
            tableColumnDate.setCellValueFactory(new PropertyValueFactory<ObjectFinishOrder, Date>("Date"));
            tableColumnPriseGen.setCellValueFactory(new PropertyValueFactory<ObjectFinishOrder, Double>("PriseGen"));

            tableView.setItems(obslist);
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
    @FXML
    public void SendscrolPaneMat(){

        String prodName = null;
        int prodKolVo = 0;
        int prodPrise = 0;
        String prodKrOpisa = null;
        String prodVid = null;
        String prodSkla = null;
        String prodPostavsch = null;
        FileInputStream fis = null;

        try {
            prodName = TFcreateElemName.getText();
            prodKolVo = Integer.parseInt(TFcreateElemKolVo.getText());
            prodPrise = Integer.parseInt(TFcreateElemPrise.getText());
            prodKrOpisa = TFcreateElemKrOpisa.getText();
            fis = new FileInputStream(selectedFile);
            prodVid = CBcreteElemVid.getValue().toString();
            prodSkla = CBcreteElemSkla.getValue().toString();
            prodPostavsch = CBcreteElemPostavsch.getValue().toString();

        } catch (Exception e) {
            System.out.println("Ошибка в записи");
        }
        try {
            String quary = "insert Goods values (?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(quary);
            preparedStatement.setString(2, prodName);
            preparedStatement.setInt(3, prodKolVo);
            preparedStatement.setInt(4, prodPrise);
            preparedStatement.setBlob(5, fis);
            preparedStatement.setString(6, prodKrOpisa);
            preparedStatement.setString(7, prodVid);
            preparedStatement.setString(8, prodSkla);
            preparedStatement.setString(9, prodPostavsch);

            int rows = preparedStatement.executeUpdate();
            System.out.println("Успешно");
        } catch (SQLException e) {
            System.out.println("Неуспешно");
            printSQLException(e);
        }

    }
    public void editImagePozition(ImageView imageViewEdit){

        Image img = imageViewEdit.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageViewEdit.getFitWidth() / img.getWidth();
            double ratioY = imageViewEdit.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageViewEdit.setX((imageViewEdit.getFitWidth() - w) / 2);
            imageViewEdit.setY((imageViewEdit.getFitHeight() - h) / 2);
        }
    }

    @FXML
    protected void addImageForCreatePane() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());

                imageEditPane.setImage(image);
                editImagePozition(imageEditPane);


        }


    }
}

//    ObservableList<ObjectFinishOrder> listOrderFinish = FXCollections.observableArrayList();

//endregion

