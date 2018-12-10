package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.common.JasperReportGenerator;
import lk.ijse.pos.common.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void customerFullReportButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/pos/common/reports/Customer.jasper");
    }

    @FXML
    void dashboardLabalClicked(MouseEvent event) {
        LoadPane.loadPanel("/lk/ijse/pos/view/dashboard.fxml", event, this.getClass());
    }

    @FXML
    void itemFullReportButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/pos/common/reports/Item.jasper");
    }

    @FXML
    void orderDetailFullReportButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/pos/common/reports/Order_Detail.jasper");
    }

    @FXML
    void ordersFullReportButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(), "/lk/ijse/pos/common/reports/Order.jasper");
    }
}
