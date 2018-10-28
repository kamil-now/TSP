import java.util.ArrayList;

public class Population {
    ArrayList<Element> elements;

    public int size() {
        return elements.size();
    }

    public void add(Element n) {
        elements.add(n);
    }

    public Element getBest(int[][] fitnessInfo) {

        Element best = elements.get(0);

        for (Element element : elements) {
            if (best.getFitness(fitnessInfo) < element.getFitness(fitnessInfo)) {
                best = element;
            }
        }

        return best;
    }

    public Population(int[][] fitnessInfo, int size, boolean init) {

        elements = new ArrayList<>();
        if (init) {
            initialize(size, fitnessInfo[0].length);
        }

    }

    private void initialize(int populationSize, int elementSize) {

        for (int i = 0; i < populationSize; i++) {
            elements.add(Element.getRandom(elementSize));
        }
    }

    public boolean isBetter(Population population, int[][] fitnessInfo) {
        if (this.getBest(fitnessInfo).isBetter(population.getBest(fitnessInfo), fitnessInfo)) {
            return true;
        }
        return false;
    }

    public void displayElements(int[][] fitnessInfo) {
        for (Element n : elements) {
            System.out.printf(n.getFitness(fitnessInfo) + "  ");
            n.display();
            System.out.println();
        }
    }
    public void displayElements() {
        for (Element n : elements) {
            n.display();
            System.out.println();
        }
    }

}

