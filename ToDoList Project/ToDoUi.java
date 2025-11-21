import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ToDoUI extends Application {

    private ListView<String> taskListView;
    private TextField taskField;
    private ToDoFX.TaskList taskList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("📝 To-Do List Manager (DSA)");

        taskList = new ToDoFX.TaskList();

        taskField = new TextField();
        taskField.setPromptText("Enter your task");
        taskField.setPrefWidth(250);
        taskField.setFont(Font.font(14));

        taskListView = new ListView<>();
        taskListView.setStyle("-fx-font-size: 14px;");

        Button addButton = new Button("➕ Add");
        Button doneButton = new Button("✔ Done");
        Button deleteButton = new Button("❌ Delete");
        Button clearButton = new Button("🗑 Clear All");

        String buttonStyle = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;";
        addButton.setStyle(buttonStyle);
        doneButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        clearButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");

        addButton.setPrefWidth(70);
        doneButton.setPrefWidth(80);
        deleteButton.setPrefWidth(80);
        clearButton.setPrefWidth(90);

        addButton.setOnAction(e -> addTask());
        doneButton.setOnAction(e -> markDone());
        deleteButton.setOnAction(e -> deleteTask());
        clearButton.setOnAction(e -> clearTasks());

        HBox inputBox = new HBox(10, taskField, addButton);
        inputBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(15, doneButton, deleteButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, inputBox, taskListView, buttonBox);
        layout.setPadding(new Insets(25));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#ecf0f1"), new CornerRadii(12), Insets.EMPTY)));

        Scene scene = new Scene(layout, 480, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskList.addTask(task);
            refreshList();
            taskField.clear();
        }
    }

    private void markDone() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            taskList.markDone(index);
            refreshList();
        }
    }

    private void deleteTask() {
        int index = taskListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            taskList.deleteTask(index);
            refreshList();
        }
    }

    private void clearTasks() {
        taskList.clear();
        refreshList();
    }

    private void refreshList() {
        taskListView.getItems().clear();
        taskListView.getItems().addAll(taskList.getTasks());
    }
}
