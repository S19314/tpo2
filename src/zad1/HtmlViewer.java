/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zad1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class HtmlViewer extends Application {
    private static String city;
    public static void main(String[] args) {
        city = args[0];
        args[0] = "";
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        JFrame jFrame = new JFrame("FX");
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JFXPanel fXPanel = new JFXPanel();
        WebEngine engine; 
        WebView webView = new WebView();
        engine = webView.getEngine();
        engine.load("https://pl.wikipedia.org/wiki/" + city);
//       fXPanel.add(webView);
        VBox root = new VBox();
        root.getChildren().add(webView);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
//        jFrame.add(fXPanel);
//       jFrame.setVisible(true);
        primaryStage.show();
    }
 
}
