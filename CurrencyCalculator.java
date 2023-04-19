import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

import java.util.Scanner;

public class CurrencyCalculator {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Source Currency code:");
            String srcCurrName = sc.nextLine();
            System.out.println("Enter Recieve Currency code:");
            String recCurrName = sc.nextLine();
            
            // Make a request to the API endpoint
            String urlString = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=fcb27c43f62c4362a13698c987a08d14&symbols="+srcCurrName+","+recCurrName;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Read the response into a String
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response JSON and extract the exchange rates
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject rates = jsonResponse.getJSONObject("rates");
            double srcRate = rates.getDouble(srcCurrName);
            double recRate = rates.getDouble(recCurrName);

            // Calculate the exchange rate
            
            System.out.println("Enter the source currency quantity to exchange:");
            Double val = Double.parseDouble(sc.nextLine());
            
            double srcAmount = val;  // The amount to exchange
            double recAmount = srcAmount * recRate / srcRate;
            System.out.println("Exchange rate today is : " + recRate / srcRate);  // Print the exchange rate
            System.out.println("Conversion of " + srcCurrName + " " + srcAmount + " = " + recCurrName + " "  + recAmount);  // Print the exchanged amount
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
