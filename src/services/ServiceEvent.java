/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.gui.Affichage;
import entities.Evenement;
import entities.user;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author CHAIMA
 */
public class ServiceEvent {

    public static String dateString;

    public String getDateS() {
        return dateString;
    }
    ;

    private ConnectionRequest connectionRequest;
    ConnectionRequest con = new ConnectionRequest();
    String url = "http://localhost/medina/web/app_dev.php/mobile/";

    public void ajoutEvent(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/medina/web/app_dev.php/mobile/ajouterEvent?nom=" + ta.getNom()
                + "&date=" + ta.getDate()
                + "&description=" + ta.getDescription()
                + "&image=" + ta.getImage()
                + "&lieu=" + ta.getLieu()
                + "&prix=" + ta.getPrix()
                + "&ticket=" + ta.getTicket();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        Dialog.show("Succés", "Evenement ajouté", "ok", null);
    }

    public ArrayList<Evenement> getList2() {
        ArrayList<Evenement> listTasks = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/medina/web/app_dev.php/mobile/allEvent");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Evenement task = new Evenement();
                        task.setId_event((int) Float.parseFloat(obj.get("idEvent").toString()));
                        task.setNom(obj.get("nom").toString());

                        String DateS = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        System.out.println(dateString);

                        task.setDate(currentTime);
                        // task.setUser((user) obj.get("user"));
                        task.setDescription(obj.get("description").toString());
                        task.setImage(obj.get("image").toString());
                        task.setLieu(obj.get("lieu").toString());
                        listTasks.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    // @Override

    public List<Evenement> ListeEvent() {
        List<Evenement> listeChef = new ArrayList<Evenement>();
        con.setUrl(url + "ListeEvent");
        con.setHttpMethod("GET");
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("----------");
        String response = new String(con.getResponseData());
        JSONArray jSONArray = new JSONArray(response);
        for (Object object : jSONArray) {
            JSONObject json = (JSONObject) object;
            Evenement p = new Evenement();
            // p.setId(json.getInt("id"));
            p.setNom(json.getString("nom"));
            // p.setPrenom(json.getString("prenom"));
            // p.setEmail(json.getString("email"));
            listeChef.add(p);
        }
        return listeChef;
    }

    public void modifierEvent(Evenement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/medina/web/app_dev.php/mobile/modifEvent/" + ta.getId_event() + "?nom=" + ta.getNom()
                + "&description=" + ta.getDescription()
                + "&lieu=" + ta.getLieu()
                + "&prix=" + ta.getPrix()
                + "&ticket=" + ta.getTicket();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Event modifié", "ok", null);

            Affichage a = new Affichage();
            a.getF().show();

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void supprimerEvent(Evenement e) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/medina/web/app_dev.php/mobile/suppEvent/" + e.getId_event();
        con.setUrl(Url);
        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succés", "Evenement supprimée", "ok", null);

            Affichage a = new Affichage();
            a.getF().show();

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void editerEvent(Evenement ev) {
        connectionRequest = new ConnectionRequest() {

            @Override
            protected void postResponse() {
                Dialog.show("confirmation", "modifié", "ok", null);
//                Dialog d = new Dialog("Popup Title");
//                TextArea popupBody = new TextArea("Book updated");
//                popupBody.setUIID("PopupBody");
//                popupBody.setEditable(false);
//                d.setLayout(new BorderLayout());
//                d.add(BorderLayout.CENTER, popupBody);
//                d.show();
                System.out.println("bc modifié : " + ev);
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/scripts/update.php?nom=" + ev.getNom()
                + "&description=" + ev.getDescription()
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }

}
