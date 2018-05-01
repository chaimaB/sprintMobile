/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import entities.Evenement;
import services.ServiceEvent;

/**
 *
 * @author CHAIMA
 */
public class ModifEvent {
    Form f;
    TextField ttitre;
    TextField tdescription;
    TextField tlieu;
    TextField tprix;
    TextField tnbreticket;
    Button btnMod,btnAnnuler;

    public ModifEvent( Evenement ta) {
        f = new Form("Modification");
        ttitre = new TextField(ta.getNom());
        tdescription = new TextField(ta.getDescription());
        tprix = new TextField(ta.getPrix());

        tlieu = new TextField(ta.getLieu());
        tnbreticket = new TextField(ta.getTicket());
        
        btnMod = new Button("Modifier");
        btnAnnuler=new Button("Annuler");
        f.add(ttitre);
        f.add(tdescription);
        f.add(tprix);

        f.add(tlieu);
        f.add(tnbreticket);
        
        f.add(btnMod);
        f.add(btnAnnuler);
        btnMod.addActionListener((e) -> {
            ServiceEvent ser = new ServiceEvent();
            ta.setNom(ttitre.getText());
           
            ta.setDescription(tdescription.getText());
            ta.setPrix(tprix.getText());
             ta.setLieu(tlieu.getText());
            ta.setTicket(Integer.parseInt(tnbreticket.getText()));
//            Event t = new Event( ttitre.getText(),
//                    tlieu.getText() ,
//                    Double.valueOf(tprix.getText()), 
//                   // Double.parseDouble(tprix.getText()),
//                    tdescription.getText(),     
//                    Integer.parseInt(tnbreticket.getText()));
//            //Event t = new Event( ttitre.getText(), tdescription.getText(), Double.parseDouble(tprix.getText()), tlieu.getText() ,Integer.parseInt(tnbreticket.getText()));
//
            ser.modifierEvent(ta);
//                        Dialog.show("Succés", "Evenement modifié", "ok", null);

            

        }); 
        
        btnAnnuler.addActionListener((e)->{
        Affichage a=new Affichage();
        a.getF().show();
        });
    }

    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return ttitre;
    }

    public void setTnom(TextField ttitre) {
        this.ttitre = ttitre;
    }
    
}
