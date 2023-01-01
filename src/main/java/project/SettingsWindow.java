package project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsWindow extends JFrame { // ОКНО НАСТРОЕК

    private static final int WINDOW_WIDTH = 350; // ШИРИНА ОКНА
    private static final int WINDOW_HEIGHT = 300;// ВЫСОТА ОКНА
    private static final int MIN_WIN_LENGTH = 3;// ДЛИННА СИМВОЛОВ ДЛЯ ПОБЕДЫ
    private static final int MIN_FIELD_SIZE = 3;// МИНИМАЛЬНЫЙ РАЗМЕР ПОЛЯ
    private static final int MAX_FIELD_SIZE = 10;// МАКСИМАЛЬНЫЙ РАЗМЕР ПОЛЯ
    private static final String FIELD_SIZE_PREFIX = "Field size";// ТЕКСТ, КОТОРЫЙ  МЫ БУДЕМ ПИСАТЬ, ГДЕ ИГРОК ВЫБИРАЕТ РАЗМЕРЫ ПОЛЯ
    private static final String WIN_LENGTH_PREFIX = "Win length";// ТЕКСТ, КОТОРЫЙ МЫ БУДЕМ ПИСАТЬ, ГДЕ ИГРОК ВЫБИРАЕТ КОЛИЧЕСТВО СИМВОЛОВ ПОДРЯД ДЛЯ ПОБЕДЫ

    // РЕЖИМЫ ВЫБОРА ИГРЫ
    private JRadioButton humanVsAi; // РАДИО-КНОПКА ПЕРЕКЛЮЧАЕТ ЛИБО НА ОДНО ЗНАЧЕНИЕ, ЛИБО НА ДРУГОЕ
    private JRadioButton humanVsHuman;// РАДИО-КНОПКА ПЕРЕКЛЮЧАЕТ ЛИБО НА ОДНО ЗНАЧЕНИЕ, ЛИБО НА ДРУГОЕ


    private JSlider sliderWinLength;// ВЫБОР РАЗМЕРА СИМВОЛОВ ПОДРЯД ДЛЯ ВЫЙГРЫША В ВИДЕ СЛАЙДЕРА
    private JSlider sliderFieldSize;// ВЫБОР РАЗМЕРОВ ПОЛЯ В ВИДЕ СЛАЙДЕРА
    private GameWindow gameWindow;// ОКНО ИГРЫ


    public SettingsWindow(GameWindow gameWindow) { // АРГУМЕНТ В ВИДЕ НАШЕГО КЛАССА GAME_WINDOW

        this.gameWindow = gameWindow;

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);// УСТАНОВКА РАЗМЕРОВ
        setLocationRelativeTo(gameWindow);// РАСПОЛОЖЕНИЕ ОТНОСИТЕЛЬНО GAME_WINDOW
        setResizable(false);// НЕИЗМЕНЯЕМЫЙ РАМЕР
        setTitle("New game settings");// НАЗВАНИЕ ШАПКИ
        setLayout(new GridLayout(10,1 ));// В ВИДЕ ТАБЛИЧКИ 10 СТРОЧЕК И 1 СТОЛБЕЦ

        addGameMode();

        addFieldSize();

        JButton btnStart = new JButton("Start new game");// КНОПКА СТАРТА ИГРЫ
        btnStart.addActionListener(e -> submitSettings(gameWindow));// ДОБАВЛЕНИЕ СЛУШАТЕЛЯ, ПРИ НАЖАТИИ ВЫСКОЧИТ ОКНО GAME_WINDOW
        add(btnStart); // ДОБАВЛЕНИЕ КНОПКИ Start_new_game

    }

    private void addFieldSize() {
        JLabel labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);// ПАНЕЛЬ ВЫБОРА ПОЛЯ С СООТВЕТСТВУЮЩИМ ТЕКСТОМ
        JLabel labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);// ПАНЕЛЬ ВЫБОРА ДЛИННЫ СИМВОЛОВ ПОБЕДЫ С СООТВЕТСТВУЮЩИМ ТЕСТОМ

        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);// КОНСТРУКТОР СЛАЙДЕРА РАЗМЕРОВ ПОЛЯ
        sliderWinLength = new JSlider(MIN_WIN_LENGTH, MAX_FIELD_SIZE, MIN_WIN_LENGTH);// КОНСТУКТОРА СЛАЙДЕРА РАЗМЕРА ДЛИННЫ СИМВОЛОВ ДЛЯ ПОБЕДЫ

        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSize.getValue();// ЗНАЧЕНИЕ ПОЛУЧЕННОЕ ИЗ СЛАЙДЕРА
                labelFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);// УСТАНОВКА ТЕКСТА НА ЛЕЙБЛ ВЫБОРА РАЗМЕРА ПОЛЯ И СООТВЕТСТВЕННО ТЕКСТ В СКОБКАХ
                sliderWinLength.setMaximum(currentValue); // УСТАНОВКА МАКСИМАЛЬНОГО ЗНАЧЕНИЯ ПО ТЕКУЩЕМУ ЗНАЧЕНИЮ CURRENT_VALUE
            }
        });

        sliderWinLength.addChangeListener(
                e -> labelWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLength.getValue())
        );


        // ДОБАВЛЕНИЕ
        add(new JLabel("Choose field size"));
        add(labelFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Choose win length"));
        add(labelWinLength);
        add(sliderWinLength);
    }

    private void addGameMode() {
        add(new JLabel("Choose game mode:"));// ТЕКСТ ВЫБЕРИ РЕЖИМ ИГРЫ
        humanVsAi = new JRadioButton("Human versus AI", true);// РАДИО КНОПКА ВЫБОРА РЕЖИМА (TRUE ЗНАЧИТ ВЫБРАН ПО УМОЛЧАНИЮ)
        humanVsHuman = new JRadioButton("Human versus human");// ВТОРАЯ КНОПКА ВЫБОРА РЕЖИМА ИГРЫ

        // ДОБАВЛЯЕМ ДВЕ РАДИО КНОПКИ В ОДНУ ГРУППУ(BUTTONGROUP, ЧТОБЫ ИХ НЕЛЬЗЯ БЫЛО ВЫБРАТЬ ОБЕ ОДНОВРЕМЕННО
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humanVsAi);
        gameMode.add(humanVsHuman);

        // СООТВЕТСТВЕННО ВЫВЕДЕНИЕ ЭТИХ РАДИО КНОПОК
        add(humanVsAi);
        add(humanVsHuman);
    }

    private void submitSettings(GameWindow gameWindow) {
        int gameMode;

        if (humanVsAi.isSelected()) {
            gameMode = GameMap.MODE_VS_AI;
        } else {
            gameMode = GameMap.MODE_VS_Human;
        }

        int fieldSize = sliderFieldSize.getValue();
        int winLength = sliderWinLength.getValue();

        gameWindow.startGame(gameMode, fieldSize, winLength);

        setVisible(false);
    }
}
