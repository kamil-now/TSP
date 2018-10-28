import java.util.*;

public class Population {
    private ArrayList<Element> elements;

    public ArrayList<Element> getElements() {
        return elements;
    }

    public int size() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    public void add(Element n) {
        elements.add(n);
    }

    public void remove(Element n) {
        elements.remove(n);
    }

    public void distinct(int minimum) throws Exception {
        List<Element> all = new ArrayList<>();
        for (Element element : elements) {
            all.add(element.copy());
        }
        elements.clear();
        for (Element element : all) {
            if (!contains(element))
                elements.add(element);
        }
        if (size() < minimum) {
            Element best = all.get(0);
            for (Element element : all) {
                if (best.getFitness() < element.getFitness()) {
                    best = element;
                }
            }
            while (size() < minimum) {
                elements.add(best);
            }
        }

    }

    public void shuffle() {
        Collections.shuffle(elements);
    }

    public boolean contains(Element x) throws Exception {
        for (Element element : elements) {
            if (element.isEqual(x))
                return true;
        }
        return false;
    }

    public Element getBest() {

        Element best = elements.get(0);
        for (Element element : elements) {
            if (best.getFitness() < element.getFitness()) {
                best = element;
            }
        }

        return best;
    }

    public int getBestFitness(int[][] fitnessInfo) {
        int best = elements.get(0).getFitness();

        for (Element element : elements) {
            int tmp = element.getFitness();
            if (best < tmp) {
                best = tmp;
            }
        }

        return best;
    }

    public Population(int[][] fitnessInfo, int size, boolean init) {

        elements = new ArrayList<>();
        if (init) {
            initialize(size, fitnessInfo[0].length);
           calculateFitness(fitnessInfo);
        }

    }

    private void initialize(int populationSize, int elementSize) {

        for (int i = 0; i < populationSize; i++) {
            elements.add(Element.getRandom(elementSize));
        }
    }

    public void calculateFitness(int[][] info) {
        int max = getMax(info);
        int sum = getSum(info);
        for (Element element : elements) {
            int value = element.getValue(info);
            int fitness = (int) (((double) (max - value) / (max)) * 100)+1;
            element.setFitness(fitness);
        }
    }

    int getMax(int[][] fitnessInfo) {
        int max = 0;
        for (Element element : elements) {
            max = Math.max(element.getValue(fitnessInfo), max);
        }
        return max;
    }

    int getSum(int[][] info) {
        int sum = 0;
        for (Element element : elements) {
            sum += element.getValue(info);
        }
        return sum;
    }

    public boolean isBetter(Population population, int[][] fitnessInfo) {
        if (this.getBestFitness(fitnessInfo) < population.getBestFitness(fitnessInfo)) {
            return true;
        }
        return false;
    }

    public void displayElements(int[][] fitnessInfo) {
        for (Element n : elements) {
            System.out.printf(n.getFitness() + "  ");
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

