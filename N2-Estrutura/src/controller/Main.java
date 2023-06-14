package controller;

import model.HospitalModel;
import view.TelaInicialView;

public class Main {
    public static void main(String[] args) {
        HospitalModel model = new HospitalModel();
        TelaInicialView view = new TelaInicialView();
        TelaInicialController controller = new TelaInicialController(model, view);
    }
}
