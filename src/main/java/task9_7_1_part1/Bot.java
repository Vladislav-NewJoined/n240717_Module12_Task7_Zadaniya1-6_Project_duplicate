package task9_7_1_part1;

//// ПРИМЕР 1 РАБОТАЕТ. Обрабатывает и сохраняет 1 изображение. Создаёт 9 кнопок (5+4), но не отвечает на них.
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import task9_7_1_part1.utils.PhotoMessageUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "lgcyrrerrr_002_bot"; // Название вашего бота
    }

    @Override
    public String getBotToken() {
        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        final String localFileName = "src/main/java/task9_7_1_part1/" + "cloned_image.jpg";
        Message message = update.getMessage();
        PhotoSize photoSize = message.getPhoto().get(0);
        final String fileId = photoSize.getFileId();
        try {
            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
            saveImage(imageUrl, localFileName);
        } catch (TelegramApiException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            PhotoMessageUtils.processingImage(localFileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
        ///
        sendPhoto.setChatId(message.getChatId().toString());
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localFileName));
        sendPhoto.setPhoto(newFile);
        sendPhoto.setCaption("Обработанное изображение");

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void saveImage(String url, String fileName) throws IOException {
        URL urlModel = new URL(url);
        InputStream inputStream = urlModel.openStream();
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        int rowCount = 3;
        int columnCount = 3;
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            KeyboardRow row = new KeyboardRow();
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                if (rowIndex == 0 && columnIndex == 0) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part1.functions.FilterOperation");
                        String methodName = "greyScale";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton1 = new KeyboardButton(methodName);
                        row.add(keyboardButton1);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 0 && columnIndex == 1) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part1.functions.FilterOperation");
                        String methodName = "onlyRed";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton2 = new KeyboardButton(methodName);
                        row.add(keyboardButton2);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 0 && columnIndex == 2) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part1.functions.FilterOperation");
                        String methodName = "onlyGreen";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton3 = new KeyboardButton(methodName);
                        row.add(keyboardButton3);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 1 && columnIndex == 0) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part1.functions.FilterOperation");
                        String methodName = "onlyBlue";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton4 = new KeyboardButton(methodName);
                        row.add(keyboardButton4);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else if (rowIndex == 1 && columnIndex == 1) {
                    try {
                        Class<?> filterOperationClass = Class.forName("task9_7_1_part1.functions.FilterOperation");
                        String methodName = "sepia";
                        Method method = filterOperationClass.getDeclaredMethod(methodName, float[].class);
                        float[] rgbArray = new float[3]; // Creating an RGB array
                        Object result = method.invoke(null, rgbArray);
                        KeyboardButton keyboardButton5 = new KeyboardButton(methodName);
                        row.add(keyboardButton5);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    KeyboardButton keyboardButton = new KeyboardButton("button" + (rowIndex*3+columnIndex+1));
                    row.add(keyboardButton);
                }
            }
            keyboardRows.add(row);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        sendPhoto.setChatId(chatId);
        sendPhoto.setReplyMarkup(replyKeyboardMarkup);
        InputFile newFile = new InputFile();
        newFile.setMedia(new File(localPath));
        sendPhoto.setPhoto(newFile);
        return sendPhoto;
    }
}
//// конец ПРИМЕРА 1




