package org.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public List<String> game(){
        String vocab = "'USA ', 'China ', 'Japan ', 'India ', 'Australia ', 'UK ', 'Germany ', 'France ', 'Canada ', 'Korea ', 'Rusia ', 'Teacher ','Student ', 'Doctor ', 'Chef ', 'Bank Clerk ', 'Reporter ','Researcher ', 'Company ', 'Employee ', 'Here ', 'Mr/MRs ','I (yo) ', 'This person ', 'Person ', 'Name ', 'Nationality ','Job ', 'Address ', 'Telephone ', 'Wall ', 'Kitchen ', 'Outside ','Washing Machine ', 'Porridge ', 'Watermelon ', 'Post Office ','Chicken ', 'Soil ', 'Eye ', 'Mountain ', 'Money ', 'Photography ','Newspaper ', 'Library ', 'Real ', 'Busan ', 'Orange ', 'Ramen ','To walk ', 'Clothes ', 'Flower ', 'Under ', 'Pot ', 'Comb ','Daytime ', 'Rice ', 'Foot ', 'Arm ', 'Seoul ', 'Classroom ','Fruit ', 'Bulgogi ', 'Grandmother ', 'Strawberry ', 'Spring ','Mom ', 'Kimchi ', 'Ginseng ', 'Cold ', 'Lunch ', 'Computer ','House ', 'Mouth ', 'Knee ', 'Seven ', 'Front ', 'Side ','Wallet ', 'Forest ', 'Hospital ', 'Window ', 'Bag ', 'River ','Room ', 'Market ', 'Love ', 'Cleaning ', 'Movie ', 'Restroom ','Younger sibling ', 'Summer '";
        String[] vocabArray = vocab.split(",");

        List<String> vocabList = List.of(vocabArray);

        return vocabList;


    }

    public MainView() {

        AtomicReference<List<String>> vocabList = new AtomicReference<>(game());
        Label label = new Label("");

        AtomicInteger aciertos = new AtomicInteger();
        AtomicInteger fallos = new AtomicInteger();
        Button button = new Button("Nueva palabra", event -> {
            int index = (int) (Math.random() * vocabList.get().size());
            label.setText(vocabList.get().get(index));
            vocabList.get().remove(index);
            if(vocabList.get().size() == 0){
                Notification.show("Game Over");
                aciertos.set(0);
                fallos.set(0);
            }
        });

        Label aciertos_fallos = new Label("Aciertos: " + aciertos + " Fallos: " + fallos);

        Button acierto = new Button("Acierto", event-> {
            aciertos.getAndIncrement();
            aciertos_fallos.setText("Aciertos: " + aciertos + " Fallos: " + fallos);
        });

        Button fallo = new Button("Fallo", event-> {
            fallos.getAndIncrement();
            aciertos_fallos.setText("Aciertos: " + aciertos + " Fallos: " + fallos);
        });


        Button reset = new Button("Reset", event -> {
            vocabList.set(game());
            label.setText("");
        });
        add(button, label, reset, acierto, fallo, aciertos_fallos);
    }
}
