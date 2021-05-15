package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            int count = 0;
            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == ':') count++;
            }
            if (count == 1) {
                String[] mesArray = message.split(": ");
                Calendar calendar = new GregorianCalendar();
                switch (mesArray[1]) {
                    case "дата": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "день": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "месяц": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }

                    case "год": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "время": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "час": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "минуты": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("m");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                    case "секунды": {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("s");
                        sendTextMessage("Информация для " + mesArray[0] + ": " + simpleDateFormat.format(calendar.getTime()));
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int a = (int) (Math.random() * 100);
        return "date_bot_" + a;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
