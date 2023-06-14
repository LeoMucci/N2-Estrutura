package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.HospitalModel;
import view.PainelAtendimentoView;
import view.TelaInicialView;

public class TelaInicialController {
    private HospitalModel model;
    private TelaInicialView view;
    private PainelAtendimentoView painelAtendimentoView;

    public TelaInicialController(HospitalModel model, TelaInicialView view) {
        this.model = model;
        this.view = view;

        view.addAtendimentoButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelAtendimentoView = new PainelAtendimentoView(); // Inicialize o objeto painelAtendimentoView
                PainelAtendimentoController painelAtendimentoController = new PainelAtendimentoController(model, painelAtendimentoView);
                painelAtendimentoView.show();
            }
        });
        
        
    }
}
