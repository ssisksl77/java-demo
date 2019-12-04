package java8_impatiant.concurrent.sec08;
/*
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;*/

import java.net.URL;
import java.util.Scanner;

/**
 * 비동기 계산
 * 이 절에는 대기 없는 계산, 즉 비동기 계산을 구현하는 방법을 알아보자.
 * 1. 사용자 인터페이스 콜백에서 오랜 시간 실행하는 태스크
 * 예를들어 사용자가 버튼을 클릭하는 순간 웹 페이지를 읽으려면 다음과 같이 하면 안된다.
 *
 * Button read = new Button("Read");
 * read.setOnAction(event -> {  // 안 좋은 방법 - UI 스레드에서 액션을 수행한다.
 *     Scanner in = new Scanner(url.openStream());
 *     while (in.hasNextLine()) {
 *         String line = in.nextLine();
 *     }
 * });
 *
 * ------- 대신 별도의 스레드에서 작업을 수행해야 한다.
 * read.setOnAction(event -> {  // 좋은 방법 - 장시간 실행하는 액션을 별도의 스레드에서 수행한다.
 *      Runnable task = () -> {
 *          ...
 *      }
 *      new Thread(task).start();
 * });
 *
 * 하지만 이런 일에는 조심해야 한다. JavaFX, 스윙, 안드로이드 와 같은 사용자 인터페이스는 스레드에 안전하지 않다.
 * 여러 스레드에서 사용자 인터페이스 요소를 조작하면 해당 요소가 손상될 수 있다.
 *
 * 실제로 JavaFX, 안드로이드에서는 이 부분을 검사해서 UI스레드가 아니면 사용자 인터페이스에 접근할 때 예외가 던져진다.
 *
 * 따라서 UI 업데이트가 UI스레드에서 일어나도록 스케줄링해야 한다. 각 사용자 인터페이스 라이브러리에는 UI 스레드에서 실행되는
 * Runnable을 스케줄링 하는 메커니즘이 있다. 예를 들어 JavaFX에서는 다음과 같이 사용할 수 있다.
 *
 * Platform.runLater(() ->  message.appendText(line + "\n"));
 *
 */
public class WebReader /*extends Application*/ {
/*    TextArea message = new TextArea("");

    public void start(Stage stage) {
        VBox pane = new VBox(10);
        HBox box = new HBox(10);
        Button read = new Button("Read");
        String url = "http://horstmann.com";
        read.setOnAction(event -> read(url));
        Button quit = new Button("Quit");
        quit.setOnAction(event -> System.exit(0));
        box.getChildren().addAll(read, quit);
        pane.getChildren().addAll(message, box);
        pane.setPadding(new Insets(10));
        stage.setScene(new Scene(pane));
        stage.setTitle("Hello");
        stage.show();
    }

    public void read(String url) {
        Runnable task = () -> {
            try {
                try (Scanner in = new Scanner(new URL(url).openStream())) {
                    while (in.hasNextLine()) {
                        Platform.runLater(() ->
                                message.appendText(in.nextLine() + "\n"));
                        Thread.sleep(100);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        new Thread(task).start();
    }*/
}