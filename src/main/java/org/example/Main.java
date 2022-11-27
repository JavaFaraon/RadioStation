package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Song song0 = new Song(4, "Владимир Высоцкий", "Холода");
        Song song1 = new Song(5, "Борис Гребенщиков", "Под небом голубым");
        Interview interview0 = new Interview(10, "Jack Nicholson");
        Interview interview1 = new Interview(8, "Oliver Stone");
        Advertising advertising0 = new Advertising(0.5, "Colgate");
        Advertising advertising1 = new Advertising(0.3, "Pampers");
        Presenter presenter0 = new StaffPresenter("John Black", 6);
        Presenter presenter1 = new GuestPresenter("Nick Stone", "Very good Presenter, with beautiful voice and big experience.");


        List<Song> songArrayList = new ArrayList<>(); //лист песен
        songArrayList.add(song0);
        songArrayList.add(song1);
        for (Song song : songArrayList) {
            System.out.println(song);
        }

        List<Interview> interviewArrayList = new ArrayList<>(); //лист интервью
        interviewArrayList.add(interview0);
        interviewArrayList.add(interview1);
        for (Interview interview : interviewArrayList) {
            System.out.println(interview);
        }

        List<Advertising> advertisingArrayList = new ArrayList<>(); //лист рекламы
        advertisingArrayList.add(advertising0);
        advertisingArrayList.add(advertising1);
        for (Advertising advertising : advertisingArrayList) {
            System.out.println(advertising);
        }

        List<Presenter> presenterList = new ArrayList<>(); //лист ведущих
        presenterList.add(presenter0);
        presenterList.add(presenter1);
        for (Presenter presenter : presenterList) {
            System.out.println(presenter);
        }

        Broadcast broadcast0 = new Broadcast(LocalDate.of(2022, 11, 20), 30, presenter0 , new LinkedList<>());
        broadcast0.typeOfBroadcastsList.add(song0);
        broadcast0.typeOfBroadcastsList.add(interview0);
        broadcast0.typeOfBroadcastsList.add(advertising0);

        Broadcast broadcast1 = new Broadcast(LocalDate.of(2022, 11, 21), 24, presenter1 , new LinkedList<>());
        broadcast1.typeOfBroadcastsList.add(song1);
        broadcast1.typeOfBroadcastsList.add(interview1);
        broadcast1.typeOfBroadcastsList.add(advertising1);

        List<Broadcast> broadcastList = new ArrayList<>(); //лист трансляций
        broadcastList.add(broadcast0);
        broadcastList.add(broadcast1);
        for (Broadcast broadcast : broadcastList) {
            System.out.println(broadcast);
        }

        presenter0.addBrodCast(broadcast0);
        presenter1.addBrodCast(broadcast1);

        choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);



    }

    private static void choiceOfAction(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {

        System.out.println("""
                Укажите номер желаемого действия:
                 1. Создать трансляцию;
                 2. Создать песню;
                 3. Создать интервью;
                 4. Создать рекламу;
                 5. Создать штатного ведущего;
                 6. Создать приглашенного ведущего;
                 7. Просмотр сведений о трансляциях;
                 8. Отчет по доходам трансляций;
                 0. Выход""");


            switch (checkOfChoiceOfAction()) {
                case 1:
                    makeBroadcast(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);break;
                case 2:
                    makeSong(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 3:
                    makeInterview(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 4:
                    makeAdvertising(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 5:
                    makeStaffPresenter(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 6:
                    makeGuestPresenter(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 7:
                    viewOfBroadcast(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 8:
                    incomeReport(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList); break;
                case 0:
                    break;
                }
            }


    private static int checkOfChoiceOfAction() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int number = scan.nextInt();
            if (number < 9 & number >= 0){
                return number;
            }
            else {System.out.println("Введите цифры от 1 до 8 или ноль для выхода");
                return checkOfChoiceOfAction();}

        }
        else {System.out.println("Введите цифры от 1 до 8 или ноль для выхода");
            return checkOfChoiceOfAction();}
    }

    private static void makeBroadcast(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание трансляции");
        System.out.println("Введите дату трансляции:");
        LocalDate localDate = inputDate();
        System.out.println("Введите продолжительность трансляции:");
        double duration = inputDuration();
        System.out.println("Выберите ведущего трансляции:");
        PresentersInterface presenter = choiceOfPresenters(presenterList);
        System.out.println("Вы выбрали ведущего: " + presenter);
        System.out.println("Выберите элементы трансляции");

        LinkedList<TypeOfBroadcasts> typeOfBroadcastsList = new LinkedList<>();

        double remainingDuration = duration;
        double noPayingDuration = duration;
        do {
            TypeOfBroadcasts typeOfBroadcasts = choiceOfTypeOfBroadcasts(songArrayList, interviewArrayList, advertisingArrayList);
            int price = typeOfBroadcasts.getPrice();

            if(price == 0) {
                if (typeOfBroadcasts.getDuration() < remainingDuration) {
                    typeOfBroadcastsList.add(typeOfBroadcasts);
                    remainingDuration -= typeOfBroadcasts.getDuration();
                    System.out.println("Осталось заполнить " + remainingDuration + " минут.");
                } else {
                    System.out.println("Недостаточно времени для трансляции");
                    System.out.println("Желаете добавить ещё что-то? Y/N");
                    if (yesOrNo()) {

                    } else {
                        remainingDuration -= typeOfBroadcasts.getDuration();
                    }

                }
            }
            else {
                noPayingDuration -= typeOfBroadcasts.getDuration();
                long percentOfFree = Math.round(noPayingDuration / duration * 100);
                if(percentOfFree > 50) {
                    if (typeOfBroadcasts.getDuration() < remainingDuration) {
                        typeOfBroadcastsList.add(typeOfBroadcasts);
                        remainingDuration -= typeOfBroadcasts.getDuration();
                        System.out.println("Осталось заполнить " + remainingDuration + " минут.");
                    } else {
                        System.out.println("Недостаточно времени для трансляции");
                        System.out.println("Желаете добавить что-нибудь другое? Y/N");
                        if (yesOrNo()) {

                        } else {
                            remainingDuration = 0;
                        }
                    }
                }
                else {
                    System.out.println("Время бесплатного контента снизится до " + percentOfFree + "%. Это недопустимо!");
                    noPayingDuration += typeOfBroadcasts.getDuration();
                    System.out.println("Желаете добавить что-нибудь другое? Y/N");
                    if (yesOrNo()) {

                    } else {
                        remainingDuration = 0;
                    }
                }

            }
        } while (remainingDuration > 0);

        Broadcast broadcast = new Broadcast(localDate, duration, presenter, typeOfBroadcastsList);
        presenter.addBrodCast(broadcast);
        System.out.println("Добавлена " + broadcast);
        broadcastList.add(broadcast);
        System.out.println("Список трансляций:");
        for (Broadcast b : broadcastList) {
            System.out.println(b);
        }
        System.out.println("Трансляции " + presenter.getName() + " :");
        List<Broadcast> presenterBroadCastsList = presenter.getBroadCastsList();
        for (Broadcast b : presenterBroadCastsList) {
            System.out.println(b);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }

    private static boolean yesOrNo() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext("[y,Y]")) {
            return true;}
        else if (scan.hasNext("[n,N]")) {
            return false;

        } else {System.out.println("Выберите Y или N");
            return yesOrNo();}
    }


    private static void makeSong(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание песни");
        System.out.println("Введите название песни:");
        String nameOfSong = inputString();
        System.out.println("Введите исполнителя песни:");
        String nameOfSinger = inputString();
        System.out.println("Введите длительность песни:");
        double duration = inputDuration();
        Song song = new Song(duration, nameOfSinger,nameOfSong);
        songArrayList.add(song);
        System.out.println("Песня добавлена в список:");
        for (Object o : songArrayList) {
            System.out.println(o);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }

    private static String inputString() {
        Scanner scan = new Scanner(System.in);
        String s = null;
        if (scan.hasNextLine()) {
            s = scan.nextLine();
        } else {
            System.out.println("Введите название в виде строки");
        }
        return s;
    }

    private static void makeInterview(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание интервью");
        System.out.println("Введите имя гостя:");
        String nameOfInterviewee = inputString();
        System.out.println("Введите длительность интервью:");
        double duration = inputDuration();
        Interview interview = new Interview(duration, nameOfInterviewee);
        interviewArrayList.add(interview);
        System.out.println("Интервью добавлено в список:");
        for (Object o : interviewArrayList) {
            System.out.println(o);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }

    private static void makeAdvertising(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание рекламы");
        System.out.println("Введите название рекламируемого продукта:");
        String nameOfPromotionalProduct = inputString();
        System.out.println("Введите длительность рекламы:");
        double duration = inputDuration();
        Advertising advertising = new Advertising(duration, nameOfPromotionalProduct);
        advertisingArrayList.add(advertising);
        System.out.println("Реклама '" + nameOfPromotionalProduct + "' добавлена в список:");
        for (Object o : advertisingArrayList) {
            System.out.println(o);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }

    }

    private static void makeStaffPresenter(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание штатного ведущего");
        System.out.println("Введите имя ведущего:");
        String name = inputString();
        System.out.println("Введите стаж работы:");
        int workExperience = inputInt();
        Presenter presenter = new StaffPresenter(name, workExperience);
        presenterList.add(presenter);
        System.out.println("Ведущий " + presenter.getName() + " добавлен в список: " );
        for (Presenter p : presenterList) {
            System.out.println(p);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }

    private static void makeGuestPresenter(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Создание приглашенного ведущего");
        System.out.println("Введите имя ведущего:");
        String name = inputString();
        System.out.println("Введите текс резюме:");
        String resume = inputString();
        Presenter presenter = new GuestPresenter(name, resume);
        presenterList.add(presenter);
        System.out.println("Ведущий " + presenter.getName() + " добавлен в список: " );
        for (Presenter p : presenterList) {
            System.out.println(p);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }


    private static void viewOfBroadcast(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Выберите трансляцию для просмотра сведений:");
        int i = 0;
        for (Broadcast broadcast : broadcastList) {
            System.out.print(i + ". ");
            System.out.println(broadcast);
            i+=1;        }
        Broadcast b = broadcastList.get(inputChoice(broadcastList));
        System.out.println("Трансляция на " + b.getAirDate() + " длится " + b.getDurationOfBroadcast() + " мин, ведущий - " + b.getPresenter());
        System.out.println("Состав трансляции:");
        for (TypeOfBroadcasts typeOfBroadcasts : b.typeOfBroadcastsList) {
            System.out.println(typeOfBroadcasts);
        }
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }
    }

    private static void incomeReport(
            List<Broadcast> broadcastList,
            List<Presenter> presenterList,
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {
        System.out.println("Выберите трансляцию для рассчёта доходов:");
        int i = 0;
        for (Broadcast broadcast : broadcastList) {
            System.out.print(i + ". ");
            System.out.println(broadcast);
            i+=1;        }
        Broadcast b = broadcastList.get(inputChoice(broadcastList));
        double sum = 0;
        for (TypeOfBroadcasts typeOfBroadcasts : b.typeOfBroadcastsList) {
            double profit = typeOfBroadcasts.getPrice()*typeOfBroadcasts.getDuration();
            System.out.println("Доход от " + typeOfBroadcasts + " составляет " + profit + " " + TypeOfBroadcasts.CURRENCY);
            sum += profit;
        }
        System.out.println("Итого: " + sum + " " + TypeOfBroadcasts.getCURRENCY());
        System.out.println("\nЖелаете выполнить ещё что-то? Y/N");

        if(yesOrNo()) {
            choiceOfAction(broadcastList, presenterList, songArrayList, interviewArrayList, advertisingArrayList);
        }

    }

    public static LocalDate inputDate() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите год: ");
        int year = scan.nextInt();
        System.out.print("Введите месяц: ");
        int month = scan.nextInt();
        System.out.print("Введите дату: ");
        int date = scan.nextInt();
        return LocalDate.of(year, month, date);
    }
    private static double inputDuration() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextDouble()) {
            return scan.nextDouble();
        }
        else {System.out.println("Введите число с дробной запятой:");
            return inputDuration();}
    }

    private static int inputInt() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            return scan.nextInt();
        }
        else {System.out.println("Введите целое число:");
            return inputInt();}
    }
    private static int inputPresenter(List<Presenter> presenterList) {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int i = scan.nextInt();
            if(i<presenterList.size()){
                return i;}
            else {System.out.println("Введите цифры от 0 до " + (presenterList.size()-1));
                return inputPresenter(presenterList);}
        }
        else {System.out.println("Введите цифры от 0 до " + (presenterList.size()-1));
            return inputPresenter(presenterList);}
    }
    private static PresentersInterface choiceOfPresenters(List<Presenter> presenterList) {
        int i = 0;
        for (Object presenter : presenterList) {
            System.out.print(i + ". ");
            System.out.println(presenter);
            i+=1;
        }
        System.out.print("Наберите номер ведущего из списка: ");
        System.out.println();
        return presenterList.get(inputPresenter(presenterList));
    }

    private static TypeOfBroadcasts choiceOfTypeOfBroadcasts(
            List<Song> songArrayList,
            List<Interview> interviewArrayList,
            List<Advertising> advertisingArrayList
    ) {

        System.out.println("""
                Что вы хотите добавить?
                 1. Выбрать песню;
                 2. Выбрать интервью;
                 3. Выбрать рекламу;
                """);

        return switch (checkOfChoiceOfType()) {
            case 1 -> (TypeOfBroadcasts) choiceOfSong(songArrayList);
            case 2 -> (TypeOfBroadcasts) choiceOfInterview(interviewArrayList);
            case 3 -> (TypeOfBroadcasts) choiceOfAdvertising(advertisingArrayList);
            default -> null;
        };
    }

    private static Object choiceOfAdvertising(List<Advertising> advertisingArrayList) {
        int i = 0;
        for (Object advertising : advertisingArrayList) {
            System.out.print(i + ". ");
            System.out.println(advertising);
            i+=1;
        }
        System.out.println("Выберите рекламу из списка:");
        System.out.println();
        return advertisingArrayList.get(inputChoice(advertisingArrayList));
    }

    private static Object choiceOfInterview(List<Interview> interviewArrayList) {
        int i = 0;
        for (Object interview : interviewArrayList) {
            System.out.print(i + ". ");
            System.out.println(interview);
            i+=1;
        }
        System.out.println("Выберите интервью из списка:");
        System.out.println();
        return interviewArrayList.get(inputChoice(interviewArrayList));
    }

    private static Object choiceOfSong(List<Song> songArrayList) {
        int i = 0;
        for (Object song : songArrayList) {
            System.out.print(i + ". ");
            System.out.println(song);
            i+=1;
        }
        System.out.println("Выберите песню из списка:");
        System.out.println();
        return songArrayList.get(inputChoice(songArrayList));
    }

    private static int checkOfChoiceOfType() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int number = scan.nextInt();
            if (number < 4 & number > 0){
                return number;
            }
            else {System.out.println("Введите цифры от 1 до 3");
                return checkOfChoiceOfType();}

        }
        else {System.out.println("Введите цифры от 1 до 3");
            return checkOfChoiceOfType();}
    }

    private static int inputChoice(List arrayList) {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int i = scan.nextInt();
            if(i<arrayList.size()){
                return i;}
            else {System.out.println("Введите цифры от 0 до " + (arrayList.size()-1));
                return inputChoice(arrayList);}
        }
        else {System.out.println("Введите цифры от 0 до " + (arrayList.size()-1));
            return inputChoice(arrayList);}
    }
}
