/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import entities.Evenement;
import services.ServiceEvent;

/**
 *
 * @author Radhouen
 */
public class HomeEvent extends Form {

    Resources res;
    private Resources theme;

    public HomeEvent(Resources res) {
        this.res = res;
        setLayout(new BorderLayout());
        //loadProfile();
        //loadEvent();
        theme = UIManager.initFirstTheme("/theme_1");
        Container south = new Container(new FlowLayout(Component.CENTER));
        south.setUIID("footer");

        Button Exit = new Button("Sortir", res.getImage("power-signal.png"));
        Exit.setGap(Exit.getStyle().getFont().getHeight());
        Exit.setUIID("LoginBtn");
        Exit.addActionListener(e
                -> System.exit(0)
        );

        south.add(Exit);
        this.add(BorderLayout.SOUTH, south);
        this.getToolbar().addCommandToSideMenu("Ajouter un event", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //FillActualite f = new FillActualite(theme);
                    //f.show();
                    AEvenement a = new AEvenement();
                } catch (ParseException ex) {
                }
            }
        });
        this.getToolbar().addCommandToSideMenu(" Mes Taches", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // MyTachesForm m =new MyTachesForm(theme, Personne.connectedUser.toString());
                //m.show();
            }
        });
       
        this.getToolbar().addCommandToSideMenu(" Gestion Bugs", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //ConsulterBugs ListeChefProjet = new ConsulterBugs(res);
                //ListeChefProjet.show();
            }
        });
        this.getToolbar().addCommandToOverflowMenu(" Deconnexion", res.getImage("logout.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //Personne.connectedUser = null;
                LoginForm login = new LoginForm(theme);
                login.show();
            }
        });
        
    }

////    public void loadProfile() {
////        Container center = new Container(BoxLayout.y());
////        center.setScrollableY(true);
////        center.setUIID("loginbackground");
////        IProfileService pr = new ProfileService();
////        Personne ps = new Personne();
////        ps = pr.showProfile(Personne.connectedUser.getId());
////        System.out.println(ps);
////        Label nom = new Label("Nom : " + ps.getNom());
////        Label prenom = new Label("Prenom : " + ps.getPrenom());
////        Label emaillbl = new Label("Email : " + ps.getEmail());
////        Label username = new Label("Username : " + ps.getUsername());
////        Label cin = new Label("Cin : " + ps.getCin());
////        Label adresse = new Label("Adresse : " + ps.getAdresse());
////        Label ville = new Label("Ville : " + ps.getVille());
////        Container info = new Container(BoxLayout.y());
////        Button loadUpdate = new Button("Modifier");
////        loadUpdate.setUIID("UpdateBtn");
////        loadUpdate.addActionListener(e -> {
////            this.removeAll();
////            this.refreshTheme();
////            loadupdate(pr.showProfile(Personne.connectedUser.getId()));
////        });
////        info.add(nom);
////        info.add(prenom);
////        info.add(emaillbl);
////        info.add(username);
////        info.add(cin);
////        info.add(adresse);
////        info.add(ville);
////        info.add(loadUpdate);
////        center.add(info);
////
////        //Label blbl = new Label("Bienvenue Sur LinkPro\n\n chefdept");
////        //Label emaillbl = new Label("\n" + email);
////        //center.add(blbl);
////        //center.add(emaillbl);
////        this.add(BorderLayout.CENTER, center);
////    }
////
////    public void loadupdate(Personne p) {
////        this.refreshTheme();
////        Container center = new Container(BoxLayout.y());
////        center.setScrollableY(true);
////        center.setUIID("loginbackground");
////        Container ctn = new Container(BoxLayout.y());
////        TextField userName = new TextField(p.getUsername());
////        userName.setUIID("loginTextFieald");
////        TextField Cin = new TextField(p.getCin());
////        Cin.setUIID("loginTextFieald");
////        TextField Adresse = new TextField(p.getAdresse());
////        Adresse.setUIID("loginTextFieald");
////        TextField Ville = new TextField(p.getVille());
////        Ville.setUIID("loginTextFieald");
////        Button modif = new Button("Modifier");
////        modif.setUIID("UpdateBtn");
////        ctn.add(userName);
////        ctn.add(Cin);
////        ctn.add(Adresse);
////        ctn.add(Ville);
////        ctn.add(modif);
////
////        System.out.println(p);
////        modif.addActionListener(e -> {
////            IProfileService sp = new ProfileService();
////            p.setUsername(userName.getText());
////            p.setCin(Cin.getText());
////            p.setAdresse(Adresse.getText());
////            p.setVille(Ville.getText());
////            sp.updateProfile(p);
////            this.removeAll();
////            Dialog.show("Succes", "Modification avec succes", "OK", "Cancel");
////            this.refreshTheme();
////            loadProfile();
////        });
////        center.add(ctn);
////        this.add(CENTER, center);
////    }

    private void loadEvent() {
        
        //Container center = new Container(new FlowLayout());
        Container center = new Container(BoxLayout.y());
        center.setScrollableY(true);
        center.setUIID("loginbackground");
        System.out.println("test");
        ServiceEvent Sp = new ServiceEvent();
        System.out.println("test");

        System.out.println("test");
        //System.out.println(Sp.getProjets(Personne.connectedUser.getDepartement().getId()));
        for (Evenement personne : Sp.getList2()){
            Container list = new Container(BoxLayout.y());
            Label  nomP = new Label("Nom :" + personne.getNom());
            Label PrenomP = new Label("Prenom :" /*+ personne.getPrenom()*/);
            PrenomP.setUIID("ListLabel");
            Label EmailP = new Label("Email :"/* + personne.getEmail()*/);
            EmailP.setUIID("ListLabel");
//            Button DeleteChef= new  Button("Supprimer Chef");
//            DeleteChef.setUIID("LoginBtn");
//            DeleteChef.addActionListener(e->{
//               //String resp=Sp.deleteChef(personne.getId());
//               
//            //        ListeEmpDispo Liste =new ListeEmpDispo(res);
//             //       Liste.show();
//
//            });
            //int h= nomP.getHeight()+2*DateDebP.getHeight()+DescP.getHeight()+DescP.getHeight()+EquipeP.getHeight();
            //list.setHeight(h);
            list.add(nomP);
            list.add(PrenomP);
            list.add(EmailP);
            //list.add(DeleteChef);
            //list.setUIID("listContaner");
            center.add(list);
        //    this.add(BorderLayout.CENTER, center);
            //this.add(list);
        }
    }

}
