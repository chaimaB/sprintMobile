/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.share.FacebookShare;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import services.ServiceEvent;
import entities.Evenement;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class Affichage {

    Resources res;
    private Resources theme;
    Form f, form;
    SpanLabel lb;
    String url = "http://localhost/medina/web/EventsImages";
    EncodedImage enc;
    Label nomD, descD, prixD, dateD, lieuD;
    Button particip, modif, supp;

    public Affichage() {
        theme = UIManager.initFirstTheme("/theme_1");
        f = new Form(BoxLayout.y());

        Style s = UIManager.getInstance().getComponentStyle("Title");
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        f.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();
            if (t.length() < 1) {
                for (Component cmp : f.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for (Component cmp : f.getContentPane()) {
                    String val = null;
                    if (cmp instanceof Label) {
                        val = ((Label) cmp).getText();
                    } else {
                        if (cmp instanceof TextArea) {
                            val = ((TextArea) cmp).getText();
                        } else {
                            val = (String) cmp.getPropertyValue("text");
                        }
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show); // <3>
                    cmp.setVisible(show);
                }
            }
            f.getContentPane().animateLayout(250);
        });
        f.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync(); // <4>
        });

        lb = new SpanLabel("");
        f.add(lb);
        ServiceEvent SP = new ServiceEvent();
        ArrayList<Evenement> lis = SP.getList2();
        for (Evenement li : lis) {
            Container cc = new Container(BoxLayout.x());
            Container c = new Container(BoxLayout.y());

            Image placeholder = Image.createImage(380, 300);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, li.getImage(), url + "/" + li.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

            TextField a = new TextField(li.getId_event());

            Label aa = new Label("Nom  : " + li.getNom().toString());
            Label desc = new Label("Description : " + li.getDescription().toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(li.getDate());
            Label d = new Label("Date :" + dateString);
            //c.add(a);
            c.add(aa);
            c.add(desc);
            c.add(d);
            c.add(imgV);
            f.add(c);

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            aa.addPointerPressedListener((l) -> {

                form = new Form(BoxLayout.x());
                Label lbser = new Label();
                Container F3 = new Container(BoxLayout.y());
                //Container x=new Container(BoxLayout.x());

                F3.add(lbser);
                // form.add(share);

                System.out.println("imaage");
                Image placeholder1 = Image.createImage(55, 50);
                EncodedImage en = EncodedImage.createFromImage(placeholder1, false);
                URLImage urli = URLImage.createToStorage(en, li.getImage(), url + "/" + li.getImage());
                ImageViewer img = new ImageViewer();
                img.setImage(urli);

                ConnectionRequest con = new ConnectionRequest();

                String url = "http://localhost/medina/web/app_dev.php/mobile/findEvent/" + li.getId_event();
                con.setUrl(url);

                con.addResponseListener((le) -> {
                    String reponse = new String(con.getResponseData());
                    System.out.println(reponse);
                    //lbser.setText(reponse);
                    nomD = new Label("Nom :" + li.getNom());
                    descD = new Label("Description :" + li.getDescription());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String dateS = format.format(li.getDate());
                    dateD = new Label("Date :" + dateS);

                });
                NetworkManager.getInstance().addToQueueAndWait(con);
                System.out.println("test");
                particip = new Button("Participer");
                particip.addActionListener((evt) -> {
                    // FacebookShare fc = new FacebookShare();
                    // fc.share(url);
                   String u="http://localhost/medina/web/app_dev.php/mobile/particip?id="+5+"&event="+li.getId_event();
                   con.setUrl(u);
                   System.out.println(u);

                }
                );
                modif = new Button("Modifier");
                modif.addActionListener((evt) -> {
                    ModifEvent m = new ModifEvent(li);
                    m.getF().show();
                });
                supp = new Button("Supprimer");
                supp.addActionListener((evt) -> {
                   ServiceEvent service = new ServiceEvent();
                   service.supprimerEvent(li);
                });
                F3.add(particip);
                F3.add(modif);
                F3.add(supp);
                F3.add(nomD);
                F3.add(descD);
                F3.add(dateD);
                form.add(img);
                form.add(F3);
                form.show();
            });
            c.setLeadComponent(aa);

            System.out.println("test ba3d a");

        }
        // lb.setText(lis.toString());
        f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
            AEvenement h;
            try {
                h = new AEvenement();
                h.getF().show();
            } catch (ParseException ex) {
            }

        });
    }
//        f = new Form();
//        lb = new SpanLabel("");
//        f.add(lb);
//        ServiceEvent serviceTask=new ServiceEvent();
//        ArrayList<Evenement> lis=serviceTask.getList2();
//        lb.setText(lis.toString());
////////          f.getToolbar().addCommandToRightBar("back", null, (ev)->{AEvenement h=new AEvenement(int idEvent ,
////////                  string nom ,
////////                  string description);
////////          h.getF().show();
////////          });
//    }
//

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
