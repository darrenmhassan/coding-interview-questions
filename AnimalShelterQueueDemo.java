/**
 * Created by dhssn on 10/16/16.
 */
public class AnimalShelterQueueDemo {

    private abstract static class Animal {
        protected Animal nextAnimal;
    }

    private static class Dog extends Animal {
        public String toString() {
            return "woof woof";
        }
    }

    private static class Cat extends Animal {
        public String toString() {
            return "meow";
        }
    }

    private static class AnimalShelterQueue {

        private Animal first, last;

        public void enqueue(final Animal animal) {
            if (first == null) {
                first = animal;
                last = first;
            } else {
                last.nextAnimal = animal;
                last = animal;
            }
        }

        public Animal dequeue() {
            if (first == null) {
                throw new RuntimeException("animal shelter empty");
            }
            final Animal adopted = first;
            first = first.nextAnimal;
            return adopted;
        }


        public Dog dequeueDog() {
            if (first == null) {
                throw new RuntimeException("animal shelter empty");
            }
            if (first instanceof Dog) {
                return (Dog)dequeue();
            }
            Animal prev = first, next = first.nextAnimal;
            while (next != null && !(next instanceof Dog)) {
                next = next.nextAnimal;
                prev = prev.nextAnimal;
            }
            if (next == null) {
                throw new RuntimeException("no dogs at the animal shelter");
            }
            prev.nextAnimal = next.nextAnimal;
            return (Dog)next;
        }

        public Cat dequeueCat() {
            if (first == null) {
                throw new RuntimeException("animal shelter empty");
            }
            if (first instanceof Cat) {
                return (Cat)dequeue();
            }
            Animal prev = first, next = first.nextAnimal;
            while (next != null && !(next instanceof Cat)) {
                next = next.nextAnimal;
                prev = prev.nextAnimal;
            }
            if (next == null) {
                throw new RuntimeException("no cats at the animal shelter");
            }
            prev.nextAnimal = next.nextAnimal;
            return (Cat)next;
        }
    }

    public static void main(final String args[]) {

        final AnimalShelterQueue asq = new AnimalShelterQueue();
        asq.enqueue(new Cat());
        asq.enqueue(new Dog());
        asq.enqueue(new Dog());
        asq.enqueue(new Cat());
        asq.enqueue(new Dog());

        try {
            System.out.println(asq.dequeueDog());
            System.out.println(asq.dequeueCat());
            while (true) {
                System.out.println(asq.dequeue());
            }
        } catch (final RuntimeException e) {
            System.out.println(e);
        }
    }
}