//// ПРИМЕР 2 РАБОТАЕТ. Обрабатывает и сохраняет 1 изображение. Создаёт 3 кнопки и отвечает на них. Создаёт лишнюю надпись "Команда не из кнопки".
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import task9_7_1_part1.commands.AppBotCommand;
//import task9_7_1_part1.commands.BotCommonCommands;
//import task9_7_1_part1.functions.FilterOperation;
//import task9_7_1_part1.utils.PhotoMessageUtils;
//
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static task9_7_1_part1.utils.PhotoMessageUtils.processingImage;
//
//public class Bot extends TelegramLongPollingBot {
//
//    Class[] commandClasses = new Class[] {BotCommonCommands.class};
//
//    @Override
//    public String getBotUsername() {
//        return "lgcyrrerrr_002_bo"; // Название вашего бота
//    }
//
//    @Override
//    public String getBotToken() {
//        return "7042048331:AAFXdhcICubXad5RxZGyItCIi7L6IUwvaJo"; // Токен вашего бота
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        Message message = update.getMessage();
//        String chatId = message.getChatId().toString();
//
//        String response = null;
//        response = runCommand(message.getText());
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        final String localFileName = "src/main/java/task9_7_1_part1/" + "cloned_image.jpg";
//        PhotoSize photoSize = message.getPhoto().get(0);
//        response = runCommand(message.getText());
//        final String fileId = photoSize.getFileId();
//        try {
//            final org.telegram.telegrambots.meta.api.objects.File file = execute(new GetFile(fileId));
//            final String imageUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + file.getFilePath();
//            saveImage(imageUrl, localFileName);
//        } catch (TelegramApiException | IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            processingImage(localFileName);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        SendPhoto sendPhoto = preparePhotoMessage(localFileName, message.getChatId().toString());
//        SendPhoto sendPhoto2 = preparePhotoMessage2(localFileName, message.getChatId().toString());
//        ///
//        sendPhoto.setChatId(message.getChatId().toString());
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localFileName));
//        sendPhoto.setPhoto(newFile);
//        sendPhoto.setCaption("Обработанное изображение");
//
//        try {
//            execute(sendPhoto);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//        sendMessage.setChatId(chatId);
//        sendMessage.setText(response);
//
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String runCommand(String text) {
//        BotCommonCommands commands = new BotCommonCommands();
//        Method[] classMethods = commands.getClass().getDeclaredMethods();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                if (annotation.name().equals(text)) {
//                    try {
//                        method.setAccessible(true);
//                        return (String) method.invoke(commands);
//                    } catch (IllegalAccessException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "Команда не из кнопки";
//    }
//
//    private void saveImage(String url, String fileName) throws IOException {
//        URL urlModel = new URL(url);
//        InputStream inputStream = urlModel.openStream();
//        OutputStream outputStream = new FileOutputStream(fileName);
//        byte[] b = new byte[2048];
//        int length;
//        while ((length = inputStream.read(b)) != -1) {
//            outputStream.write(b, 0, length);
//        }
//        inputStream.close();
//        outputStream.close();
//    }
//
//    private SendPhoto preparePhotoMessage(String localPath, String chatId) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setReplyMarkup(getKeyboard()); // Это две кнопки
//        sendPhoto.setChatId(chatId);
//        InputFile newFile = new InputFile();
//        newFile.setMedia(new File(localPath));
//        sendPhoto.setPhoto(newFile);
//        return sendPhoto;
//
//    }
//
//    private SendPhoto preparePhotoMessage2(String localFileName, String chatId) {
//        SendPhoto sendPhoto2 = new SendPhoto();
//        sendPhoto2.setPhoto(new InputFile(new File(localFileName)));
//        sendPhoto2.setChatId(chatId);
//        return sendPhoto2;
//    }
//
//    private ReplyKeyboardMarkup getKeyboard() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        ArrayList<KeyboardRow> allKeyboardRows = new ArrayList<>();
//        allKeyboardRows.addAll(getKeyboardRows(BotCommonCommands.class));
//        allKeyboardRows.addAll(getKeyboardRows(FilterOperation.class));
//
//        replyKeyboardMarkup.setKeyboard(allKeyboardRows);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        return replyKeyboardMarkup;
//    }
//
//    private ArrayList<KeyboardRow> getKeyboardRows(Class someClass) {
//        Method[] classMethods = someClass.getDeclaredMethods();
//        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//
//        for (Method method : classMethods) {
//            if (method.isAnnotationPresent(AppBotCommand.class)) {
//                AppBotCommand annotation = method.getAnnotation(AppBotCommand.class);
//                KeyboardButton button = new KeyboardButton();
//                button.setText(annotation.name());
//                row.add(button);
//            }
//        }
//
//        keyboardRows.add(row);
//        return keyboardRows;
//    }
//}
//// конец ПРИМЕРА 2
