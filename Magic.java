
import java.util.*;

public class Magic {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] deck = {
            "A_C", "A_D", "A_H", "A_S", "2_C", "2_D", "2_H", "2_S", "3_C", "3_D", "3_H", "3_S", "4_C", "4_D", "4_H", "4_S", "5_C", "5_D", "5_H", "5_S", "6_C", "6_D", "6_H", "6_S", "7_C", "7_D", "7_H", "7_S", "8_C", "8_D", "8_H", "8_S", "9_C", "9_D", "9_H", "9_S", "10_C", "10_D", "10_H", "10_S", "J_C", "J_D", "J_H", "J_S", "Q_C", "Q_D", "Q_H", "Q_S", "K_C", "K_D", "K_H", "K_S",};

        ArrayList<String> cards = new ArrayList<>();
        ArrayList<Integer> cardIndices = new ArrayList<>();
        ArrayList<Integer> cardSuits = new ArrayList<>();
        ArrayList<Integer> cardNumbers = new ArrayList<>();
        int[] numSuits = {0, 0, 0, 0, 0};
        int pairSuit = -1;

        for (int i = 0; i < 52; i++) {
            if (i != 0 && i % 4 == 0) {
                System.out.println();
            }
            System.out.print(deck[i] + " ");
        }

        System.out.println();

        for (int i = 1; i <= 5; i++) {

            System.out.print("Give Card " + i + " in above format: ");
            String card = sc.next();
            cards.add(card);
            int n = findIndexInDeck(deck, card);

            int cardNumber = getCardNumber(card);
            int cardSuit = getCardSuit(card);

            cardIndices.add(n);
            cardSuits.add(cardSuit);
            cardNumbers.add(cardNumber);

            numSuits[cardSuit] += 1;
            if (numSuits[cardSuit] > 1) {
                pairSuit = n % 4;
            }

        }
        System.out.println(cardIndices);
        System.out.println(cardNumbers);
        System.out.println(cardSuits);
        System.out.println(cards);
        System.out.println(pairSuit);

        // Find two card out of 5 that have same suit:
        ArrayList<Integer> cardH = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (cardSuits.get(i) == pairSuit) {
                cardH.add(i);
            }
        }
        System.out.println(cardH);

        int[] hidden_other_encode = outputFirstCard(cardNumbers, cardH, cards);
        ArrayList<Integer> remainingIndices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i != hidden_other_encode[0] && i != hidden_other_encode[1]) {
                remainingIndices.add(cardIndices.get(i));
            }
        }
        // System.out.println(remainingIndices);
        Collections.sort(remainingIndices);
        System.out.println(remainingIndices);

        outputNext3Cards(hidden_other_encode[2], remainingIndices, deck);
    }

    static void outputNext3Cards(int code, ArrayList<Integer> index, String[] deck) {

        int second, third, fourth;

        switch (code) {
            case 1 -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
            case 2 -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
            case 3 -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
            case 4 -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
            case 5 -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
            default -> {
                second = index.get(0);
                third = index.get(1);
                fourth = index.get(2);
            }
        }

        System.out.println("Second card " + deck[second]);
        System.out.println("Third card " + deck[third]);
        System.out.println("Fourth card " + deck[fourth]);
    }

    static int[] outputFirstCard(ArrayList<Integer> numbers, ArrayList<Integer> oneTwo, ArrayList<String> cards) {

        int[] hidden_other_encode = new int[3];

        hidden_other_encode[2] = (numbers.get(oneTwo.get(0)) - numbers.get(oneTwo.get(1))) % 13;
        if (hidden_other_encode[2] > 0 && hidden_other_encode[2] <= 6) {
            hidden_other_encode[0] = oneTwo.get(0);
            hidden_other_encode[1] = oneTwo.get(1);
        } else {
            hidden_other_encode[0] = oneTwo.get(1);
            hidden_other_encode[1] = oneTwo.get(0);
            hidden_other_encode[2] = (numbers.get(oneTwo.get(1)) - numbers.get(oneTwo.get(0))) % 13;
        }

        System.out.println("Hidden card is " + cards.get(hidden_other_encode[0]) + " and need to encode " + hidden_other_encode[2]);
        System.out.println("First card is " + cards.get(hidden_other_encode[1]));

        return hidden_other_encode;
    }

    static int findIndexInDeck(String[] deck, String card) {

        int cardNumber = getCardNumber(card);
        int cardSuit = getCardSuit(card);

        return cardNumber * 4 + cardSuit;
    }

    static int getCardNumber(String card) {

        if (card.length() == 4) {
            return 9;
        }

        char firstCharOnCard = card.charAt(0);

        switch (firstCharOnCard) {
            case 'A' -> {
                return 0;
            }
            case 'J' -> {
                return 10;
            }
            case 'Q' -> {
                return 11;
            }
            case 'K' -> {
                return 12;
            }
            default -> {
            }
        }

        return (card.charAt(0) - '0') - 1;
    }

    // Clubs -> 0
    // Diamonds -> 1
    // Hearts -> 2
    // Spades -> 3
    static int getCardSuit(String card) {
        return switch (card.charAt(card.length() - 1)) {
            case 'C' ->
                0;
            case 'D' ->
                1;
            case 'H' ->
                2;
            default ->
                3;
        };
    }
}
