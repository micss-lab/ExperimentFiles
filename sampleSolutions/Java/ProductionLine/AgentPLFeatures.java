package JavaProductionLine;




import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgentPLFeatures {
static int productCounter=0;

    static    String redVal="none";
    static    String greenVal="none";
    static    String blueVal="none";

static List<SampleData> dataList = new ArrayList<>();

    // Static method for BoolColourSensor functionality
    public static void boolColourSensor() throws InterruptedException {


     //  Instant start = Instant.now();

        float red = dataList.get(productCounter).r;
        float green = dataList.get(productCounter).g;
        float blue = dataList.get(productCounter).b;

        productCounter++;



        System.out.println("RED: " + red + " Green: " + green + " Blue: " + blue);
        boolGroup(red, green, blue);
    }

    // Static method for parsing input data file
    public static void parseDataFile() {

        /*String inputFilePath = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\" +
                "Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\Randoms\\SampleInput1.txt";
*/
      //  String inputFilePath = "/home/robot/productionLineEmpirical/src/java/SampleInput1.txt";


        String inputFilePath = "C:\\Users\\Burak\\OneDrive - Universiteit Antwerpen\\Bureaublad\\Activities\\" +
                "Publications\\TSF\\Case Studies\\Production Line\\OutputFiles\\Randoms\\SampleInputEnhanced.txt";



        try {

            // Read all lines from the input file
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));

            for (String line : lines) {
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                // Split the line by spaces or tabs
                String[] parts = line.split("\\s+");

                // Parse each component
                int id = Integer.parseInt(parts[0].substring(2)); // Extract ID from "N=xxx"
                float r = Float.parseFloat(parts[1]);
                float g = Float.parseFloat(parts[2]);
                float b = Float.parseFloat(parts[3]);
                int number = Integer.parseInt(parts[4]);

                // Join the remaining parts for the color name
                String color = String.join(" ", Arrays.copyOfRange(parts, 5, parts.length));

                // Create a new SampleData object and add to the list
                dataList.add(new SampleData(id, r, g, b, number, color));
            }

            // Display the parsed data
            System.out.println("Parsed Data:");
            for (SampleData data : dataList) {
                System.out.println(data);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // Static method for boolean grouping of color thresholds
    public static void boolGroup(float red, float green, float blue) {



            // Red value assignment based on thresholds
            if (red <= 60.0) {
                redVal = "low";
            } else if (red > 60.0 && red <= 115.0) {
                redVal = "medium";
            } else {
                redVal = "high";
            }

            // Green value assignment based on thresholds
            if (green <= 20.0) {
                greenVal = "low";
            } else if (green > 20.0 && green <= 30.0) {
                greenVal = "medium";
            } else if (green > 30.0 && green <= 50.0) {
                greenVal = "high";
            } else if (green > 50.0 && green <= 80.0) {
                greenVal = "veryhigh";
            } else if (green > 80.0 && green <= 110.0) {
                greenVal = "ultralow";
            } else if (green > 110.0 && green <= 145.0) {
                greenVal = "ultramedium";
            } else {
                greenVal = "ultrahigh";
            }

            // Blue value assignment based on thresholds
            if (blue <= 10.5) {
                blueVal = "low";
            } else if (blue > 10.5 && blue <= 19.5) {
                blueVal = "medium";
            } else {
                blueVal = "high";
            }


                SortAgent.isRed=redVal;
                SortAgent.isGreen=greenVal;
                SortAgent.isBlue=blueVal;

    }




    // Example SampleData class (you can adjust as needed)
    public static class SampleData {
        int id;
        float r, g, b;
        int number;
        String color;

        public SampleData(int id, float r, float g, float b, int number, String color) {
            this.id = id;
            this.r = r;
            this.g = g;
            this.b = b;
            this.number = number;
            this.color = color;
        }

        @Override
        public String toString() {
            return "SampleData{id=" + id + ", r=" + r + ", g=" + g + ", b=" + b + ", number=" + number + ", color='" + color + "'}";
        }
    }


    }


