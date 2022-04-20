import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Stream;

public class TimeTests {

    public TimeTests(File mainFold) {
        this.mainFold = mainFold;
        this.mainFold.mkdir();

        operations = new HashSet<>();
        Stream.of("add", "remove", "element").forEach(a -> operations.add(a));
    }
    private static File mainFold;
    private HashSet<String> operations;

    private static final int countOfTests = 10;
    private static final int range = 1000;
    private static final int countOfDataSets = 10;
    private static final int dataSetSize = (int) 1e6;



    public static void main(String[] args) throws IOException, InterruptedException {


        TimeTests test = new TimeTests(new File("C:\\Users\\ockap\\Desktop\\TestsData"));
        test.createDataSets("add");
        test.createDataSets("remove");
        test.createDataSets("element");


        File results = new File(mainFold.toPath().resolve("Results").toString());
        results.mkdir();
        File addResults = new File(mainFold.toPath().resolve("Results\\add.txt").toString());
        test.addTimeTest(addResults);
        File removeResults = new File(mainFold.toPath().resolve("Results\\remove.txt").toString());
        test.removeTimeTest(removeResults);
        File elementResults = new File(mainFold.toPath().resolve("Results\\element.txt").toString());
        test.elementTimeTest(elementResults);


    }



    public void createDataSets(String operation) throws IOException {
        if (!operations.contains(operation)) {
            throw new RuntimeException("Wrong operation");
        }
        File foldForOperation = new File(mainFold.toPath().resolve(operation).toString());
        foldForOperation.mkdir();
        for (int i = 0; i < countOfDataSets; i++) {
            this.createDataSet(new File(foldForOperation.toPath().resolve("dataSet" + i + ".txt").toString()));
        }
    }

    private void createDataSet(File data) throws IOException {
        data.createNewFile();
        FileWriter out = new FileWriter(data);
        for (int i = 0; i < dataSetSize; i++) {
            out.write((int)(Math.random()*1000000000) + " ");
        }
        out.flush();
        out.close();
    }




    public void addTimeTest(File results) throws IOException {

        double[] times = new double[dataSetSize / range];
        for (int i = 0; i < countOfDataSets; i++) {

            Scanner data = new Scanner(new File(mainFold.toPath().resolve("add").resolve( "dataSet" + i + ".txt").toString()));

            int[] nums = new int[dataSetSize];
            for (int j = 0; j < dataSetSize; j++) {
                nums[j] = data.nextInt();
            }

            for (int j = 0; j < countOfTests; j++) {
                PriorityQueue qu = new PriorityQueue(dataSetSize + 5);
                qu.add(100);
                qu.add(100);

//                qu.poll();

                for (int k = 0; k < dataSetSize / range; k++) {
                    long t1 = System.currentTimeMillis();
                    for (int r = 0; r < range; r++) {
                        qu.add(nums[k * range + r]);
                    }
                    long t2 = System.currentTimeMillis();
                    times[k] += t2 - t1;
                }

            }

        }

        FileWriter out = new FileWriter(results);
        for (int j = 0; j < dataSetSize / range; j++) {
            times[j] = times[j] / (countOfDataSets * countOfTests);
        }
//        out.write("n (" + range + ") \ttime\n");
        for (int j = 0; j < dataSetSize / range; j++) {
            out.write(((j+1) + "\t " + times[j] + "\n").replace('.',','));
        }
        out.flush();
        out.close();
    }



    public void removeTimeTest(File results) throws IOException{
        double[] times = new double[dataSetSize / range];
        for (int i = 0; i < countOfDataSets; i++) {

            for (int j = 0; j < countOfTests; j++) {
                Scanner data = new Scanner(new File(mainFold.toPath().resolve("remove").resolve( "dataSet" + i + ".txt").toString()));
                PriorityQueue qu = new PriorityQueue(dataSetSize);
                for (int k = 0; k < dataSetSize; k++) {
                    qu.add(data.nextInt());
                }
                data.close();

                for (int k = dataSetSize / range - 1; k >= 0 ; k--) {
                    long t1 = System.currentTimeMillis();
                    for (int r = 0; r < range; r++) {
                        qu.remove();
                    }
                    long t2 = System.currentTimeMillis();
                    times[k] += t2 - t1;
                }

            }

        }

        FileWriter out = new FileWriter(results);
        for (int j = 0; j < dataSetSize / range; j++) {
            times[j] = times[j] / (countOfDataSets * countOfTests);
        }
//        out.write("n\ttime\n");
        for (int j = 0; j < dataSetSize / range; j++) {
            out.write(((j+1) + "\t " + times[j] + "\n").replace('.',','));
        }
        out.flush();
        out.close();
    }

    public void elementTimeTest(File results) throws IOException {

        double[] times = new double[dataSetSize / range];
        for (int i = 0; i < countOfDataSets; i++) {


            for (int j = 0; j < countOfTests; j++) {
                Scanner data = new Scanner(new File(mainFold.toPath().resolve("element").resolve( "dataSet" + i + ".txt").toString()));
                PriorityQueue qu = new PriorityQueue(dataSetSize);
                for (int k = 0; k < dataSetSize; k++) {
                    qu.add(data.nextInt());
                }
                data.close();

                for (int k = dataSetSize / range - 1; k >= 0 ; k--) {
                    long t1 = System.currentTimeMillis();
                    for (int r = 0; r < range; r++) {
                        qu.element();
                    }
                    long t2 = System.currentTimeMillis();
                    times[k] += t2 - t1;
                    qu.remove();
                }
            }
        }

        FileWriter out = new FileWriter(results);
        for (int j = 0; j < dataSetSize / range; j++) {
            times[j] = times[j] / (countOfDataSets * countOfTests);
        }
//        out.write("n\t\ttime\n");
        for (int j = 0; j < dataSetSize / range; j++) {
            out.write(((j+1) + "\t " + times[j] + "\n").replace('.',','));
        }
        out.flush();
        out.close();
    }
}
