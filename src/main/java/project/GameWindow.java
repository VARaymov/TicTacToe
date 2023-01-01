package project;

import javax.swing.*;
import java.awt.*;

// ОКНО ИГРЫ
public class GameWindow extends JFrame {
    private static final int WINDOW_WIDTH = 640;
    private static final int WINDOW_HEIGHT = 640;
    private GameMap gameMap;

    public GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);// ВЫХОД ПО КРЕСТИКУ
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);// РАЗМЕРЫ ОКНА
        setLocationRelativeTo(null);// ОКНО ПО ЦЕНТРУ ЭКРАНА
        setTitle("TicTacToe");// ШАПКА ОКНА
        setResizable(false);// НЕИЗМЕНЯЕМЫЙ РАЗМЕР ОКНА
        JButton btnStart = new JButton("<html><body><b>START</body></html>");// КНОПКА НАЧАЛА ИГРЫ С ПРИМЕНЕНИЕМ HTML РАЗМЕТКИ
        JButton btnExit = new JButton("Exit");// КНОПКА ВЫХОДА ИГРЫ

        // ПАНЕЛЬ, НА КОТОРОЙ НАШИ КНОПКИ
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));//ОДНА СТРОКА ДВА СТОЛБЦА
        buttonPanel.add(btnStart);//ДОБАВЛЕНИЕ КНОПКИ СТАРТ
        buttonPanel.add(btnExit);//ДОБАВЛЕНИЕ КНОПКИ ВЫХОД
        add(buttonPanel, BorderLayout.SOUTH);//ДОБАЛЕНИЕ ПАНЕЛИ НА ЮГ

        //ИГРОВОЕ ПОЛЕ
        gameMap = new GameMap();// ИГРОВОЕ ПОЛЕ

        SettingsWindow settings = new SettingsWindow(this);// ОКНО НАСТРОЕК
        add(gameMap, BorderLayout.CENTER);// ДОБАВЛЕНИЕ В ЦЕНТР
        setVisible(true);// ВИДИМОСТЬ ОКНА

        btnStart.addActionListener(e -> settings.setVisible(true));//ДЕЙСТВИЕ ПРИ НАЖАТИИ КНОПКИ СТАРТ. ПОКАЗЫВАЕТ ОКНО НАСТРОЕК
        btnExit.addActionListener(e -> System.exit(0));// ДЕЙСТВИЕ ПРИ НАЖАТИИ КНОПКИ ВЫХОД. ВЫКЛЮЧЕНИЕ ИГРЫ

    }

    public void startGame(int gameMode, int fieldSize, int winLength) { // МЕТОД ДЛЯ ЗАПУСКА ИГРЫ ИЗ ОКНА НАСТРОЕК
        gameMap.startNewGame(gameMode, fieldSize, winLength);
        System.out.printf("Mode: %d, Size: %d, length: %d\n", gameMode, fieldSize, winLength);

    }


}
