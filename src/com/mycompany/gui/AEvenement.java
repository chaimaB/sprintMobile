/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import entities.Evenement;
import java.io.IOException;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;
import services.ServiceEvent;

/**
 *
 * @author sana
 */
public class AEvenement {

    Resources res;
    private Resources theme;
    Form form;
    Container f;
    TextField tnom;
    TextField tdescription;
    TextField tlieu;
    TextField tprix;
    TextField tticket, image;
    Button btnajout, btnaff, btnedit, imgBtn;

    String path;

    private Evenement currentEvent;
    //Date tdate;

    public AEvenement() throws ParseException {
        theme = UIManager.initFirstTheme("/theme_1");

        form = new Form("Ajout d'un nouvel évènement");
        f = new Container();
        tnom = new TextField();
        tnom.setHint("Nom");
        
        Calendar c = new Calendar();
        //System.out.println( c.getDate()); c.getDate();

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        datePicker.setFormatter(dateFormatter);
        System.out.println(datePicker.getText());
        //dateFormat.format(datePicker);
            // SimpleDateFormat simple = new SimpleDateFormat(datePicker.getText());
        Date date= dateFormatter.parse(datePicker.getText());
        System.out.println(date);
        System.out.println(dateFormatter.format(date));

        tdescription = new TextField();
        tdescription.setHint("Description");

        tlieu = new TextField();
        tlieu.setHint("Lieu");

        tprix = new TextField();
        tprix.setHint("Prix");

        tticket = new TextField();
        tticket.setHint("Nombre des tickets");

        image = new TextField();
        image.setHint("Veuillez saisir l'url de votre image");

        btnajout = new Button("ajouter");
        //btnajout.getUnselectedStyle().setFgColor(5542241);
        btnaff = new Button("Affichage");
        btnedit = new Button("Modifier");
        imgBtn = new Button("parcourir");
        imgBtn.addActionListener(e -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    if (ev != null && ev.getSource() != null) {
                        path = (String) ev.getSource();
                        System.out.println(path.substring(7));
                        Image img = null;
                        image.setText(path.substring(7));//image heya just label nsob feha fel path
                        try {
                            img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
                            System.out.println(img);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }, Display.GALLERY_IMAGE);
        });

        f.add(tnom);
        f.add(datePicker);
        //f.add(c);
        //f.add(dateFormat.format(date).toString());
        f.add(tdescription);
        f.add(imgBtn);
        // f.add(image);
        f.add(tlieu);
        f.add(tprix);
        f.add(tticket);
        f.add(btnajout);
        f.add(btnaff);
        f.add(btnedit);

        form.add(f);
        //form.show();
        btnajout.addActionListener((e) -> {

            System.out.println("owel el add");
            FileUploader fc = new FileUploader("localhost/medina/web/");
            try {
                System.out.println("owel el try");
                String f = fc.upload(image.getText());
                System.out.println("ba3d el upload");
                ServiceEvent ser = new ServiceEvent();
                Evenement t = new Evenement(tnom.getText(), 
                        datePicker.getDate(),
                        tdescription.getText(),
                        f,
                        tlieu.getText(),
                        tprix.getText(),
                        Integer.parseInt(tticket.getText())
                );
                ser.ajoutEvent(t);
                System.out.println(t);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        btnaff.addActionListener((e) -> {
            Affichage a = new Affichage();
            a.getF().show();
        });

        //currentEvent = new Evenement(idEvent ,nom, description);
        btnedit.addActionListener((e) -> {
            ServiceEvent ser = new ServiceEvent();
            Evenement ev = new Evenement(tnom.getText(), tdescription.getText());
            ser.editerEvent(ev);
        });
        form.show();
    }

    public Form getF() {
        return form;
    }

    public void setF(Form form) {
        this.form = form;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
