/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import entities.user;
//import com.PiMasters.linkpro.IServices.IAuthentificationService;
//import com.PiMasters.linkpro.Services.AuthentificationService;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import entities.user;

/**
 *
 * @author Radhouen
 */
public class LoginForm extends Form {

    final Resources res;
    private Form c;
    private Resources theme;
    TextField emailTxt;

    public LoginForm(Resources res) {
        //super("Login1");
        this.res = res;
       // theme = UIManager.initFirstTheme("/themeFB");
        setLayout(new BorderLayout());
        Container north = new Container(new FlowLayout(Component.CENTER));
        north.setUIID("header");
        ImageViewer logo = new ImageViewer(res.getImage("logo.png"));

        north.add(logo);
        this.add(BorderLayout.NORTH, north);
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("loginbackground");

        emailTxt = new TextField();
        emailTxt.setHint("Email");
        emailTxt.setUIID("loginTextFieald");
        TextField pwdTxt = new TextField();
        pwdTxt.setHint("Mot de Passe");
        pwdTxt.setUIID("loginTextFieald");
        pwdTxt.setConstraint(TextField.PASSWORD);
        Button loginBtn = new Button();
        loginBtn.setUIID("LoginBtn");
        loginBtn.setText("Se Connecter");

        center.add(emailTxt);
        center.add(pwdTxt);
        center.add(loginBtn);
        center.setScrollableY(false);
        center.setScrollableX(false);

        this.add(BorderLayout.CENTER, center);

        Container south = new Container(new FlowLayout(Component.CENTER));
        south.setUIID("footer");

        Button Exit = new Button("Sortir", res.getImage("power-signal.png"));
        Exit.setGap(Exit.getStyle().getFont().getHeight());
        Exit.setUIID("LoginBtn");
//        Exit.addActionListener(e
//                -> {
//            RegistrationForm RegistrationForm = new RegistrationForm(theme);
//            RegistrationForm.show();
//        }
//        );
//
        south.add(Exit);
        this.add(BorderLayout.SOUTH, south);
        System.out.println("aazddza");
        loginBtn.addActionListener(e -> {
          //  AEvenement l = new AEvenement();
//            l.show();
c = new HomeEvent(res);
                    c.show();
//            IAuthentificationService authentificationService = new AuthentificationService();
//            Personne personne = authentificationService.Auth(emailTxt.getText(), pwdTxt.getText());
//            Personne.connectedUser = personne;
//            System.out.println("Personne = " + personne);
//            System.out.println("Personne = " + Personne.connectedUser);
//            System.out.println(Personne.connectedUser.getDepartement());
//            theme = UIManager.initFirstTheme("/theme");
//            if (Personne.connectedUser.getId() > 0) {
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_RE\"]")) {
//                    EntrepriseAjoutForm EntrepriseAjoutForm = new EntrepriseAjoutForm(theme);
//                    EntrepriseAjoutForm.show();
//                }
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_CHEFDEPT\"]")) {
//                    c = new HomeChefDept(res, personne.toString());
//                    c.show();
//                }
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_CEO\"]")) {
//                    c = new HomeCeo(res, personne.toString());
//                    c.show();
//                }
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_STAGIAIRE\"]")) {
//                    c = new HomeStagiaire(res, personne.toString());
//                    c.show();
//                }
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_EMPLOYEE\"]") && Personne.connectedUser.getChefProjet() == true) {
//                    c = new HomeChefProjet(res, personne.toString());
//                    c.show();
//                }
//                if (Personne.connectedUser.getRoles().equals("[\"ROLE_EMPLOYEE\"]") && Personne.connectedUser.getChefProjet() == false) {
//                    c = new HomeEmployee(res, personne.toString());
//                    c.show();
//                }
//            }
         });
    }
}
