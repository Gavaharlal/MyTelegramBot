
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Date;
import java.util.HashMap;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update
                .getMessage()
                .hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String response;

            switch (message_text) {
                case "/start":
                    response = "Hi there! I'm Alex's bot.\nI can show timetable!\n" +
                            "Type \"/help\" for more information";
                    break;
                case "/help":
                    response = "Available commands: \n" +
                            "   /help\n" +
                            "   /getToday\n" +
                            "   /getTomorrow\n" +
                            "   /showAll\n" +
                            "   /showDate";
                    break;

                case "/getToday":
                    response = "Today is: " + getToday(update) + "\n" + SCHEDULE.get(getToday(update));
                    break;

                case "/getTomorrow":
                    response = "Tomorrow wil be: " + getTomorrow(update) + "\n" + SCHEDULE.get(getTomorrow(update));
                    break;

                case "/showAll":
                    response = "Понедельник: \n" + SCHEDULE.get("Mon") + "\n\n" +
                            "Вторник:     \n" + SCHEDULE.get("Tue") + "\n\n" +
                            "Среда:       \n" + SCHEDULE.get("Wed") + "\n\n" +
                            "Четверг:     \n" + SCHEDULE.get("Thu") + "\n\n" +
                            "Пятница:     \n" + SCHEDULE.get("Fri") + "\n\n";
                    break;
                case "/showDate":
                    response = new Date((long) update
                            .getMessage()
                            .getDate() * 1000)
                            .toString();
                    break;

                case "/Mina":
                    response = "Мина - это зайка Александра, вообще-то!";
                    break;


                default:
                    response = "A can't understand this";

            }

            SendMessage message = new SendMessage()
                    .setChatId(chat_id)
                    .setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Gavik's bot";
    }

    @Override
    public String getBotToken() {
        return "650261192:AAGUIa4yuBH8rlpfQ_RAXGlC1e0V1Fnzta4";
    }

    private String getToday(Update update) {
        return new Date((long) update
                .getMessage()
                .getDate() * 1000)
                .toString()
                .split(" ")[0];
    }

    private String getTomorrow(Update update) {
        String ans = "hmmm";
        switch (getToday(update)) {
            case "Mon":
                ans = "Tue";
                break;

            case "Tue":
                ans = "Wed";
                break;

            case "Wed":
                ans = "Thu";
                break;

            case "Thu":
                ans = "Fri";
                break;

            case "Fri":
                ans = "Mon";
                break;

        }
        return ans;
    }

    private static final HashMap<String, String> SCHEDULE;

    static {
        SCHEDULE = new HashMap<>();

        SCHEDULE.put("Mon", "8-20(н):\n" +
                "8-20(ч):\n" +
                "10-00(н): МатАн (лаб)\n" +
                "10-00(ч): МатАн (лаб)\n" +
                "11-40(н): C++ (лек)\n" +
                "11-40(ч): C++ (лек)\n");

        SCHEDULE.put("Tue", "8-20(н):  ИнЯз (пр)\n" +
                "8-20(ч):  ИнЯз (пр)\n" +
                "10-00(н): ДиффУры (лек)\n" +
                "10-00(ч): ДиффУры (лек)\n" +
                "11-40(н): Дискретка (лек)\n" +
                "11-40(ч): Дискретка (лек)\n" +
                "13-30(н):\n" +
                "13-30(ч): БЖД (лек)\n");

        SCHEDULE.put("Wed",
                "8-20(н):\n" +
                        "8-20(ч):\n" +
                        "10-00(н): Web (лек)\n" +
                        "10-00(ч): Web (лек)\n" +
                        "11-40(н): Web-4 (лаб)\t\n" +
                        "11-40(ч): Web-4 (лаб)\t\n" +
                        "13-30(н): Дискретка (лаб)\n" +
                        "13-30(ч): Дискретка (лаб)");

        SCHEDULE.put("Thu", "8-20(н):\n" +
                "8-20(ч):\n" +
                "10-00(н): АиСД (лаб)\n" +
                "10-00(ч): АиСД (лаб)\n" +
                "11-40(н): ДиффУры (лаб)\n" +
                "11-40(ч): ДиффУры (лаб)\n" +
                "13-30(н):\n" +
                "13-30(ч): МатАн (лек)\n" +
                "15-20(н): МатАн (лек)\n" +
                "15-20(ч): МатАн (лек)\n" +
                "17-00(н): C++ (лаб)\n" +
                "17-00(ч): C++ (лаб)");

        SCHEDULE.put("Fri", "8-20(н):  ИнЯз (пр)\n" +
                "8-20(ч):  ИнЯз (пр)\n" +
                "10-00(н): АиСД (лек)\n" +
                "10-00(ч): АиСД (лек)\n" +
                "11-40(н): БЖД (пр)\n" +
                "11-40(ч):\n" +
                "13-30(н): БЖД (пр)\n");

    }
}
