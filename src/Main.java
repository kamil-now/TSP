import java.util.Random;

public class Main {
    static String _filePath = "berlin52.txt";
    static Random _rand = new Random();
    static int _populationSize = 10000;
    static int[][] _fitnessInfo;
    static int optimalValue = 10000;
    //10 -> 10/100 -> 10%
    static int _mutationFactor = 10;

    public static void main(String[] args) throws Exception {
        _fitnessInfo = FileReaderWriter.readFile(System.getProperty("user.dir") + "\\" + _filePath);

//        Element tmp = new Element(new int[]{3,4,1,6,7,0,9,8,2,5});// Element.getRandom(10);
//        Element tmp2 =new Element(new int[]{9,1,2,7,4,0,8,6,5,3});// Element.getRandom(10);
//        Element tmp3 = tmp.crossover(tmp2, 6,8);
//        System.out.println();
        Population population = new Population(_fitnessInfo, _populationSize, true);

        int currentBest = population.getBest().getValue(_fitnessInfo);
        System.out.println("BEST: fitness " + population.getBest().getFitness() + ", value " + currentBest);

        int populations = 1;
        System.out.print("\b\b" + populations);
        while (true) {

            Population selected = selection(population);
            selected.calculateFitness(_fitnessInfo);
            if (selected.size() == 0)
                break;
            crossover(selected);
            mutate(selected, _mutationFactor);
            selected.calculateFitness(_fitnessInfo);
            selected.shuffle();
            if (population.getBestFitness(_fitnessInfo) < selected.getBestFitness(_fitnessInfo)) {
                population.clear();
                for (int i = 0; i < _populationSize; i++) {
                    Element best = selected.getBest();
                    population.add(best);
                    selected.remove(best);
                }

                Element best = population.getBest();
                currentBest = best.getValue(_fitnessInfo);
                System.out.println("BEST: fitness " + best.getFitness() + ", value " + currentBest);
            }
            System.out.print("\r" + ++populations);
        }

    }

    static Population selection(Population population) {//, int selectionCount) {
        Population retval = new Population(_fitnessInfo, population.size(), false);
        for (Element element : population.getElements()) {
            int fitness = element.getFitness();
            for (int i = 0; i < fitness; i++) {
                Element copy = element.copy();
                retval.add(copy);
            }
        }
        retval.calculateFitness(_fitnessInfo);
        return retval;
    }

    static void crossover(Population population) throws Exception {
        int size = population.size() / 2;
        int elementSize = population.getElements().get(0).size();

        for (int i = 1; i < size; i += 2) {
            Element a = population.getElements().get(i - 1);
            Element b = population.getElements().get(i);
            if (a.isEqual(b)) {
                continue;
            } else {
                int cross1 = _rand.nextInt(elementSize - 1) + 1;
                int cross2 = _rand.nextInt(elementSize - 1) + 1;

                if (cross1 > cross2) {
                    int tmp = cross1;
                    cross1 = cross2;
                    cross2 = tmp;
                }
                a = a.crossover(b, cross1, cross2);
                b = b.crossover(a, cross1, cross2);
            }
        }
    }

    static void mutate(Population population, int _mutationFactor) throws Exception {
        for (Element element : population.getElements()) {
            if (_rand.nextInt(101) >= _mutationFactor) {
                element.mutate();
            }
        }
    }

}

