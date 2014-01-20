package prog01;

public class Main {
  public static void main (String[] args) {
    System.out.println("Set a breakpoint here and then single-step.");

    // I practiced single-stepping at home
    
    Computer c1 = new Computer("Dell", "Core 2", 2, 140, 2.40);
    Notebook n1 = new Notebook("Apple", "Core i7", 4, 500, 2.66, 15.0, 8.5);
    Computer c2 = n1; // o.k., a Notebook is a Computer
    //Notebook n2 = c2; // compiler error
    Notebook n3 = (Notebook) c2; // o.k., c2 references a Notebook
    //Notebook n4 = (Notebook) c1; // run time error

    System.out.println(c1 instanceof Notebook);
    System.out.println(c2 instanceof Notebook);
    System.out.println(n1 instanceof Computer);

    System.out.println(c1);
    System.out.println(n1);
    System.out.println(c2);

    System.out.println(n1.getWeight());
   // System.out.println(c2.getWeight());

    System.out.println(c2.computePower());
  }
}
    
    