package ics4u.a9;

/**
 * Assignment 9
 *
 * @author Yu
 */
public class MainTest {
    public static void main(String[] args) {
        // Test all the classes
        Pet pet = new Pet("Red", 33);
        System.out.println("Pet:");
        System.out.println(pet.eat());
        System.out.println(pet.sleep());
        System.out.println();

        Mammal mammal = new Mammal("White", "Long");
        System.out.println("Mammal:");
        System.out.println(mammal.eat());
        System.out.println(mammal.sleep());
        System.out.println(mammal.speak());
        System.out.println();

        Dog dog = new Dog("Beige", "Short", true, true);
        System.out.println("Cat:");
        System.out.println(dog.eat());
        System.out.println(dog.sleep());
        System.out.println(dog.speak());
        System.out.println(dog.bark());
        dog.playFetch();
        dog.playFetch();
        System.out.println();

        Cat cat = new Cat("Yellow", true, "Medium");
        System.out.println("Cat:");
        System.out.println(cat.eat());
        System.out.println(cat.sleep());
        System.out.println(cat.speak());
        System.out.println(cat.meow());
        System.out.println(cat.purr());
        System.out.println();

        Hamster hamster = new Hamster("Black", "Very Long");
        System.out.println("Hamster:");
        System.out.println(hamster.eat());
        System.out.println(hamster.sleep());
        System.out.println(hamster.speak());
        hamster.runInWheel();
        System.out.println();

        Bird bird = new Bird("Red");
        System.out.println("Bird:");
        System.out.println(bird.eat());
        System.out.println(bird.sleep());
        bird.fly();
        System.out.println();

        Snake snake = new Snake("Red");
        System.out.println("Snake:");
        System.out.println(snake.eat());
        System.out.println(snake.sleep());
        System.out.println(snake.hiss());
    }
}
