import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom; //Для рандома

public class Game1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in); //Нужно прописать

        //Выводим сообщение о количестве и названии доступных классов
        String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS - нужно для newLine (начать текст с новой строки)
        System.out.println("Классы на выбор:" + newLine + "1. Воин" + newLine + "2. Лучник" + newLine + "3. Маг" + newLine + "Введите соответствующую цифру:");
        int player_class = in.nextInt(); //Это переменная, в которой содержится номер класса

        //Базовые характеристики, которые зависят от класса
        int max_HP = 0;
        int HP = 0; //Текущее HP
        int max_AP = 0;
        int AP = 0; //Текущее AP
        //Базовые характеристики, которые НЕ зависят от класса
        int Gold = 100;
        int Food = 5;
        int attack = 10;
        int action_attack = 25;

        //Выбираем класс
        switch (player_class) {
            case 1 -> { //Выбрали Воина
                max_HP = 100;
                max_AP = 25;
                HP = max_HP;
                AP = max_AP;
                System.out.println("Вы выбрали сурового Воина. Ваше максимальное здоровье (HP) = " + HP + " , максимальное число очков действия (AP) = " + AP);
            }
            case 2 -> { //Выбрали Лучника
                max_HP = 50;
                max_AP = 50;
                HP = max_HP;
                AP = max_AP;
                System.out.println("Вы выбрали ловкого Лучника. Ваше максимальное здоровье (HP) = " + HP + " , максимальное число очков действия (AP) = " + AP);
            }
            case 3 -> { //Выбрали Мага
                max_HP = 25;
                max_AP = 100;
                HP = max_HP;
                AP = max_AP;
                System.out.println("Вы выбрали мудрого Мага. Ваше максимальное здоровье (HP) = " + HP + " , максимальное число очков действия (AP) = " + AP);
            }
        }

        //Вступление
        System.out.println("Ну короче Вы - славный приключенец, который решил отправиться на поиски приключений и славы в неизведанных землях."
                + newLine + "Там Вас ждут чудовища и сокровища (именно в таком порядке).");

        //Игра
        int capital = 0; //Эта переменная отвечает за то, находимся ли мы в столице. Когда она равна 1, мы в столице.
        while (true) {
            //Восстановили здоровье и очки действия
            HP = max_HP;
            AP = max_AP;
            System.out.println("Вы находитесь в столице. HP и AP восстановлены. У Вас " + Gold + " золотых и " + Food + " еды. Дальнейшие действия? Введите соответствующую цифру:"
                    + newLine + "1. Купить 5 мешков еды за 50 золотых"
                    + newLine + "2. Получить прибавку к максимальному здоровью (+5 HP) за 50 золотых"
                    + newLine + "3. Получить прибавку к максимальным очкам действия (+5 AP) за 50 золотых"
                    + newLine + "4. Отправиться в неизведанные земли"
                    + newLine + "5. Закончить игру (сохранений пока нет, так что вот)");

            int location = in.nextInt(); //Чем мы сейчас заняты (или место, где мы находимся)

            switch (location) {
                case 1 -> { //Мы покупаем еду
                    System.out.println("Вы решили купить еды. Приятного аппетита!");
                    if (Gold - 50 >= 0) {
                        Gold = Gold - 50;
                        Food = Food + 5;
                    } else System.out.println("У Вас недостаточно денег");

                    capital = 1;
                }

                case 2 -> { //Мы качаем HP
                    System.out.println("Вы решили увеличить максимальное HP");
                    if (Gold - 50 >= 0) {
                        Gold = Gold - 50;
                        max_HP = max_HP + 5;
                    } else System.out.println("У Вас недостаточно денег");

                    capital = 1;
                }

                case 3 -> { //Мы качаем AP
                    System.out.println("Вы решили увеличить максимальное число очков действия");
                    if (Gold - 50 >= 0) {
                        Gold = Gold - 50;
                        max_AP = max_AP + 5;
                    } else System.out.println("У Вас недостаточно денег");

                    capital = 1;
                }

                case 4 -> { //Пошли сражаться
                    System.out.println("Вы отправились в неизведанные земли. Удачи!");

                    int player_is_dead = 0; //Для проверки пульса

                    //ВЫБИРАЕМ ТИП ВСТРЕЧЕННОГО ВРАГА
                    int enemy_counter = 0; //Считает, сколько мы победили врагов, и когда на нас кидать дракона
                    //Введем случайный параметр, который будет определять тип встречи (мирная или враждебная)
                    int min_enemy = 1;
                    int max_enemy = 2;
                    int random_enemy = 0;
                    //if enemy_counter

                    //ХАРАКТЕРИСТИКИ ВРАГА
                    int enemy_HP = 0;
                    int enemy_AP = 0;
                    int enemy_max_AP = 0;
                    int enemy_attack = 0;
                    int enemy_attack_action = 0;
                    int enemy_reward_HP = 0; //Считай, максимальное ХП врага. Нужно для рассчета награды (если брать обычное ХП, то награда равна 0 - в конце боя ХП врага = 0).

                    //ГОБЛИН
                    int goblin_reward_HP = 40; //Для расчета награды
                    //int goblin_HP = goblin_reward_HP; //Стартовое значение
                    int goblin_attack = 5;
                    int goblin_attack_action = 0;
                    int goblin_AP = 0;
                    int goblin_max_AP = 0;

                    //ПРИЗРАК
                    int ghost_reward_HP = 20; //Для расчета награды
                    //int ghost_HP = ghost_reward_HP; //Стартовое значение
                    int ghost_attack = 15;
                    int ghost_attack_action = 0;
                    int ghost_AP = 0;
                    int ghost_max_AP = 0;

                    //ДРАКОН
                    int dragon_reward_HP = 100; //Для расчета награды
                    //int dragon_HP = dragon_reward_HP; //Стартовое значение
                    int dragon_attack = 20;
                    int dragon_attack_action = 50;
                    int dragon_AP = 0;
                    int dragon_max_AP = 25;

                    //НАЧАЛО ПУТЕШЕСТВИЯ
                    int travel = 1; //Переменная, чтобы ломать цикл путешествия

                    int dog_help = 0; //Если игроку помогает пёсик

                    while (true) { //ЦИКЛ ПУТЕШЕСТВИЯ


                        //Введем случайный параметр, который будет определять тип встречи (мирная или враждебная)
                        int min_rand = 1;
                        int max_rand = 100;
                        int randomNum = ThreadLocalRandom.current().nextInt(min_rand, max_rand + 1); //Случайный номер от 1 до 100 (включительно)

                        //СЛУЧАЙНАЯ ВСТРЕЧА
                        if (randomNum > 30) {
                            System.out.println("Враждебная встреча!");

                            if (enemy_counter == 8) {
                                random_enemy = 3;
                            } else {
                                random_enemy = ThreadLocalRandom.current().nextInt(min_enemy, max_enemy + 1); //Случайный номер от 1 до 3 (включительно)
                            }

                            //Хар-ки врагов были тут, работало плохо - враги не становились сильнее, но урон получали

                            //ВЫБИРАЕМ ТИП ВРАГА
                            switch (random_enemy) {
                                case 1 -> {
                                    enemy_reward_HP = goblin_reward_HP; //Для расчета награды
                                    enemy_HP = goblin_reward_HP; //Стартовое значение
                                    enemy_attack = goblin_attack;
                                    enemy_AP = goblin_AP;
                                    enemy_max_AP = goblin_max_AP;
                                    enemy_attack_action = goblin_attack_action;
                                    System.out.println("Путь Вам преграждает злой гоблин!");
                                }
                                case 2 -> {
                                    enemy_reward_HP = ghost_reward_HP; //Для расчета награды
                                    enemy_HP = ghost_reward_HP; //Стартовое значение
                                    enemy_attack = ghost_attack;
                                    enemy_AP = ghost_AP;
                                    enemy_max_AP = ghost_max_AP;
                                    enemy_attack_action = ghost_attack_action;
                                    System.out.println("Путь Вам преграждает злой призрак!");
                                }
                                case 3 -> {
                                    enemy_reward_HP = dragon_reward_HP; //Для расчета награды
                                    enemy_HP = dragon_reward_HP; //Стартовое значение
                                    enemy_attack = dragon_attack;
                                    enemy_AP = dragon_AP;
                                    enemy_max_AP = dragon_max_AP;
                                    enemy_attack_action = dragon_attack_action;
                                    System.out.println("Путь Вам преграждает злой дракон!");
                                }
                            } //switch random_enemy


                            //Сам бой
                            int combat = 1; //Чтобы ломать цикл боя
                            int turn = 1; //Переменная, определяющая, кто сейчас ходит
                            int retreat = 0; //Определяет, сбежал ли игрок

                            while (true) { //Цикл боя

                                //ЦИКЛ ХОДА ИГРОКА
                                while (true) { //Цикл хода игрока

                                    //Что делает игрок
                                    if (max_AP - AP >= 10) { //Идет восстановление AP
                                        AP = AP + 10;
                                    } else {
                                        AP = max_AP;
                                    }

                                    System.out.println("ИГРОК: HP = " + HP + ", в рюкзаке " + Food + " мешков еды"
                                            + newLine + "AP = " + AP + ", восстановление 10 AP за 1 ход"
                                            + newLine + "HP врага = " + enemy_HP + ", АТАКА врага = " + enemy_attack + ", AP врага = " + enemy_AP);

                                    System.out.println("Ваш ход!"
                                            + newLine + "1. Обычный удар (10 урона, не требует AP)"
                                            + newLine + "2. Применить способность (25 урона, 25 AP)"
                                            + newLine + "3. Съесть 1 мешок еды (+ 50 HP)"
                                            + newLine + "4. Сбежать с поля боя! (Потратить 1 мешок еды и продолжить приключение)");

                                    //Помощь от пёсика
                                    if (dog_help == 1) {
                                        System.out.println("Пёсик, которого Вы спасли из колючих зарослей, спешит Вам на помощь! Он кусает врага, и тот теряет 100 HP!");
                                        enemy_HP = enemy_HP - 100;
                                        dog_help = 0;
                                    }

                                    int player_action = in.nextInt();
                                    switch (player_action) {
                                        case 1 -> {
                                            System.out.println("Вы наносите удар! Враг теряет " + attack + " HP!");
                                            enemy_HP = enemy_HP - attack;
                                            turn = 2; //Ход переходит к врагу
                                        }
                                        case 2 -> {
                                            if (AP >= 25) {
                                                AP = AP - 25;
                                                System.out.println("Вы применяете навык! Враг теряет " + action_attack + " HP!");
                                                enemy_HP = enemy_HP - action_attack;
                                                turn = 2; //Ход переходит к врагу
                                            } else {
                                                System.out.println("Недостаточно AP!");
                                                AP = AP - 10; //Иначе AP регенятся после этого сообщения. Эта строка убирает реген (да, костыль. Отстань)
                                                turn = 1;
                                            }

                                        }
                                        case 3 -> {
                                            System.out.println("Вы решили перекусить!");
                                            if (Food > 0) {
                                                if (HP + 50 <= max_HP) {
                                                    HP = HP + 50;
                                                } else {
                                                    HP = max_HP;

                                                }
                                                Food = Food - 1;
                                                System.out.println("Вы съедаете целый мешок еды. Получите + 50 HP");
                                                turn = 2; //Ход переходит к врагу
                                            } else {
                                                System.out.println("У Вас больше не осталось еды!");
                                                turn = 1; //Ход остается у игрока
                                            }
                                        }
                                        case 4 -> {
                                            System.out.println("Вы решаете спасаться бегством!");
                                            if (Food > 0) {
                                                Food = Food - 1;
                                                retreat = 1;
                                                turn = 2; //Ход идет к врагу
                                                System.out.println("Вы успешно скрылись от врага!");
                                            } else {
                                                System.out.println("У Вас нет мешка еды, чтобы сбежать! Придется сражаться до конца!");
                                                turn = 1;
                                            }

                                        }

                                    } //switch player_action

                                    if (turn == 1) { //Ход игрока продолжается
                                        System.out.println("Выберите другое действие!");
                                        //вот тут можно поставить continue, но можно и не ставить

                                    } else { //Ход игрока закончен
                                        System.out.println("Ваш ход закончен!");
                                        break;
                                    }

                                } //Конец цикла хода игрока


                                //ХОД ВРАГА

                                //Игрок может сбежать

                                if (retreat == 1) {
                                    combat = 0;

                                    // Если игрок решил сражаться, то враг ходит

                                } else if (enemy_HP > 0) { //Если враг жив
                                    System.out.println("Ход врага!");

                                    //Особая атака
                                    if (enemy_max_AP - enemy_AP > 5) { //Идет восстановление AP
                                        enemy_AP = enemy_AP + 5;
                                    } else {
                                        enemy_AP = enemy_max_AP;
                                    }
                                    if (enemy_AP == enemy_max_AP && enemy_AP != 0) {
                                        System.out.println("Враг наносит ОСОБЫЙ удар! Вы теряете " + enemy_attack_action + " HP!");
                                        enemy_AP = 0;
                                        HP = HP - enemy_attack_action;
                                        turn = 1;
                                        combat = 1;
                                    } else {
                                        System.out.println("Враг наносит удар! Вы теряете " + enemy_attack + " HP!");
                                        HP = HP - enemy_attack;
                                        turn = 1;
                                        combat = 1;
                                    }

                                } else { //Враг погиб
                                    int reward = enemy_reward_HP / 2 + 10;
                                    Gold = Gold + reward;
                                    System.out.println("Вы победили! Вы получаете " + reward + " золотых!"
                                            + newLine + "Враги становятся сильнее!");
                                    //На данное путешествие враги становятся сильнее
                                    switch (random_enemy) {
                                        case 1 -> {
                                            goblin_reward_HP = goblin_reward_HP + 10;
                                            //goblin_HP = enemy_reward_HP; //Стартовое ХП
                                            goblin_attack = goblin_attack + 5;
                                        }
                                        case 2 -> {
                                            ghost_reward_HP = ghost_reward_HP + 10;
                                            //ghost_HP = enemy_reward_HP; //Стартовое ХП
                                            ghost_attack = ghost_attack + 5;
                                        }
                                    }
                                    enemy_counter = enemy_counter + 1; //Считает, сколько осталось до дракона

                                    combat = 0;

                                } //Конец хода врага

                                //Проверим, жив ли игрок
                                if (HP < 1) {
                                    System.out.println("Вы погибли!");
                                    combat = 0;
                                    player_is_dead = 1;
                                }
                                //Проверили

                                //КОНЕЦ ХОДА ИЛИ КОНЕЦ БОЯ

                                if (combat == 1) {
                                    System.out.println("Ход завершен!");
                                    //вот тут можно поставить continue, но можно и не ставить

                                } else { //Если combat = 0, бой завершен
                                    System.out.println("Бой завершен!");
                                    break;
                                }

                            } //КОНЕЦ ЦИКЛА БОЯ


                            //МИРНАЯ СЛУЧАЙНАЯ ВСТРЕЧА

                        } else {
                            System.out.println("Случайная встреча");
                            int min_random_encounter = 1;
                            int max_random_encounter = 5; //НЕ ЗАБУДЬ ПОМЕНЯТЬ, ЕСЛИ БУДЕШЬ ДЕЛАТЬ НОВЫЕ ВСТРЕЧИ
                            int random_encounter = ThreadLocalRandom.current().nextInt(min_random_encounter, max_random_encounter + 1); //Случайный номер от 1 до 3 (включительно)

                            switch (random_encounter) {

                                case 1 -> {
                                    System.out.println("Идя по тихому лесу, Вы замечаете оленя. Пойдете на охоту?"
                                            + newLine + "1. Да, попытаться убить оленя"
                                            + newLine + "2. Нет, отпустить оленя с миром");
                                    int choise_deer = in.nextInt();
                                    if (choise_deer == 1) {
                                        if (player_class == 2) {
                                            System.out.println("Вы подстрелили оленя метким выстрелом из лука. Получите 3 мешка еды!");
                                            Food = Food + 3;
                                        } else {
                                            System.out.println("Вы не смогли поймать оленя. Эх, если бы у Вас был лук...");
                                        }
                                    } else {
                                        System.out.println("Олень удаляется в лесную чащу, Вы продолжаете путь.");
                                    }
                                }

                                case 2 -> {
                                    System.out.println("Вы подходите к небольшому городку, которого нет на картах. Рядом с ним стоит шатер, из которого доносится музыка. Зайдете внуть?"
                                            + newLine + "1. Да, зайти в шатер"
                                            + newLine + "2. Нет, пройти мимо");
                                    int choise_cirque = in.nextInt();
                                    if (choise_cirque == 1) {
                                        switch (player_class) {
                                            case 1 -> {
                                                System.out.println("Вы заходите внутрь и попадаете на цирковое представление. Артисты просят Вас поучавствовать в представлении."
                                                        + newLine + "Вы соглашаетесь и поднимаете двух слонов на потеху публике. Получите 50 золотых за участие в шоу!");
                                                Gold = Gold + 50;
                                            }
                                            case 2 -> {
                                                System.out.println("Вы заходите внутрь и попадаете на цирковое представление. Артисты просят Вас поучавствовать в представлении."
                                                        + newLine + "Вы соглашаетесь и жонглируете горящими кинжалами. Получите 50 золотых за участие в шоу!");
                                                Gold = Gold + 50;
                                            }
                                            case 3 -> {
                                                System.out.println("Вы заходите внутрь и попадаете на цирковое представление. Артисты просят Вас поучавствовать в представлении."
                                                        + newLine + "Вы соглашаетесь и достаете кролика из шляпы. Получите кролика и 50 золотых за участие в шоу!");
                                                Gold = Gold + 50;
                                            }
                                        }
                                    } else {
                                        System.out.println("Вы проходите мимо красивого шатра. Ну и зря...");
                                    }

                                }

                                case 3 -> {
                                    System.out.println("Вам навстречу идет бродячий лекарь (да, такие бывают). Восполнить недостающие HP за 10 золотых?"
                                            + newLine + "1. Да, купить у лекаря подорожник"
                                            + newLine + "2. Нет, пройти мимо");
                                    int choise_healer = in.nextInt();
                                    if (choise_healer == 1) {
                                        if (Gold >= 10) {
                                            if (player_class == 2) {
                                                System.out.println("Ой, этому лекарю не нравятся лучники. Он отказвается Вас лечить. Придется идти дальше.");
                                            } else {
                                                System.out.println("Вы заплатили лекарю и он восстановил Ваше здоровье.");
                                                Gold = Gold - 10;
                                                HP = max_HP;
                                            }
                                        } else
                                            System.out.println("У Вас не хватате денег! Придется идти дальше");
                                    } else {
                                        System.out.println("Вы проходите мимо лекаря и продолжаете путь.");
                                    }
                                }

                               case 4 -> {
                                    System.out.println("Вам встречается торговец зеркалами. Он предлагает сыграть в кости за 10 золотых. Согласитесь?"
                                            + newLine + "1. Да, сыграем!"
                                            + newLine + "2. Нет, продолжить путь.");
                                    int lottery = in.nextInt();
                                    if (lottery == 1) {
                                        if (Gold >= 10) {
                                            Gold = Gold - 10;
                                            int min_lottery = 1;
                                            int max_lottery = 6;
                                            int lottery_result = ThreadLocalRandom.current().nextInt(min_lottery, max_lottery + 1); //Случайный номер от 1 до 6 (включительно)
                                            switch (lottery_result) {
                                                case 1 -> {
                                                    System.out.println("Удача улыбнулась! Но не Вам! (Выпала 1) Торговец зеркалами куда-то исчез, а из вашего кармана пропали 200 золотых! (Если столько у Вас не было, Вы влезли в долг)");
                                                    Gold = Gold - 200;
                                                }
                                                case 2 -> {
                                                    System.out.println("Эх, не повезло. (Выпала 2) Вы проиграли 100 монет! (Если столько у Вас не было, Вы влезли в долг)");
                                                    Gold = Gold - 50;
                                                }
                                                case 3 -> {
                                                    System.out.println("Вы вышли в ноль (Выпала 3), получите назад свои 10 золотых");
                                                    Gold = Gold + 10;
                                                }
                                                case 4 -> {
                                                    System.out.println("Вы выиграли 60 золотых! (Выпала 4)");
                                                    Gold = Gold + 60;
                                                }
                                                case 5 -> {
                                                    System.out.println("Вы выиграли 110 золотых! (Выпала 5)");
                                                    Gold = Gold + 110;
                                                }
                                                case 6 -> {
                                                    System.out.println("Вым неслыханно повезло! Получите 110 золотых и прибавку к силе обычной атаки (+5)! (Выпала 6)");
                                                    Gold = Gold + 110;
                                                    attack = attack + 5;
                                                }
                                            }
                                        } else {
                                            System.out.println("У Вас не хватает денег! Придется идти дальше...");
                                        }
                                    } else {
                                        System.out.println("Вы проходите мимо торговца зеркалами...");
                                    }
                                }

                                case 5 -> {
                                    System.out.println("Идя по лесу, Вы обнаруживаете пёсика, который застрял в колючих зарослях. Спасти пёсика? (Вы потеряете 10 HP)"
                                    +newLine + "1. Да, спасти пёсика!"
                                    +newLine + "2. Нет, пройти мимо (какой ужас, Вам не стыдно?)");
                                    int dog_choise = in.nextInt();
                                    //int dog_help = 0;
                                    switch (dog_choise) {
                                        case 1 -> {
                                            if (HP <= 10) {
                                                System.out.println("У Вас слишком мало HP! Придется пройти мимо...");
                                            } else {
                                                System.out.println("Вы успешно вытаскиваете пёсика, при этом сами немного царапаетесь. Потеряйте 10 HP");
                                                dog_help = 1;
                                                HP = HP - 10;
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Вы бросаете пёсика в беде. Отвратительно!");
                                        }
                                    }
                                }


                            } //switch (random_encounter)
                        } //Конец мирных случайных встреч, конец всех встреч впринципе


                        //ПУТЕШЕСТВИЕ ПОДХОДИТ К КОНЦУ
                        if (player_is_dead == 1) {
                            System.out.println("Ваше путешествие подошло к концу...");
                            break;

                        } else { //Если игрок жив, он выбирает дальнейшее действие
                            System.out.println("Выберите действие:"
                                    + newLine + "1. Продолжать путешествие"
                                    + newLine + "2. Вернуться в столицу");

                            travel = in.nextInt(); //Сам выбор действия игроком

                            if (travel == 1) {
                                System.out.println("Приключение продолжается!!");
                                //вот тут можно поставить continue, но можно и не ставить

                            } else { //Если travel = 0, приключение завершено
                                System.out.println("Приключение завершено!");
                                break;

                            }
                        }


                    } //КОНЕЦ ЦИКЛА ПУТЕШЕСТВИЯ

                    if (player_is_dead == 1) { //Еще раз, если игрок погиб
                        capital = 0; //Конец игры
                    } else {
                        capital = 1; //Продолжаем
                    }

                }

                case 5 -> { //Конец игры

                    capital = 0; //Конец игры
                }

            } //switch location

            //Вот эта штука позволяет нам возвращаться в столицу раз за разом ( при помощи while(true) )
            if (capital == 1) {
                System.out.println("Вы вернулись в столицу");
                //вот тут можно поставить continue, но можно и не ставить

            } else {
                System.out.println("До свидания!");
                break;
            }
            //Все, мы или вернулись в столицу, или вышли из игры

        } //while (true)


    }
}